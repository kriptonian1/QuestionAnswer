package com.app.website.database.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.website.database.interfaces.MiscDao;
import com.app.website.entity.Notification;
import com.app.website.entity.User;

@Transactional
@Repository
public class MiscDaoImpl implements MiscDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void deleteNotification(int notificationId) {
		Session session = sessionFactory.getCurrentSession();
		Notification notification = session.get(Notification.class, notificationId);
		session.delete(notification);
	}

	@Override
	public List<User> getNotificationUsers(int notificationId) {
		Session session = sessionFactory.getCurrentSession();
		Notification notification = session.get(Notification.class, notificationId);
		return notification.getUsers();
	}
	
}
