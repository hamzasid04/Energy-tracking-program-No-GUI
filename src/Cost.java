import java.util.Arrays;

public class Cost {
    private Object[] data;
    private double fridgeCost;
    private double microwaveCost;
    private double tvCost;
    private double dishwasherCost;
    private double lightsCost;
    private double washerCost;
    private double dryerCost;
    private double ACCost;
    /**
     * Constructs a Cost object with specified power consumption values for various appliances.
     * The cost for each appliance is calculated based on the given power consumption value and a fixed rate.
     *
     * @param fridgeKWh      Power consumption of the fridge in kWh.
     * @param microwaveKWh   Power consumption of the microwave in kWh.
     * @param tvKWh          Power consumption of the TV in kWh.
     * @param dishwasherKWh  Power consumption of the dishwasher in kWh.
     * @param lightKWh       Power consumption of the lights in kWh.
     * @param washerKWh      Power consumption of the washer in kWh.
     * @param dryerKWh       Power consumption of the dryer in kWh.
     * @param acCost         Cost of the air conditioner usage.
     */

    // this is the contructor where we have all the variables that we need to access for other classes to use
    public Cost (double fridgeKWh,double microwaveKWh,double tvKWh,double dishwasherKWh,double lightKWh,double washerKWh,double dryerKWh, double acCost){
        this.fridgeCost= ((fridgeKWh*12.79)/100);
        this.microwaveCost = ((microwaveKWh*12.79)/100);
        this.tvCost = ((tvKWh*12.79)/100);
        this.dishwasherCost = ((dishwasherKWh*12.79)/100);
        this.lightsCost = ((lightKWh*12.79)/100);
        this.washerCost = ((washerKWh*12.79)/100);
        this.dryerCost = ((dryerKWh*12.79)/100);
        this.ACCost = acCost;

    }

    public Object[] getData() {
        return data;
    }

    public double getFridgeCost() {
        return this.fridgeCost;
    }

    public double getMicrowaveCost() {
        return this.microwaveCost;
    }

    public double getTvCost() {
        return this.tvCost;
    }

    public double getDishwasherCost() {
        return this.dishwasherCost;
    }

    public double getLightsCost() {
        return this.lightsCost;
    }

    public double getWasherCost() {
        return this.washerCost;
    }

    public double getDryerCost() {
        return this.dryerCost;
    }

    public double getAcCost() {
        return this.ACCost;
    }
    /**
     * Calculates the total cost of power consumption for all appliances.
     *
     * @return The sum of the costs of all appliances.
     */
    public double getTotalCost(){
        double totalCost = this.ACCost + this.fridgeCost +this.microwaveCost+this.tvCost+this.lightsCost+this.dishwasherCost+this.washerCost+this.dryerCost;
        return totalCost;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "$" +"Fridge Cost= " + fridgeCost +
                "$" + "Microwave Cost= " + microwaveCost +
                "$" + "TV Cost= " + tvCost +
                "$" + "Dishwasher Cost= " + dishwasherCost +
                "$" + "Lights Cost= " + lightsCost +
                "$" + "Washer Cost= " + washerCost +
                "$" +"Dryer Cost= " + dryerCost +
                "$" +"AC Cost= " + ACCost +
                "$" +"Total Cost= " + getTotalCost() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cost cost = (Cost) o;

        return Double.compare(fridgeCost, cost.fridgeCost) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(fridgeCost);
        return (int) (temp ^ (temp >>> 32));
    }
}
