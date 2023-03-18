package CW_OOP;
import com.raven.swing.TimePicker;
import com.toedter.calendar.JCalendar;
import java.awt.event.ActionListener;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GUI extends JFrame {
    static Container c;
    static JPanel navPanel=new JPanel();
    static JPanel patientDetailPanel = new JPanel();
    static JPanel consultationDetails = new JPanel();
    static JPanel doctorTablePanel = new JPanel();
    static JPanel actionControlPanel = new JPanel();
    static JPanel allConsultationPanel = new JPanel();
    static JPanel consultViewerPanel = new JPanel();


//    selected ROW
    static int selectedDoctorRow;
    static JTable table;
    static JCalendar bookingDate;
    static TimePicker bookingTime;

//    action controller
    static Doctor selectedDocObj;
    static LocalTime bookingTimeLT;
    static Date bookingDateST;

// patient detial
    static JTextField mobileNo;
    static JTextField lName;
    static JTextField fName;
    static JCalendar dateBirth;
    static JTextField addNote;
    static JTextField patientId;
    static JTextField patientNIC;
    static JTextField numberOfConsulations;

    static int consultationStartingNumber= 1000;


//    consultation view
    static String clickedConsultation;
    static JFrame window;

    //////////////////////////////////////////////
    //////////////////////////////////////////////
    public GUI(){

//      FRAME CONTROLS
        setTitle("Jframe for SCS ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        //setting the bounds for the JFrame
        setBounds(100,0,1400,800);
        c= getContentPane();
        c.setBackground(Color.decode("#FFEFD6")); //

////// navigation /////////

        navPanel.setLayout(null);
        navPanel.setBackground(Color.decode("#0E5E6F"));
        navPanel.setBounds(0,0,1400,100);

        //adding a label element to the panel
        JLabel label=new JLabel(" Welcome to Westminster Skin Consultation");
        label.setBounds(40,40,500,20);
        label.setForeground(Color.decode("#F2DEBA"));
        label.setFont(new Font("Poppins",Font.BOLD,20 ));
        navPanel.add(label);



      ///// Adding a patient "Consultation" Button
        JButton consultBtn = new JButton();
        consultBtn.setBounds(750,40,150,40);
        consultBtn.setText("Consultation");
        consultBtn.setForeground(Color.decode("#F2DEBA"));
        consultBtn.setBackground(Color.decode("#3A8891"));
        consultBtn.setBorder(null);
        consultBtn.setOpaque(true);
        consultBtn.setFocusable(false);
        consultBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                doctorViewController(e);
            }
        });
        navPanel.add(consultBtn);


        ///// Adding a patient "All Consultations" Button
        JButton consultationsBtn = new JButton();
        consultationsBtn.setBounds(920,40,150,40);
        consultationsBtn.setText("All Consultations");
        consultationsBtn.setForeground(Color.decode("#F2DEBA"));
        consultationsBtn.setBackground(Color.decode("#3A8891"));
        consultationsBtn.setBorder(null);
        consultationsBtn.setOpaque(true);
        consultationsBtn.setFocusable(false);
        consultationsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allConsultationsContoller(e);
            }
        });
        navPanel.add(consultationsBtn);


        //adding the panel to the Container of the JFrame
        c.add(navPanel,BorderLayout.NORTH);
        c.add(patientDetailPanel,BorderLayout.WEST);
        c.add(consultationDetails,BorderLayout.CENTER);
        c.add(doctorTablePanel,BorderLayout.CENTER);
        c.add(actionControlPanel,BorderLayout.CENTER);
        patientDetailPanel.setVisible(false);

        window = this;
        setVisible(true);

    }


    public static void doctorViewController(ActionEvent actionE) {

        doctorTablePanel = new JPanel();
        c.add(doctorTablePanel);
        doctorTablePanel.setBackground(Color.decode("#FFEFD6"));
        doctorTablePanel.setBounds(100, 100, 1200, 800);
        doctorTablePanel.setLayout(null);


//        panel clearence
        c.remove(actionControlPanel);
        c.remove(consultationDetails);
        c.remove(allConsultationPanel);
        c.remove(patientDetailPanel);
        c.remove(consultViewerPanel);
        window.repaint();



        ArrayList<Doctor>doctors=  Main.doctors;



        //Displaying elements of array after sorting
        System.out.println("Doctors list in Alphabetical order ");

        //sort the array list by name decending order
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        String[] columnNames = {"First Name", "Last Name", "Specialization"};
        Object[][] data = new Object[doctors.size()][3];
        for (int i = 0; i < doctors.size(); i++) {
            data[i][0] = doctors.get(i).getName();
            data[i][1] = doctors.get(i).getSurName();
            data[i][2] = doctors.get(i).getSpecialization();

         JLabel coloumnOneLabel =new JLabel();
         coloumnOneLabel.setBounds(30,40,500,200);
         coloumnOneLabel.setText("First Name");
         coloumnOneLabel.setFont(new Font("Poppins",Font.BOLD,12 ));

         JLabel coloumnTwoLabel =new JLabel();
         coloumnTwoLabel.setBounds(130,40,500,200);
         coloumnTwoLabel.setText("SurName");
         coloumnTwoLabel.setFont(new Font("Poppins",Font.BOLD,12 ));

         JLabel coloumnThreeLabel =new JLabel();
         coloumnThreeLabel.setBounds(230,40,500,200);
         coloumnThreeLabel.setText("Specialization");
         coloumnThreeLabel.setFont(new Font("Poppins",Font.BOLD,12 ));
         doctorTablePanel.add(coloumnOneLabel);
         doctorTablePanel.add(coloumnTwoLabel);
         doctorTablePanel.add(coloumnThreeLabel);



        }

        table = new JTable(data, columnNames);
        table.setBounds(30, 150, 350, 300);
        table.setRowHeight(20);
        table.setForeground(Color.decode("#f7f7f7"));
        table.setBackground(Color.decode("#F08080"));
        table.setShowVerticalLines(false);
        table.setGridColor(Color.decode("#9575cd"));
        table.setBorder(new EmptyBorder(3,15,3,3));
        table.setFont(new Font("Poppins",Font.PLAIN,14));

//        selection
        ListSelectionModel select = table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedDoctorRow = table.getSelectedRow();
                System.out.println(selectedDoctorRow);
            }
        });



        JButton nextbtn = new JButton();
        JLabel bookingDateLabel =new JLabel();
        JLabel numberOfConsulationsLabel = new JLabel();

        JButton okay = new JButton();
        okay.setBounds(250, 600, 250, 30);


        // number of consulations booked
        numberOfConsulations = new JTextField();
        numberOfConsulations.setBounds(100, 530, 250, 30);
        numberOfConsulations.setBorder(new EmptyBorder(3, 15, 3, 3));
        numberOfConsulations.setFont(new Font("Poppins", Font.PLAIN, 12));

        numberOfConsulationsLabel.setBounds(100, 560, 150, 30);
        numberOfConsulationsLabel.setText(" Number of hours: ");


        okay.setText("Check Availability");
        okay.setForeground(Color.decode("#ede7f6"));
        okay.setBackground(Color.decode("#F08080"));
        okay.setBorder(null);
        okay.setOpaque(true);
        okay.setFocusable(false);
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                bookingDateLabel.setBounds(560,40, 550, 30);
                bookingDateLabel.setText("Select a Date and Time");
                bookingDateLabel.setFont(new Font("Poppins",Font.BOLD,23 ));

                bookingDate = new JCalendar();
                bookingDate.setBounds(560,80,250, 220);

                bookingTime = new TimePicker();
                bookingTime.setBounds(560,300, 250,600);
                doctorTablePanel.add(bookingDate);
                doctorTablePanel.add(bookingTime);




