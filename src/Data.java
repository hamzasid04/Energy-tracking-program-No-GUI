/** *
 *
 * @author @hamza.siddiqui  Hamza Siddiqui, @marwah.ahmed@ucalgary.ca Marwah Ahmed
 * @UCID 30183881 , 30180880
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
/**
 * This class represents the data structure for storing information related to users' power consumption, costs, and other utilities.
 * It includes methods for storing and retrieving data about users, their appliances, and the corresponding costs.
 */
public class Data {
    public static final HashMap<Integer, Boolean> userAc= new HashMap<Integer, Boolean>();
    //private static public static final HashMap<String, Double > costList= new HashMap<String, Double>();ashMap<Integer,String > monthList= new HashMap<Integer,String>();
    public static final HashMap<Integer, Boolean> userHeater= new HashMap<Integer, Boolean>();
    public static final ArrayList<Boolean> userAcResponse = new ArrayList<Boolean>();
    public static final ArrayList<Boolean> userHeaterResponse = new ArrayList<Boolean>();
    public static final double[] utilityConsumptionKWh = new double[7];
    public static final ArrayList<boolean[]> booleanList = new ArrayList<boolean[]>();
    public static final ArrayList<String> monthList = new ArrayList<>();
    public static final ArrayList<boolean[]> userAC = new ArrayList<>();
    public static final ArrayList<String> nameList = new ArrayList<String>();
    public static final ArrayList<Integer> reportIdList = new ArrayList<Integer>();
    public static final ArrayList<Double> acCostList = new ArrayList<Double>();
    public static final ArrayList<Double> acPowerList = new ArrayList<Double>();

    public static final ArrayList<Double> heaterCostList = new ArrayList<Double>();
    public static final ArrayList<String> currentSeasonList = new ArrayList<String>();
    public static final HashMap<Integer, Double> acCostList1 = new HashMap<>();
    public static final HashMap<Integer, String> lookUp = new HashMap<>();

    private final ArrayList<Person> personAndIDList;
    private ArrayList<Power> allUtilityPowerConsumptionList;
    private  ArrayList<Cost> allUtilityCostsList;
    public static final ArrayList<double[]> TotalutilityCost = new ArrayList<>();
    private final ArrayList<boolean[]> booleanResponses;
    public static final int nameIndex = 1;
    public static final int reportIDIndex= 0;


    // MUST REMOVE WHEN DONE DOING COST CLASS
    public static final int fridgeIndex = 0;
    public static final int microwaveIndex = 1;
    public static final int tvIndex = 2;
    public static final int dishwasherIndex = 3;
    public static final int lightsIndex = 4;
    public static final int washerIndex = 5;
    public static final int dryerIndex = 6;

