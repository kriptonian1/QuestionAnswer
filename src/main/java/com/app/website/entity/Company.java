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
@Table(name = TABLE_COMPANY)
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_NAME)
	private String name;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = TABLE_COMPANY_QUESTION,
			joinColumns = @JoinColumn(name = FIELD_COMPANY_ID),
			inverseJoinColumns = @JoinColumn(name = FIELD_QUESTION_ID)
			)
	private List<Question> questions;
	
	public Company() {
		
	}

	public Company(String name) {
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
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		forceLoadQuestions();
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
		return "Company [id=" + id + ", name=" + name + "]";
	}

}
