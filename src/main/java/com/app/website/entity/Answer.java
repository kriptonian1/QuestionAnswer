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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = TABLE_ANSWER)
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_ANSWER)
	private String answer;
	
	@Column(name = FIELD_DATE_CREATED)
	private String dateCreated;
	
	@Column(name = FIELD_DATE_MODIFIED)
	private String dateModified;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = FIELD_USER_ID)
	private User creatorUser;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_LIKED_ANSWER,
			joinColumns = @JoinColumn(name = FIELD_ANSWER_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_USER_ID)
			)
	private List<User> usersLiked;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = FIELD_QUESTION_ID)
	private Question question;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "answer")
	private List<AnswerComment> answerComments;
	
	public Answer() {

	}
	
	public Answer(String answer, String dateCreated) {
		super();
		this.answer = answer;
		this.dateCreated = dateCreated;
		dateModified = dateCreated;
	}

	public Answer(String answer) {
		this.answer = answer;
	}

	public int getId() {
		return id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public User getCreatorUser() {
		return creatorUser;
	}

	public List<User> getUsersLiked() {
		forceLoadAnswerLikes();
		return usersLiked;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public Question getQuestion() {
		return question;
	}

	public List<AnswerComment> getAnswerComments() {
		forceLoadAnswerComments();
		return answerComments;
	}

	public void setCreatorUser(User creatorUser) {
		this.creatorUser = creatorUser;
	}

	public void setUsersLiked(List<User> usersLiked) {
		this.usersLiked = usersLiked;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setAnswerComments(List<AnswerComment> answerComments) {
		this.answerComments = answerComments;
	}
	
	public void addLikedUser(User user) {
		forceLoadAnswerLikes();
		if (usersLiked == null)
			usersLiked = new ArrayList<>();
		usersLiked.add(user);
//		user.addLikedAnswer(this);
	}
	
	public void removeLikedUser(User user) {
		forceLoadAnswerLikes();
		usersLiked.remove(user);
	}
	
	public void addAnswerComment(AnswerComment answerComment) {
		forceLoadAnswerComments();
		if (answerComments  == null)
			answerComments = new ArrayList<>();
		answerComments.add(answerComment);
		answerComment.setAnswer(this);
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answer=" + answer + ", creatorUser=" + creatorUser + ", usersLiked=" + usersLiked
				+ ", question=" + question + "]";
	}
	
	private void forceLoadAnswerComments() {
		try {
			this.getAnswerComments().size();
		} catch (NullPointerException e) {
		}
	}
	
	private void forceLoadAnswerLikes() {
		try {
			this.getUsersLiked().size();
		} catch (NullPointerException e) {
		}
	}

}
