package com.app.website.logic.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.website.database.interfaces.CompanyDao;
import com.app.website.database.interfaces.MiscDao;
import com.app.website.database.interfaces.TagDao;
import com.app.website.database.interfaces.UserDao;

@Aspect
@Component
public class CleanupAspect {

	@Autowired
	private MiscDao miscDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TagDao tagDao;
	@Autowired
	private CompanyDao companyDao;
	
	@AfterReturning(pointcut = "execution(* com.app.website.controller.UserController.removeNotification(..))", returning = "status")
	public void afterRemovingNotification(JoinPoint joinPoint, Object status) {
		if (String.valueOf(status).equals("SUCCESS")) {
			int notificationId = Integer.parseInt(String.valueOf(joinPoint.getArgs()[1]));
			if (miscDao.getNotificationUsers(notificationId).size() == 0) {
				miscDao.deleteNotification(notificationId);
			}
		}
	}
	
//	@AfterReturning(pointcut = "execution(* com.app.website.controller.QuestionController.removeTag(..))", returning = "status")
//	public void afterRemovingTag(JoinPoint joinPoint, Object status) {
//		if (String.valueOf(status).equals("SUCCESS")) {
//			int tagId = Integer.parseInt(String.valueOf(joinPoint.getArgs()[1]).toString());
//			if (tagDao.getQuestionOfTag(tagId).size() == 0) {
//				tagDao.deleteTag(tagId);
//			}
//			System.out.println(tagId);
//		}
//	}
//	
//	@AfterReturning(pointcut = "execution(* com.app.website.controller.QuestionController.removeCompany(..))", returning = "status")
//	public void afterRemovingCompany(JoinPoint joinPoint, Object status) {
//		if (String.valueOf(status).equals("SUCCESS")) {
//			int companyId = Integer.parseInt(String.valueOf(joinPoint.getArgs()[0]).toString());
//			if (companyDao.getAllQuestionsOfCompany(companyId).size() == 0) {
//				companyDao.deleteCompany(companyId);
//			}
//			System.out.println(companyId);
//		}
//	}

}
