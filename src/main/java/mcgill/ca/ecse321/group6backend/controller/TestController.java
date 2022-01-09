package mcgill.ca.ecse321.group6backend.controller;


import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ca.ecse321.group6backend.model.*;
import mcgill.ca.ecse321.group6backend.repository.*;

@CrossOrigin
@RestController
public class TestController {
	
	@Autowired
	UserRepository uRepository;
	@Autowired
	StopRepository sRepository;
	@Autowired
	TripRepository tRepository;
	
	@RequestMapping(value = "/testCreateStop", method = RequestMethod.GET)
    @ResponseBody
    public Stop testCreateStop() throws JSONException {
		Stop stop = sRepository.createStop("Montreal", 1, 1, 0.1, 4, "2018-11-15");
		//Stop stop = new Stop("Montreal", 1, 1, 0, 4, "2018-11-15");
        return stop;
	}
	
	@RequestMapping(value = "/testGetTripById", method = RequestMethod.GET)
    @ResponseBody
    public Trip testGetTripById() {
		Trip trip = tRepository.getTripById(27);
		//Stop stop = new Stop("Montreal", 1, 1, 0, 4, "2018-11-15");
        return trip;
	}
	
	@RequestMapping(value = "/testGetTrips", method = RequestMethod.GET)
    @ResponseBody
	public List<Trip> testGetTrips() {
		List<Trip> trips = tRepository.getTrips();
		return trips;
	}
	
	@RequestMapping(value = "/testGetUserById", method = RequestMethod.GET)
    @ResponseBody
    public User testGetUserById() {
		User user = uRepository.getUserById(2);
		//Stop stop = new Stop("Montreal", 1, 1, 0, 4, "2018-11-15");
        return user;
	}
	@RequestMapping(value = "/testGetStopById", method = RequestMethod.GET)
    @ResponseBody
    public Stop testGetStopById() {
		Stop stop = sRepository.getStopById(15);
		//Stop stop = new Stop("Montreal", 1, 1, 0, 4, "2018-11-15");
        return stop;
	}
	
	@RequestMapping(value = "/testGetStop", method = RequestMethod.GET)
    @ResponseBody
    public List<Stop> testGetStop() throws JSONException {
		List<Stop> stops = sRepository.getStops("Montreal");
        return stops;
	}
	
	@RequestMapping(value = "/testGetDrivers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> testGetDrivers() throws JSONException {
        List<User> users = uRepository.getUsers(1); 
        return users;
    }
	
	@RequestMapping(value = "/testTripsByTime", method = RequestMethod.GET)
    @ResponseBody
    public Object testTripsByTime() throws ParseException{
		Object obj = tRepository.getTripsByTime("2018-09-09", "2018-09-13");
		return obj;
	}
	


}
