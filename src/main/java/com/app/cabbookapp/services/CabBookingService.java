package com.app.cabbookapp.services;

import com.app.cabbookapp.pojo.Driver;
import com.app.cabbookapp.pojo.User;
import org.springframework.stereotype.Service;
import com.app.cabbookapp.exceptions.DriverNotFound;
import com.app.cabbookapp.exceptions.NoAvailableRidesException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CabBookingService {
    private List<User> users = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public List<Driver> findRide(String username, int[] source, int[] destination) throws NoAvailableRidesException {
        List<Driver> availableRides = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                double distance = calculateDistance(driver.getCurrentLocation(), source);
                if (distance <= 5) {  // 5 units as the maximum distance for availability
                    availableRides.add(driver);
                }
            }
        }
        if (availableRides.isEmpty()) {
            throw new NoAvailableRidesException("No rides available.");
        }
        return availableRides;
    }


    public synchronized String chooseRide(String username, String driverName) throws DriverNotFound {
        String rideConfirmation = "";
        for (Driver driver : drivers) {
            if (driver.getName().equals(driverName) && driver.isAvailable()) {
                driver.setAvailable(false);
                rideConfirmation = "Ride booked with " + driver.getName();
                break;
            }
        }
        if(rideConfirmation.isEmpty()){
            throw new DriverNotFound("Selected driver is not available");
        }
        return rideConfirmation;
    }

    private double calculateDistance(int[] location1, int[] location2) {
        return Math.sqrt(Math.pow(location1[0] - location2[0], 2) + Math.pow(location1[1] - location2[1], 2));
    }
}
