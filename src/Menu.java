import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** *
 *
 * @author @hamza.siddiqui  Hamza Siddiqui, @marwah.ahmed@ucalgary.ca Marwah Ahmed
 * @UCID 30183881 , 30180880
 */

/**
 * The Menu class provides an interactive command-line interface for users to input and manage energy consumption data.
 * It offers various options like entering personal details, utility usage, and saving or loading data from a file.
 */
public class Menu {
    private static Data data = new Data();

    private static boolean hasAC = false;
    private static boolean hasHeater = false;
    private static boolean hasSolar = false;

    private static Scanner scanner = new Scanner(System.in);
    private static final String entryMessage = """
            Welcome to the energy tracking program!!
                        
            Home Page
                        
            Choose one of the following options:
            \tPress '1' to start energy tracking program
            \tPress '2' to exit the program
            """;
    private static final String menuOptions = """
            \tMenu Options
            \t1. Enter a new person
            \t2. Enter desired month
            \t3. Enter power consumption for individual utility items
            \t4. Enter AC, heater and Solar usage
            \t5. Print electricity summary
            \t6. Average Energy Comparison   
            \t7. Energy Efficiency Tips
            \t8. Load a file.
            \t9. Save to a file 
            """;
    private static final String continuation = """
            \tPress '1' to continue with energy tracking program
            \tPress '2' to go back to home page 
            """;

    //we add scanner.nextLine() because after using nextInt, it will read user's number but then will automatically go to an empty line (when u press enter) where ther will be no value.
    //this is the buffer line and when u add another nextInt(), it will read this empty line. So in order to not read this empty line, we do scanner.nextLine() to consume this empty/buffer line and store the value to the place where user will enter the number.

