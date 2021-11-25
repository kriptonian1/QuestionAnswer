package com.app.website.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.website.database.interfaces.UserDao;
import com.app.website.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		int id = -1;
		if (getUserByEmail(user.getEmail()) == null) {
			id = (int) session.save(user);
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByFirstName(String firstName) {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createQuery(String.format("from User where firstName like '%s'", firstName)).getResultList();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByLastName(String lastName) {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = new ArrayList<>();
		for (User user : (List<User>) session.createQuery(String.format("from User where lastName like '%s'", lastName)).getResultList()) {
			
			users.add(user);
		}
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User user = (User) session.createQuery(String.format("from User where email='%s'", email)).getSingleResult();
			return user;
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = new ArrayList<>();
		for (User user : (List<User>) session.createQuery("select u from User u").getResultList()) {
			users.add(user);
		}
		return users;
	}

	@Override
	public boolean deleteUser(int userId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User user = session.get(User.class, userId);
			session.delete(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateUserFirstName(int userId, String firstName) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User user = session.get(User.class, userId);
			user.setFirstName(firstName);
			session.update(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateUserLastName(int userId, String lastName) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User user = session.get(User.class, userId);
			user.setLastName(lastName);
			session.update(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateUserPassword(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addFollower(int userId, int followerUserId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User user = session.get(User.class, userId);
			User followerUser = session.get(User.class, followerUserId);
			user.addFollower(followerUser);
			session.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addFollowing(int userId, int followingUserId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User user = session.get(User.class, userId);
			User followingUser = session.get(User.class, followingUserId);
			user.addFollower(followingUser);
			session.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<User> getFollowers(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		user.forceLoadFollowers();
		return user.getFollowers();
	}

	@Override
	public List<User> getFollowing(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		user.forceLoadFollowing();
		return user.getFollowing();
	}

	@Override
	public User getFollowerById(int userId, int followerId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		user.forceLoadFollowers();
		for (User u : user.getFollowers())
			if (u.getId() == followerId)
				return u;
		return null;
	}

	@Override
	public User getFollowingById(int userId, int followingId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		user.forceLoadFollowing();
		for (User u : user.getFollowing())
			if (u.getId() == followingId)
				return u;
		return null;
	}

	@Override
	public boolean removeFollower(int userId, int followerUserId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User user = session.get(User.class, userId);
			User followerUser = session.get(User.class, followerUserId);
			user.removeFollower(followerUser);
			session.update(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeFollowing(int userId, int followingUserId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User user = session.get(User.class, userId);
			User followingUser = session.get(User.class, followingUserId);
			user.removeFollowing(followingUser);
			session.update(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
