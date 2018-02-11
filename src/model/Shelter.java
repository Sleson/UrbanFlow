package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shelter {
    private ArrayList<String> dateDelivered = new ArrayList<>();
    private Map<String, Integer> quantityDelivered = new HashMap<>();
    private String address;
    private ArrayList<String> shelterAddresses = new ArrayList<String>();
    private Map<String,String> shelters = new HashMap<String,String>(); //username, password

//    public void addDelivery (String date, Integer quantity){
//        this.dateDelivered.add(date);
//        this.quantityDelivered.add (quantity);
//    }
    public void addQuantityDelivered(String shelter, Integer amount){
        Integer prevAmount = getQuantityDelivered(shelter);
        this.quantityDelivered.put(shelter, prevAmount + amount);
    }
    public Integer getQuantityDelivered(String shelter){
        return this.quantityDelivered.get(shelter);
    }
    public ArrayList<String> shelterDeliveryOrder(){
        return this.shelterAddresses;
    }
//    public Integer getQuantityDeliveredTotal(String username) {
//        int total = 0;
//        for(Integer i: this.quantityDelivered) {
//            total += i;
//        }
//        return total;
//    }
    public String nextReceiverAddress(){
        return this.shelterAddresses.get(0);
    }
    public void moveReceiverAddressToEnd(){
        this.address = shelterAddresses.remove(0);
        this.shelterAddresses.add(address);
    }
    public void addShelter(String username, String password){
        this.shelters.put(username, password);
        this.quantityDelivered.put(username, 0);
    }
    public boolean isShelter(String username) {
        return this.shelters.containsKey(username);
    }
    public boolean correctPassword(String username, String password) {
        return this.shelters.get(username).equals(password);
    }
}
