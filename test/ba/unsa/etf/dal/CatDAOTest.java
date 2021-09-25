package ba.unsa.etf.dal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CatDAOTest {

    private CatDAO dao = CatDAO.getInstance();

    @BeforeEach
    public void resetDB() throws SQLException {
        dao.defaultDatabase();
    }

    @Test
    void getCat() {
        this.dao = CatDAO.getInstance();
        ArrayList<CatDAO> cats = dao.getAllCats();
        assertEquals("Nucco", cats.get(0).getName());
        assertEquals("F", cats.get(1).getSex());
    }

    @Test
    void addCat() {
        CatDAO cat = new CatDAO(10,"Love", "M", "1 year", "Tabby", "tabby.jpg", 5, 0,0,0,0,0);
        dao.insertCat(cat);
        ArrayList<CatDAO> cats = dao.getAllCats();
        assertEquals("Love", cats.get(9).getName());
    }

    @Test
    void deleteCat() {
        dao.deleteCat(new CatDAO(10,"Love", "M", "1 year", "Tabby", "tabby.jpg", 5, 0,0,0,0,0));

        ArrayList<CatDAO> cats = dao.getAllCats();
        assertEquals(9, cats.size());
        assertEquals("Purrito", cats.get(3).getName());
        assertEquals("2 years", cats.get(6).getAge());
    }



}