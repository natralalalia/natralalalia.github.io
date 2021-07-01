package monkeys;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonkeyTest {

    String species = "Orangutan";  // Prime Video channel
    String name = "BabyMonkey"; // Prime Video title
    Monkey x = new Monkey(name, species);

    MonkeyTest() throws Exception {
    }

    @Test
    void getName() {
        assertEquals(x.getName(), name);
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
        String newName = "AdultMonkey";
        x.setName(newName);
        assertEquals(x.getName(), newName);
    }

    @Test
    void setSpecies() {
        String newSpecies = "Gorilla";
        x.setName(newSpecies);
        assertEquals(x.getName(), newSpecies);
    }

    @Test
    void testEquals() throws Exception {
        Monkey newMonkey = new Monkey(name, species);
        assertEquals(x, newMonkey);
    }

    @Test
    void testToString() {
        assertEquals(x.toString(), "Monkey{" + "id=" + x.getId() + ", name='BabyMonkey" + '\'' + ", species='Orangutan" + '\'' + '}');
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