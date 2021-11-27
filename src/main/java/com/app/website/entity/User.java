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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.app.website.util.DatabaseConstants.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = TABLE_USER)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_FIRST_NAME)
	private String firstName;
	
	@Column(name = FIELD_LAST_NAME)
	private String lastName;
	
	@Column(name = FIELD_EMAIL)
	private String email;
	
	@JsonIgnore
	@Column(name = FIELD_PASSWORD)
	private String password;
	
	@Column(name = FIELD_RANK)
	private int rank;
	
	@Column(name = FIELD_EXP)
	private int exp;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "creatorUser")
	private List<Answer> answers;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "commentUser")
	private List<AnswerComment> answerComments;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "creatorUser")
	private List<Question> questions;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = TABLE_LIKED_ANSWER,
			joinColumns = @JoinColumn(name = FIELD_USER_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_ANSWER_ID)
			)
	private List<Answer> likedAnswers;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = TABLE_LIKED_ANSWER_COMMENT,
			joinColumns = @JoinColumn(name = FIELD_USER_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_ANSWER_COMMENT_ID)
			)
	private List<AnswerComment> likedAnswerComments;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = TABLE_LIKED_QUESTION,
			joinColumns = @JoinColumn(name = FIELD_USER_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_QUESTION_ID)
			)
	private List<Question> likedQuestions;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_FOLLOWER,
			joinColumns = @JoinColumn(name = FIELD_USER_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_FOLLOWER_ID)
			)
	private List<User> followers;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_FOLLOWER,
			joinColumns = @JoinColumn(name = FIELD_FOLLOWER_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_USER_ID)
			)
	private List<User> following;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_USER_NOTIFICATION,
			joinColumns = @JoinColumn(name = FIELD_USER_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_NOTIFICATION_ID)
			)
	private List<Notification> notifications;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_USER_DOMAIN_PREFERENCE,
			joinColumns = @JoinColumn(name = FIELD_USER_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_DOMAIN_ID)
			)
	private List<Domain> domains;
	
	public User() {
	}

	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Answer> getAnswers() {
		forceLoadAnswers();
		return answers;
	}

	public List<AnswerComment> getAnswerComments() {
		forceLoadAnswerComments();
		return answerComments;
	}

	public List<Answer> getLikedAnswers() {
		forceLoadLikedAnswers();
		return likedAnswers;
	}

	public List<AnswerComment> getLikedAnswerComments() {
		forceLoadLikedAnswerComments();
		return likedAnswerComments;
	}

	public List<Question> getLikedQuestions() {
		forceLoadLikedQuestions();
		return likedQuestions;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void setAnswerComments(List<AnswerComment> answerComments) {
		this.answerComments = answerComments;
	}

	public void setLikedAnswers(List<Answer> likedAnswers) {
		this.likedAnswers = likedAnswers;
	}

	public void setLikedAnswerComments(List<AnswerComment> likedAnswerComments) {
		this.likedAnswerComments = likedAnswerComments;
	}

	public void setLikedQuestions(List<Question> likedQuestions) {
		this.likedQuestions = likedQuestions;
	}
	
	public List<Question> getQuestions() {
		forceLoadQuestions();
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<User> getFollowers() {
		forceLoadFollowers();
		return followers;
	}

	public List<User> getFollowing() {
		forceLoadFollowing();
		return following;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<Notification> getNotifications() {
		forceLoadNotifications();
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public String getPassword() {
		return password;
	}

	public int getRank() {
		return rank;
	}

	public int getExp() {
		return exp;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public List<Domain> getDomains() {
		forceLoadDomains();
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}

	public void addAnswer(Answer answer) {
		forceLoadAnswers();
		if (answers == null)
			answers = new ArrayList<>();
		answers.add(answer);
		answer.setCreatorUser(this);
	}
	
	public void addAnswerComment(AnswerComment answerComment) {
		forceLoadAnswerComments();
		if (answerComments == null)
			answerComments = new ArrayList<>();
		answerComments.add(answerComment);
		answerComment.setCommentUser(this);
	}
	
	public void addQuestion(Question question) {
		forceLoadQuestions();
		if (questions == null)
			questions = new ArrayList<>();
		questions.add(question);
		question.setCreatorUser(this);
	}
	
	public void addLikedAnswer(Answer answer) {
		forceLoadLikedAnswers();
		if (likedAnswers == null)
			likedAnswers = new ArrayList<>();
		likedAnswers.add(answer);
	}
	
	public void addLikedAnswerComment(AnswerComment answerComment) {
		forceLoadLikedAnswerComments();
		if (likedAnswerComments == null)
			likedAnswerComments = new ArrayList<>();
		likedAnswerComments.add(answerComment);
	}
	
	public void addLikedQuestion(Question question) {
		forceLoadLikedQuestions();
		if (likedQuestions == null)
			likedQuestions = new ArrayList<>();
		likedQuestions.add(question);
	}
	
	public void addFollower(User user) {
		forceLoadFollowers();
		if (followers == null)
			followers = new ArrayList<>();
		followers.add(user);
	}
	
	public void addFollowing(User user) {
		forceLoadFollowing();
		if (following == null)
			following = new ArrayList<>();
		following.add(user);
	}
	
	public void addNotification(Notification notification) {
		forceLoadNotifications();
		if (notifications == null)
			notifications = new ArrayList<>();
		notifications.add(notification);
	}
	
	public void addDomain(Domain domain) {
		forceLoadDomains();
		if (domains == null)
			domains = new ArrayList<>();
		domains.add(domain);
	}
	
	public void removeNotification(Notification notification) {
		forceLoadNotifications();
		notifications.remove(notification);
	}
	
	public void removeFollower(User user) {
		forceLoadFollowers();
		followers.remove(user);
	}
	
	public void removeFollowing(User user) {
		forceLoadFollowing();
		following.remove(user);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email;
	}
	
	private void forceLoadAnswerComments() {
		try {
			this.answerComments.size();
		} catch (NullPointerException e) {
		}
	}
	
	private void forceLoadAnswers() {
		try {
			this.likedAnswers.size();
		} catch (NullPointerException e) {
		}
	}
	
	private void forceLoadLikedAnswers() {
		try {
			this.likedAnswers.size();
		} catch (Exception e) {
		}
	}
	
	private void forceLoadLikedAnswerComments() {
		try {
			this.likedAnswerComments.size();
		} catch (NullPointerException e) {
		}
	}
	
	private void forceLoadQuestions() {
		try {
			this.questions.size();
		} catch (NullPointerException e) {
		}
	}
	
	private void forceLoadLikedQuestions() {
		try {
			this.likedQuestions.size();
		} catch (NullPointerException e) {
		}
	}
	
	private void forceLoadFollowers() {
		try {
			this.followers.size();
		} catch (Exception e) {
		}
	}
	
	private void forceLoadFollowing() {
		try {
			this.following.size();
		} catch (Exception e) {
		}
	}
	
	private void forceLoadNotifications() {
		try {
			this.notifications.size();
		} catch (Exception e) {
		}
	}
	
	private void forceLoadDomains() {
		try {
			this.domains.size();
		}catch (Exception e) {
		}
	}
	
}
