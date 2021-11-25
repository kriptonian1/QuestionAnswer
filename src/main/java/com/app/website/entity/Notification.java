package com.app.website.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * This notification message gets sent to followers when the person they are following
 * post some question/answer/comment/starts to follow
 */

//@Entity
//@Table()
public class Notification {
	
	private int id;
	private int userId;
	private String notification;
	private String url;
	
	public Notification() {
		userId = 0;
		notification = "";
		url = "";
	}

	public Notification(int id, int userId, String notification, String url) {
		super();
		this.id = id;
		this.userId = userId;
		this.notification = notification;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public String getNotification() {
		return notification;
	}

	public String getUrl() {
		return url;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
