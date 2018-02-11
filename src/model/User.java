package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class User {
    private Map<String, String> validUsers = new HashMap<String,String>();
    private int DonationTotal;
    private int currDonation; //gets sent to delviery person so they know how much to carry
    private ArrayList<String> listOfPickupAddresses = new ArrayList<String>();
    private Map<String,Integer> listOfPickups = new HashMap<String, Integer>();
    Properties properties = new Properties();

    public void addUser(String username, String password) {
        this.validUsers.put(username, password);
        this.currDonation = 0;
        this.DonationTotal = 0;
    }
//    public void writeToFile() {
//        try {
//            FileOutputStream fileOut =
//                    new FileOutputStream("/tmp/fileout.ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            for (Map.Entry<String,String> e: this.validUsers.entrySet()) {
//                out.writeObject(e);
//            }
//            out.close();
//            fileOut.close();
////            System.out.printf("Written to file");
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
//    }

//    public void readFromFile(){
//        try{
//            File toRead=new File("/tmp/fileout.ser");
//            FileInputStream fis=new FileInputStream(toRead);
//            Scanner sc=new Scanner(fis);
//            HashMap<String,String> mapInFile=new HashMap<String,String>();
//
//            //read data from file line by line:
//            String currentLine;
//            while(sc.hasNextLine()){
//                currentLine=sc.nextLine();
//                //now tokenize the currentLine:
//                StringTokenizer st=new StringTokenizer(currentLine,"=",false);
//                //put tokens ot currentLine in map
//                mapInFile.put(st.nextToken(),st.nextToken());
//            }
//            fis.close();
//        }catch(Exception e){}
//    }

    public boolean isUser(String username) {
        return validUsers.containsKey(username);
    }
    public boolean correctPassword(String username, String password) {
        return (this.validUsers.get(username).equals(password));
    }
    public ArrayList<String> listOfPickupAddresses(){
        return listOfPickupAddresses;
    }

    public int donationTotal(){
        return this.DonationTotal;
    }

    public void makeDonation(int amount, String address){
        this.DonationTotal += amount;
        this.currDonation = amount;
        listOfPickupAddresses.add(address);
        listOfPickups.put(address, amount);
    }

    public String deliveryAddressPickup(){
        return listOfPickupAddresses.get(0);
    }
    public Integer deliveryPickupAmount(){
        String address = listOfPickupAddresses.get(0);
        return listOfPickups.get(address);
    }
    public void removePickupAddress(){
        String address = listOfPickupAddresses.remove(0);
        listOfPickups.remove(address);
    }
}
