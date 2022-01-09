package mcgill.ca.ecse321.group6backend.controller;

import java.util.List;

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

import mcgill.ca.ecse321.group6backend.model.User;
import mcgill.ca.ecse321.group6backend.repository.UserRepository;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	UserRepository uRepository;
	
	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> createUser(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("role") int role,
            @RequestParam("pwd") String pwd,
            @RequestParam("repwd") String repwd
    ) throws JSONException {
        if (pwd.equals(repwd)) {
			User user = uRepository.createUser(name,phone,role,pwd,repwd);
	        if (user != null) {
	            JSONObject json = new JSONObject();
	            json.put("msg","data"+" " + phone + " created!");
	            return new ResponseEntity<>(json.toString(), HttpStatus.OK);
	        } else {
	            JSONObject json = new JSONObject();
	            json.put("msg","data" + " " + phone + " could not be created.");
	            return new ResponseEntity<>(json.toString(), HttpStatus.CONFLICT);
	        }
        } else {
        	JSONObject json = new JSONObject();
            json.put("msg","Password is not consist");
			return new ResponseEntity<>(json.toString(),HttpStatus.CONFLICT);
		}      
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public User getUser(HttpServletRequest request,
            @RequestParam("phone") String phone,
            @RequestParam("role") int role,
            @RequestParam("pwd") String pwd) {
		User user = uRepository.getUser(phone,role,pwd);
		if (null!=user) {
			if (pwd.equals(user.getPwd())&&role==user.getRole()) {				
				request.getSession().setAttribute("user", user);
			} else {
				return null;
			}
		}
		return user;
	}
	
	@RequestMapping(value = "/getDrivers", method = RequestMethod.GET)
    @ResponseBody
    public Object getDrivers() throws JSONException {
        List<User> users = uRepository.getUsers(1); 
        return users;
    }
 
	@RequestMapping(value = "/getPassengers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getPassengers() throws JSONException {
        List<User> users = uRepository.getUsers(2);
        return users;
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
    @ResponseBody
    public User editUser() throws JSONException {
		User user = uRepository.getUserById(1);
		user.setScore(3);
		uRepository.editUser(user);
        return user;
	}
	
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public User getUserById(HttpServletRequest request,
            @RequestParam("id") int id) {
		User user = uRepository.getUserById(id);
		return user;
	}
}
