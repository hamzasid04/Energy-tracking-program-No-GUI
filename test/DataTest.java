import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    private Data data;


    @BeforeEach
    void setUp() {
        data = new Data();
        Data.heaterCostList.clear();
        Data.monthList.clear();
        Data.userAcResponse.clear();
        Data.userAc.clear();
        Data.acCostList.clear(); // Clearing the AC cost list
        Data.lookUp.clear();
        // Clear other lists if necessary
    }

    @Test
    void storePerson() {
        int reportID = 270;
        String name = "Bobby";
        data.storePerson(reportID, name);

        boolean found = false;
        for (Person person : data.getPersonAndIDList()) {
            if (person.getName().equals(name) && person.getReportID() == reportID) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Person not stored correctly");
        assertEquals(name, Data.lookUp.get(reportID), "Name does not exist in lookUp, not properly stored");
    }

    @Test
    void storeUserMonth() {
        String month = "JUNE";
        Data.storeUserMonth(month);

        assertEquals(1, Data.monthList.size(), "Size of month list is not 1");
        assertTrue(Data.monthList.contains(month), "Given month has not been properly stored in month list");
    }

    @Test
    void getUserMonth() {
        String month = "JUNE";
        Data.storeUserMonth(month);

        ArrayList<String> expected = new ArrayList<>();
        expected.add(month);
        ArrayList<String> actual = Data.getUserMonth();

        assertEquals(expected, actual, "Cannot retrieve month");
    }

    @Test
    void testUtilityConsumption() {
        double[] expected = {20.9, 30.9, 40.8, 900.7, 87.8, 56.0, 37.0};
        double[] actual = Data.utilityConsumption(expected);

        assertArrayEquals(expected, actual, "Utility consumption list returns incorrect list");
    }

    @Test
    void storeUserAC() {
        boolean hasAc = true;
        int reportId = 123;

        Data.storeUserAC(reportId, hasAc);

        assertEquals(1, Data.userAc.size(), "Size of stored AC list is not 1");
        assertTrue(Data.userAc.containsKey(reportId), "Report ID does not exist, not properly stored");
        assertTrue(Data.userAc.get(reportId), "User's AC response does not exist, not properly stored");
    }

    @Test
    void getUserAc() {
        boolean hasAc = true;
        int reportId = 123;

        Data.storeUserAC(reportId, hasAc);

        ArrayList<Boolean> expected = new ArrayList<>();
        expected.add(hasAc);
        ArrayList<Boolean> actual = Data.getUserAc();

        assertEquals(expected, actual, "Contents of AC response does not match expected");
    }

    @Test
    void storeAcCost() {
        double cost = 278.02;

        Data.storeAcCost(cost);

        assertEquals(1, Data.acCostList.size(), "AC cost list not size 1");
        assertTrue(Data.acCostList.contains(cost), "AC cost does not exist, not properly stored");
    }

    @Test
    void getAcCost() {
        double cost = 900.98;
        Data.storeAcCost(cost);

        ArrayList<Double> expected = new ArrayList<>();
        expected.add(cost);

        ArrayList<Double> actual = Data.getAcCost();
        assertEquals(expected, actual, "Contents of AC cost do not match expected");
    }

    @Test
    void storeHeaterCost() {
        double cost = 36.03;

        Data.storeHeaterCost(cost);

        assertEquals(1, Data.heaterCostList.size(), "Heater cost list not size 1");
        assertTrue(Data.heaterCostList.contains(cost), "Heater cost does not exist, not properly stored");
    }

    @Test
    void getHeaterCost() {
        double cost = 34.00;
        Data.storeHeaterCost(cost);

        ArrayList<Double> expected = new ArrayList<>();
        expected.add(cost);

        ArrayList<Double> actual = Data.getHeaterCost();
        assertEquals(expected, actual, "Contents of heater cost do not match expected");
    }
}
