package mcgill.ca.ecse321.group6backend.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ca.ecse321.group6backend.model.*;
import mcgill.ca.ecse321.group6backend.repository.*;

@CrossOrigin
@RestController
public class BookingContoller {
	
	@Autowired
	TripRepository tRepository;
	@Autowired
	UserRepository uRepository;
	@Autowired
	StopRepository sRepository;
	
	@RequestMapping(value = "/createBooking", method = RequestMethod.GET)
	public Object createBooking(HttpServletRequest request,
			// The parameters needed
			@RequestParam("p_id") int p_id, 
			@RequestParam("tripId") int tripId
	) throws JSONException {
		Trip trip = tRepository.getTripById(tripId);
		User passenger = uRepository.getUserById(p_id);
		// Check whether have created
		if (trip != null&&passenger!=null) {
			if(passenger.getRole()==2&&trip.getSeats()>0) {
				passenger.setCurrentAtTrip(true);
				passenger.setCurrentTripId(tripId);
				uRepository.editUser(passenger);
				trip.setSeats(trip.getSeats()-1);
				if(trip.getPassengerIds().isEmpty()) {
					trip.setPassengerIds(""+p_id);
				}
				else {
					trip.setPassengerIds(trip.getPassengerIds()+","+p_id);
				}
				tRepository.updateTrip(trip);
				
				return trip;
			}
			else {
				return new String("Invalid user or trip");
			}
		} else {
			return new String("NO");
		}
	}
	
	@RequestMapping(value = "/finishBooking", method = RequestMethod.GET)
	public Object finishBooking(HttpServletRequest request,
			// The parameters needed
			@RequestParam("p_id") int p_id,
			@RequestParam("score") int score
	) throws JSONException {
		User passenger = uRepository.getUserById(p_id);
		if(!passenger.getCurrentAtTrip()) {
			return new String("NO");
		}
		Trip trip = tRepository.getTripById(passenger.getCurrentTripId());
		int tripId = trip.getId();
		User driver = uRepository.getUserById(trip.getDriverId());
		// Check whether have created
		if (trip != null&&passenger!=null) {
			if(passenger.getRole()==2) {
				passenger.setCurrentAtTrip(false);
				uRepository.editUser(passenger);
				trip.setSeats(trip.getSeats()+1);
				tRepository.updateTrip(trip);
				driver.setScore((driver.getScore()*driver.getTotalNo()+score)/(driver.getTotalNo()+1));		
				driver.setTotalNo(driver.getTotalNo()+1);
				uRepository.editUser(driver);

				return driver;
			}
			else {
				return new String("Invalid user");
			}
		} else {
			return new String("NO");
		}
	}
	
	
	@RequestMapping(value = "/getCitiesInPeriod", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> getPassengerInPeriod(
    		@RequestParam("startDate") String startDate,
    		@RequestParam("endDate") String endDate) throws ParseException{
		Map<String, Integer> resultMap = new TreeMap<>();
		List<Trip> tripList = tRepository.getTripsByTime(startDate, endDate);
		// get All the passengers
		for (Trip trip : tripList) {
			List<String> cityIds = Arrays.asList(trip.getStopIds().split(","));
			for (String cityIdString : cityIds) {
				int cityId = Integer.parseInt(cityIdString);
				String cityName = sRepository.getStopById(cityId).getCity();
				if (!resultMap.containsKey(cityName)) {
					resultMap.put(cityName, 1);
				} else {
					resultMap.replace(cityName, resultMap.get(cityName)+1);
				}
			}
		}
		List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(resultMap.entrySet());
		Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			} 
        });
		List<Map> finalList = new ArrayList<Map>();
		for(Entry<String, Integer> c:list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name",c.getKey());
			map.put("count",c.getValue());
			finalList.add(map);
		}
		return finalList;
	}


	
}
