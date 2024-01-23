package com.app.cabbookapp.pojo;

public class Driver {
    private String name;
    private String gender;
    private int age;
    private String vehicleDetails;
    private int[] currentLocation;
    private boolean available;

    public Driver(String name, String gender, int age, String vehicleDetails, int[] currentLocation) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.vehicleDetails = vehicleDetails;
        this.currentLocation = currentLocation;
        this.available = true;
    }

    public String getName() {

        return this.name;
    }

    public int[] getCurrentLocation(){
        return this.currentLocation;
    }
    public boolean isAvailable() {
        if(this.available) {
            return true;
        }
        return false;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
