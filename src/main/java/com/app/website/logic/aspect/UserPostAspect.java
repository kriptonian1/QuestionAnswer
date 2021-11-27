package com.app.website.logic.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.website.database.interfaces.UserDao;
import com.app.website.entity.Notification;
import com.app.website.entity.User;
import com.app.website.entity.request.PostQuestion;

/*
 * This handles all the user post methods.
 * 
 * Upon the event where user posts an answer/question/comment,
 * methods are executed to notify their followers about that event 
 * via notifications and emails
 * 
 */

@Aspect
@Component
public class UserPostAspect {
	
	@Autowired
	private UserDao userDao;
	
	@AfterReturning(pointcut = "execution(* com.app.website.database.interfaces.QuestionDao.postQuestion(..))", returning = "questionId")
	public void onQuestionPost(JoinPoint joinPoint, Object questionId) {
		if (Integer.parseInt(String.valueOf(questionId)) != -1) {
			PostQuestion postQuestion = (PostQuestion) joinPoint.getArgs()[0];
			User user = userDao.getUserById(postQuestion.getUserId());
			Notification notification = new Notification();
			notification.setUserId(postQuestion.getUserId());
			notification.setUrl(String.format("/QuestionAnswer/question/%d", Integer.parseInt(String.valueOf(questionId))));
			notification.setNotification(String.format("%s %s just posted a new question. Check it out over here -> %s", user.getFirstName(), user.getLastName(), notification.getUrl()));
			for (User u : user.getFollowers()) {
				userDao.addNotification(u.getId(), notification);
			}
		}
	}
	
	@AfterReturning(pointcut = "execution(* com.app.website.database.interfaces.AnswerDao.postAnswer(..))", returning = "answerId")
	public void onAnswerPost(JoinPoint joinPoint, Object answerId) {
		if (Integer.parseInt(String.valueOf(answerId)) != -1) {
			int userId = Integer.parseInt(String.valueOf(joinPoint.getArgs()[0]));
			int questionId = Integer.parseInt(String.valueOf(joinPoint.getArgs()[1]));
			User user = userDao.getUserById(userId);
			Notification notification = new Notification(); // To be sent to followers
			notification.setUserId(userId);
			notification.setUrl(String.format("/QuestionAnswer/question/%d", questionId));
			notification.setNotification(String.format("%s %s just posted an answer to a question. Check it out over here -> %s", user.getFirstName(), user.getLastName(), notification.getUrl()));
			for (User u : user.getFollowers()) {
				userDao.addNotification(u.getId(), notification);
			}
			Notification notification2 = new Notification(); //To be sent to the creator of the question
			notification2.setUserId(userId);
			notification2.setUrl(String.format("/QuestionAnswer/question/%d", questionId));
			notification2.setNotification(String.format("%s %s just posted an answer to your question. Check it out over here -> %s", user.getFirstName(), user.getLastName(), notification.getUrl()));
			userDao.addNotification(userId, notification2);
		}
	}
	
	@AfterReturning(pointcut = "execution(* com.app.website.database.interfaces.AnswerDao.postAnswer(..))", returning = "answerCommentId")
	public void onAnswerCommentPost(JoinPoint joinPoint, Object answerCommentId) {
		if (Integer.valueOf(String.valueOf(answerCommentId)) != -1) {
			int userId = Integer.parseInt(String.valueOf(joinPoint.getArgs()[0]));
			int answerId = Integer.parseInt(String.valueOf(joinPoint.getArgs()[1]));
			User user = userDao.getUserById(userId);
			Notification notification = new Notification(); // To be sent to followers
			notification.setUserId(userId);
			notification.setUrl(String.format("/QuestionAnswer/answer/%d", answerId));
			notification.setNotification(String.format("%s %s just posted a comment to an answer. Check it out over here -> %s", user.getFirstName(), user.getLastName(), notification.getUrl()));
			for (User u : user.getFollowers()) {
				userDao.addNotification(u.getId(), notification);
			}
			Notification notification2 = new Notification(); //To be sent to the creator of the question
			notification2.setUserId(userId);
			notification2.setUrl(String.format("/QuestionAnswer/answer/%d", answerId));
			notification2.setNotification(String.format("%s %s just posted a comment to your answer. Check it out over here -> %s", user.getFirstName(), user.getLastName(), notification.getUrl()));
			userDao.addNotification(userId, notification2);
		}
	}
	
//	
//	@AfterReturning(pointcut = "execution(* com.app.website.controller.QuestionController.postQuestion)", returning = "success")
//	public void notifyFollowersOnAnswerCommentPost(JoinPoint joinPoint, Object success) {
//		
//	}
	
}
