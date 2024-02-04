import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CostTest {


    @Test
    void hashcode() {
        Cost cost = new Cost(10,11,12,13,14,15,16,0);
        Cost cost1 = new Cost(10,11,12,13,14,15,16,0);
        int one = cost.hashCode();
        int two = cost1.hashCode();
        assertEquals(one,two);
    }
    @Test
    void getData() {
        Cost cost = new Cost(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0);
        Data data = new Data();
        System.out.println(Arrays.deepToString(cost.getData()));
        System.out.println(data.getallutilityCostList());
    }


    @Test
    void getFridgeCost() {

        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        double expected = ((1.0*12.79)/100);
        double actual = cost.getFridgeCost();
        assertEquals(expected, actual);

    }

    @Test
    void getMicrowaveCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        double expected = ((2.0*12.79)/100);
        double actual = cost.getMicrowaveCost();
        assertEquals(expected, actual);
    }

    @Test
    void getTvCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        double expected = ((3.0*12.79)/100);
        double actual = cost.getTvCost();
        assertEquals(expected, actual);
    }

    @Test
    void getDishwasherCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        double expected = ((4.0*12.79)/100);
        double actual = cost.getDishwasherCost();
        assertEquals(expected, actual);
    }

    @Test
    void getLightsCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        double expected = ((5.0*12.79)/100);
        double actual = cost.getLightsCost();
        assertEquals(expected, actual);
    }

    @Test
    void getWasherCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        double expected = ((6.0*12.79)/100);
        double actual = cost.getWasherCost();
        assertEquals(expected, actual);
    }

    @Test
    void getDryerCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        double expected = ((7.0*12.79)/100);
        double actual = cost.getDryerCost();
        assertEquals(expected, actual);
    }

    @Test
    void getAcCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        double expected = 8.0; // As per the constructor call in CostTest, the AC cost is directly passed as 8.0
        double actual = cost.getAcCost();
        assertEquals(expected, actual);

    }

    @Test
    void getTotalCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        double expected = ((1.0*12.79)/100)+ ((2.0*12.79)/100)+((3.0*12.79)/100)+ ((4.0*12.79)/100)+((5.0*12.79)/100)+((6.0*12.79)/100)+((7.0*12.79)/100)+8.0;
        double actual = cost.getTotalCost();
        assertEquals(expected, actual, 0.001); // The third argument is the delta within which the expected and actual values must lie to be considered equal.
    }
}