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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = TABLE_TOPIC)
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_TOPIC)
	private String topic;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "topic")
	private List<Subtopic> subtopics;
	
	public Topic() {
		
	}
	
	public Topic(String topic) {
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public String getTopic() {
		return topic;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<Subtopic> getSubtopics() {
		forceLoadSubtopics();
		return subtopics;
	}

	public void setSubtopics(List<Subtopic> subtopics) {
		this.subtopics = subtopics;
	}
	
	public void addSubtopic(Subtopic subtopic) {
		if (subtopics == null)
			subtopics = new ArrayList<>();
		subtopics.add(subtopic);
		subtopic.setTopic(this);
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", topic=" + topic + "]";
	}
	
	public void forceLoadSubtopics() {
		try {
			subtopics.size();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
