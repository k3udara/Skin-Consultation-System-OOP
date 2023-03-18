package CW_OOP;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class availabilitySet {
    ArrayList<Doctor>doctors=  Main.doctors;
    Doctor doc1 = doctors.get(0);
    Doctor doc2 = doctors.get(1);
    Doctor doc3 = doctors.get(2);



    public void testCases() throws ParseException {
        Date tempDate = new SimpleDateFormat("dd/MM/yyyy").parse("5/1/2023");
        LocalTime tempStartingTime = LocalTime.of(Integer.parseInt("13"), 0);
        LocalTime tempEndingTime = LocalTime.of(Integer.parseInt("22"),0);
        LocalTime[] tempArray= new LocalTime[2];
        tempArray[0] = tempStartingTime;
        tempArray[1] = tempEndingTime;
        doc1.availability.put(tempDate,tempArray);
    }
    public void testCase2() throws ParseException {
        Date tempDate = new SimpleDateFormat("dd/MM/yyyy").parse("5/1/2023");
        LocalTime tempStartingTime = LocalTime.of(Integer.parseInt("13"), 0);
        LocalTime tempEndingTime = LocalTime.of(Integer.parseInt("22"),0);
        LocalTime[] tempArray= new LocalTime[2];
        tempArray[0] = tempStartingTime;
        tempArray[1] = tempEndingTime;
        doc2.availability.put(tempDate,tempArray);
   }
    public void testCase3() throws ParseException {
        Date tempDate = new SimpleDateFormat("dd/MM/yyyy").parse("7/1/2023");
        LocalTime tempStartingTime = LocalTime.of(Integer.parseInt("13"), 0);
        LocalTime tempEndingTime = LocalTime.of(Integer.parseInt("22"),0);
        LocalTime[] tempArray= new LocalTime[2];
        tempArray[0] = tempStartingTime;
        tempArray[1] = tempEndingTime;
        doc3.availability.put(tempDate,tempArray);
    }

}




