package com.app.website.database.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.website.database.interfaces.TopicDao;
import com.app.website.entity.Subtopic;
import com.app.website.entity.Topic;

@Repository
@Transactional
public class TopicDaoImpl implements TopicDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int createTopic(Topic topic) {
		Session session = sessionFactory.getCurrentSession();
		int id = -1;
		if (getTopicByName(topic.getTopic()) == null)
			id = (int) session.save(topic);
		return id;
	}

	@Override
	public Topic getTopicByName(String topic) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return (Topic) session.createQuery(String.format("from Topic where topic='%s'", topic)).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Topic getTopicById(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return (Topic) session.createQuery(String.format("from Topic where id='%d'", id)).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopicsByName(String topic) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(String.format("from Topic where topic like '%s'", topic)).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopics() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Topic").getResultList();
	}

	@Override
	public boolean updateTopic(int id, String topic) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Topic t = session.get(Topic.class, id);
			t.setTopic(topic);
			session.save(t);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteTopic(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Topic t = session.get(Topic.class, id);
			session.delete(t);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public List<Subtopic> getSubtopics(int id){
		List<Subtopic> subtopics = null;
		try {
			Topic topic = getTopicById(id);
			topic.forceLoadSubtopics();
			subtopics = topic.getSubtopics();
		}catch (Exception e) {
		}
		return subtopics;
	}

}
