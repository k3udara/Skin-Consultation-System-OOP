package CW_OOP;

import javax.swing.text.DateFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

class WestminsterSkinConsultationManager implements SkinConsultationManager{


    public static Doctor findDoctor(String name){

        for (int i = 0; i < Main.doctors.size(); i++) {
            if(Main.doctors.get(i).name.toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))){
                return Main.doctors.get(i);

            }
        }
        return null;
    }

    @Override

    public void addNewDoctor(ArrayList<Doctor> doctors){
        if (doctors.size() < 10) { //Add a new doctor to the program
        System.out.println("\n\n\t\t<<---Add New Doctor--->>");

        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter Doctor Name\t\t\t:"); //Get doctor name
        String name = input.next().toUpperCase();


        System.out.print("Enter Doctor surName\t\t:"); //Get surName
        String surName = input.next().toUpperCase();

        System.out.print("Enter Doctor lic number\t\t:"); //Get lic number
            String licNumber = input.next();

        boolean dobCheck = true;
        String DOB = "";
        while (dobCheck){
            System.out.print("Enter Doctor dateOfBirth\t\t:"); //Get dateOfBirth
            System.out.println("ex: 12/02/2021");
            DOB = input.next();
            try {
                Date d = new SimpleDateFormat("dd/MM/yyyy").parse(DOB);
                dobCheck= false;
            }catch (Exception e){

            }

        }


        System.out.println("Enter Doctor NIC Number\t\t: "); //Get NIC number
        String nicNumber = input.next();

        System.out.print("Enter Doctor mobileNo\t\t:"); //Get mobileNo
        String mobileNo = input.next();
        System.out.print("Enter Doctor specialization\t\t:");
        String specialization = input.next();

        Doctor dc = new Doctor(name,surName,nicNumber, DOB,mobileNo,licNumber,specialization);
        doctors.add(dc);
            System.out.println("Doctor Added Successfully");
        } else {
            System.out.println("Maximum number of doctors reached");
        }

    }

    @Override
    public void deleteDoctor(ArrayList<Doctor> doctors) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Doctor Medical Licence Number: ");
        String medicalLicenceNo = input.nextLine();
        for (Doctor doctor : doctors) {
            if (doctor.getLicNumber().equals(medicalLicenceNo)) {
                doctors.remove(doctor);
                System.out.println("Doctor Deleted Successfully."+ " "+doctors.size()+ " " + "doctors in the list right now");
                break;
            }
        }
    }


   @Override
   public void printDoctorList(ArrayList<Doctor> doctors) {
       doctors.sort((o1, o2) -> o1.getSurName().compareTo(o2.getSurName()));
       for (Doctor doctor : doctors) {
           System.out.println("Doctor Name: " + doctor.getName());
           System.out.println("Doctor Surname: " + doctor.getSurName());
           System.out.println("Doctor Medical Licence Number: " + doctor.getLicNumber());
           System.out.println("Doctor Date of Birth: " + doctor.getDateOfBirth());
           System.out.println("Doctor Mobile Number: " + doctor.getMobileNo());
           System.out.println("Doctor Speciality: " + doctor.getSpecialization());
           System.out.println("Doctor Patients: " + doctor.getPatients());
           System.out.println("-------------------------------------------------");
       }
   }

    //save all the information doctors to the app.txt file
    @Override
    public void save(ArrayList<Doctor> doctors) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Consultation.txt"));
            for (Doctor doctor : doctors) {
                writer.write(doctor.getName() + "," + doctor.getSurName() + "," + doctor.getNicNumber() + "," + doctor.getDateOfBirth() + "," + doctor.getMobileNo() + ","  + doctor.getLicNumber()+ ","+ doctor.getSpecialization());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //load all the information doctors from the consultation.txt file and save them to the arraylist doctors
    @Override
    public void load(ArrayList<Doctor> doctors) {
        try {

            Scanner input = new Scanner(new java.io.File("Consultation.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] data = line.split(",");
                Doctor doctor = new Doctor(data[0], data[1], data[2], data[3], data[4], data[5],data[6]);
                System.out.println(doctor.name);
                doctors.add(doctor);
            }
            input.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

