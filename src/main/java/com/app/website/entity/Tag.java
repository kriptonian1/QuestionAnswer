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

@Entity
@Table(name = TABLE_TAG)
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_QUESTION_TAG,
			joinColumns = @JoinColumn(name = FIELD_TAG_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_QUESTION_ID)
			)
	private List<Question> questions;
	
	@Column(name = FIELD_NAME)
	private String name;
	
	public Tag() {
		
	}

	public Tag(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Question> getQuestions() {
		forceLoadQuestions();
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		forceLoadQuestions();
		this.questions = questions;
	}
	
	private void forceLoadQuestions() {
		try {
			this.questions.size();
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}

}
