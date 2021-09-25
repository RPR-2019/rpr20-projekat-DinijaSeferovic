package ba.unsa.etf.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private double workhours;
    private int roomates;
    private int homework;
    private int yard;
    private int pets;
    private String appointment;
    private int approved;
    private int profileSet;
    private UserDAO user = UserDAO.getInstance();

    public ObservableList<User> users = FXCollections.observableArrayList();

    public User(int id, String name, String username, String email, String password, double workhours, int roomates,
                int homework, int yard, int pets, String appointment, int approved, int profileSet) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.workhours = workhours;
        this.roomates = roomates;
        this.homework = homework;
        this.yard = yard;
        this.pets = pets;
        this.appointment = appointment;
        this.approved = approved;
        this.profileSet = profileSet;
    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public ObservableList getUsers() {
        for (User u: user.getAllUsers()) {
            users.add(u);
        }
        return users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWorkhours(double workhours) {
        this.workhours = workhours;
    }

    public void setRoomates(int roomates) {
        this.roomates = roomates;
    }

    public void setHomework(int homework) {
        this.homework = homework;
    }

    public void setYard(int yard) {
        this.yard = yard;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getWorkhours() {
        return workhours;
    }

    public int getRoomates() {
        return roomates;
    }

    public int getHomework() {
        return homework;
    }

    public int getYard() {
        return yard;
    }

    public int getPets() {
        return pets;
    }

    public String getAppointment() {
        return appointment;
    }

    public int getProfileSet() { return profileSet; }

    public void setProfileSet(int profileSet) { this.profileSet = profileSet; }

    public int getApproved() { return approved; }

    public void setApproved(int approved) { this.approved = approved; }

}
