package ba.unsa.etf.dal;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CatDAO extends Pet{

    private Connection con = null;
    private PreparedStatement getCatsStatement,getLikedCatsStatement,insertLikedCatStatement, insertCatStatement, getNewIdLiked, getNewId,
                              deleteLikedCatStatement, deleteCatStatement, updateAdoptionStatement, getUnadoptedCatsStatement, updateUrgentStatement;
    private static CatDAO instance = null;
    private static UserDAO user;

    private CatDAO() {

        try {
            con = UserDAO.getConnection();
            getCatsStatement =  con.prepareStatement("SELECT * FROM cats");
            getUnadoptedCatsStatement =  con.prepareStatement("SELECT * FROM cats WHERE adopted = 0");
            getLikedCatsStatement =  con.prepareStatement("SELECT catid FROM likedpets WHERE catid IS NOT NULL AND userid=?");
            getNewIdLiked = con.prepareStatement("SELECT MAX(id)+1 FROM likedpets");
            getNewId = con.prepareStatement("SELECT MAX(id)+1 FROM cats");
            insertLikedCatStatement = con.prepareStatement("INSERT INTO likedpets VALUES (?,?,?,?)");
            insertCatStatement = con.prepareStatement("INSERT INTO cats VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            deleteLikedCatStatement = con.prepareStatement("DELETE FROM likedpets WHERE catid = ?");
            deleteCatStatement = con.prepareStatement("DELETE FROM cats WHERE id = ?");
            updateAdoptionStatement = con.prepareStatement("UPDATE cats SET adopted = 1 WHERE id = ?");
            updateUrgentStatement = con.prepareStatement("UPDATE cats SET urgent = 1 WHERE id = ?");

        } catch (SQLException e) {
            regenerateDatabase();
            try {
                getCatsStatement =  con.prepareStatement("SELECT * FROM cats");
                getUnadoptedCatsStatement =  con.prepareStatement("SELECT * FROM cats WHERE adopted = 0");
                getLikedCatsStatement =  con.prepareStatement("SELECT catid FROM likedpets WHERE catid IS NOT NULL AND userid=?");
                getNewIdLiked = con.prepareStatement("SELECT MAX(id)+1 FROM likedpets");
                getNewId = con.prepareStatement("SELECT MAX(id)+1 FROM cats");
                insertLikedCatStatement = con.prepareStatement("INSERT INTO likedpets VALUES (?,?,?,?)");
                insertCatStatement = con.prepareStatement("INSERT INTO cats VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                deleteLikedCatStatement = con.prepareStatement("DELETE FROM likedpets WHERE catid = ?");
                deleteCatStatement = con.prepareStatement("DELETE FROM cats WHERE id = ?");
                updateAdoptionStatement = con.prepareStatement("UPDATE cats SET adopted = 1 WHERE id = ?");
                updateUrgentStatement = con.prepareStatement("UPDATE cats SET urgent = 1 WHERE id = ?");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public CatDAO(int id,String name, String sex, String age, String breed, String imgSrc, double dedicationHours, int peopleTolerance, int yardNeed, int petTolerance, int adopted, int urgent) {
        super(id,name,sex,age,breed,imgSrc,dedicationHours,peopleTolerance,yardNeed,peopleTolerance,adopted, urgent);
    }

    private static void initialize() {
        user = UserDAO.getInstance();
        instance = new CatDAO();

    }

    public static CatDAO getInstance() {
        if (instance == null) initialize();
        return instance;
    }


    public ArrayList<CatDAO> getAllCats() {
        ArrayList<CatDAO> list = new ArrayList<>();

        try {
            ResultSet set = getCatsStatement.executeQuery();
            while (set.next()) {
                list.add(new CatDAO(set.getInt(1), set.getString(2), set.getString(3),
                        set.getString(4), set.getString(5), set.getString(6),
                        set.getDouble(7), set.getInt(8), set.getInt(9), set.getInt(10), set.getInt(11), set.getInt(12)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<CatDAO> getAllUnadoptedCats() {
        ArrayList<CatDAO> list = new ArrayList<>();

        try {
            ResultSet set = getUnadoptedCatsStatement.executeQuery();
            while (set.next()) {
                if (set.getInt(11)!=1) {
                    list.add(new CatDAO(set.getInt(1), set.getString(2), set.getString(3),
                            set.getString(4), set.getString(5), set.getString(6),
                            set.getDouble(7), set.getInt(8), set.getInt(9), set.getInt(10), set.getInt(11), set.getInt(12)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<CatDAO> getLikedCats() {
        ArrayList<CatDAO> list = new ArrayList<>();

        try {
            getLikedCatsStatement.setInt(1, user.getCurrentUser().getId());
            ResultSet set = getLikedCatsStatement.executeQuery();
            while (set.next()) {
                int petId = set.getInt(1);
                //duplicate cats not allowed
                if (!list.stream().anyMatch(c -> c.getId()==petId)) {
                    list.addAll((ArrayList<CatDAO>) getAllCats().stream().filter(c -> c.getId() == petId).collect(Collectors.toList()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void insertLikedCat(CatDAO cat) {
        try {
            ResultSet result = getNewIdLiked.executeQuery();
            result.next();

            insertLikedCatStatement.setInt(1, result.getInt(1) );
            insertLikedCatStatement.setInt(2, cat.getId());
            insertLikedCatStatement.setNull(3, Types.INTEGER);
            insertLikedCatStatement.setInt(4, user.getCurrentUser().getId());
            insertLikedCatStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCat(CatDAO cat) {
        try {
            ResultSet result = getNewId.executeQuery();
            result.next();

            insertCatStatement.setInt(1, result.getInt(1) );
            insertCatStatement.setString(2, cat.getName());
            insertCatStatement.setString(3, cat.getSex());
            insertCatStatement.setString(4, cat.getAge());
            insertCatStatement.setString(5, cat.getBreed());
            insertCatStatement.setString(6, cat.getImgSrc());
            insertCatStatement.setDouble(7, cat.getDedicationHours());
            insertCatStatement.setInt(8, cat.getPeopleTolerance());
            insertCatStatement.setInt(9, cat.getYardNeed());
            insertCatStatement.setInt(10, cat.getPetTolerance());
            insertCatStatement.setInt(11, cat.getAdopted());
            insertCatStatement.setInt(12, cat.getUrgent());
            insertCatStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteLikedCat(CatDAO cat) {
        try {
            deleteLikedCatStatement.setInt(1, cat.getId());
            deleteLikedCatStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCat(CatDAO cat) {
        try {
            deleteCatStatement.setInt(1, cat.getId());
            deleteCatStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAdopted (CatDAO cat) {
        try {
            updateAdoptionStatement.setInt(1, cat.getId());
            updateAdoptionStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUrgent (CatDAO cat) {
        try {
            updateUrgentStatement.setInt(1, cat.getId());
            updateUrgentStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        stmt.executeUpdate("DELETE FROM cats");
        regenerateDatabase();
    }

}
