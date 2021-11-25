package com.app.website.logic.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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

//	@AfterReturning(pointcut = "execution(* com.app.website.controller.QuestionController.postQuestion)", returning = "success")
//	public void notifyFollowersOnQuestionPost(JoinPoint joinPoint, Object success) {
//		
//	}
//	
//	@AfterReturning(pointcut = "execution(* com.app.website.controller.QuestionController.postQuestion)", returning = "success")
//	public void notifyFollowersOnAnswerPost(JoinPoint joinPoint, Object success) {
//		
//	}
//	
//	@AfterReturning(pointcut = "execution(* com.app.website.controller.QuestionController.postQuestion)", returning = "success")
//	public void notifyFollowersOnAnswerCommentPost(JoinPoint joinPoint, Object success) {
//		
//	}
	
}
