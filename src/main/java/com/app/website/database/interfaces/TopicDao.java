package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.Domain;
import com.app.website.entity.Subtopic;
import com.app.website.entity.Topic;

public interface TopicDao {
	
	public int createTopic(Topic topic);
	public Topic getTopicByName(String topic);
	public Topic getTopicById(int id);
	public List<Topic> getTopicsByName(String topic);
	public List<Topic> getAllTopics();
	public List<Subtopic> getSubtopics(int id);
	public Domain getDomain(int topicId);
	public void updateTopic(int id, String topic);
	public void deleteTopic(int id);

}
