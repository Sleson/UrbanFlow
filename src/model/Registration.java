package model;

public class Registration {
    private String userName;
    private String userPassword;
    private Boolean isShelter;
    private User User;
    private Shelter shelter;

    public Registration(String user, String password, Boolean isShelter) {
        this.userName = user;
        this.userPassword = password;
        this.isShelter = isShelter;
    }

    //getters
    public String getUserName() {
        return userName;
    }

//    public String getUserPassword() {
//        return userPassword;
//    }

    public Boolean getIsShelter() {
        return isShelter;
    }

    //setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String name) {
        this.userPassword = name;
    }

    public void setIsShelter(Boolean isShelter) {
        this.isShelter = isShelter;
    }

//
//    public void addUserOrShelter() {
//        if (isShelter = true && (!User.isUser(this.userName))) {
//            User.addUser(this.userName,this.userPassword);
//        } else if (isShelter = false && (!Shelter.ShelterList.contains(userName))) {
//            // TODO:
//            Shelter.addShelter(userName);
//        } else System.out.println("Error adding User :(");
//    }
}
