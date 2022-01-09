package mcgill.ca.ecse321.group6backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stops")
public class Stop {
	
	private int id;
	private String city;
	private int tripId;
	private int seqNo;
	private double cost;
	private int seats;
	private String time;
	
	// default constructor without parameter
	public Stop() {}
	
	// used when creating stop
	public Stop(String city, int tripId, int seqNo, double cost, int seats, String time) {
		this.city = city;
		this.tripId = tripId;
		this.seqNo = seqNo;
		this.cost = cost;
		this.seats = seats;
		this.time = time;
	}
	
	// used when searching stop
	public Stop(int id, String city, int tripId, int seqNo, double cost, int seats, String time) {
		this.id = id;
		this.city = city;
		this.tripId = tripId;
		this.seqNo = seqNo;
		this.cost = cost;
		this.seats = seats;
		this.time = time;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "trip_id")
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	@Column(name = "seq_no")
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	@Column(name = "cost")
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	@Column(name = "seats")
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Column(name = "time")
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
