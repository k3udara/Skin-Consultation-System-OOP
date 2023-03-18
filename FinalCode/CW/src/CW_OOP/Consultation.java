package CW_OOP;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Consultation {
    private Date temDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Object[] notes;
    private double cost;
    private Doctor doctors;
    private Patient patients;
    private int consultationNo;
    private int hours;

    public Consultation(Date temDate, LocalTime startTime, LocalTime endTime, Object[] notes, double cost, Doctor doctors, Patient patients, int consultationNo,int hours) {
        this.temDate = temDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
        this.cost = cost;
        this.doctors = doctors;
        this.patients = patients;
        this.consultationNo = consultationNo;
        this.hours = hours;
    }


    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Date getTemDate() {
        return temDate;
    }

    public void setTemDate(Date temDate) {
        this.temDate = temDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Object[] getNotes() {
        return notes;
    }

    public void setNotes(Object[] notes) {
        this.notes = notes;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Doctor getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctor doctors) {
        this.doctors = doctors;
    }

    public Patient getPatients() {
        return patients;
    }

    public void setPatients(Patient patients) {
        this.patients = patients;
    }

    public int getConsultationNo() {
        return consultationNo;
    }

    public void setConsultationNo(int consultationNo) {
        this.consultationNo = consultationNo;
    }
}