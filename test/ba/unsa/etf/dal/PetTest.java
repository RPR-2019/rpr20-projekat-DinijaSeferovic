package ba.unsa.etf.dal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    public void testGetters() {
        Pet pet = new Pet(1,"Love", "M", "1 year", "Tabby", "tabby.jpg", 5, 0,0,0,0,0);
        assertAll(
                () -> assertEquals(1, pet.getId()),
                () -> assertEquals("Love", pet.getName()),
                () -> assertEquals("M", pet.getSex()),
                () -> assertEquals("1 year", pet.getAge()),
                () -> assertEquals("Tabby", pet.getBreed()),
                () -> assertEquals("tabby.jpg", pet.getImgSrc()),
                () -> assertEquals(5, pet.getDedicationHours()),
                () -> assertEquals(0, pet.getPeopleTolerance()),
                () -> assertEquals(0, pet.getYardNeed()),
                () -> assertEquals(0, pet.getPetTolerance()),
                () -> assertEquals(0, pet.getAdopted()),
                () -> assertEquals(0, pet.getUrgent())
        );
    }

    @Test
    public void testSetters() {
        Pet pet = new Pet();
        pet.setId(2);
        pet.setName("Lav");
        pet.setSex("M");
        pet.setAge("1 year");
        pet.setBreed("Tabby");
        pet.setImgSrc("tabby.jpg");
        pet.setDedicationHours(5);
        pet.setPeopleTolerance(0);
        pet.setYardNeed(0);
        pet.setPetTolerance(0);
        pet.setAdopted(0);
        pet.setUrgent(0);
        assertAll(
                () -> assertEquals(2, pet.getId()),
                () -> assertEquals("Lav", pet.getName()),
                () -> assertEquals("M", pet.getSex()),
                () -> assertEquals("1 year", pet.getAge()),
                () -> assertEquals("Tabby", pet.getBreed()),
                () -> assertEquals("tabby.jpg", pet.getImgSrc()),
                () -> assertEquals(5, pet.getDedicationHours()),
                () -> assertEquals(0, pet.getPeopleTolerance()),
                () -> assertEquals(0, pet.getYardNeed()),
                () -> assertEquals(0, pet.getPetTolerance()),
                () -> assertEquals(0, pet.getAdopted()),
                () -> assertEquals(0, pet.getUrgent())
        );
    }

}