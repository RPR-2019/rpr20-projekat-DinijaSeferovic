package ba.unsa.etf.dal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @Test
    public void testGetters() {
        User user = new User(5,"User Useric","username", "user@gmail.com", "user123", 5,0,0,0,0, "2021-09-29", 0,1);
        assertAll(
                () -> assertEquals(5, user.getId()),
                () -> assertEquals("User Useric", user.getName()),
                () -> assertEquals("username", user.getUsername()),
                () -> assertEquals("user@gmail.com", user.getEmail()),
                () -> assertEquals("user123", user.getPassword()),
                () -> assertEquals(5, user.getWorkhours()),
                () -> assertEquals(0, user.getRoomates()),
                () -> assertEquals(0, user.getHomework()),
                () -> assertEquals(0, user.getYard()),
                () -> assertEquals(0, user.getPets()),
                () -> assertEquals("2021-09-29", user.getAppointment()),
                () -> assertEquals(0, user.getApproved()),
                () -> assertEquals(1, user.getProfileSet())
        );
    }

    @Test
    public void testSetters() {
        User user = new User("User", "username", "user@gmail.com", "password");
        user.setId(6);
        user.setWorkhours(5);
        user.setRoomates(0);
        user.setHomework(0);
        user.setYard(0);
        user.setPets(0);
        user.setAppointment("2021-09-29");
        user.setApproved(0);
        user.setProfileSet(1);
        assertAll(
                () -> assertEquals(6, user.getId()),
                () -> assertEquals("User", user.getName()),
                () -> assertEquals("username", user.getUsername()),
                () -> assertEquals("user@gmail.com", user.getEmail()),
                () -> assertEquals("password", user.getPassword()),
                () -> assertEquals(5, user.getWorkhours()),
                () -> assertEquals(0, user.getRoomates()),
                () -> assertEquals(0, user.getHomework()),
                () -> assertEquals(0, user.getYard()),
                () -> assertEquals(0, user.getPets()),
                () -> assertEquals("2021-09-29", user.getAppointment()),
                () -> assertEquals(0, user.getApproved()),
                () -> assertEquals(1, user.getProfileSet())
        );
    }

}