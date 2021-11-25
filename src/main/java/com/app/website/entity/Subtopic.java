package com.app.website.entity;

import static com.app.website.util.DatabaseConstants.*;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = TABLE_SUBTOPIC)
public class Subtopic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_SUBTOPIC)
	private String subtopic;
	
	@ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = FIELD_TOPIC_ID)
	private Topic topic;
	
	@Transient
	@JsonIgnore
	private int topicId;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "subtopic")
	private List<Question> questions;
	
	public Subtopic() {
		
	}
	
	public Subtopic(String subtopic, int topicId) {
		this.subtopic = subtopic;
		this.topicId = topicId;
	}

	public int getId() {
		return id;
	}

	public String getSubtopic() {
		return subtopic;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSubtopic(String subtopic) {
		this.subtopic = subtopic;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public List<Question> getQuestions() {
		forceLoadQuestions();
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void forceLoadQuestions() {
		try {
			this.questions.size();
		} catch (NullPointerException e) {
		}
	}

	@Override
	public String toString() {
		return "Subtopic [id=" + id + ", subtopic=" + subtopic + ", topic=" + topic + "]";
	}

}
