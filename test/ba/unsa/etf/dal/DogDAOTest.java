package ba.unsa.etf.dal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DogDAOTest {


    private DogDAO dao = DogDAO.getInstance();

    @BeforeEach
    public void resetDB() throws SQLException {
        dao.defaultDatabase();
    }

    @Test
    void getDog() {
        this.dao = DogDAO.getInstance();
        ArrayList<DogDAO> dogs = dao.getAllDogs();
        assertEquals("Basi", dogs.get(0).getName());
        assertEquals("F", dogs.get(1).getSex());
    }

    @Test
    void addDog() {
        DogDAO dog = new DogDAO(10,"Love", "M", "1 year", "Pug", "pug.jpg", 5, 0,0,0,0,0);
        dao.insertDog(dog);
        ArrayList<DogDAO> dogs = dao.getAllDogs();
        assertEquals("Love", dogs.get(9).getName());
    }

    @Test
    void deleteDog() {
        dao.deleteDog(new DogDAO(10,"Love", "M", "1 year", "Pug", "pug.jpg", 5, 0,0,0,0,0));

        ArrayList<DogDAO> dogs = dao.getAllDogs();
        assertEquals(9, dogs.size());
        assertEquals("Ali", dogs.get(3).getName());
        assertEquals("1 year", dogs.get(6).getAge());
    }


}