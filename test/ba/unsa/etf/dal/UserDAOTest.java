package ba.unsa.etf.dal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {


    private UserDAO dao = UserDAO.getInstance();

    @BeforeEach
    public void resetDB() throws SQLException {
        dao.defaultDatabase();
    }

    @Test
    void getUser() {

        ArrayList<User> users = dao.getAllUsers();
        assertEquals("Dinija Seferovic", users.get(0).getName());
        assertEquals("mihom", users.get(1).getPassword());
    }

    @Test
    void addUser() {
        User user = new User(4,"User Useric","username", "user@gmail.com", "user123", 5,0,0,0,0, "2021-09-29", 0,1);
        dao.registerUser(user);
        ArrayList<User> users = dao.getAllUsers();
        assertEquals("username", users.get(3).getUsername());
    }

    @Test
    void deleteUser() {
        User user = new User(4,"User Useric","username", "user@gmail.com", "user123", 5,0,0,0,0, "2021-09-29", 0,1);

        ArrayList<User> users = dao.getAllUsers();
        assertEquals(3, users.size());
        assertEquals("miho123", users.get(1).getUsername());
    }


}