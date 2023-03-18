package CW_OOP;
 import java.util.ArrayList;
 import java.util.Objects;
 import java.util.Scanner;

public class ConsultDirect {
    public static ArrayList<Consultation> consultationList = new ArrayList<>();
    public static void addConsultationToDirectory(Consultation consultation){
        consultationList.add(consultation);
    }

    public static Consultation searchConsultationById(int id){
        for (Consultation consult: consultationList) {
            if(id == consult.getConsultationNo()){
                return consult;
            }
        }
        return null;
    }




}
