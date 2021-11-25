package com.app.website.entity.request;

import java.util.List;

public class PostQuestion {
	
	private String question;
	private int companyId;
	private int subtopicId;
	private List<Integer> tagId;
	private int userId;
	
	public PostQuestion() {
		
	}
	
	public PostQuestion(String question, int companyId, int subtopicId, List<Integer> tagId, int userId) {
		super();
		this.question = question;
		this.companyId = companyId;
		this.subtopicId = subtopicId;
		this.tagId = tagId;
		this.userId = userId;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	
	public int getSubtopicId() {
		return subtopicId;
	}
	
	public List<Integer> getTagId() {
		return tagId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public void setSubtopicId(int subtopicId) {
		this.subtopicId = subtopicId;
	}
	
	public void setTagId(List<Integer> tagId) {
		this.tagId = tagId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PostQuestion [question=" + question + ", companyId=" + companyId + ", subtopicId=" + subtopicId
				+ ", tagId=" + tagId + ", userId=" + userId + "]";
	}
	
}
