package CW_OOP;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Consultation> consultations = new ArrayList<>();
    static ArrayList<ConsultDirect> consultationCentres = new ArrayList<>();
    static  ArrayList<Doctor> doctors = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {


        WestminsterSkinConsultationManager cm = new WestminsterSkinConsultationManager();
        cm.load(doctors);
        System.out.println(doctors);

        availabilitySet av = new availabilitySet();
        av.testCases();
        av.testCase2();
        av.testCase3();


        Scanner input = new Scanner(System.in);
        String choicestr = " ";
        int choice = 9;

        do {
            System.out.println("1. Add a new doctor in the system");
            System.out.println("2. Delete a doctor from the system");
            System.out.println("3. Print the list of the doctors in the consultation centre");
            System.out.println("4. Save in a file all the information entered by the user so far");
            System.out.println("5. Open GUI");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            choicestr = input.nextLine();
            try {
                choice = Integer.parseInt(choicestr);

            }
            catch(NumberFormatException e){
                System.out.println("Enter integer only");

            }


            switch (choice) {
                case 1:
                    cm.addNewDoctor(doctors);
                    break;
                case 2:
                    cm.deleteDoctor(doctors);
                    break;
                case 3:
                   cm.printDoctorList(doctors);
                    break;
                case 4:
                    cm.save(doctors);
                    break;
                case 5:
                    GUI gui = new GUI();
                    gui.setVisible(true);
                    break;

                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
    }
}