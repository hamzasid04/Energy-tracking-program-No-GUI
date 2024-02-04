import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void constructor() {
        Person person = new Person("Fatima", 123);
        assertEquals("Fatima",person.getName());
        assertEquals(123,person.getReportID());
    }
    @Test
    void getName() {
        Person person = new Person("Fatima", 123);
        String expected = "Fatima";
        String actual = person.getName();
;       assertEquals(expected,actual);
    }

    @Test
    void getReportID() {
        Person person = new Person("Fatima", 123);
        int expected = 123;
        int actual = person.getReportID();
        assertEquals(expected,actual);
    }
}