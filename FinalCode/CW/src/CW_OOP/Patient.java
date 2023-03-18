package CW_OOP;
import java.util.ArrayList;

 public class Patient extends Person {
    private String patientNo;
    private ArrayList<Doctor> doctors;

     public Patient(String firstName, String lastName,String nicNumber, String dateOfBirth, String mobileNo, String patientNo) {
         super(firstName, lastName, nicNumber, dateOfBirth, mobileNo);
         this.patientNo = patientNo;
     }


     public void setPatientNo(String patientNo) {
         this.patientNo = patientNo;
     }
     public String getPatientNo(){
         return this.patientNo;
     }

     public ArrayList<Doctor> getDoctors() {
         return doctors;
     }

     public void setDoctors(ArrayList<Doctor> doctors) {
         this.doctors = doctors;
     }
 }
