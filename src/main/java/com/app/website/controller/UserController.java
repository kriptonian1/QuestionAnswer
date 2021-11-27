package com.app.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.website.database.interfaces.UserDao;
import com.app.website.entity.Notification;
import com.app.website.entity.User;

@Configuration
@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	private static final String STATUS_SUCCESS = "SUCCESS";
	private static final String STATUS_ERROR = "ERROR";
	
	// De
	@GetMapping("/hi")
	public String hi() {
		return "Hi";
	}
	
	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int addUser(@RequestBody User user) {
		return userDao.createUser(user);
	}
	
	@GetMapping(value = "/users/first-name/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsersByFirstName(@PathVariable String firstName) {
		return userDao.getUsersByFirstName(firstName);
	}
	
	@GetMapping(value = "/users/last-name/{lastName}")
	public List<User> getusersByLastName(@PathVariable String lastName){
		return userDao.getUsersByLastName(lastName);
	}
	
	@GetMapping(value = "/user/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByEmail(@PathVariable String email) {
		return userDao.getUserByEmail(email);
	}
	
	@GetMapping(value = "/user/id/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserById(@PathVariable int userId) {
		return userDao.getUserById(userId);
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	@PutMapping("/user/{userId}/first-name")
	public String updateUserFirstName(@PathVariable int userId, @RequestBody String firstName) {
		try {
			userDao.updateUserFirstName(userId, firstName);
			return STATUS_SUCCESS;
		}catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@PutMapping("/user/{userId}/last-name")
	public String updateUserLastName(@PathVariable int userId, @RequestBody String lastName) {
		try {
			userDao.updateUserLastName(userId, lastName);
			return STATUS_SUCCESS;
		}catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@PutMapping("/user/{userId}/password")
	public String updateUserPassword(@PathVariable int userId, @RequestBody String password) {
		try {
			userDao.updateUserPassword(userId, password);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@DeleteMapping("/user/{userId}")
	public String deleteUser(@PathVariable int userId) {
		try {
			userDao.deleteUser(userId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@PutMapping("/user/{userId}/follower/{followerUserId}")
	public String addFollower(@PathVariable int userId, @PathVariable int followerUserId) {
		try {
			userDao.addFollower(userId, followerUserId);
			return STATUS_SUCCESS;
		}catch(Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@DeleteMapping("/user/{userId}/follower/{followerUserId}")
	public String removeFollower(@PathVariable int userId, @PathVariable int followerUserId) {
		try {
			userDao.removeFollower(userId, followerUserId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@GetMapping(value = "/user/{userId}/follower/{followerUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getFollowerById(@PathVariable int userId, @PathVariable int followerUserId) {
		return userDao.getFollowerById(userId, followerUserId);
	}
	
	@GetMapping(value = "/user/{userId}/followers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllFollowers(@PathVariable int userId) {
		return userDao.getFollowers(userId);
	}
	
	@DeleteMapping("/user/{userId}/following/{followingUserId}")
	public String removeFollowing(@PathVariable int userId, @PathVariable int followingUserId) {
		try {
			userDao.removeFollowing(userId, followingUserId);
			return STATUS_SUCCESS;
		}catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@GetMapping(value = "/user/{userId}/following/{followingUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getFollowingById(@PathVariable int userId, @PathVariable int followingUserId) {
		return userDao.getFollowingById(userId, followingUserId);
	}
	
	@GetMapping(value = "/user/{userId}/followings", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllFollowings(@PathVariable int userId) {
		return userDao.getFollowing(userId);
	}
	
	@GetMapping(value = "/user/{userId}/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Notification> getAllNotifications(@PathVariable int userId){
		return userDao.getAllNotifications(userId);
	}
	
	@GetMapping(value = "/user/notification/{notificationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Notification getNotificationById(@PathVariable int notificationId) {
		return userDao.getNotificationById(notificationId);
	}
	
	@DeleteMapping(value = "/user/{userId}/notification/{notificationId}")
	public String removeNotification(@PathVariable int userId, @PathVariable int notificationId) {
		try {
			userDao.removeNotification(userId, notificationId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@GetMapping("/user/{userId}/password")
	public String isPasswordCorrect(@PathVariable int userId, @RequestBody String password) {
		return String.valueOf(userDao.isPasswordCorrect(userId, password));
	}

}
