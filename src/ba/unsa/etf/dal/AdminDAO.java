package ba.unsa.etf.dal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminDAO {

    private Connection con = null;
    private Admin currentAdmin;
    private static AdminDAO instance = null;
    private PreparedStatement getNewId, getAllAdminsStatement, getPasswordStatement;

    private AdminDAO() {
        try {
            con = DBConnection.createConnection();
            getNewId = con.prepareStatement("SELECT MAX(id)+1 FROM users");
            getAllAdminsStatement = con.prepareStatement("SELECT * FROM admins");
            getPasswordStatement = con.prepareStatement("SELECT password FROM admins WHERE username=?");

        } catch (SQLException e) {
            regenerateDatabase();
            try {
                getNewId = con.prepareStatement("SELECT MAX(id)+1 FROM users");
                getAllAdminsStatement = con.prepareStatement("SELECT * FROM admins");
                getPasswordStatement = con.prepareStatement("SELECT password FROM admins WHERE username=?");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static void initialize() {
        instance = new AdminDAO();
    }

    public static AdminDAO getInstance() {
        if (instance == null) initialize();
        return instance;
    }

    public ArrayList<Admin> getAllAdmins() {
        ArrayList<Admin> list = new ArrayList<>();

        try {
            ResultSet set = getAllAdminsStatement.executeQuery();
            while (set.next()) {
                list.add(new Admin(set.getInt(1), set.getString(2), set.getString(3)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean usernameExists(String n) {
        if (getAllAdmins().stream().anyMatch(a -> a.getUsername().equals(n))) return true;
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

    public void setCurrentAdmin(Admin current) {
        currentAdmin = current;
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
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
        stmt.executeUpdate("DELETE FROM admins");
        regenerateDatabase();
    }

}
