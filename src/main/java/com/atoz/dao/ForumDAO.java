package com.atoz.dao;

import com.atoz.model.ForumPost;
import com.atoz.model.ForumSubject;

import java.util.List;

/**
 * Created by Sergiu on 15.01.2016.
 */
public interface ForumDAO {
  boolean saveTopic(ForumSubject forumSubject);
  List<ForumSubject> getAllTopics();
  boolean savePost(ForumPost forumPost);
  List<ForumPost> getPostsForTopic(int topicId);
}