    /**
     * Function loops the available menu options, allowing users to choose what option they like until they exit out the program
     */
    public static void menuLoop() {
        System.out.println(entryMessage);
        int optionSelected = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        while (optionSelected != 2) {//While user doesn't equal 2 = exit program continue looping
            System.out.println(menuOptions);
            int inputOption = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (inputOption) {
                case 1:
                    userName();
                    break;

                case 2:
                    usersMonth();
                    break;

                case 3:
                    //calls all the functions and then the value that the fucntions are returning (KWh for each indiviual appliance) will be then stored in Data class as seperate indivual powers that is stored
                    utilityItemsPowerConsumed(fridgePower(), microwavePower(), tvPower(), dishwasherPower(), lightsPower(), washerPower(), dryerPower(),powerOfAC(),costOfAC(powerOfAC()));
                    //Data.utilityConsumption(Menu.utilityItemsPowerConsumed());
                    break;

                //Runs and displays all the boolean values of true and false entered by user
                case 4:
                    data.storeAllBooleanResponseGathered(Menu.allBooleanResponsesGathered());//allBooleanResponsesGathered();
                    break;

                case 5:
                    printElectricitySummary();
                    break;
                case 6:
                    householdComparison();
                    break;
                case 7:
                    budgetingAdvice();
                    break;
                case 8:
                    loadAFile();
                    break;
                case 9:
                    try {
                        Writer writer = new Writer(); // Create an instance of Writer
                        writer.writeDataToFile(data); // Call the method with the 'data' object
                        System.out.println("Data has been successfully saved to the file.");
                    } catch (RuntimeException e) {
                        System.out.println("An error occurred while writing data to the file: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.printf("Option is not recognized: %d\n", inputOption);
            }

            System.out.println(continuation);
            optionSelected = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("Thank you for using this program");
    }

    /**
     * Function asks for user's name, then calls menu function to store name along with declared report ID
     */
    private static void userName() {
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();


        //Code for generating random numbers is from https://stackoverflow.com/questions/32534601/java-getting-a-random-number-from-100-to-999#:~:text=Random%20random%20%3D%20new%20Random()%3B%20int%20randomNumber%20%3D%20random.,randomNumber%20must%20be%20three%20digit.
        // initialize a Random object somewhere; you should only need one
        Random random = new Random();
        // generate a random integer from 0 to 999,
        int reportID = random.nextInt(1000);

        System.out.println("Your name is:\t" + name + "\n\nYour ReportID number is:\t" + reportID + "\n");

        data.storePerson(reportID, name);//Calls .storeperson() with parameters report id and name

    }

    /**
     * Prompts the user to enter a current season based on the month provided and stores the season.
     */
    private static void currentSeason() {
        for (String month : Data.getUserMonth()) {
            if (month.equals("MARCH") || month.equals("APRIL") || month.equals("MAY")) {
                String season = "SPRING";
                System.out.print("may");
                Data.storeCurrentSeason(season);

            } else if (month.equals("JUNE") || month.equals("JULY") || month.equals("AUGUST")) {
                String season = "SUMMER";
                Data.storeCurrentSeason(season);

            } else if (month.equals("SEPTEMBER") || month.equals("OCTOBER") || month.equals("NOVEMBER")) {
                String season = "FALL";
                Data.storeCurrentSeason(season);
            } else if (month.equals("DECEMBER") || month.equals("JANUARY") || month.equals("FEBRUARY")) {
                String season = "WINTER";
                Data.storeCurrentSeason(season);
//return season;
            } else {
                System.out.println("Make sure you entered the right month correctly previously");
            }
        }
    }

    /**
     * Gathers boolean responses from the user about their usage of AC, electric heater, and solar installation.
     * Stores these responses in an array and updates the data object.
     *
     * @return An array of boolean values representing the responses to AC, heater, and solar usage questions.
     */
    public static boolean[] allBooleanResponsesGathered() {
        boolean[] allResponsesGathered = new boolean[3];

        allResponsesGathered[0] = usesAC();
        allResponsesGathered[1] = usesElectricHeater();
        allResponsesGathered[2] = installSolar();

        data.storeAllBooleanResponseGathered(allResponsesGathered);
        return allResponsesGathered;
    }
    /**
     * Asks the user if they have installed solar panels at their house and returns the response.
     *
     * @return A boolean value indicating whether the user has installed solar panels.
     */
    public static boolean installSolar() {
        System.out.println("Please answer the following question with either 'yes' or 'no'");
        System.out.println("Have you installed solar at your house");
        String answer = scanner.nextLine().trim().toUpperCase();
        if (answer.equals("YES")) {
            boolean hasSolar = true;
            // Data.costForAC();
            return hasSolar;

        } else if (answer.equals("NO")) {
            boolean hasSolar = false;
            return hasSolar;

        } else {
            System.out.println("Please enter a valid answer:");
        }
        return false;

    }
    /**
     * Asks the user if they use an electric heater and returns the response.
     *
     * @return A boolean value indicating whether the user uses an electric heater.
     */
    public static boolean usesElectricHeater() {
        System.out.println("Please answer the following question with either 'yes' or 'no'");
        System.out.println("Do you have an electric heater?");
        String answer = scanner.nextLine().trim().toUpperCase();
        if (answer.equals("YES")) {
            boolean hasHeater = true;
            for (Person person : data.getPersonAndIDList()) {
                Data.storeUserHeater(person.getReportID(), hasHeater);
            }
            return hasHeater;
        } else if (answer.equals("NO")) {
            boolean hasHeater = false;
            for (Person person : data.getPersonAndIDList()) {
                Data.storeUserHeater(person.getReportID(), hasHeater);
            }
            return hasHeater;

        } else {
            System.out.println("Please enter a valid answer:");
        }
        return false;
    }

    public static int electricHeaterNum() {
        System.out.println("How many electric heaters do you have:");
        int numOfHeaters = scanner.nextInt();
        System.out.print(numOfHeaters);
        return numOfHeaters;
    }

    ;
    /**
     * Asks the user if they have an AC and returns the response.
     *
     * @return A boolean value indicating whether the user has an AC.
     */
    public static boolean usesAC() {
        System.out.println("Please answer the following question with either 'yes' or 'no'");
        System.out.println("Do you have an AC?");
        String answer = scanner.nextLine().trim().toUpperCase();
        if (answer.equals("YES")) {
            boolean hasAC = true;
            for (Person person : data.getPersonAndIDList()) {
                Data.storeUserAC(person.getReportID(), hasAC);
            }
            return hasAC;

        } else if (answer.equals("NO")) {
            boolean hasAC = false;
            for (Person person : data.getPersonAndIDList()) {
                Data.storeUserAC(person.getReportID(), hasAC);
            }
            return hasAC;

        } else {
            System.out.println("Please enter a valid answer:");
        }
        return false;

        //Data.storeUserAC(name, hasAC);
    }


    private static double fridgePower() {
        //KWh for fridge
        System.out.println("Enter the rated power input of your fridge in Watts:");
        int fridge = scanner.nextInt();
        //formula to convert watts to KWh(unit used for calcualting electrcity prices on enmax)
        // fridge runs approx 8 hrs a day according to google thus 8hrs a day * 30 days in a month = 240 hrs a month
        double fridgeKWh = (fridge * 240) / 1000;
        return fridgeKWh;
    }

    ;

    private static double microwavePower() {

        //KWh for microwave
        System.out.println("Enter the rated power input of your microwave in Watts:");
        int microwave = scanner.nextInt();
        // Microwave runs 0.25 hrs per day * 30 = 7.5 hrs per month
        double microwaveKWh = (microwave * 7.5) / 1000;
        return microwaveKWh;
    }

    ;

    private static double tvPower() {
        //KWh for tv
        System.out.println("Enter the rated power input of your TV in Watts:");
        int tv = scanner.nextInt();
       // System.out.println("How many hours is the TV run per day:");
        double tvHrs = 3;
        // Tv runs tvHrs hrs per day * 30
        double tvKWh = (tv * (tvHrs * 30)) / 1000;
        return tvKWh;
    }

    ;

    private static double dishwasherPower() {
        //KWh for dishwasher
        System.out.println("Enter the rated power input of your dishwasher in Watts:");
        int dishwasher = scanner.nextInt();
        //System.out.println("How many hrs does each dishwashing cycle take:");
        int dishwasherCycleHrs = 2;
        //System.out.println("How many times do you run the dishwasher per week: ");
        int dishwasherRanPerWeek = 5;
        int dishwashermonthlyHrs = ((dishwasherCycleHrs * dishwasherRanPerWeek) * 4);
        // dishwasher runs dishwashermonthlyHrs per month
        double dishwasherKWh = (dishwasher * dishwashermonthlyHrs / 1000);
        return dishwasherKWh;
    }

    ;

    private static double lightsPower() {
        //KWh for light
        System.out.println("Enter the rated power input of your light bulbs in Watts:");
        int lights = scanner.nextInt();
        //System.out.println("How many hrs do most of your lights run per day:");
        int lightHrs = 12;
        double lightKWh = (lights * (lightHrs * 30) / 1000);
        return lightKWh;
    }

    ;

    private static double washerPower() {
        //KWh for washer
        System.out.println("Enter the rated power input of your washer in Watts:");
        int washer = scanner.nextInt();
        //System.out.println("How many hrs does each washing cycle take:");
        int washerHrs = 1;
        //System.out.println("How many times do you use washer per month");
        int washerRanPerMonth = 4;
        ;
        double washerMonthlyHrs = (washerHrs * washerRanPerMonth);
        double washerKWh = (washer * washerMonthlyHrs / 1000);
        return washerKWh;
    }

    ;

    private static double dryerPower() {
        //KWh for dryer
        System.out.println("Enter the rated power input of your dryer in Watts:");
        int dryer = scanner.nextInt();
        //System.out.println("How many hrs does each dryer cycle take:");
        int dryerHrs = 1;
        //System.out.println("How many times do you use dryer per mont");
        int dryerRanPerMonth = 4;
        ;
        double dryerMonthlyHrs = (dryerHrs * dryerRanPerMonth);
        double dryerKWh = (dryer * dryerMonthlyHrs / 1000);
        return dryerKWh;
    }

    ;


    // calls the individual functions and also stores it into data class
    private static double[] utilityItemsPowerConsumed(double fridgeKWh, double microwaveKWh, double tvKWh, double dishwasherKWh, double lightKWh, double washerKWh, double dryerKWh, double acPower, double acCost) {

        //array filled with all the power consumption in KWh from the user
        double[] allUtiliesPower = new double[8];
        allUtiliesPower[0] = fridgeKWh;
        allUtiliesPower[1] = microwaveKWh;
        allUtiliesPower[2] = tvKWh;
        allUtiliesPower[3] = dishwasherKWh;
        allUtiliesPower[4] = lightKWh;
        allUtiliesPower[5] = washerKWh;
        allUtiliesPower[6] = dryerKWh;
        allUtiliesPower[7] = acPower;
        //Data.utilityConsumption(allUtiliesPower);

        data.storeUtilityConsumptionPower(fridgeKWh, microwaveKWh, tvKWh, dishwasherKWh, lightKWh, washerKWh, dryerKWh,acPower);
        data.storeAllutilityConsumptionCosts(fridgeKWh, microwaveKWh, tvKWh, dishwasherKWh, lightKWh, washerKWh, dryerKWh,acCost);
        return allUtiliesPower;
    }

    // asks the user for the month and stores it in the data class
    private static void usersMonth() {
        System.out.println("Which month do you want the energy saving's report");
        String month = scanner.nextLine().toUpperCase();
        System.out.printf("The month of your energy saving's report is %s\n\n", month);
        Data.storeUserMonth(month);

    }

    /*
    private static void userName() {
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        //Code from line 137 to 140 is from https://stackoverflow.com/questions/32534601/java-getting-a-random-number-from-100-to-999#:~:text=Random%20random%20%3D%20new%20Random()%3B%20int%20randomNumber%20%3D%20random.,randomNumber%20must%20be%20three%20digit.
        // initialize a Random object somewhere; you should only need one
        Random random = new Random();
        // generate a random integer from 0 to 999,
        int reportID = random.nextInt(1000);

        System.out.println("Your name is:\t" + name+"\n\nYour ReportID number is:\t" + reportID+"\n");

        Data.storePerson(reportID, name);

    }

     */
    /*
    private static void individualCostPerUtility(){
        double[] utilityCost = Data.getCostArray();
        for(double item: utilityCost){
            System.out.println(item);
        }
    }

     */
     private  static double powerOfAC(){
         currentSeason();
         double acPower = 0;
         for (Boolean hasAcAnswer : Data.getUserAc()) {
             for (String season : Data.getCurrentSeason()) {
                 if (hasAcAnswer) {

                     if (season.equals("SPRING")) {
                         //avg central AC in most homes have 3.5KWh central AC systems
                         //avg num of hrs AC is used in Calgary spring is 0-2 hrs per day according to google
                         acPower = ((3.5 * 2) * 30);
                     } else if (season.equals("SUMMER")) {
                         //avg num of hrs AC is used in Calgary summer is 1-4 hrs per day according to google
                         acPower = ((3.5 * 4) * 30);
                     } else if (season.equals("FALL")) {
                         //avg num of hrs AC is used in Calgary summer is 1 hr per day according to google
                         acPower = ((3.5 * 1) * 30);
                     } else if (season.equals("WINTER")) {
                         //avg num of hrs AC is used in Calgary Winter is 0 hrs per day according to google
                         acPower = ((3.5 * 0) * 30);

                     }

                 }

             }
         }


         return acPower;
     };
    //determines the cost of using the ac depending on the curretn season user has inputted
    private static double costOfAC(double acPower) {
        //acPower is gotten from powerOfAc function
        currentSeason();
        double acCost=0;
        for (Boolean hasAcAnswer : Data.getUserAc()) {
            for (String season : Data.getCurrentSeason()) {
                if (hasAcAnswer) {
                    if (season.equals("SPRING")) {
                        //avg central AC in most homes have 3.5KWh central AC systems
                        //avg num of hrs AC is used in Calgary spring is 0-2 hrs per day according to google
                        //acPower = ((3.5 * 2) * 30);
                        // cost from enamx fixed rate of electrcity at 12.79¢/kWh±. Converts from cents to dollars
                         acCost = (acPower * 12.79) / 100;
                        Data.storeAcCost(acCost);
                        System.out.printf("%-20s\t$%.2f%n", "AC Cost:", acCost);
                        //return acCost;
                    } else if (season.equals("SUMMER")) {
                        //avg num of hrs AC is used in Calgary summer is 1-4 hrs per day according to google
                        //acPower = ((3.5 * 4) * 30);
                         acCost = (acPower * 12.79) / 100;
                        Data.storeAcCost(acCost);
                        System.out.printf("%-20s\t$%.2f%n", "AC Cost:", acCost);
                    } else if (season.equals("FALL")) {
                        //avg num of hrs AC is used in Calgary summer is 1 hr per day according to google
                        //acPower = ((3.5 * 1) * 30);
                         acCost = (acPower * 12.79) / 100;
                        Data.storeAcCost(acCost);
                        System.out.printf("%-20s\t$%.2f%n", "AC Cost:", acCost);
                    } else if (season.equals("WINTER")) {
                        //avg num of hrs AC is used in Calgary Winter is 0 hrs per day according to google
                        //acPower = ((3.5 * 0) * 30);
                         acCost = (acPower * 12.79) / 100;
                        Data.storeAcCost(acCost);
                        System.out.printf("%-20s\t$%.2f%n", "AC Cost:", acCost);

                    }

                    //Data.storeAcPower(acPower);
                }

            }
        }

    return acCost;
    }

    ;
// determines the cost of using heater depending on the season

    private static void costOfHeater(int numOfHeaters) {
        currentSeason();
        for (Boolean hasHeaterAnswer : Data.getUserHeater()) {
            for (String season : Data.getCurrentSeason()) {
                if (hasHeaterAnswer) {
                    if (season.equals("SPRING") || season.equals("SUMMER")) {
                        //avg electric heater in most homes have 1.5 KWh electric heater
                        //avg num of hrs electric heater used in Calgary spring and summer is 0 hrs per day according to google
                        double heaterPower = ((1.5 * 0) * 30);
                        // cost from enmax fixed rate of electrcity at 12.79¢/kWh±. Converts from cents to dollars
                        double heaterCost = ((heaterPower * 12.79) / 100) * numOfHeaters;
                        Data.storeHeaterCost(heaterCost);
                        System.out.printf("%-20s\t$%.2f%n", "Heater Cost:", heaterCost);

                    } else if (season.equals("FALL")) {
                        //avg num of hrs electric heater used in Calgary fall is 2-3 hrs per day according to google
                        double heaterPower = ((1.5 * 2) * 30);
                        // cost from enmax fixed rate of electrcity at 12.79¢/kWh±. Converts from cents to dollars
                        double heaterCost = ((heaterPower * 12.79) / 100) * numOfHeaters;
                        Data.storeHeaterCost(heaterCost);
                        System.out.printf("%-20s\t$%.2f%n", "Heater Cost:", heaterCost);
                    } else if (season.equals("WINTER")) {
                        //avg num of hrs electric heater used in Calgary Winter is 5-10 hrs per day according to google
                        double heaterPower = ((1.5 * 7) * 30);
                        // cost from enmax fixed rate of electricity at 12.79¢/kWh±. Converts from cents to dollars
                        double heaterCost = ((heaterPower * 12.79) / 100) * numOfHeaters;
                        Data.storeHeaterCost(heaterCost);
                        System.out.printf("%-20s\t$%.2f%n", "Heater Cost:", heaterCost);
                    }
                }

            }
        }


    }

    private static double savingsFromSolar(String season, boolean hasSolar) {
        // WILL ELIMINATE DISTRIBUTION COSTS. Distribution charges constitute approximately 33% of a customer's total bill, on average.
        // WILL HAVE TO IMPLEMENT IT ONCE TOTAL COST IT GOTTEN SO JUST REDUCE TOTAL COST BY 33%
        return 0;
    }


    //prints the elctricity summary depneding on the
    private static void printElectricitySummary() {
        ArrayList<Person> personAndIDList = data.getPersonAndIDList();
        //System.out.println(data.getallutilityPowerConsumptionList());
        ArrayList<Power> allUtilityPowerConsumptionList = data.getallutilityPowerConsumptionList();
        ArrayList<Cost> allUtilityCostList = data.getallutilityCostList();
        ArrayList<Double> getAcPower = Data.getAcPower();

        //ArrayList<Integer> reportId = Data.getPersonReportID();
        ArrayList<Double> getAcCost = Data.getAcCost();
        ArrayList<Double> getHeaterCost = Data.getHeaterCost();


        System.out.printf("Summary of your Electricity report: \n");
        System.out.println("----------------------------------------------------------\n");
        //System.out.printf("%-20s\t%-8s%n","Name","Report ID");
        for (Person entry : personAndIDList) {
            //the for each loop enters everything from personAndIDList into entry object array.
            System.out.printf("%-20s\t%-8s%n", "Report ID", entry.getReportID());
            System.out.printf("%-20s\t%-8s%n", "Name", entry.getName());
            System.out.println("");
        }
        System.out.println("--------------\n");
        for (Power powers : allUtilityPowerConsumptionList) {
            System.out.println("Power of utilities in kWh:\n");

            System.out.printf("Fridge: %.1f kWh, ", powers.getFridgePower());
            System.out.printf("Microwave: %.1f kWh, ", powers.getMicrowavePower());
            System.out.printf("TV: %.1f kWh, ", powers.getTVPower());
            System.out.printf("Dishwasher: %.1f kWh, ", powers.getDishwasherPower());
            System.out.printf("Lights: %.1f kWh, ", powers.getLightPower());
            System.out.printf("Washer: %.1f kWh, ", powers.getWasherPower());
            System.out.printf("Dryer: %.1f kWh%n", powers.getDryerPower());
            System.out.printf("AC: %.1f kWh%n", powers.getAcPower());

                //Displays totalPower
            System.out.printf("-> Total power: %.1f KWh %n%n", powers.getTotalPower());
            //System.out.println(powers.toString());
        }
        System.out.println("--------------\n");
        for (Cost costs : allUtilityCostList) {
            System.out.println("Cost of utilities in $:\n");

            System.out.printf("Fridge: %.1f $, ", costs.getFridgeCost());
            System.out.printf("Microwave: %.1f $, ", costs.getMicrowaveCost());
            System.out.printf("TV: %.1f $, ", costs.getTvCost());
            System.out.printf("Dishwasher: %.1f $, ", costs.getDishwasherCost());
            System.out.printf("Lights: %.1f $, ", costs.getLightsCost());
            System.out.printf("Washer: %.1f $, ", costs.getWasherCost());
            System.out.printf("Dryer: %.1f $%n", costs.getDryerCost());
            System.out.printf("AC: %.1f $%n", costs.getAcCost());


            System.out.printf("-> Total cost: %.1f $ %n%n", costs.getTotalCost());
        }

       // System.out.println(Data.getUserMonth());
        //System.out.println(Data.getCurrentSeason());
    }


    public static void householdComparison() {
        ArrayList<Cost> allUtilityCostList = data.getallutilityCostList();
        System.out.println("Enter the report ID you would like for the average comparison");
        int reportId = scanner.nextInt();
        //IDEA WAS TO STORE THE REPORTID AS A KEY AND HAVE THE VALUE BE THE TOTAL COST THAT WAY WHEN WE PROMPT FOR
        //REPORT ID WE CAN SEARCH AND THE TOTAL COST BASED OF THAT PERSON
        for (Cost costs : allUtilityCostList) {
            System.out.printf("Total Energy Cost:" + costs.getTotalCost() + "\n");
            //System.out.print("The average household energy cost: $158.73");
            if (costs.getTotalCost() < 158.73) {
                System.out.println("Wow, great job your total energy cost is below the average energy cost");
            } else if (costs.getTotalCost() == 158.73) {
                System.out.println("Your total energy cost is the same as the average energy cost");
            } else if (costs.getTotalCost() > 158.73) {
                System.out.println("Your total energy cost is now above the average energy cost");
            }
        }
    }


    public static void budgetingAdvice () {
        ArrayList<Cost> allUtilityCostList = data.getallutilityCostList();
        ArrayList<Power> allUtilityPowerConsumptionList = data.getallutilityPowerConsumptionList();

        System.out.println("Were here to help you save money! The following is a list of tips in budgeting");
        for (Power powers : allUtilityPowerConsumptionList) {
              if (powers.getWasherPower() >= 12.77) {
                  System.out.println("- Try using cold water in your washing machines, this way you can save up 75-90% of the energy needed to heat the water");
              }
              if (powers.getDryerPower() >= 13) {
                  System.out.println("- To help reduce the amount of energy your dryer is taking make sure to clean your dryer vents every 12 months");
              }
              if (powers.getFridgePower() > 200) {
                  System.out.println("- Fridge coils and vents should be regularly cleaned otherwise it could slow down the operating system and exert more energy than needed");
              }
              if (powers.getTVPower() >= 4.55) {
                  System.out.println("- Did you know by adjusting your TV's display settings you can reduce its energy consumption about 5-20% ");
              }
              if (powers.getDishwasherPower() >= 20.98) {
                  System.out.println("- Only run full loads when using the dishwasher and be sure to not rinse them before ");
              }
          }
          for (Cost costs : allUtilityCostList) {
              if (costs.getAcCost() > 0) {
                  System.out.println("- Make sure to keep up with regular AC maintenance otherwise it can decrease its efficiency by 5-15%");
              }
          }

    }
    public static void loadAFile(){
        System.out.println("Enter filename:");
        String filename = scanner.nextLine();
        Reader reader = new Reader();
        data = reader.loadFile(filename);
        System.out.println(data);


    }
}



    /*
    private static double costOfAC(String season, boolean hasAC) {

        if (hasAC == true) {
            if (season.equals("SPRING")) {
                //avg central AC in most homes have 3.5KWh central AC systems
                //avg num of hrs AC is used in Calgary spring is 0-2 hrs per day according to google
                double acPower = ((3.5 * 2) * 30);
                // cost from enamx fixed rate of electrcity at 12.79¢/kWh±. Converts from cents to dollars
                double acCost = (acPower * 12.79) / 100;
                Data.storeAcCost(acCost);
                return acCost;
            } else if (season.equals("SUMMER")) {
                //avg num of hrs AC is used in Calgary summer is 1-4 hrs per day according to google
                double acPower = ((3.5 * 4) * 30);
                double acCost = (acPower * 12.79) / 100;
                Data.storeAcCost(acCost);
                return acCost;
            } else if (season.equals("FALL")) {
                //avg num of hrs AC is used in Calgary summer is 1 hr per day according to google
                double acPower = ((3.5 * 1) * 30);
                double acCost = (acPower * 12.79) / 100;
                Data.storeAcCost(acCost);
                return acCost;
            } else if (season.equals("WINTER")) {
                //avg num of hrs AC is used in Calgary Winter is 0 hrs per day according to google
                double acPower = ((3.5 * 0) * 30);
                double acCost = (acPower * 12.79) / 100;
                Data.storeAcCost(acCost);
                return acCost;
            }
        }
        double acCost = 0;
        Data.storeAcCost(acCost);
        return 0;

     */