//                add button
                nextbtn.setBounds(950, 530, 200, 30);
                nextbtn.setText("NEXT");
                nextbtn.setForeground(Color.decode("#ede7f6"));
                nextbtn.setBackground(Color.decode("#F08080"));
                nextbtn.setBorder(null);
                nextbtn.setOpaque(true);
                nextbtn.setFocusable(false);
                nextbtn.addActionListener(GUI::actionController);
                doctorTablePanel.remove(okay);

            }
        });

                doctorTablePanel.add(table);
                doctorTablePanel.add(numberOfConsulations);
                doctorTablePanel.add(numberOfConsulationsLabel);
                window.remove(doctorTablePanel);
                window.repaint();
                window.add(doctorTablePanel);
                window.validate();
                doctorTablePanel.add(nextbtn);
                doctorTablePanel.add(bookingDateLabel);
                doctorTablePanel.add(okay);

    }

    public static void actionController(ActionEvent actionE) {

        //clear pannelling

        actionControlPanel = new JPanel();
        c.add(actionControlPanel);

        //  panel clearence
        c.remove(doctorTablePanel);
        c.remove(consultationDetails);
        c.remove(allConsultationPanel);
        c.remove(patientDetailPanel);
        c.remove(consultViewerPanel);



        //  pannel settings
        actionControlPanel.setBackground(Color.decode("#FFEFD6"));
        actionControlPanel.setBounds(100, 100, 1200, 700);
        actionControlPanel.setLayout(null);

        doctorTablePanel.setVisible(false);
        consultationDetails.setVisible(false);

         // select doctors
        ArrayList<Doctor> doctors = Main.doctors;
        //  select the doctor
        String docName = (String) table.getValueAt(selectedDoctorRow,0);
        System.out.println(docName);
        Doctor selectedDoc = WestminsterSkinConsultationManager.findDoctor(docName);
        assert selectedDoc != null;
        selectedDocObj = selectedDoc;
        System.out.println(selectedDocObj.name);

        // take the user time and date
        String sDate1= bookingDate.getDayChooser().getDay()  + "/"+ (bookingDate.getMonthChooser().getMonth()+1)+ "/"+ bookingDate.getYearChooser().getYear();
        String pickerTime = bookingTime.getSelectedTime();
        bookingTimeLT = Utilities.convertingPickerTimeToLocalTime(pickerTime);

        try {
            bookingDateST=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            int val = Integer.parseInt(numberOfConsulations.getText()) ;

        } catch (Exception e) {
            numberOfConsulations.setText("1");
        }


        //   check the doctors availability
        boolean docAvailablity = selectedDoc.checkAvailability(bookingDateST,bookingTimeLT);
        boolean slotAvilability = selectedDoc.checkSlotAvailability(bookingDateST,bookingTimeLT,Integer.parseInt(numberOfConsulations.getText()));
        System.out.println("availability : " + docAvailablity);

        JLabel showDocNameLabel = new JLabel();
        showDocNameLabel.setBounds(50, 210, 1500, 100);
        showDocNameLabel.setFont(new Font("Poppins", Font.PLAIN, 30));

        JButton continuebtn = new JButton();
        JButton backbtn = new JButton();

         // confirm availability
        if(docAvailablity && slotAvilability){
            showDocNameLabel.setText("Your doctor " + selectedDocObj.name  + " is available.");

            continuebtn.setBounds(700, 530, 200, 24);
            continuebtn.setText("NEXT");
            continuebtn.setForeground(Color.decode("#ede7f6"));
            continuebtn.setBackground(Color.decode("#F08080"));
            continuebtn.setBorder(null);
            continuebtn.setOpaque(true);
            continuebtn.setFocusable(false);
            continuebtn.addActionListener(GUI::patientDetailController);


        }
        else{
            Doctor availableDoctor = Utilities.searchAnyAvailableDoctor(bookingDateST,bookingTimeLT,Integer.parseInt(numberOfConsulations.getText()));

            if (availableDoctor !=null && availableDoctor.checkSlotAvailability(bookingDateST, bookingTimeLT,Integer.parseInt(numberOfConsulations.getText()))){

                showDocNameLabel.setText("Your doctor " + selectedDocObj.name  + " is not available. Instead Assigned to Dr."+ availableDoctor.name);
                selectedDocObj = availableDoctor;
                continuebtn.setBounds(700, 530, 100, 24);
                continuebtn.setText("NEXT");
                continuebtn.setForeground(Color.decode("#ede7f6"));
                continuebtn.setBackground(Color.decode("#F08080"));
                continuebtn.setBorder(null);
                continuebtn.setOpaque(true);
                continuebtn.setFocusable(false);
                continuebtn.addActionListener(GUI::patientDetailController);


            }
            else{
                showDocNameLabel.setText("None of the doctors are available");
                backbtn.setBounds(50, 530, 100, 24);
                backbtn.setText("Back");
                backbtn.setForeground(Color.decode("#ede7f6"));
                backbtn.setBackground(Color.decode("#F08080"));
                backbtn.setBorder(null);
                backbtn.setOpaque(true);
                backbtn.setFocusable(false);
                backbtn.addActionListener(GUI::doctorViewController);
            }


        }


        JLabel chckAvlbLabel = new JLabel();
        chckAvlbLabel.setBounds(70, 50, 350, 100);
        chckAvlbLabel.setText(" Availability");
        chckAvlbLabel.setFont(new Font("Poppins", Font.BOLD, 20));


        actionControlPanel.add(chckAvlbLabel);
        actionControlPanel.add(showDocNameLabel);
        actionControlPanel.add(continuebtn);
        actionControlPanel.add(backbtn);


    }

    private static void patientDetailController(ActionEvent actionEvent) {

        patientDetailPanel = new JPanel();
        c.add(patientDetailPanel);
        patientDetailPanel.setBackground(Color.decode("#3A8891"));
        patientDetailPanel.setBounds(100, 100, 1200, 700);
        patientDetailPanel.setLayout(null);
        patientDetailPanel.setVisible(true);

        doctorTablePanel.setVisible(false);
        actionControlPanel.setVisible(false);


        //        panel clearence
        c.remove(actionControlPanel);
        c.remove(consultationDetails);
        c.remove(allConsultationPanel);
        c.remove(doctorTablePanel);
        c.remove(consultViewerPanel);
        c.repaint();



        /// first Name  ///
        fName = new JTextField();
        fName.setBounds(250, 50, 250, 30);
        fName.setBorder(new EmptyBorder(3, 15, 3, 3));
        fName.setFont(new Font("Poppins", Font.PLAIN, 12));
        JLabel fNameLabel = new JLabel();
        fNameLabel.setBounds(100, 50, 150, 30);
        fNameLabel.setText("First Name: ");

        //// last name ///
        lName = new JTextField();
        lName.setBounds(250, 100, 250, 30);
        lName.setBorder(new EmptyBorder(3, 15, 3, 3));
        lName.setFont(new Font("Poppins", Font.PLAIN, 12));
        JLabel lNameLabel = new JLabel();
        lNameLabel.setBounds(100, 100, 150, 30);
        lNameLabel.setText("Last Name : ");

        /// Telephone number  ///
        mobileNo = new JTextField();
        mobileNo.setBounds(250, 150, 250, 30);
        mobileNo.setBorder(new EmptyBorder(3, 15, 3, 3));
        mobileNo.setFont(new Font("Poppins", Font.PLAIN, 12));
        JLabel mobileNoLabel = new JLabel();
        mobileNoLabel.setBounds(100, 150, 150, 30);
        mobileNoLabel.setText(" Mobile No : ");


        /// patient id Number
        patientId = new JTextField();
        patientId.setBounds(250, 200, 250, 30);
        patientId.setBorder(new EmptyBorder(3, 15, 3, 3));
        patientId.setFont(new Font("Poppins", Font.PLAIN, 12));
        JLabel patientIdLabel = new JLabel();
        patientIdLabel.setBounds(100, 200, 150, 30);
        patientIdLabel.setText(" Patient NO: ");

        /// patient NIC Number
        patientNIC = new JTextField();
        patientNIC.setBounds(250, 250, 250, 30);
        patientNIC.setBorder(new EmptyBorder(3, 15, 3, 3));
        patientNIC.setFont(new Font("Poppins", Font.PLAIN, 12));
        JLabel patientNICLabel = new JLabel();
        patientNICLabel.setBounds(100, 250, 150, 30);
        patientNICLabel.setText(" Patient NIC NO: ");



        // addNote
        addNote = new JTextField();
        addNote.setBounds(750, 100, 250, 100);
        addNote.setBorder(new EmptyBorder(3, 15, 3, 3));
        addNote.setFont(new Font("Poppins", Font.PLAIN, 12));
        JLabel addNoteLabel = new JLabel();
        addNoteLabel.setBounds(600, 100, 150, 100);
        addNoteLabel.setText(" Additional Notes: ");

        ////  date of birth ///
        dateBirth = new JCalendar();
        dateBirth.setBounds(250, 350, 250, 200);
        JLabel dateBirthLabel = new JLabel();
        dateBirthLabel.setBounds(100, 350, 150, 30);
        dateBirthLabel.setText("Date of Birth");

        JButton saving = new JButton();
        saving.setBounds(100, 580, 150, 24);
        saving.setText("Book the Consultation");
        saving.setForeground(Color.decode("#ede7f6"));
        saving.setBackground(Color.decode("#673ab7"));
        saving.setBorder(null);
        saving.setOpaque(true);
        saving.setFocusable(false);

        saving.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checkAvailability(Doctor,Patient);
                try {
                    bookingControllor(e);
                } catch (NoSuchPaddingException ex) {
                    ex.printStackTrace();
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                }
            }
        });

        patientDetailPanel.add(fName);
        patientDetailPanel.add(fNameLabel);
        patientDetailPanel.add(lName);
        patientDetailPanel.add(lNameLabel);
        patientDetailPanel.add(mobileNo);
        patientDetailPanel.add(mobileNoLabel);
        patientDetailPanel.add(patientId);
        patientDetailPanel.add(patientIdLabel);
        patientDetailPanel.add(dateBirth);
        patientDetailPanel.add(dateBirthLabel);
        patientDetailPanel.add(saving);
        patientDetailPanel.add(addNote);
        patientDetailPanel.add(addNoteLabel);
        patientDetailPanel.add(patientNICLabel);
        patientDetailPanel.add(patientNIC);



    }
    private static void bookingControllor( ActionEvent actionEvent) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

            consultationDetails = new JPanel();
            c.add(consultationDetails);
            consultationDetails.setBackground(Color.decode("#3A8891"));
            consultationDetails.setBounds(100, 150, 1000, 620);
            consultationDetails.setLayout(null);
            consultationDetails.setVisible(true);


        //        panel clearence
        c.remove(actionControlPanel);
        c.remove(allConsultationPanel);
        c.remove(doctorTablePanel);
        c.remove(consultViewerPanel);
        c.remove(patientDetailPanel);
        window.repaint();

