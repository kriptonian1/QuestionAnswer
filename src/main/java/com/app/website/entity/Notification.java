package com.app.website.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.app.website.util.DatabaseConstants.*;

import java.util.List;

/*
 * This notification message gets sent to followers when the person they are following
 * post some question/answer/comment/starts to follow
 */

@Entity
@Table(name = TABLE_NOTIFICATION)
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_USER_ID)
	private int userId;
	
	@Column(name = FIELD_NOTIFICATION)
	private String notification;
	
	@Column(name = FIELD_URL)
	private String url;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_USER_NOTIFICATION,
			joinColumns = @JoinColumn(name = FIELD_NOTIFICATION_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_USER_ID)
			)
	private List<User> users;
	
	public Notification() {
		userId = 0;
		notification = "";
		url = "";
	}

	public Notification(int userId, String notification, String url) {
		super();
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

	public List<User> getUsers() {
		forceLoadUsers();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	private void forceLoadUsers() {
		try {
			this.users.size();
		}catch (Exception e) {
		}
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", userId=" + userId + ", notification=" + notification + ", url=" + url
				+ "]";
	}

}
