package com.app.website.entity;

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

import static com.app.website.util.DatabaseConstants.*;

@Entity
@Table(name = TABLE_DOMAIN)
public class Domain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_NAME)
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "domain")
	private List<Topic> topics;
	
	public Domain() {
		
	}
	
	public Domain(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Topic> getTopics() {
		forceLoadTopics();
		return topics;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTopics(List<Topic> topics) {
		forceLoadTopics();
		this.topics = topics;
	}
	
	public void addTopic(Topic topic) {
		if (topics == null)
			topics = new ArrayList<>();
		topics.add(topic);
		topic.setDomain(this);
	}
	
	private void forceLoadTopics() {
		this.topics.size();
	}

}
