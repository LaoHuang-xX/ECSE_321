package mcgill.ca.ecse321.group6backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trips")
public class Trip {

	private int id;
	private int driverId;
	private String passengerIds = "";
	private boolean active = true;
	private int currentStopId = -1;
	private String stopIds;
	private int seats;
	private double score;
	private String startTime;
	private String endTime;
	private String startDate;
	private String endDate;
	
	//default constructor with no parameters
	public Trip() {}
	
	//This constructor is used when creating trip
	public Trip(int driverId,int seats, String startTime,String endTime,String startDate, String endDate) {
		this.driverId = driverId;
		this.seats = seats;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.stopIds = "";
	}
	
	//This constructor is used when searching trip
	public Trip(int id, int driverId, String passengerIds, boolean active, 
			int currentStopId, String stopIds, int seats, double score, 
			String startTime, String endTime, String startDate, String endDate) {
		this.id = id;
		this.driverId = driverId;
		this.passengerIds = passengerIds;
		this.active = active;
		this.currentStopId = currentStopId;
		this.stopIds = stopIds;
		this.seats = seats;
		this.score = score;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@Id
	@Column(name="id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="driver_id")
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	@Column(name="passenger_ids")
	public String getPassengerIds() {
		return passengerIds;
	}
	public void setPassengerIds(String passengerIds) {
		this.passengerIds = passengerIds;
	}
	
	@Column(name="active")
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name="current_stop_id")
	public int getCurrentStopId() {
		return currentStopId;
	}
	public void setCurrentStopId(int currentStopId) {
		this.currentStopId = currentStopId;
	}

	@Column(name="stop_ids")
	public String getStopIds() {
		return stopIds;
	}
	public void setStopIds(String stopIds) {
		this.stopIds = stopIds;
	}

	@Column(name="seats")
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Column(name="score")
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

	@Column(name="start_time")
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name="end_time")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name="start_date")
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name="end_date")
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
