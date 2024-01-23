package com.app.cabbookapp.controller;

import com.app.cabbookapp.exceptions.DriverNotFound;
import com.app.cabbookapp.exceptions.NoAvailableRidesException;
import com.app.cabbookapp.services.CabBookingService;
import com.app.cabbookapp.pojo.Driver;
import com.app.cabbookapp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cab")
public class CabBookingController {
    private final CabBookingService cabBookingService;

    @Autowired
    public CabBookingController(CabBookingService cabBookingService) {
        this.cabBookingService = cabBookingService;
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        cabBookingService.addUser(user);
    }

    @PostMapping("/addDriver")
    public void addDriver(@RequestBody Driver driver) {
        cabBookingService.addDriver(driver);
    }

    @GetMapping("/findRide")
    public List<Driver> findRide(@RequestParam String username,
                                 @RequestParam int[] source,
                                 @RequestParam int[] destination) throws NoAvailableRidesException {
        return cabBookingService.findRide(username, source, destination);
    }

    @PostMapping("/chooseRide")
    public String chooseRide(@RequestParam String username,
                             @RequestParam String driverName) throws DriverNotFound {
        return cabBookingService.chooseRide(username, driverName);
    }
}
