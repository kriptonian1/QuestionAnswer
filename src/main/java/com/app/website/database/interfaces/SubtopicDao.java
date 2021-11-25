package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.Subtopic;
import com.app.website.entity.Topic;

public interface SubtopicDao {
	
	public int createSubtopic(Subtopic subtopic);
	public Subtopic getSubtopicByName(String name);
	public Subtopic getSubtopicById(int id);
	public List<Subtopic> getSubtopicsByName(String name);
	public List<Subtopic> getAllSubtopics();
	public Topic getTopic(int id);
	public boolean updateSubtopic(int id, String name);
	public boolean deleteSubtopic(int id);

}
