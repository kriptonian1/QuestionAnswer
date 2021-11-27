package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.User;

public interface MiscDao {
	
	public void deleteNotification(int notificationId);
	public List<User> getNotificationUsers(int notificationId);

}
