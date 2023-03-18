package CW_OOP;

import java.text.ParseException;
import java.util.ArrayList;

public interface SkinConsultationManager {
    void addNewDoctor(ArrayList<Doctor> doctors) throws ParseException;

    void deleteDoctor(ArrayList<Doctor> doctors);

    void printDoctorList(ArrayList<Doctor> doctors);

    void save(ArrayList<Doctor> doctors);

    //load all the information doctors from the app.txt file and save them to the arraylist doctors
    void load(ArrayList<Doctor> doctors);
}


