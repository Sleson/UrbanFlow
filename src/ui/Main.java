package ui;

import model.Shelter;
import model.User;

import javax.swing.*;

public class Main {
    private static User user;
    private static Shelter shelter;
    private static Object selectedValue;
    private static String username;
    private static String password;
    private static Integer cupAmount = 0;

    public static void main(String[] args) {
        user = new User();
        shelter = new Shelter();
//        user.readFromFile();
//        user.deserialize();
        // Enter username and Password:
        enterInformation();


    }
    public static void enterInformation(){

        //shelter/User option
        Object[] possibleValues = { "User", "Shelter", "Register"};
        selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        if (selectedValue == null) {
            return;
        }
        if(selectedValue == "User" || selectedValue == "Shelter") {
            // ENTERS USERNAME
            username = JOptionPane.showInputDialog(null,"Please input your username");
            if (username == null) {
                return;
            }
            // ENTERS PASSWORD
            password = JOptionPane.showInputDialog(null,"Please input your password");
            if (password == null) {
                enterInformation();
                return;
            }

            // IF VALID USERNAME FOR SHELTER OR USER:
            if ((selectedValue == "Shelter" &&
                    (!shelter.isShelter(username) || !shelter.correctPassword(username, password))) ||
                    (selectedValue == "User" && (!user.isUser(username) || !user.correctPassword(username, password)))){
                JOptionPane.showMessageDialog(null, "Invalid Username or Password",
                        "alert", JOptionPane.ERROR_MESSAGE);
                    enterInformation();
            }
            // VALID USER USERNAME AND PASSWORD
            else if (selectedValue == "User" && user.isUser(username) && user.correctPassword(username, password)) {
                userOptions();
            }
            // VALID SHELTER USERNAME AND PASSWORD
            else if (selectedValue == "Shelter" && shelter.isShelter(username)
                    && shelter.correctPassword(username, password)) {
                shelterOptions();
            }
        } else {
            register();

        }
    }
    public static void userOptions(){
        Object[] possibleValues = { "Donated Cup(s) for Pickup", "Donate Money", "Pick-Up and Deliver"};
        selectedValue = JOptionPane.showInputDialog(null,
                "Which option do you want:", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        if (selectedValue == null) {
            return;
        } else if (selectedValue == "Donated Cup(s) for Pickup"){
            donatedCups();
        } else if (selectedValue == "Donate Money"){
            JOptionPane.showMessageDialog(null,
                    "This Option has not been set up yet.  Thank you for your patience",
                    "alert", JOptionPane.ERROR_MESSAGE);
            userOptions();
        } else {
            schedulePickup();
        }
    }
    public static void schedulePickup(){
        if(user.listOfPickupAddresses().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No pickups available at this time",
                    "Sorry", JOptionPane.ERROR_MESSAGE);
            userOptions();
        }
        JOptionPane.showMessageDialog(null,
                "Pickup Address: " + user.deliveryAddressPickup() +
                        " Number of Cups: " + user.deliveryPickupAmount() +
                        " Delivery Address: " + shelter.nextReceiverAddress(),
                "Success", JOptionPane.ERROR_MESSAGE);
        user.removePickupAddress();
        shelter.addQuantityDelivered(username, cupAmount);
        shelter.moveReceiverAddressToEnd();
        userOptions();
    }
    public static void donatedCups(){
        String numberOfCups = JOptionPane.showInputDialog(null,
                "How many cups do you have for donation?");
        if (numberOfCups == null) {
            return;
        }
        cupAmount = Integer.parseInt(numberOfCups);
        String address = JOptionPane.showInputDialog(null,
                "Enter Address for Pickup");
        if (address == null) {
            return;
        }
        user.makeDonation(cupAmount, address);
        JOptionPane.showMessageDialog(null,
                "Your pickup has successfully be scheduled" + "\n" +
                        "Your contributions have helped/will " +
                user.donationTotal() + " individuals",
                "Congratulations", JOptionPane.ERROR_MESSAGE);
        unlockedAchievement();
    }
    public static void unlockedAchievement(){
        if (user.donationTotal() == 10) {
            JOptionPane.showMessageDialog(null,
                    "Woo! 10 more people are shark warriors because of you!",
                    "Congratulations", JOptionPane.ERROR_MESSAGE);
        }
        else if (user.donationTotal() == 25){
            JOptionPane.showMessageDialog(null,
                    "You are a graceful rainbow-infused land merperson! Thank you for your 25 donations!",
                    "Congratulations", JOptionPane.ERROR_MESSAGE);
        }
        else if (user.donationTotal() == 50){
            JOptionPane.showMessageDialog(null,
                    "High five, you opalescent majestic koi fish! Keep fighting the good fight!",
                    "Congratulations", JOptionPane.ERROR_MESSAGE);
        }
        else if (user.donationTotal() == 100){
            JOptionPane.showMessageDialog(null,
                    "You have reached Wonder Woman status. Your super uterus pin is on its way!",
                    "Congratulations", JOptionPane.ERROR_MESSAGE);
            // send pin
        }
        userOptions();
    }
    // TODO:
    public static void shelterOptions(){
        JOptionPane.showMessageDialog(null,"Number of deliveries before your shelter: " +
                shelter.shelterDeliveryOrder().indexOf(username) + " \n" +
                "Total cups delivered to your shelter: " + shelter.getQuantityDelivered(username),
                "Statistics", JOptionPane.ERROR_MESSAGE);
    }
    public static void register() {
        username = JOptionPane.showInputDialog(null,"Please input a username");
        if (username == null) {
            return;
        } else if(user.isUser(username) || username.length() < 1){
            JOptionPane.showMessageDialog(null,
                    "Invalid Username or Username already taken",
                    "alert", JOptionPane.ERROR_MESSAGE);
        }

        password = JOptionPane.showInputDialog(null,"Please input a password");
        if (password == null) {
            // Do we want this to return to the main screen?
            return;
        }
        else if(password.length() < 4){
            JOptionPane.showMessageDialog(null,
                    "Password must be at least 4 characters long",
                    "alert", JOptionPane.ERROR_MESSAGE);
        }
//        user.writeToFile();

        Object[] possibleValues = { "User", "Shelter"};
        selectedValue = JOptionPane.showInputDialog(null,
                "Select your purpose", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        if (selectedValue == null) {
            return;
        }
        else if(selectedValue == "User") {
            user.addUser(username, password);
        }
        else {
            shelter.addShelter(username,password);
        }
        username = null;
        password = null;
        enterInformation();
    }
}
