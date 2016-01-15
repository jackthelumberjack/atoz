package com.atoz.ui;

import com.atoz.model.ForumPost;
import com.atoz.model.ForumSubject;
import com.atoz.service.ForumService;
import com.vaadin.ui.*;

import java.util.Date;
import java.util.List;

/**
 * Created by andrei on 10-Jan-16.
 */
public class TopicView extends VerticalLayout {
  private ListSelect list;
  private ForumSubject forumSubject;
  private HorizontalLayout horizontal;
  private Button send;
  private TextField text;
  private String userName;
  private ForumService forumService;


  public TopicView(ForumSubject forumSubject, String userName) {
    forumService = ContextAware.getBean(ForumService.class);
    list = new ListSelect(forumSubject.getTitle());
    this.forumSubject = forumSubject;
    forumSubject.setPosts(forumService.getPostsForTopic(forumSubject.getId()));
    horizontal = new HorizontalLayout();
    send = new Button("Send");
    text = new TextField();
    this.userName = userName;
    initTopics();
    initListeners();
  }

  public void initListeners() {
    send.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent event) {
        ForumPost forumPost = new ForumPost(forumSubject.getId(), userName, text.getValue(), new Date());
        postMessage(forumPost);
        forumService.savePost(forumPost);
        Notification.show("Post added");
      }
    });
  }

  public void initTopics() {
    List<ForumPost> messages = forumSubject.getPosts();
    for (ForumPost forumPost : messages) {
      list.addItem(forumPost.toString());
    }
  }

  public void constructView() {
    this.removeAllComponents();

    list.setSizeFull();
    list.setWidth("105%");
    list.setNullSelectionAllowed(false);
    this.addComponent(list);

    horizontal.removeAllComponents();
    horizontal.addComponent(text);
    horizontal.addComponent(send);
    this.addComponent(horizontal);
    this.setSizeFull();


  }

  public void postMessage(ForumPost forumPost) {
    forumSubject.addPost(forumPost);
    list.addItem(forumPost.toString());
  }
}
