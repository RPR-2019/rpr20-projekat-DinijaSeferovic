package ba.unsa.etf.dal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {



    @Test
    public void testGetters() {
        Admin admin = new Admin(2,"admin", "password");
                assertAll(
                () -> assertEquals(2, admin.getId()),
                () -> assertEquals("admin", admin.getUsername()),
                () -> assertEquals("password", admin.getPassword())
        );
    }

    @Test
    public void testSetters() {
        Admin admin = new Admin("admin", "password");
        admin.setId(3);
        assertAll(
                () -> assertEquals(3, admin.getId()),
                () -> assertEquals("admin", admin.getUsername()),
                () -> assertEquals("password", admin.getPassword())
        );
    }


}