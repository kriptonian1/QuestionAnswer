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
@Table(name = TABLE_QUESTION)
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_QUESTION)
	private String question;
	
	@Column(name = FIELD_DATE_CREATED)
	private String dateCreated;
	
	@Column(name = FIELD_DATE_MODIFIED)
	private String dateModified;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = FIELD_USER_ID)
	private User creatorUser;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
	private List<Answer> answers;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_QUESTION_TAG,
			joinColumns = @JoinColumn(name = FIELD_QUESTION_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_TAG_ID)
			)
	private List<Tag> tags;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_LIKED_QUESTION,
			joinColumns = @JoinColumn(name = FIELD_QUESTION_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_USER_ID)
			)
	private List<User> likes;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_COMPANY_QUESTION,
			joinColumns = @JoinColumn(name = FIELD_QUESTION_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_COMPANY_ID)
			)
	private List<Company> companies;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = FIELD_SUBTOPIC_ID)
	private Subtopic subtopic;
	
	public Question() {
		
	}
	
	public Question(String question) {
		this.question = question;
	}

	public Question(String question, String dateCreated) {
		super();
		this.question = question;
		this.dateCreated = dateCreated;
		dateModified = dateCreated;
	}

	public int getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getCreatorUser() {
		return creatorUser;
	}

	public void setCreatorUser(User creatorUser) {
		this.creatorUser = creatorUser;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		forceLoadAnswers();
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Tag> getTags() {
		forceLoadTags();
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public List<Company> getCompanies() {
		forceLoadCompanies();
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<User> getLikes() {
		forceLoadLikes();
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Subtopic getSubtopic() {
		return subtopic;
	}

	public void setSubtopic(Subtopic subtopic) {
		this.subtopic = subtopic;
	}

	public void addAnswer(Answer answer) {
		forceLoadAnswers();
		if (answers == null)
			answers = new ArrayList<>();
		answers.add(answer);
		answer.setQuestion(this);
	}
	
	public void addTag(Tag tag) {
		forceLoadTags();
		if (tags == null)
			tags = new ArrayList<>();
		tags.add(tag);
	}
	
	public void addTags(List<Tag> tags) {
		forceLoadTags();
		if (this.tags == null)
			this.tags = new ArrayList<>();
		for (Tag tag : tags) {
			this.tags.add(tag);
		}
	}
	
	public void removeTag(Tag tag) {
		forceLoadTags();
		tags.remove(tag);
	}
	
	public void removeCompany(Company company) {
		forceLoadCompanies();
		companies.remove(company);
	}
	
	public void removeLike(User user) {
		forceLoadLikes();
		likes.remove(user);
	}
	
	public void addCompany(Company company) {
		forceLoadCompanies();
		if (companies == null)
			companies = new ArrayList<>();
		companies.add(company);
	}
	
	public void addLike(User user) {
		forceLoadCompanies();
		if (likes == null)
			likes = new ArrayList<>();
		likes.add(user);
		user.addLikedQuestion(this);
	}
	
	private void forceLoadAnswers() {
		try {
			this.answers.size();
		}catch (NullPointerException e) {
		}
	}
	
	private void forceLoadTags() {
		try {
			this.tags.size();
		}catch (NullPointerException e) {
		}
	}
	
	private void forceLoadCompanies() {
		try {
			this.companies.size();
		}catch (NullPointerException e) {
		}
	}
	
	private void forceLoadLikes() {
		try {
			this.likes.size();
		}catch (NullPointerException e) {
		}
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", creatorUser=" + creatorUser + "]";
	}

}
