package ba.unsa.etf.dal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminDAOTest {


    private AdminDAO dao = AdminDAO.getInstance();

    @BeforeEach
    public void resetDB() throws SQLException {
        dao.defaultDatabase();
    }

    @Test
    void getAdmin() {

        ArrayList<Admin> admins = dao.getAllAdmins();
        assertEquals("admin", admins.get(0).getUsername());
        assertEquals("admin", admins.get(0).getPassword());
    }

    @Test
    void getPassword() {
        assertEquals("admin", dao.getUserPassword("admin"));
    }

    @Test
    void currentAdmin() {
        Admin admin = new Admin(1,"admin", "admin");
        dao.setCurrentAdmin(admin);
        assertEquals("admin", dao.getCurrentAdmin().getUsername());
    }


}