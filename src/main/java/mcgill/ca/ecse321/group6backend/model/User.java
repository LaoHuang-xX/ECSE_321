package mcgill.ca.ecse321.group6backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	private int id;
	private String name;
	private String phone;
	private String pwd;
	// 0 for admin, 1 for driver, 2 for passenger
	private int role;
	private boolean currentAtTrip = false;
	private int currentTripId = -1;
	private double score = 4;
	private int totalNo = 0;
	
	//never use actually, but by convention we need to have this
	public User() {}
	
	//use when create user
	public User(String name, String phone, String pwd, int role) {
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
		this.role = role;
	}
	
	public User(int id, String name, String phone, String pwd, int role, 
			boolean currentAtTrip, int currentTripId, double score, int totalNo) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
		this.role = role;
		this.currentAtTrip = currentAtTrip;
		this.currentTripId = currentTripId;
		this.score = score;
		this.totalNo = totalNo;
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
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="pwd")
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Column(name="role")
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	@Column(name="current_at_trip")
	public boolean getCurrentAtTrip() {
		return currentAtTrip;
	}
	public void setCurrentAtTrip(boolean currentAtTrip) {
		this.currentAtTrip = currentAtTrip;
	}
	@Column(name="current_trip_id")
	public int getCurrentTripId() {
		return currentTripId;
	}
	public void setCurrentTripId(int currentTripId) {
		this.currentTripId = currentTripId;
	}
	@Column(name="score")
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Column(name="total_no")
	public int getTotalNo() {
		return totalNo;
	}
	public void setTotalNo(int totalNo) {
		this.totalNo = totalNo;
	}
	
	
}
