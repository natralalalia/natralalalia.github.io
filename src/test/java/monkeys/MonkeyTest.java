package monkeys;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonkeyTest {

    String species = "Orangutan";  // Prime Video channel
//    String name = "BabyMonkey"; // Prime Video title
    String firstName = "Baby";
    String lastName = "Monkey";
    Monkey x = new Monkey(firstName, lastName, species);

    MonkeyTest() throws Exception {
    }

    @Test
    void getName() {
        assertEquals(x.getName(), "Baby Monkey");
    }

    @Test
    void getSpecies() {
        assertEquals(x.getSpecies(), species);
    }

    @Test
    void setId() {
        long newId = 13;
        x.setId(newId);
        assertEquals(x.getId(), newId);
    }

    @Test
    void setName() {
        String newName = "Adult Monkey";
        x.setName(newName);
        assertEquals(x.getName(), newName);
    }

    @Test
    void setFirstName() {
        String newFirstName = "Arthur";
        x.setFirstName(newFirstName);
        assertEquals(x.getFirstName(), newFirstName);
    }

    @Test
    void setLastName() {
        String newLastName = "Frederic";
        x.setLastName(newLastName);
        assertEquals(x.getLastName(), newLastName);
    }

    @Test
    void setSpecies() {
        String newSpecies = "Gorilla";
        x.setSpecies(newSpecies);
        assertEquals(x.getSpecies(), newSpecies);
    }

    @Test
    void testEquals() throws Exception {
        Monkey newMonkey = new Monkey(firstName, lastName, species);
        assertEquals(x, newMonkey);
    }

    @Test
    void testToString() {
        assertEquals(x.toString(), "Monkey{" + "id=" + x.getId() + ", firstName='Baby', lastName='" + lastName + "', species='Orangutan" + '\'' + '}');
    }

//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @Test // (expected = IndexOutOfBoundsException.class)
//    void testNotNullMonkey() throws Exception {
//        thrown.expect(Exception.class);
//        Monkey newMonkey = new Monkey("", species);
//    }

//    @Test
//    public void testFooThrowsIndexOutOfBoundsException() {
//        try {
//            Monkey newMonkey = new Monkey("", species);
//            fail("expected exception was not occurred.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}