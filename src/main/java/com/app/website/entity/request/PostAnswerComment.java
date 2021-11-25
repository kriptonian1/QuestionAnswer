package com.app.website.entity.request;

public class PostAnswerComment {
	
	private int answerId;
	private int userId;
	private String comment;
	
	public PostAnswerComment() {
		
	}
	
	public PostAnswerComment(int answerId, int userId, String comment) {
		super();
		this.answerId = answerId;
		this.userId = userId;
		this.comment = comment;
	}

	public int getAnswerId() {
		return answerId;
	}

	public int getUserId() {
		return userId;
	}

	public String getComment() {
		return comment;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
