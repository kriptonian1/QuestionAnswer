package com.app.website.entity;

import static com.app.website.util.DatabaseConstants.*;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = TABLE_ANSWER_COMMENT)
public class AnswerComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_ANSWER_COMMENT)
	private String comment;
	
	@Column(name = FIELD_DATE_CREATED)
	private String dateCreated;
	
	@Column(name = FIELD_DATE_MODIFIED)
	private String dateModified;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = FIELD_USER_ID)
	private User commentUser;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = FIELD_ANSWER_ID)
	private Answer answer;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_LIKED_ANSWER_COMMENT,
			joinColumns = @JoinColumn(name = FIELD_ANSWER_COMMENT_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_USER_ID)
			)
	private List<User> usersLiked;
	
	public AnswerComment() {

	}
	
	public AnswerComment(String comment, String dateCreated) {
		this.comment = comment;
		this.dateCreated = dateCreated;
		dateModified = dateCreated;
	}

	public int getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public List<User> getUsersLiked() {
		forceLoadAnswerCommentLikes();
		return usersLiked;
	}
	
	public void addLikedUser(User user) {
		forceLoadAnswerCommentLikes();
		if (usersLiked == null) 
			usersLiked = new ArrayList<>();
		usersLiked.add(user);
	}
	
	public void removeLikedUser(User user) {
		forceLoadAnswerCommentLikes();
		usersLiked.remove(user);
	}
	
	private void forceLoadAnswerCommentLikes() {
		try {
			usersLiked.size();
		} catch (NullPointerException e) {
		}
	}

	public void setUsersLiked(List<User> usersLiked) {
		this.usersLiked = usersLiked;
	}

	@Override
	public String toString() {
		return "AnswerComment [id=" + id + ", comment=" + comment + ", commentUser=" + commentUser + "]";
	}

}
