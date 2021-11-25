package com.app.website.database.dao;

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

	@Override
	public int postAnswer(int userId, int questionId, String answer) {
		Session session = sessionFactory.getCurrentSession();
		Answer a = new Answer(answer);
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
		user.forceLoadAnswers();
		return user.getAnswers();
	}

	@Override
	public List<Answer> getAnswersToQuestion(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		question.forceLoadAnswers();
		return question.getAnswers();
	}

	@Override
	public List<AnswerComment> getAnswerCommentsToAnswer(int answerId) {
		Session session = sessionFactory.getCurrentSession();
		Answer answer = session.get(Answer.class, answerId);
		answer.forceLoadAnswerComments();
		return answer.getAnswerComments();
	}
	
	@Override
	public List<AnswerComment> getAnswerCommentsByUser(int answerId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, answerId);
		user.forceLoadAnswerComments();
		return user.getAnswerComments();
	}

	@Override
	public int postAnswerComment(int userId, int answeId, String comment) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		Answer answer = session.get(Answer.class, answeId);
		AnswerComment answerComment = new AnswerComment(comment);
		answer.addAnswerComment(answerComment);
		user.addAnswerComment(answerComment);
		int id = (int) session.save(answerComment);
		session.update(answer);
		session.update(user);
		return id;
	}

	@Override
	public boolean updateAnswer(int answerId, String answer) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Answer a = session.get(Answer.class, answerId);
			a.setAnswer(answer);
			session.update(a);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateAnswerComment(int answerCommentId, String comment) {
		Session session = sessionFactory.getCurrentSession();
		try {
			AnswerComment a = session.get(AnswerComment.class, answerCommentId);
			a.setComment(comment);
			session.update(a);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteAnswer(int answerId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Answer answer = session.get(Answer.class, answerId);
			System.out.println(answer);
			session.delete(answer);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAnswerComment(int answerCommentId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			AnswerComment answerComment = session.get(AnswerComment.class, answerCommentId);
			session.delete(answerComment);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
		answer.forceLoadAnswerLikes();
		return answer.getUsersLiked();
	}

	@Override
	public boolean addAnswerLike(int answerId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Answer answer = session.get(Answer.class, answerId);
			User user = session.get(User.class, userId);
			answer.addLikedUser(user);
//			session.update(user);
			session.update(answer);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeAnswerLike(int answerId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Answer answer = session.get(Answer.class, answerId);
			User user = session.get(User.class, userId);
			answer.removeLikedUser(user);
//			session.update(user);
			session.update(answer);
			return true;
		}catch (Exception e) {
			return false;
		}
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
		answerComment.forceLoadAnswerCommentLikes();
		return answerComment.getUsersLiked();
	}

	@Override
	public boolean addAnswerCommentLike(int answerCommentId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			AnswerComment answerComment = session.get(AnswerComment.class, answerCommentId);
			User user = session.get(User.class, userId);
			answerComment.addLikedUser(user);
			session.save(answerComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeAnswerCommentLike(int answerCommentId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			AnswerComment answerComment = session.get(AnswerComment.class, answerCommentId);
			User user = session.get(User.class, userId);
			answerComment.removeLikedUser(user);
			session.save(answerComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	

}
