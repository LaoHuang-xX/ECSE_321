package mcgill.ca.ecse321.group6backend.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mcgill.ca.ecse321.group6backend.model.Stop;

@Repository
public class StopRepository {
	
	@Autowired
	EntityManager eManager;
	
	@Transactional
	public Stop createStop(String city, int tripId, int seqNo, double cost, int seats, String time) {
		Stop stop = new Stop(city,tripId,seqNo,cost,seats,time);
		//Stop stop = new Stop("Montreal", 1, 1, 0, 4, "2018-11-15");
		eManager.persist(stop);
		return stop;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Stop> getStops(String cityName) {
		try {
			String sql = "SELECT new Stop(id,city,tripId,seqNo,cost,seats,time) FROM Stop WHERE city='"+cityName+"'";
			Query query = eManager.createQuery(sql);
			return (List<Stop>)query.getResultList();
		}
		catch (Exception e) {
	        return new ArrayList<Stop>();
	    }
	}
	
	@Transactional
	public Stop getStopById(int Id) {	
		Stop stop = eManager.find(Stop.class, Id);
		return stop;
	}
	
	@Transactional
	public void editStop(Stop stop) {
		eManager.merge(stop);
	}
	
	
	
}
