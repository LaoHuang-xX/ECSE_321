package mcgill.ca.ecse321.group6backend.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mcgill.ca.ecse321.group6backend.model.User;

@Repository 
public class UserRepository {
	
	@Autowired
	EntityManager eManager;
	
	@Transactional
	public User createUser(String name, String phone, int role, String pwd, String repwd) {
		User user = new User(name,phone,pwd,role);
		eManager.persist(user);
		return user;
	}
	
	@Transactional
	public User getUser(String phone, int role, String pwd) {	
//		Query query = eManager.createNativeQuery("select * from users where phone = '"+phone+"'");
//		return query.getSingleResult();
		Query query = eManager.createQuery(
				"select new User(id,name,phone,pwd,role,currentAtTrip,currentTripId,score,totalNo) from User where phone = '"+phone+"'");
		User user = (User)query.getSingleResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getUsers(int role) {
		try {
			String sql = "SELECT new User(id,name,phone,pwd,role,currentAtTrip,currentTripId,score,totalNo) FROM User WHERE role="+role;
			Query query = eManager.createQuery(sql);
			return (List<User>)query.getResultList();
		}
		catch (Exception e) {
	        return new ArrayList<User>();
	    }
	}
	
	@Transactional
	public User getUserById(int Id) {	
		//Query query = eManager.createQuery(
		//		"select new User(id,name,phone,pwd,role,currentAtTrip,currentTripId,score,totalNo) from User where phone = '"+phone+"'");
		
		User user = eManager.find(User.class, Id);
		return user;
	}
	
	@Transactional
	public void editUser(User user) {
		eManager.merge(user);
	}
	

	
}
