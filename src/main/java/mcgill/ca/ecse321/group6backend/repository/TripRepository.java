package mcgill.ca.ecse321.group6backend.repository;

import java.util.ArrayList;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mcgill.ca.ecse321.group6backend.model.Trip;
import mcgill.ca.ecse321.group6backend.model.User;

@Repository
public class TripRepository {
	
	@Autowired
	EntityManager eManager;
	
	@Transactional
	public Trip createTrip(int driverId, int seats, String startTime, String endTime,String startDate, String endDate) {
		Trip trip = new Trip(driverId,seats,startTime,endTime,startDate,endDate);
		eManager.persist(trip);
		return trip;
	}
	
	@Transactional
	public Trip getTripById(int id) {
		try {
			String sql = "SELECT new Trip(id, driverId, passengerIds, active, currentStopId, stopIds, seats, score, startTime, endTime, startDate, endDate) FROM Trip WHERE id="+id;
			Query query = eManager.createQuery(sql);
			return (Trip)query.getSingleResult();
		}
		catch (Exception e) {
	        return new Trip();
	    }
	}
	
	@Transactional
	public List<Trip> getTripsByTime(String startTime, String endTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(startTime);
        Date date2 = sdf.parse(endTime);
        Query query = eManager.createQuery(
				"select new Trip(id, driverId, passengerIds, active, currentStopId, stopIds, seats, score, startTime, endTime, startDate, endDate) from Trip");
        List<Trip> scaledTrips = new ArrayList<>();
        @SuppressWarnings("unchecked")
		List<Trip> wholeTrips = query.getResultList();
        for (Trip trip : wholeTrips) {
        	Date dates = sdf.parse(trip.getStartDate());
            Date datee = sdf.parse(trip.getEndDate());
            if (dates.compareTo(date1) > 0 && datee.compareTo(date2) < 0) {
            	if(!trip.getActive()) {
            		scaledTrips.add(trip);
            	}
            }
        }
        return scaledTrips;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Trip> getTrips(){
		Query query = eManager.createQuery(
				"select new Trip(id,driverId,passengerIds,active,currentStopId,stopIds,seats,score,startTime,endTime,startDate,endDate) from Trip");
		return query.getResultList();
	}
	
	@Transactional
	public void updateTrip(Trip trip) {
		eManager.merge(trip);
	}
}
