package ba.unsa.etf.dal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDAO {

    private static Connection con = null;
    private static UserDAO instance = null;
    private User currentUser;
    private int currentId;
    private PreparedStatement getNewId, updateDateStatement, getAllUsersStatement, insertRegUserStatement, getPasswordStatement, deleteUserStatement,
                              updateHomeStatement, updateYardStatement, updatePetsStatement, getCurrentIdStatement, updateWorkStatement, updateRoomatesStatement,
                              updateApprovedStatement, getProfileStatement, updateProfileStatement, getEmailStatement, getApprovedStatement;

    private UserDAO() {

        try {
            con = DBConnection.createConnection();
            getNewId = con.prepareStatement("SELECT MAX(id)+1 FROM users");
            getAllUsersStatement = con.prepareStatement("SELECT * FROM users");
            getPasswordStatement = con.prepareStatement("SELECT password FROM users WHERE username=?");
            getEmailStatement = con.prepareStatement("SELECT email FROM users WHERE username=?");
            getCurrentIdStatement = con.prepareStatement("SELECT id from users WHERE username=?");
            getProfileStatement = con.prepareStatement("SELECT profile from users WHERE username=?");
            getApprovedStatement = con.prepareStatement("SELECT approved from users WHERE username=?");
            updateDateStatement = con.prepareStatement("UPDATE users SET appointment=? WHERE id=?");
            updateHomeStatement = con.prepareStatement("UPDATE users SET homework=? WHERE id=?");
            updateYardStatement = con.prepareStatement("UPDATE users SET yard=? WHERE id=?");
            updatePetsStatement = con.prepareStatement("UPDATE users SET pets=? WHERE id=?");
            updateWorkStatement = con.prepareStatement("UPDATE users SET workhours=? WHERE id=?");
            updateRoomatesStatement = con.prepareStatement("UPDATE users SET roomates=? WHERE id=?");
            updateApprovedStatement = con.prepareStatement("UPDATE users SET approved=1 WHERE id=?");
            updateProfileStatement = con.prepareStatement("UPDATE users SET profile=1 WHERE id=?");
            insertRegUserStatement = con.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            deleteUserStatement = con.prepareStatement("DELETE FROM users WHERE username = ?");

        } catch (SQLException e) {
            regenerateDatabase();
            try {
                getNewId = con.prepareStatement("SELECT MAX(id)+1 FROM users");
                getAllUsersStatement = con.prepareStatement("SELECT * FROM users");
                getPasswordStatement = con.prepareStatement("SELECT password FROM users WHERE username=?");
                getEmailStatement = con.prepareStatement("SELECT email FROM users WHERE username=?");
                getCurrentIdStatement = con.prepareStatement("SELECT id from users WHERE username=?");
                getProfileStatement = con.prepareStatement("SELECT profile from users WHERE username=?");
                getApprovedStatement = con.prepareStatement("SELECT approved from users WHERE username=?");
                updateDateStatement = con.prepareStatement("UPDATE users SET appointment=? WHERE id=?");
                updateHomeStatement = con.prepareStatement("UPDATE users SET homework=? WHERE id=?");
                updateYardStatement = con.prepareStatement("UPDATE users SET yard=? WHERE id=?");
                updatePetsStatement = con.prepareStatement("UPDATE users SET pets=? WHERE id=?");
                updateWorkStatement = con.prepareStatement("UPDATE users SET workhours=? WHERE id=?");
                updateRoomatesStatement = con.prepareStatement("UPDATE users SET roomates=? WHERE id=?");
                updateApprovedStatement = con.prepareStatement("UPDATE users SET approved=1 WHERE id=?");
                updateProfileStatement = con.prepareStatement("UPDATE users SET profile=1 WHERE id=?");
                insertRegUserStatement = con.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                deleteUserStatement = con.prepareStatement("DELETE FROM users WHERE username = ?");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static void initialize() {
        instance = new UserDAO();
    }

    public static UserDAO getInstance() {
        if (instance == null) initialize();
        return instance;
    }

    public static Connection getConnection() {
        return con;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();

        try {
            ResultSet set = getAllUsersStatement.executeQuery();
            while (set.next()) {
                    list.add(new User(set.getInt(1), set.getString(2), set.getString(3),
                            set.getString(4), set.getString(5), set.getDouble(6),
                            set.getInt(7), set.getInt(8), set.getInt(9), set.getInt(10), set.getString(11), set.getInt(12), set.getInt(13)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void setDate(String pickedDate) {
        try {
            updateDateStatement.setString(1, pickedDate);
            updateDateStatement.setInt(2, currentUser.getId());
            updateDateStatement.executeUpdate();
            currentUser.setAppointment(pickedDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setApproved(User user) {
        try {
            updateApprovedStatement.setInt(1, user.getId());
            updateApprovedStatement.executeUpdate();
            currentUser.setApproved(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setProfileSet(User user) {
        try {
            updateProfileStatement.setInt(1, user.getId());
            updateProfileStatement.executeUpdate();
            currentUser.setProfileSet(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setHome(int home) {
        try {
            updateHomeStatement.setInt(1, home);
            updateHomeStatement.setInt(2, currentUser.getId());
            updateHomeStatement.executeUpdate();
            currentUser.setHomework(home);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPets(int pets) {
        try {
            updatePetsStatement.setInt(1, pets);
            updatePetsStatement.setInt(2, currentUser.getId());
            updatePetsStatement.executeUpdate();
            currentUser.setPets(pets);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setYard(int yard) {
        try {
            updateYardStatement.setInt(1, yard);
            updateYardStatement.setInt(2, currentUser.getId());
            updateYardStatement.executeUpdate();
            currentUser.setYard(yard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setWork(double work) {
        try {
            updateWorkStatement.setDouble(1, work);
            updateWorkStatement.setInt(2, currentUser.getId());
            updateWorkStatement.executeUpdate();
            currentUser.setWorkhours(work);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setRoomates(int roomates) {
        try {
            updateRoomatesStatement.setInt(1, roomates);
            updateRoomatesStatement.setInt(2, currentUser.getId());
            updateRoomatesStatement.executeUpdate();
            currentUser.setRoomates(roomates);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean usernameExists(String n) {
        if (getAllUsers().stream().anyMatch(u -> u.getUsername().equals(n))) return true;
        else return false;
    }

    public String getUserPassword(String username) {
        String foundPassword = null;
        try {
            getPasswordStatement.setString(1,username);
            ResultSet set = getPasswordStatement.executeQuery();
            while (set.next()) {
                foundPassword = set.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundPassword;
    }

    public String getEmail(String user) {
        String email = null;
        try {
            getEmailStatement.setString(1,user);
            ResultSet set = getEmailStatement.executeQuery();
            while (set.next()) {
                email = set.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    public void registerUser(User userBean) {
        try {
            ResultSet result = getNewId.executeQuery();
            result.next();

            insertRegUserStatement.setInt(1, result.getInt(1) );
            insertRegUserStatement.setString(2, userBean.getName());
            insertRegUserStatement.setString(3, userBean.getUsername());
            insertRegUserStatement.setString(4, userBean.getEmail());
            insertRegUserStatement.setString(5, userBean.getPassword());
            insertRegUserStatement.setNull(6, Types.DOUBLE);
            insertRegUserStatement.setNull(7, Types.INTEGER);
            insertRegUserStatement.setNull(8, Types.INTEGER);
            insertRegUserStatement.setNull(9, Types.INTEGER);
            insertRegUserStatement.setNull(10, Types.INTEGER);
            insertRegUserStatement.setNull(11, Types.CHAR);
            insertRegUserStatement.setNull(12, Types.INTEGER);
            insertRegUserStatement.setNull(13, Types.INTEGER);

            insertRegUserStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        try {
            deleteUserStatement.setString(1, username);
            deleteUserStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentUser(User current) {
        try {
            if (current!=null) {
                getCurrentIdStatement.setString(1, current.getUsername());
                ResultSet set = getCurrentIdStatement.executeQuery();
                while (set.next()) {
                    currentId = set.getInt(1);
                }
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
        if (current!=null) current.setId(currentId);
        currentUser = current;
    }

    public int getProfileSet(String username) {
        int profile = 0;
        try {
            getProfileStatement.setString(1, username);
            ResultSet set = getProfileStatement.executeQuery();
            while (set.next()) {
                profile = set.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }

    public int getApproved(String username) {
        int approved = 0;
        try {
            getApprovedStatement.setString(1, username);
            ResultSet set = getApprovedStatement.executeQuery();
            while (set.next()) {
                approved = set.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return approved;
    }


    public User getCurrentUser() {
        return currentUser;
    }


    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance = null;
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerateDatabase() {
        Scanner enter = null;
        try {
            enter = new Scanner(new FileInputStream("petshelterdb.db.sql"));
            String sqlQuery = "";
            while (enter.hasNext()) {
                sqlQuery += enter.nextLine();
                if ( sqlQuery.length() > 1 && sqlQuery.charAt( sqlQuery.length()-1 ) == ';') {
                    try {
                        Statement stmt = con.createStatement();
                        stmt.execute(sqlQuery);
                        sqlQuery = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            enter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // For tests, gets database to default state
    public void defaultDatabase() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM likedpets");
        stmt.executeUpdate("DELETE FROM users");

        regenerateDatabase();
    }

}
