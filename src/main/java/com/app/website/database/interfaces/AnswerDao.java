package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.Answer;
import com.app.website.entity.AnswerComment;
import com.app.website.entity.Question;
import com.app.website.entity.User;

public interface AnswerDao {
	
	
	//------------Answer-------------//
	public int postAnswer(int userId, int questionId, String answer);
	public Answer getAnswerById(int answerId);
	public List<Answer> getAnswersByUser(int userId);
	public List<Answer> getAnswersToQuestion(int questionId);
	public Question getQuestionToAnswer(int answerId);
	public List<User> getAnswerLikes(int answerId);
	public void addAnswerLike(int answerId, int userId);
	public void removeAnswerLike(int answerId, int userId);
	public void updateAnswer(int answerId, String answer);
	public void deleteAnswer(int answerId);
	
	//--------Answer Comment---------//
	public int postAnswerComment(int userId, int answerId, String comment);
	public AnswerComment getAnswerCommentById(int answerCommentId);
	public Answer getAnswerOfComment(int answerCommentId);
	public List<User> getAnswerCommentLikes(int answerCommentId);
	public List<AnswerComment> getAnswerCommentsToAnswer(int answerId);
	public List<AnswerComment> getAnswerCommentsByUser(int userId);
	public void updateAnswerComment(int answerCommentId, String comment);
	public void deleteAnswerComment(int answerCommentId);
	public void addAnswerCommentLike(int answerCommentId, int userId);
	public void removeAnswerCommentLike(int answerCommentId, int userId);

}
