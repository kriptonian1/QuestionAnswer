package com.app.website.entity.request;

public class PostAnswer {
	
	private int userId;
	private int questionId;
	private String answer;
	
	public PostAnswer() {
		
	}
	
	public PostAnswer(int userId, int questionId, String answer) {
		super();
		this.userId = userId;
		this.questionId = questionId;
		this.answer = answer;
	}

	public int getUserId() {
		return userId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "PostAnswer [userId=" + userId + ", questionId=" + questionId + ", answer=" + answer + "]";
	}

}
