package ba.unsa.etf.dal;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DogDAO extends Pet{

    private Connection con = null;
    private static DogDAO instance = null;
    private PreparedStatement getDogsStatement, getLikedDogsStatement,insertLikedDogStatement, insertDogStatement, getNewIdLiked, getNewId,
                              deleteLikedDogStatement, deleteDogStatement, updateAdoptionStatement, getUnadoptedDogsStatement, updateUrgentStatement;
    private static UserDAO user;
    private DogDAO() {

        try {
            con = UserDAO.getConnection();
            getDogsStatement =  con.prepareStatement("SELECT * FROM dogs");
            getUnadoptedDogsStatement =  con.prepareStatement("SELECT * FROM dogs WHERE adopted = 0");
            getLikedDogsStatement =  con.prepareStatement("SELECT dogid FROM likedpets WHERE dogid IS NOT NULL AND userid=?");
            getNewIdLiked = con.prepareStatement("SELECT MAX(id)+1 FROM likedpets");
            getNewId = con.prepareStatement("SELECT MAX(id)+1 FROM dogs");
            insertLikedDogStatement = con.prepareStatement("INSERT INTO likedpets VALUES (?,?,?,?)");
            insertDogStatement = con.prepareStatement("INSERT INTO dogs VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            deleteLikedDogStatement = con.prepareStatement("DELETE FROM likedpets WHERE dogid = ?");
            deleteDogStatement = con.prepareStatement("DELETE FROM dogs WHERE id = ?");
            updateAdoptionStatement = con.prepareStatement("UPDATE dogs SET adopted = 1 WHERE id = ?");
            updateUrgentStatement = con.prepareStatement("UPDATE dogs SET urgent = 1 WHERE id = ?");

        } catch (SQLException e) {
            regenerateDatabase();
            try {
                getDogsStatement =  con.prepareStatement("SELECT * FROM dogs");
                getUnadoptedDogsStatement =  con.prepareStatement("SELECT * FROM dogs WHERE adopted = 0");
                getLikedDogsStatement =  con.prepareStatement("SELECT dogid FROM likedpets WHERE dogid IS NOT NULL AND userid=?");
                getNewIdLiked = con.prepareStatement("SELECT MAX(id)+1 FROM likedpets");
                getNewId = con.prepareStatement("SELECT MAX(id)+1 FROM dogs");
                insertLikedDogStatement = con.prepareStatement("INSERT INTO likedpets VALUES (?,?,?,?)");
                insertDogStatement = con.prepareStatement("INSERT INTO dogs VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                deleteLikedDogStatement = con.prepareStatement("DELETE FROM likedpets WHERE dogid = ?");
                deleteDogStatement = con.prepareStatement("DELETE FROM dogs WHERE id = ?");
                updateAdoptionStatement = con.prepareStatement("UPDATE dogs SET adopted = 1 WHERE id = ?");
                updateUrgentStatement = con.prepareStatement("UPDATE dogs SET urgent = 1 WHERE id = ?");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public DogDAO(int id,String name, String sex, String age, String breed, String imgSrc, double dedicationHours, int peopleTolerance, int yardNeed, int petTolerance, int adopted, int urgent) {
        super(id,name,sex,age,breed,imgSrc,dedicationHours,peopleTolerance,yardNeed,peopleTolerance,adopted,urgent);
    }

    private static void initialize() {
        instance = new DogDAO();

    }

    public static DogDAO getInstance() {
        user = UserDAO.getInstance();
        if (instance == null) initialize();
        return instance;
    }

    public ArrayList<DogDAO> getAllDogs() {
        ArrayList<DogDAO> list = new ArrayList<>();

        try {

            ResultSet set = getDogsStatement.executeQuery();
            while (set.next()) {
                list.add(new DogDAO(set.getInt(1), set.getString(2), set.getString(3),
                        set.getString(4), set.getString(5), set.getString(6),
                        set.getDouble(7), set.getInt(8), set.getInt(9), set.getInt(10), set.getInt(11), set.getInt(12)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<DogDAO> getAllUnadoptedDogs() {
        ArrayList<DogDAO> list = new ArrayList<>();

        try {

            ResultSet set = getUnadoptedDogsStatement.executeQuery();
            while (set.next()) {
                if (set.getInt(11)!=1) {
                    list.add(new DogDAO(set.getInt(1), set.getString(2), set.getString(3),
                            set.getString(4), set.getString(5), set.getString(6),
                            set.getDouble(7), set.getInt(8), set.getInt(9), set.getInt(10), set.getInt(11), set.getInt(12)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<DogDAO> getLikedDogs() {
        ArrayList<DogDAO> list = new ArrayList<>();

        try {
            getLikedDogsStatement.setInt(1, user.getCurrentUser().getId());
            ResultSet set = getLikedDogsStatement.executeQuery();
            while (set.next()) {
                int petId = set.getInt(1);
                //duplicate dogs not allowed
                if (!list.stream().anyMatch(d -> d.getId()==petId)) {
                    list.addAll((ArrayList<DogDAO>) getAllDogs().stream().filter(d -> d.getId() == petId).collect(Collectors.toList()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void insertLikedDog(DogDAO dog) {
        try {
            ResultSet result = getNewIdLiked.executeQuery();
            result.next();

            insertLikedDogStatement.setInt(1, result.getInt(1) );
            insertLikedDogStatement.setNull(2, Types.INTEGER);
            insertLikedDogStatement.setInt(3, dog.getId());
            insertLikedDogStatement.setInt(4, user.getCurrentUser().getId());
            insertLikedDogStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDog(DogDAO dog) {
        try {
            ResultSet result = getNewId.executeQuery();
            result.next();

            insertDogStatement.setInt(1, result.getInt(1) );
            insertDogStatement.setString(2, dog.getName());
            insertDogStatement.setString(3, dog.getSex());
            insertDogStatement.setString(4, dog.getAge());
            insertDogStatement.setString(5, dog.getBreed());
            insertDogStatement.setString(6, dog.getImgSrc());
            insertDogStatement.setDouble(7, dog.getDedicationHours());
            insertDogStatement.setInt(8, dog.getPeopleTolerance());
            insertDogStatement.setInt(9, dog.getYardNeed());
            insertDogStatement.setInt(10, dog.getPetTolerance());
            insertDogStatement.setInt(11, dog.getAdopted());
            insertDogStatement.setInt(12, dog.getUrgent());
            insertDogStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLikedDog(DogDAO dog) {
        try {
            deleteLikedDogStatement.setInt(1, dog.getId());
            deleteLikedDogStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDog(DogDAO dog) {
        try {
            deleteDogStatement.setInt(1, dog.getId());
            deleteDogStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAdopted (DogDAO dog) {
        try {
            updateAdoptionStatement.setInt(1, dog.getId());
            updateAdoptionStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUrgent (DogDAO dog) {
        try {
            updateUrgentStatement.setInt(1, dog.getId());
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
        stmt.executeUpdate("DELETE FROM dogs");
        regenerateDatabase();
    }

}
