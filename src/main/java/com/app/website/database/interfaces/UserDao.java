package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.Notification;
import com.app.website.entity.User;

public interface UserDao {
	
	public int createUser(User user);
	public int addNotification(int userId, Notification notification);
	public void addFollower(int userId, int followerUserId);
	public List<User> getUsersByFirstName(String firstName);
	public List<User> getUsersByLastName(String lastName);
	public List<User> getFollowers(int userId);
	public List<User> getFollowing(int userId);
	public List<Notification> getAllNotifications(int userId);
	public Notification getNotificationById(int notificationId);
	public User getFollowerById(int userId, int followerId);
	public User getFollowingById(int userId, int followingId);
	public User getUserByEmail(String email);
	public User getUserById(int userId);
	public List<User> getAllUsers();
	public void updateUserFirstName(int userId, String firstName);
	public void updateUserLastName(int userId, String lastName);
	public void updateUserPassword(int userId, String password);
	public void deleteUser(int userId);
	public void removeFollower(int userId, int followerUserId);
	public void removeFollowing(int userId, int followingUserId);
	public void removeNotification(int userId, int notificationId);
	public boolean isPasswordCorrect(int userId, String password);

}
