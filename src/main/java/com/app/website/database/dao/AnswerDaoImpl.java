package com.app.website.database.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.website.database.interfaces.AnswerDao;
import com.app.website.entity.Answer;
import com.app.website.entity.AnswerComment;
import com.app.website.entity.Question;
import com.app.website.entity.User;

@Repository
@Transactional
public class AnswerDaoImpl implements AnswerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	@Override
	public int postAnswer(int userId, int questionId, String answer) {
		Session session = sessionFactory.getCurrentSession();
		Answer a = new Answer(answer, new Date().toLocaleString());
		Question question = session.get(Question.class, questionId);
		User user = session.get(User.class, userId);
		user.addAnswer(a);
		question.addAnswer(a);
		int id = (int) session.save(a);
		session.update(user);
		session.update(question);
		return id;
	}

	@Override
	public Answer getAnswerById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Answer answer = session.get(Answer.class, id);
		return answer;
	}

	@Override
	public List<Answer> getAnswersByUser(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		return user.getAnswers();
	}

	@Override
	public List<Answer> getAnswersToQuestion(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		return question.getAnswers();
	}

	@Override
	public List<AnswerComment> getAnswerCommentsToAnswer(int answerId) {
		Session session = sessionFactory.getCurrentSession();
		Answer answer = session.get(Answer.class, answerId);
		return answer.getAnswerComments();
	}
	
	@Override
	public List<AnswerComment> getAnswerCommentsByUser(int answerId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, answerId);
		return user.getAnswerComments();
	}

	@SuppressWarnings("deprecation")
	@Override
	public int postAnswerComment(int userId, int answerId, String comment) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		Answer answer = session.get(Answer.class, answerId);
		AnswerComment answerComment = new AnswerComment(comment, new Date().toLocaleString());
		answer.addAnswerComment(answerComment);
		user.addAnswerComment(answerComment);
		int id = (int) session.save(answerComment);
		session.update(answer);
		session.update(user);
		return id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateAnswer(int answerId, String answer) {
		Session session = sessionFactory.getCurrentSession();
		Answer a = session.get(Answer.class, answerId);
		a.setDateModified(new Date().toLocaleString());
		a.setAnswer(answer);
		session.update(a);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateAnswerComment(int answerCommentId, String comment) {
		Session session = sessionFactory.getCurrentSession();
		AnswerComment a = session.get(AnswerComment.class, answerCommentId);
		a.setDateModified(new Date().toLocaleString());
		a.setComment(comment);
		session.update(a);
	}

	@Override
	public void deleteAnswer(int answerId) {
		Session session = sessionFactory.getCurrentSession();
		Answer answer = session.get(Answer.class, answerId);
		System.out.println(answer);
		session.delete(answer);
	}

	@Override
	public void deleteAnswerComment(int answerCommentId) {
		Session session = sessionFactory.getCurrentSession();
		AnswerComment answerComment = session.get(AnswerComment.class, answerCommentId);
		session.delete(answerComment);
	}

	@Override
	public Question getQuestionToAnswer(int answerId) {
		Session session = sessionFactory.getCurrentSession();
		Answer answer = session.get(Answer.class, answerId);
		return answer.getQuestion();
	}

	@Override
	public List<User> getAnswerLikes(int answerId) {
		Session session = sessionFactory.getCurrentSession();
		Answer answer = session.get(Answer.class, answerId);
		return answer.getUsersLiked();
	}

	@Override
	public void addAnswerLike(int answerId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Answer answer = session.get(Answer.class, answerId);
		User user = session.get(User.class, userId);
		answer.addLikedUser(user);
//		session.update(user);
		session.update(answer);
	}

	@Override
	public void removeAnswerLike(int answerId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Answer answer = session.get(Answer.class, answerId);
		User user = session.get(User.class, userId);
		answer.removeLikedUser(user);
//		session.update(user);
		session.update(answer);
	}

	@Override
	public AnswerComment getAnswerCommentById(int answerCommentId) {
		Session session = sessionFactory.getCurrentSession();
		AnswerComment answerComment = session.get(AnswerComment.class, answerCommentId);
		return answerComment;
	}

	@Override
	public Answer getAnswerOfComment(int answerCommentId) {
		Session session = sessionFactory.getCurrentSession();
		AnswerComment answerComment = session.get(AnswerComment.class, answerCommentId);
		return answerComment.getAnswer();
	}

	@Override
	public List<User> getAnswerCommentLikes(int answerCommentId) {
		Session session = sessionFactory.getCurrentSession();
		AnswerComment answerComment = session.get(AnswerComment.class, answerCommentId);
		return answerComment.getUsersLiked();
	}

	@Override
	public void addAnswerCommentLike(int answerCommentId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		AnswerComment answerComment = session.get(AnswerComment.class, answerCommentId);
		User user = session.get(User.class, userId);
		answerComment.addLikedUser(user);
		session.save(answerComment);
	}

	@Override
	public void removeAnswerCommentLike(int answerCommentId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		AnswerComment answerComment = session.get(AnswerComment.class, answerCommentId);
		User user = session.get(User.class, userId);
		answerComment.removeLikedUser(user);
		session.save(answerComment);
	}
	
	

}