//            get patient details
            String birthdate = dateBirth.getDayChooser().getDay()  + "/"+ (dateBirth.getMonthChooser().getMonth()+1)+ "/"+ dateBirth.getYearChooser().getYear();
            Patient newPatient = new Patient(fName.getText(),lName.getText(),patientNIC.getText(),birthdate,mobileNo.getText(),patientId.getText());
            Doctor newDoctor = selectedDocObj;
            //System.out.println("NIC " + patientNIC.getText());
            double cost_consulataion = 25;
            double first_consultation= 15;
            double totalCost = 15;
            try {
                totalCost=(Integer.parseInt(numberOfConsulations.getText())-1)*cost_consulataion + first_consultation;
                System.out.println("Total cost: £ " + totalCost);
            } catch (NumberFormatException n){
                totalCost =15;
             }

            consultationStartingNumber += 5;

//          perform encryption
            String notes = addNote.getText();
            byte[] noteEncrypt = notes.getBytes(StandardCharsets.UTF_8);
            Object[] encryptedData = Utilities.encryptData(noteEncrypt);

            Utilities.makeConsultation(newDoctor,newPatient,encryptedData,totalCost,bookingTimeLT,bookingTimeLT,bookingDateST,consultationStartingNumber,Integer.parseInt(numberOfConsulations.getText()));

            JLabel nameLabel = new JLabel();
            nameLabel.setText("Patient Name: " +fName.getText() +" " + lName.getText());
            nameLabel.setBounds(200,200,400,40);

            JLabel doctorLabel = new JLabel();
            doctorLabel.setText("Doctors name: "+ newDoctor.name);
            doctorLabel.setBounds(200,240,400,40);

            JLabel costLabel= new JLabel();
            costLabel.setText(" Consulatation cost : " + String.valueOf(totalCost));
            costLabel.setBounds(200,280,400, 40);

            JLabel bookedTime= new JLabel();
            bookedTime.setText("Consultation Time : "+ bookingTimeLT.getHour()+ " : " + bookingTimeLT.getMinute());
            bookedTime.setBounds(200,320,400, 40);

            JLabel bookedDate= new JLabel();
            bookedDate.setText("Consulataion Date: "+ String.valueOf(bookingDateST));
            bookedDate.setBounds(200,360,400, 40);

            JLabel patienNic= new JLabel();
            patienNic.setText("Patient NIC: " + patientNIC.getText());
            patienNic.setBounds(200,400,400, 40);

            JLabel mobileNoLabel= new JLabel();
            patienNic.setText("Mobile no: " + mobileNo.getText());
            patienNic.setBounds(200,430,400, 40);

            JLabel patientNoLabel= new JLabel();
            patientNoLabel.setText("Patient No: " + patientId.getText());
            patientNoLabel.setBounds(200,400,400, 40);




            consultationDetails.add(nameLabel);
            consultationDetails.add(doctorLabel);
            consultationDetails.add(costLabel);
            consultationDetails.add(patienNic);
            consultationDetails.add(bookedTime);
            consultationDetails.add(bookedDate);
            consultationDetails.add(mobileNoLabel);
            consultationDetails.add(patientNoLabel);






        }
    private static void allConsultationsContoller(ActionEvent actionEvent)  {

//
        allConsultationPanel = new JPanel();
        c.add(allConsultationPanel);
        allConsultationPanel.setBounds(100,100,1400,800);
        allConsultationPanel.setBackground(Color.decode("#3A8891"));

//        panel clearence
        c.remove(patientDetailPanel);
        c.remove(consultationDetails);
        c.remove(doctorTablePanel);
        c.remove(actionControlPanel);
        c.remove(consultViewerPanel);
        window.repaint();


//        get all consultations
        ArrayList<Consultation> allConsultation = ConsultDirect.consultationList;
        String[][] consultationsArr = new String[allConsultation.size()][5];
        for (int i = 0; i < allConsultation.size(); i++) {
            Consultation cons = allConsultation.get(i);
            String[] dataArray = {cons.getPatients().name,cons.getDoctors().name,cons.getStartTime().toString(),cons.getTemDate().toString(),String.valueOf(cons.getConsultationNo()) };
            consultationsArr[i] = dataArray;
        }
        String[] Columns = {"Patient Name","Doctors Name","Time","Date","Consultation No"};
        JTable consultationTable = new JTable(consultationsArr,Columns);
        consultationTable.setBounds(100,100,1000,200);
        ListSelectionModel select = consultationTable.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedConsultationRow = consultationTable.getSelectedRow();
                clickedConsultation = (String) consultationTable.getValueAt(selectedConsultationRow,4);
                System.out.println(clickedConsultation);

                try {
                    viewClickedConsultationController(e);
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                }
            }
        });
        allConsultationPanel.add(consultationTable);


    }

    private static void viewClickedConsultationController(ListSelectionEvent e) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        consultViewerPanel = new JPanel();
        c.add(consultViewerPanel);

        consultViewerPanel.setBounds(100,100,1400,600);

