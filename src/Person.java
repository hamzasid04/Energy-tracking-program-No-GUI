import java.util.ArrayList;
import java.util.Random;

public class Person {
    /**
     * The Person class represents an individual with a unique name and report ID.
     */
    private String name;
    Random random = new Random();
    // already generates random number from the menu class
    // only the number that was generated is being stored here
    private final int reportID ;// random.nextInt(1000);

    /**
     * Constructs a new Person object with a given name and report ID.
     *
     * @param name     The name of the person.
     * @param reportID The report ID associated with the person.
     */
    public Person(String name, int reportID ) {
        this.name = name;
        this.reportID = reportID;
    }
    /**
     * Retrieves the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Retrieves the report ID of the person.
     *
     * @return The report ID of the person.
     */
    public int getReportID() {
        return this.reportID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return reportID == person.reportID;
    }

    @Override
    public int hashCode() {
        return reportID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                "Report ID='" + reportID + '\'' +
                '}';
    }
}
