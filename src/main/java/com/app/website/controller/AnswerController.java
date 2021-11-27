package com.app.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.website.database.interfaces.AnswerDao;
import com.app.website.entity.Answer;
import com.app.website.entity.AnswerComment;
import com.app.website.entity.Question;
import com.app.website.entity.User;

@Configuration
@RestController
public class AnswerController {
	
	@Autowired
	private AnswerDao answerDao;
	private static final String STATUS_SUCCESS = "SUCCESS";
	private static final String STATUS_ERROR = "ERROR";
	
	@PostMapping(value = "/answer/user/{userId}/question/{questionId}")
	public int postAnswer(@PathVariable int userId, @PathVariable int questionId, @RequestBody String answer) {
		return answerDao.postAnswer(userId, questionId, answer);
	}
	
	@GetMapping(value = "/answers/question/{questionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Answer> getAnswersToQuestion(@PathVariable int questionId){
		return answerDao.getAnswersToQuestion(questionId);
	}
	
	@GetMapping(value = "/user/{userId}/answers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Answer> getAnswersByUser(@PathVariable int userId){
		return answerDao.getAnswersByUser(userId);
	}
	
	@GetMapping(value = "/answer/{answerId}/question", produces = MediaType.APPLICATION_JSON_VALUE)
	public Question getQuestionToAnswer(@PathVariable int answerId) {
		return answerDao.getQuestionToAnswer(answerId);
	}
	
	@GetMapping(value = "/answer/{answerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Answer getAnswerById(@PathVariable int answerId) {
		return answerDao.getAnswerById(answerId);
	}
	
	@GetMapping(value = "/answer_comments/answer/{answerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnswerComment> getAnswerCommentsToAnswer(@PathVariable int answerId){
		return answerDao.getAnswerCommentsToAnswer(answerId);
	}
	
	@GetMapping(value = "user/{userId}/answer-comments", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnswerComment> getAnswerCommentsByUser(@PathVariable int userId){
		return answerDao.getAnswerCommentsByUser(userId);
	}
	
	@GetMapping(value = "/answer-comment/{answerCommentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AnswerComment getAnswerCommentsById(@PathVariable int answeCommentId){
		return answerDao.getAnswerCommentById(answeCommentId);
	}
	
	@PostMapping(value = "/answer-comment/user/{userId}/answer/{answerId}")
	public int postAnswerComment(@PathVariable int userId, @PathVariable int answerId, @RequestBody String answerComment) {
		return answerDao.postAnswerComment(userId, answerId, answerComment);
	}
	
	@PutMapping(value = "/answer/{answerId}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public String updateAnswer(@PathVariable int answerId, @RequestBody String answer) {
		try {
			answerDao.updateAnswer(answerId, answer);
			return STATUS_SUCCESS;
		}catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@PutMapping(value = "/answer-comment/{answerCommentId}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public String updateAnswerComment(@PathVariable int answerCommentId, @RequestBody String comment) {
		try {
			answerDao.updateAnswerComment(answerCommentId, comment);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@DeleteMapping("/answer/{answerId}")
	public String deleteAnswer(@PathVariable int answerId) {
		try {
			answerDao.deleteAnswer(answerId);
			return STATUS_SUCCESS;
		}catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@DeleteMapping("/answer-comment/{answerCommentId}")
	public String deleteAnswerComment(@PathVariable int answerCommentId) {
		try {
			answerDao.deleteAnswerComment(answerCommentId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@PutMapping(value = "/answer/{answerId}/like/{userId}")
	public String addAnswerLike(@PathVariable int answerId, @PathVariable int userId) {
		try {
			answerDao.addAnswerLike(answerId, userId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@GetMapping(value = "/answer/{answerId}/likes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAnswerLikes(@PathVariable int answerId){
		return answerDao.getAnswerLikes(answerId);
	}
	
	@PutMapping(value = "/answer-comment/{answerCommentId}/like/{userId}")
	public String addAnswerCommentLike(@PathVariable int answerCommentId, @PathVariable int userId) {
		try {
			answerDao.addAnswerCommentLike(answerCommentId, userId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@GetMapping(value = "/answer-comment/{answerCommentId}/likes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAnswerCommentLikes(@PathVariable int answeCommentId){
		return answerDao.getAnswerCommentLikes(answeCommentId);
	}
	
	@DeleteMapping(value = "/answer/{answerId}/like/{userId}")
	public String removeLikeFromAnswer(@PathVariable int answerId, @PathVariable int userId) {
		try {
			answerDao.removeAnswerLike(answerId, userId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@DeleteMapping(value = "/answer-comment/{answerCommentId}/like/{userId}")
	public String removeLikeFromAnswerComment(@PathVariable int answerCommentId, @PathVariable int userId) {
		try {
			answerDao.removeAnswerCommentLike(answerCommentId, userId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@GetMapping(value = "/answer-comment/{answerCommentId}/answer", produces = MediaType.APPLICATION_JSON_VALUE)
	public Answer getAnswerOfComment(@PathVariable int answerCommentId) {
		return answerDao.getAnswerOfComment(answerCommentId);
	}

}