//        clearence
        c.remove(allConsultationPanel);
        c.remove(actionControlPanel);
        c.remove(consultationDetails);
        c.remove(allConsultationPanel);
        c.remove(doctorTablePanel);
        window.repaint();



        Consultation clickedConObj = ConsultDirect.searchConsultationById(Integer.parseInt(clickedConsultation));
        if (clickedConObj !=null){

            Patient patient = clickedConObj.getPatients();
            Doctor doctor = clickedConObj.getDoctors();

            //        panel components
            JLabel firstNameLabel = new JLabel();
            firstNameLabel.setBounds(100,100,300,30);
            firstNameLabel.setText("First Name: "+ patient.name );

            JLabel lastNameLabel = new JLabel();
            lastNameLabel.setBounds(100,135,300,30);
            lastNameLabel.setText("Last Name: " +patient.getSurName());

            JLabel timeLabel = new JLabel();
            timeLabel.setBounds(300,135,300,30);
            timeLabel.setText("Booking Time: " + clickedConObj.getStartTime().getHour() + ":" + clickedConObj.getStartTime().getMinute());

            JLabel dayLabel = new JLabel();
            dayLabel.setBounds(300,155,300,30);
            dayLabel.setText("Booking Date: " + clickedConObj.getTemDate());

            JLabel patientIdLabel = new JLabel("Patient NO: " + patient.getPatientNo());
            patientIdLabel.setBounds(100,170,300,30);

            JLabel phoneNumberLabel = new JLabel();
            phoneNumberLabel.setBounds(100,210,300,30);
            phoneNumberLabel.setText("Mobile Number: " + patient.getMobileNo());

            JLabel feeLabel = new JLabel();
            feeLabel.setBounds(100,276,500,30);
            feeLabel.setText(String.valueOf("Cost: " + clickedConObj.getCost()));
//
//            Decrypt data
            Object[] encryptedData = clickedConObj.getNotes();
            byte[] decryptBytes = Utilities.decryptData((Cipher) encryptedData[0],(KeyPair) encryptedData[1], (byte[]) encryptedData[2]);
            String NoteString = new String(decryptBytes, StandardCharsets.UTF_8);

            JLabel notesLabel = new JLabel();
            notesLabel.setBounds(100,296,700,40);
            notesLabel.setText("Addional Notes: \n" + NoteString);


//            consultViewerPanel.add(back);
            consultViewerPanel.add(phoneNumberLabel);
            consultViewerPanel.add(patientIdLabel);
            consultViewerPanel.add(lastNameLabel);
            consultViewerPanel.add(firstNameLabel);
            consultViewerPanel.add(notesLabel);
            consultViewerPanel.add(feeLabel);
            consultViewerPanel.add(timeLabel);
            consultViewerPanel.add(dayLabel);




        }

    }


}






