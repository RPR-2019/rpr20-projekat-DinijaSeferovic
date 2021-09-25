package ba.unsa.etf.dal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    Connection conn = null;
    public static Connection createConnection()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + System.getProperty("user.home") + "/.rpr20-projekat-DinijaSeferovic/petshelterdb.db";
            Connection con = DriverManager.getConnection("jdbc:sqlite:petshelterdb.db");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
    }
}