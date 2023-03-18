package CW_OOP;
     import java.text.ParseException;
     import java.text.SimpleDateFormat;
     import java.time.LocalDate;
     import java.time.LocalTime; //import the localtime and date

     import java.util.*;

class Doctor extends Person {
    private String licNumber;
    private String specialization;
    private ArrayList<Patient> patients;
    public  HashMap<Date, LocalTime[]> availability = new HashMap<Date, LocalTime[]>();

    public Doctor(String name, String surName, String nicNumber, String dateOfBirth, String mobileNo, String licNumber, String specialization) {
        super(name, surName, nicNumber, dateOfBirth, mobileNo);
        this.licNumber = licNumber;
        this.specialization = specialization;
    }

    public void setLicNumber(String licNumber) {
        this.licNumber = licNumber;
    }

    public String getLicNumber() {
        return licNumber;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }



    public boolean checkAvailability(Date checkDate, LocalTime checkTime){
        for(Date day : availability.keySet()){
            if(day.equals(checkDate)){
                if(availability.get(day)[1].isAfter(checkTime) &&availability.get(day)[0].isBefore(checkTime)){
                    System.out.println(availability.get(day)[0].isAfter(checkTime));
                    System.out.println(availability.get(day)[1].isBefore(checkTime));
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSlotAvailability(Date checkDate, LocalTime checkTime,int hours){
        ArrayList<Consultation>  allConsultations = ConsultDirect.consultationList;
        for (Consultation consultation : allConsultations){
            if (Objects.equals(this.name, consultation.getDoctors().name) && consultation.getTemDate().equals(checkDate)){
                LocalTime endingTime = consultation.getStartTime().plusMinutes(consultation.getHours()*60);
                LocalTime minimumStartTime = consultation.getStartTime().minusMinutes(60*hours);
                if (checkTime.isBefore(endingTime) && checkTime.isAfter(minimumStartTime)){
                    return false;
                }

            }
        }
        return true;
    }

}