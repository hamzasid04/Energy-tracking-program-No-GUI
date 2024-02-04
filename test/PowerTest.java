import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PowerTest {

    @Test
    void constructor() {
        Power power = new Power(9,8,7,6,5,4,3,2);
        assertEquals(9,power.getFridgePower());
        assertEquals(8,power.getMicrowavePower());
        assertEquals(7,power.getTVPower());
        assertEquals(6,power.getDishwasherPower());
        assertEquals(5,power.getLightPower());
        assertEquals(4,power.getWasherPower());
        assertEquals(3,power.getDryerPower());
        assertEquals(2,power.getAcPower());



    }
    @Test
    void getFridgePower() {
        Power power = new Power(13,14,15,16,17,18,19,0);
        double expected = 13;
        double actual = power.getFridgePower();
        assertEquals(expected, actual);

    }
    @Test
    void getMicrowavePower() {
        Power power = new Power(0, 14, 0, 0, 0, 0, 0, 0);
        assertEquals(14, power.getMicrowavePower());
    }

    @Test
    void getTVPower() {
        Power power = new Power(0, 0, 15, 0, 0, 0, 0, 0);
        assertEquals(15, power.getTVPower());
    }

    @Test
    void getDishwasherPower() {
        Power power = new Power(0, 0, 0, 16, 0, 0, 0, 0);
        assertEquals(16, power.getDishwasherPower());
    }

    @Test
    void getLightPower() {
        Power power = new Power(0, 0, 0, 0, 17, 0, 0, 0);
        assertEquals(17, power.getLightPower());
    }

    @Test
    void getWasherPower() {
        Power power = new Power(0, 0, 0, 0, 0, 18, 0, 0);
        assertEquals(18, power.getWasherPower());
    }

    @Test
    void getDryerPower() {
        Power power = new Power(0, 0, 0, 0, 0, 0, 19, 0);
        assertEquals(19, power.getDryerPower());
    }

    @Test
    void getAcPower() {
        Power power = new Power(0, 0, 0, 0, 0, 0, 0, 20);
        assertEquals(20, power.getAcPower());
    }

    @Test
    void getTotalPower() {
        Power power = new Power(1, 2, 3, 4, 5, 6, 7, 8);
        double expectedTotal = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8;
        assertEquals(expectedTotal, power.getTotalPower());
    }

    @Test
    void getData() {
        Power power = new Power(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0);
        Data data = new Data();
        System.out.println(Arrays.deepToString(power.getData()));
        System.out.println(data.getallutilityCostList());


    }
}