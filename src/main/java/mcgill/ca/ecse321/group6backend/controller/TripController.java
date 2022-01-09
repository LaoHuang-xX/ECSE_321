package mcgill.ca.ecse321.group6backend.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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
public class TripController {
	
	@Autowired
	TripRepository tRepository;
	@Autowired
	UserRepository uRepository;
	@Autowired
	StopRepository sRepository;
	
	@RequestMapping(value = "/createTrip", method = RequestMethod.GET)
	public Object createTrip(HttpServletRequest request,
			// The parameters needed
			@RequestParam("d_id") int d_id, @RequestParam("seats") Integer seats,
			@RequestParam("firstStopCity") String firstStopCity,
			@RequestParam("firstStopDepartureTime") String firstStopDepartureTime,
			@RequestParam("firstStopDepartureDate") String firstStopDepartureDate,
			@RequestParam("firstPeriodPrice") double firstPeriodPrice,
			@RequestParam("secondStopCity") String secondStopCity,
			@RequestParam("secondStopDepartureTime") String secondStopDepartureTime,
			@RequestParam("secondStopDepartureDate") String secondStopDepartureDate,
			@RequestParam("secondPeriodPrice") double secondPeriodPrice,
			@RequestParam("thirdStopCity") String thirdStopCity,
			@RequestParam("thirdStopDepartureTime") String thirdStopDepartureTime,
			@RequestParam("thirdStopDepartureDate") String thirdStopDepartureDate
	) throws JSONException {
		Trip trip = tRepository.createTrip(d_id, seats, firstStopDepartureTime, thirdStopDepartureTime,
				firstStopDepartureDate, thirdStopDepartureDate);
		// Check whether have created
		if (trip != null) {
			// Stop stop1 = sRepository.createStop(firstStopCity, trip.getId(), seqNo, cost,
			// seats, time)

			Stop stop1 = sRepository.createStop(firstStopCity, trip.getId(), 1, 0, seats, firstStopDepartureTime);
			Stop stop2 = sRepository.createStop(secondStopCity, trip.getId(), 1, firstPeriodPrice, seats,
					secondStopDepartureTime);
			Stop stop3 = sRepository.createStop(thirdStopCity, trip.getId(), 1, secondPeriodPrice, seats,
					thirdStopDepartureTime);
			trip.setStopIds(stop1.getId() + "," + stop2.getId() + "," + stop3.getId());
			tRepository.updateTrip(trip);
			User driver = this.uRepository.getUserById(d_id);
			driver.setCurrentAtTrip(true);
			driver.setCurrentTripId(trip.getId());
			this.uRepository.editUser(driver);
			return trip;
		} else {
			return new String("NO");
		}
	}

	// The method to get all trips
	@RequestMapping(value = "/getTrips", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> searchTrips(){
        List<Trip> tripList = tRepository.getTrips(); 
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Trip trip : tripList) {
        	Boolean active = trip.getActive();
        	// If the trip is not active, we don't display it on the frontend.
        	if (!active) {
        		continue;
        	}
			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("id", trip.getId());
			resultMap.put("startTime", trip.getStartTime());
			resultMap.put("endTime", trip.getEndTime());
			resultMap.put("startDate", trip.getStartDate());
			resultMap.put("endDate", trip.getEndDate());
			String stopIds = trip.getStopIds();		
			List<String> stopIdList = Arrays.asList(stopIds.split(","));
			Stop stop1 = sRepository.getStopById(Integer.parseInt(stopIdList.get(0)));
			Stop stop2 = sRepository.getStopById(Integer.parseInt(stopIdList.get(1)));
			Stop stop3 = sRepository.getStopById(Integer.parseInt(stopIdList.get(2)));		
			resultMap.put("city1", stop1.getCity());
			resultMap.put("city2", stop2.getCity());
			resultMap.put("city3", stop3.getCity());
			resultMap.put("active", trip.getActive());
			resultList.add(resultMap);
		}
		return resultList; 
	}
	
	//The method to finish a trip
	@RequestMapping(value = "/FinishTrip", method = RequestMethod.GET)
    @ResponseBody
	public String finishTripByDriver(HttpServletRequest request, @RequestParam("driverID") int d_id,
			@RequestParam("tripID") int t_id) throws JSONException {
		Trip trip = tRepository.getTripById(t_id);
		User driver = uRepository.getUserById(d_id);
		JSONObject json = new JSONObject();
		if (trip != null && driver != null) {
			trip.setActive(false);
			driver.setCurrentAtTrip(false);
			tRepository.updateTrip(trip);
			json.put("msg", "Trip" + " " + t_id + " finished!");
			return json.toString();
		} else {
			json.put("msg", "Trip" + " " + t_id + " could not be finished!");
			return json.toString();
		}
	}
	
	// The method to get number of trips a passenger had in a period
	@RequestMapping(value = "/getPassengerInPeriod", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> getPassengerInPeriod(
    		@RequestParam("startDate") String startDate,
    		@RequestParam("endDate") String endDate) throws ParseException{
		Map<Integer, Integer> resultMap = new TreeMap<>();
		List<Trip> tripList = tRepository.getTripsByTime(startDate, endDate);
		// get All the passengers
		//
		for (Trip trip : tripList) {
			List<String> pIds = Arrays.asList(trip.getPassengerIds().split(","));
			for (String pIdString : pIds) {
				if(!pIdString.isEmpty()) {
					int pId = Integer.parseInt(pIdString);
					if (!resultMap.containsKey(pId)) {
						resultMap.put(pId, 1);
					} else {
						resultMap.replace(pId, resultMap.get(pId)+1);
					}
				}
			}
		}
		List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(resultMap.entrySet());
		Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			} 
        });
		List<Map> finalList = new ArrayList<Map>();
		for(Entry<Integer, Integer> d:list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			User passenger = this.uRepository.getUserById(d.getKey());
			map.put("id", passenger.getId());
			map.put("name",passenger.getName());
			map.put("phone",passenger.getPhone());
			map.put("currentAtTrip", passenger.getCurrentAtTrip());
			map.put("totalNo", d.getValue());
			finalList.add(map);
		}
		return finalList;
	}
	
	// The method to get number of trips a drivers had in a period
	@RequestMapping(value = "/getDriversByTrip", method = RequestMethod.GET)
	@ResponseBody
	public List<Map> getDriversByTrip(
		@RequestParam("startDate") String startDate,
		@RequestParam("endDate") String endDate) throws ParseException{
		Map<Integer, Integer> resultMap = new TreeMap<>();
		List<Trip> tripList = tRepository.getTripsByTime(startDate, endDate);
		// get All the passengers
		for (Trip trip : tripList) {
			int dId = trip.getDriverId();
			//String pName = uRepository.getUserById(dId).getName();
			if (!resultMap.containsKey(dId)) {
				resultMap.put(dId, 1);
			} else {
				resultMap.replace(dId, resultMap.get(dId)+1);
			}
		}
		List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(resultMap.entrySet());
		Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			} 
        });
		List<Map> finalList = new ArrayList<Map>();
		for(Entry<Integer, Integer> d:list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			User driver = this.uRepository.getUserById(d.getKey());
			map.put("id", driver.getId());
			map.put("name",driver.getName());
			map.put("phone",driver.getPhone());
			map.put("currentAtTrip", driver.getCurrentAtTrip());
			map.put("totalNo", d.getValue());
			finalList.add(map);
		}
		return finalList;
	}
	
}