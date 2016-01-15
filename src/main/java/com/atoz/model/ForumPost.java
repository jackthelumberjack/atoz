package com.atoz.model;

import java.util.Date;

/**
 * Created by andrei on 30-Dec-15.
 */
public class ForumPost {

  private int topicId;
  private String author;
  private String message;
  private Date postDate;

  public ForumPost(int topicId, String author, String message, Date postDate) {
    this.topicId = topicId;
    this.author = author;
    this.message = message;
    this.postDate = postDate;
  }

  public int getTopicId() {
    return topicId;
  }

  public void setTopicId(int topicId) {
    this.topicId = topicId;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getPostDate() {
    return postDate;
  }

  public void setPostDate(Date postDate) {
    this.postDate = postDate;
  }

  public String toString() {
    return "[" + postDate + "] " + author + " : " + message;
  }

}
