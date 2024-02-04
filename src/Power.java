import java.util.ArrayList;
import java.util.Arrays;

public class Power {

    private double fridgePower;
    private double microwavePower;
    private double tvPower;
    private double dishwasherPower;
    private double lightsPower;
    private double washerPower;
    private double dryerPower;
    private double acPower;

    private Object[] data;
    //private ArrayList <Object> individualdata;

    /**
     * Constructs a new Power object with specified power consumption values for various appliances.
     *
     * @param fridgeKWh      Power consumption of the fridge in kWh.
     * @param microwaveKWh   Power consumption of the microwave in kWh.
     * @param tvKWh          Power consumption of the TV in kWh.
     * @param dishwasherKWh  Power consumption of the dishwasher in kWh.
     * @param lightKWh       Power consumption of the lights in kWh.
     * @param washerKWh      Power consumption of the washer in kWh.
     * @param dryerKWh       Power consumption of the dryer in kWh.
     * @param acPower        Power consumption of the air conditioner in kWh.
     */

// this is the contructor where we have all the variables that we need to access for other classes to use
    public Power (double fridgeKWh,double microwaveKWh,double tvKWh,double dishwasherKWh,double lightKWh,double washerKWh,double dryerKWh, double acPower){
        this.fridgePower= fridgeKWh;
        this.microwavePower = microwaveKWh;
        this.tvPower = tvKWh;
        this.dishwasherPower = dishwasherKWh;
        this.lightsPower = lightKWh;
        this.washerPower = washerKWh;
        this.dryerPower = dryerKWh;
        this.acPower = acPower;
    }

    public double getFridgePower() {
        return this.fridgePower;
    }

    public double getMicrowavePower() {
        return this.microwavePower;
    }

    public double getTVPower() {
        return this.tvPower;

    }

    public double getDishwasherPower() {
        return this.dishwasherPower;

    }

    public double getLightPower() {
        return this.lightsPower;

    }

    public double getWasherPower() {
        return this.washerPower;

    }

    public double getDryerPower() {
        return this.dryerPower;

    }

    public  double getAcPower() {
        return this.acPower;

    }
    /**
     * Calculates the total power consumption from all appliances.
     *
     * @return The sum of power consumption values of all appliances.
     */
    public double getTotalPower(){

        double totalPower = this.acPower + this.fridgePower +this.microwavePower+this.tvPower+this.lightsPower+this.dishwasherPower+this.washerPower+this.dryerPower;
        return totalPower;
    }

    public Object[] getData() {
        return data;
    }


    @Override
    public String toString() {
        return "Power{" +
                "Fridge Power=" + fridgePower + " kWh, " +
                "Microwave Power=" + microwavePower + " kWh, " +
                "TV Power=" + tvPower + " kWh, " +
                "Dishwasher Power=" + dishwasherPower + " kWh, " +
                "Lights Power=" + lightsPower + " kWh, " +
                "Washer Power=" + washerPower + " kWh, " +
                "Dryer Power=" + dryerPower + " kWh, " +
                "AC Power=" + acPower + " kWh, " +
                "Total Power=" + getTotalPower() + " kWh" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Power power = (Power) o;

        return Double.compare(fridgePower, power.fridgePower) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(fridgePower);
        return (int) (temp ^ (temp >>> 32));
    }
}
