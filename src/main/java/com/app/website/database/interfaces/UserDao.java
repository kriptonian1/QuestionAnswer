package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.User;

public interface UserDao {
	
	public int createUser(User user);
	public boolean addFollower(int userId, int followerUserId);
	public boolean addFollowing(int userId, int followingUserId);
	public List<User> getUsersByFirstName(String firstName);
	public List<User> getUsersByLastName(String lastName);
	public List<User> getFollowers(int userId);
	public List<User> getFollowing(int userId);
	public User getFollowerById(int userId, int followerId);
	public User getFollowingById(int userId, int followingId);
	public User getUserByEmail(String email);
	public User getUserById(int userId);
	public List<User> getAllUsers();
	public boolean updateUserFirstName(int userId, String firstName);
	public boolean updateUserLastName(int userId, String lastName);
	public boolean updateUserPassword(int userId, String password);
	public boolean deleteUser(int userId);
	public boolean removeFollower(int userId, int followerUserId);
	public boolean removeFollowing(int userId, int followingUserId);

}
