import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Writer {
    /**
     * Writes the given data to a file named "Saver.txt".
     * The data includes personal information, power consumption details, boolean responses, and user-selected months.
     *
     * @param data The data object containing information to be written to the file.
     */

    public void writeDataToFile(Data data) {


        {
            try {
                // Data will be saved in a file called Saver.txt within the repository
                //File will only be created once then is edited on top of it
                BufferedWriter writer = new BufferedWriter(new FileWriter("Saver.txt"));
                //it is good practice to write stuff to file as you go
                //saves username and reportID
                for (Person person : data.getPersonAndIDList()) {
                    writer.write(person.getName() + "\n");
                    writer.write(person.getReportID() + "\n");
                }

                for (Power powers : data.getallutilityPowerConsumptionList()) {
                    writer.write(Double.toString(powers.getFridgePower()) + "," +
                            Double.toString(powers.getMicrowavePower()) + "," +
                            Double.toString(powers.getTVPower()) + "," +
                            Double.toString(powers.getDishwasherPower()) + "," +
                            Double.toString(powers.getLightPower()) + "," +
                            Double.toString(powers.getWasherPower()) + "," +
                            Double.toString(powers.getDryerPower()) + "," +
                            Double.toString(powers.getAcPower()));
                    writer.newLine(); // Move to the next line after writing one record
                    //System.out.println(powers.toString());
                }

                boolean[] responses = data.storeAllBooleanResponseGathered(Menu.allBooleanResponsesGathered());
                //boolean[] responses2 = data.storeBooleanResponse222(boolean hasAc, boolean hasHeater, boolean hasSolar);

                //aapends to StringBuilder the boolean values
                // removes the need for comma to be added after last boolean value is displayed.
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < responses.length; i++) {
                    if (responses[i]) {
                        sb.append("true");
                    } else {
                        sb.append("false");
                    }
                    if (i < responses.length - 1) {
                        sb.append(",");
                    }
                }

                writer.write(sb.toString());
                writer.newLine();


                ArrayList<String> userMonths = Data.getUserMonth();
                for (String month : userMonths) {
                    writer.write(month); // write each month followed by a newline
                    writer.newLine(); // Move to the next line after writing one record

                }



                System.out.println("Inputs have been saved into Saver.txt file within repository");
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
