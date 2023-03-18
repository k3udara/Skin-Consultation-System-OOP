package CW_OOP;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Utilities {



    static LocalTime convertingPickerTimeToLocalTime(String pickerTime){
        String[] timeSplit = pickerTime.split(" ");

        String[] hourSplit = timeSplit[0].split(":");
        int pickedHour = Integer.parseInt(hourSplit[0]);
        String pickedMin = hourSplit[1];

        if (timeSplit[1].equals("PM") && pickedHour != 12){
            pickedHour += 12;
        }
        else if (timeSplit[1].equals("AM") && pickedHour == 12){
            pickedHour = 0;
        }
        String pickedSHour = String.valueOf(pickedHour);
        if (pickedHour< 10){
            pickedSHour = "0"+pickedHour;
        }

        final String formattedTime = pickedSHour + ":"+ pickedMin+":"+"00";

        LocalTime time = LocalTime.parse(formattedTime);
        return time;

    }



    //finding the available doctors in doctors list
    static Doctor searchAnyAvailableDoctor(Date bookingDate, LocalTime bookingTime,int hours){
        ArrayList<Doctor> allTheDoctors = Main.doctors;
        for (Doctor doctor: allTheDoctors) {
            boolean availableCheck = doctor.checkAvailability(bookingDate,bookingTime);
            if (availableCheck && doctor.checkSlotAvailability(bookingDate,bookingTime,hours)){
                return doctor;
            }

        }
        return null;
    }

    static void makeConsultation (Doctor doc,Patient patient,Object[] notes,double cost,LocalTime startTime, LocalTime endTime, Date date,int consNo, int hours){
        Consultation newConsultation = new Consultation(date,startTime,endTime,notes,cost,doc,patient,consNo,hours);
        ConsultDirect.addConsultationToDirectory(newConsultation);
    }
    public static Object[] encryptData (byte[] byteData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        Cipher Setup
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        //Initializing a Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        cipher.update(byteData);
        System.out.println(byteData.length);
        byte[] cipherText = cipher.doFinal();
        return new Object[]{cipher,pair,cipherText};
    }
    public static byte[] decryptData (Cipher cipher, KeyPair pair,byte[] cipherText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
        return cipher.doFinal(cipherText);
    }

}

