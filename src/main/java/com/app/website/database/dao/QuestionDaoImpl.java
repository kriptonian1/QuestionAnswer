package com.app.website.database.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.website.database.interfaces.QuestionDao;
import com.app.website.entity.Company;
import com.app.website.entity.Question;
import com.app.website.entity.Subtopic;
import com.app.website.entity.Tag;
import com.app.website.entity.Topic;
import com.app.website.entity.User;
import com.app.website.entity.request.PostQuestion;

@Repository
@Transactional
public class QuestionDaoImpl implements QuestionDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("deprecation")
	@Override
	public int postQuestion(PostQuestion requestQuestion) {
		//Assuming that all the entities are already created
		Session session = sessionFactory.getCurrentSession();
		Question question = new Question(requestQuestion.getQuestion(), new Date().toLocaleString());
		Subtopic subtopic = session.get(Subtopic.class, requestQuestion.getSubtopicId());
		User user = session.get(User.class, requestQuestion.getUserId());
		if (requestQuestion.getTagId() != null) {
			for (int tagId : requestQuestion.getTagId()) {
				question.addTag(session.get(Tag.class, tagId));
			}
		}
		if (requestQuestion.getCompanyId() != null) {
			for (int companyId : requestQuestion.getCompanyId()) {
				question.addCompany(session.get(Company.class, companyId));
			}
		}
		if (subtopic != null)
			question.setSubtopic(subtopic);
		int id = (int) session.save(question);
		user.addQuestion(question);
		session.update(user);
		return id;
	}
	
	@Override
	public Question getQuestionById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, id);
		return question;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Question> getAllQuestions(){
		Session session = sessionFactory.getCurrentSession();		
		List<Question> questions = session.createQuery("from Question").getResultList();
		return questions;
	}

	@Override
	public List<Question> getQuestionsFromUser(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		return user.getQuestions();
	}

	@Override
	public void deleteQuestion(int id) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, id);
		session.delete(question);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void removeTag(int questionId, int tagId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		question.setDateModified(new Date().toLocaleString());
		Tag tag = session.get(Tag.class, tagId);
		question.removeTag(tag);
		session.update(question);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void addTag(int questionId, int tagId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		question.setDateModified(new Date().toLocaleString());
		Tag tag = session.get(Tag.class, tagId);
		question.addTag(tag);
		session.update(question);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void removeCompany(int questionId, int companyId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		question.setDateModified(new Date().toLocaleString());
		Company company = session.get(Company.class, companyId);
		question.removeCompany(company);
		session.update(question);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void addCompany(int questionId, int companyId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		question.setDateModified(new Date().toLocaleString());
		Company company = session.get(Company.class, companyId);
		question.addCompany(company);
		session.update(question);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void editQuestion(int questionId, String question) {
		Session session = sessionFactory.getCurrentSession();
		Question q = session.get(Question.class, questionId);
		q.setDateModified(new Date().toLocaleString());
		q.setQuestion(question);
		session.update(q);
	}

	@Override
	public List<Question> getQuestionsByTag(int tagId) {
		Session session = sessionFactory.getCurrentSession();
		Tag tag = session.get(Tag.class, tagId);
		List<Question> questions = tag.getQuestions();
		return questions;
	}

	@Override
	public List<Question> getQuestionsByCompany(int companyId) {
		Session session = sessionFactory.getCurrentSession();
		Company company = session.get(Company.class, companyId);
		List<Question> questions = company.getQuestions();
		return questions;
	}

	@Override
	public List<Question> getQuestionsBySubtopic(int subtopicId) {
		Session session = sessionFactory.getCurrentSession();
		Subtopic subtopic = session.get(Subtopic.class, subtopicId);
		List<Question> questions = subtopic.getQuestions();
		return questions;
	}

	@Override
	public List<Question> getQuestionsByTopic(int topicId) {
		Session session = sessionFactory.getCurrentSession();
		Topic topic = session.get(Topic.class, topicId);
		topic.forceLoadSubtopics();
		List<Question> questions = new ArrayList<>();
		for (Subtopic subtopic : topic.getSubtopics()) {
			for (Question question : subtopic.getQuestions()) {
				questions.add(question);
			}
		}
		return questions;
	}

	@Override
	public List<Company> getCompanies(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		return question.getCompanies();
	}

	@Override
	public List<Tag> getTags(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		return question.getTags();
	}

	@Override
	public Subtopic getSubtopic(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		return question.getSubtopic();
	}

	@Override
	public void addLike(int questionId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		User user = session.get(User.class, userId);
		question.addLike(user);
		session.update(user);
		session.update(question);
	}

	@Override
	public List<User> getLikes(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		return question.getLikes();
	}

	@Override
	public void removeLike(int questionId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		User user = session.get(User.class, questionId);
		question.removeLike(user);
	}

	@Override
	public User getCreator(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		return question.getCreatorUser();
	}

}