    public static final HashSet<String> seasonList = new HashSet<>();


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data{");
        sb.append("personAndIDList=").append(personAndIDList).append('\n');
        sb.append("allUtilityPowerConsumptionList=").append(allUtilityPowerConsumptionList).append('\n');

        // Correctly printing booleanResponses
        sb.append("booleanResponses=[");
        for (boolean[] responseArray : booleanResponses) {
            sb.append(Arrays.toString(responseArray)).append(", ");
        }
        if (!booleanResponses.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("]");

        sb.append('}');
        return sb.toString();
    }

    /**
     * Constructs a new Data object with empty lists for storing information.
     */
    public Data() {
        this.personAndIDList = new ArrayList<Person>();
        this.allUtilityPowerConsumptionList = new ArrayList<Power>();
        this.booleanResponses = new ArrayList<>();
        this.allUtilityCostsList = new ArrayList<>();

    }
    //public static String seasonChecker(String month){
        //if (month.equals())
    //}
    //public static int costOfAC() {

        //System.out.println("okay");
        //return 0;
    //}

   /* public static void storePerson(int reportID, String name) {
        personList.put(reportID, name);
        System.out.println(personList);
        nameList.add(name);
        reportIdList.add(reportID);
        System.out.println(nameList);
        System.out.println(reportIdList);

        //return reportID;/

    } */

    // stores the person name and the reportID associated with it into reportIDIndex
    public void storePerson(int reportID, String name) {
        Person newPerson = new Person(name,reportID);
        //Object[] newPerson = new Object[2]; //created a within scope object variable that will contain 2 diff data type values and storing them in an array of lentgh 2
        //newPerson[nameIndex] = name;
        //newPerson[reportIDIndex] = reportID;
        //.add() adds the element into a set of collection (Hashset, ArrayList....) if not already present in the set from before.
        personAndIDList.add(newPerson);
        // .put() adds a key and a value assoicated with it. reportID is key and name is value associated with it
        // .put() is used for HashMaps
        lookUp.put(reportID, name); // lookup puts in key for value inside hashmap
        //nameList.add(reportID, name); //Puts in an array full of names (index = report id)
        reportIdList.add(reportID); //Puts all the report id's inside (loop through those and then use those integers as index to loop through all stored names
        //System.out.println(personAndIDList);
        //System.out.println(lookUp.entrySet());
        //return reportID;

    }
    /**
     * Retrieves the list of people and their IDs.
     * @return A list of containing a Person objects containing name and report ID information.
     */
    public ArrayList<Person> getPersonAndIDList(){
        //basically will return the list storing all the name and ID in there back.
        return personAndIDList;
    }

    //public static ArrayList<String> getPersonName(){
        //return nameList;//name list will consist of names that you can get by index report .get(reportID)
    //}
    //public static ArrayList<Integer> getPersonReportID() {
        //return reportIdList;
    //}

    public static double[] utilityConsumption(double[] allUtilitiesPower) {

        //utilityConsumptionKWh[fridgeIndex] = fridgeKWh;
        //utilityConsumptionKWh[microwaveIndex] = microwaveKWh;
        //utilityConsumptionKWh[tvIndex] = tvKWh;
        //utilityConsumptionKWh[dishwasherIndex] = dishwasherKWh;
        //utilityConsumptionKWh[lightsIndex] = lightKWh;
        //utilityConsumptionKWh[washerIndex] = washerKWh;
        //utilityConsumptionKWh[dryerIndex] = dryerKWh;
        for (double item : allUtilitiesPower) {
            System.out.println(item);
        }
        System.out.println(Arrays.toString(allUtilitiesPower));
        return allUtilitiesPower;
    }

    // stores the individual utiltiy's power in KWh
    /**
     * Stores the monthly utility power consumption for different appliances.
     * @param fridgeKWh The power consumption of the fridge in kWh.
     * @param microwaveKWh The power consumption of the microwave in kWh.
     * @param tvKWh The power consumption of the TV in kWh.
     * @param dishwasherKWh The power consumption of the dishwasher in kWh.
     * @param lightKWh The power consumption of the lights in kWh.
     * @param washerKWh The power consumption of the washer in kWh.
     * @param dryerKWh The power consumption of the dryer in kWh.
     * @param acPower The power consumption of the air conditioner in kWh.
     */
    public void storeUtilityConsumptionPower(double fridgeKWh, double microwaveKWh, double tvKWh, double dishwasherKWh, double lightKWh, double washerKWh, double dryerKWh,double acPower) {

        Power storeUtilitiesPowers = new Power(fridgeKWh,  microwaveKWh,  tvKWh,  dishwasherKWh,  lightKWh,  washerKWh,  dryerKWh, acPower );

        allUtilityPowerConsumptionList.add(storeUtilitiesPowers);

    };
    // takes in the power results from the user and converts how much the power used would cost
    public void storeAllutilityConsumptionCosts(double acCost, double fridgeKWh, double microwaveKWh, double tvKWh, double dishwasherKWh, double lightKWh, double washerKWh, double dryerKWh) {
        //acCost is caslcualted differently than all the other utilities here
        Cost storeUtilitiesCosts = new Cost(acCost, fridgeKWh,  microwaveKWh,  tvKWh,  dishwasherKWh,  lightKWh,  washerKWh,  dryerKWh);

        allUtilityCostsList.add(storeUtilitiesCosts);

    }


    public static void storeUserMonth(String month) {
        monthList.add(month);
        System.out.println(monthList);
    }
    public static ArrayList<String> getUserMonth(){
       // System.out.print("Went through an got user month");
       // System.out.print(monthList);
        return monthList;
    }


    public static void storeUserAC(int reportID, boolean hasAC ) {
        userAc.put(reportID, hasAC);
        userAcResponse.add(hasAC);

        //System.out.println(userAc);
        //System.out.println(userAcResponse);

    }
    public static ArrayList<Boolean> getUserAc(){
        //System.out.print(userAcResponse);
        return userAcResponse;
    }
    //public static void seasonChecker(month){
        //if
    public static void storeUserHeater(int reportId, boolean hasHeater){
        userHeater.put(reportId, hasHeater);
        userHeaterResponse.add(hasHeater);

        System.out.println(userHeater);
        System.out.println(userHeaterResponse);

    }
    public static ArrayList<Boolean> getUserHeater(){
        //System.out.println(userHeaterResponse);
        return userHeaterResponse;

    }
    public static void storeData(boolean[] data) {
        booleanList.add(data);
    }
    public static boolean[] storeAllBooleanResponseGathered(boolean[] allResponsesGathered){
        for (boolean response : allResponsesGathered) {
            // System.out.println(item);
        }
       // System.out.println(Arrays.toString(allResponsesGathered));
        return allResponsesGathered;
    }
    public void storeBooleanResponse(boolean AC, boolean heater, boolean solar) {
        boolean[] responses = { AC, heater, solar};


        booleanResponses.add(responses);
    }
    /**
     * Retrieves the list of boolean responses gathered.
     * @return A list of boolean arrays containing responses.
     */
    public ArrayList<boolean[]> getBooleanResponses(){
        return booleanResponses;
    }

    public static ArrayList<boolean[]> getAllBooleanResponsesGathered() {
        //create a function that calls everything here and call this function to access the data
        return booleanList;
    }
    public static void storeAcCost(double acCost){
        acCostList.add(acCost);
       // System.out.println(acCostList);
    }
    public static ArrayList<Double> getAcCost(){
        //System.out.println(acCostList);
        return acCostList;
    }

    public static void storeHeaterCost(double heaterCost) {
        System.out.print(heaterCostList);
        heaterCostList.add(heaterCost);
    }
    public static ArrayList<Double> getHeaterCost(){
        System.out.print(heaterCostList);
        return heaterCostList;
    }

    public static void storeCurrentSeason(String season) {
        System.out.print("wasnt stored");
        //System.out.print(currentSeasonList);
        currentSeasonList.add(season);

    }
    public static ArrayList<String> getCurrentSeason(){
        //System.out.print("wasn't retrieved");
        //System.out.print(currentSeasonList);
        return currentSeasonList;
    }

    public static double[] getTotalEnergyCost() {
        return new double[0];
    }

    public ArrayList<Power> getallutilityPowerConsumptionList() {
        return this.allUtilityPowerConsumptionList;
    }

    public ArrayList<Cost> getallutilityCostList() {

        return this.allUtilityCostsList;
    }

    public static void storeAcPower(double acPower) {
        acPowerList.add(acPower);
    }

    public static ArrayList<Double> getAcPower(){
        //System.out.println(acCostList);
        return acPowerList;
    }

//stored total power in power class

//stored total costs in cost class



    /*
    public static double getAcCost(){
        //acCostList1
        for (Double aDouble : acCostList) {
            return aDouble;
        }
    }

     */


}


