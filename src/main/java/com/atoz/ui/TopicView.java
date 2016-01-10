package com.atoz.ui;

import com.atoz.model.Forum;
import com.atoz.model.ForumPost;
import com.atoz.model.ForumSubject;
import com.vaadin.ui.*;

import java.util.ArrayList;

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

    public TopicView(ForumSubject forumSubject, String userName) {
        list = new ListSelect(forumSubject.getTitle());
        this.forumSubject = forumSubject;
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
                postMessage(text.getValue());
                Notification.show("alksndl");
            }
        });
    }

    public void initTopics() {
        ArrayList<ForumPost> messages = forumSubject.getPosts();

        for(int i=0;i<messages.size();i++) {
            list.addItem(messages.toString());
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

    public void postMessage(String message) {
        ForumPost p = new ForumPost(userName,message,forumSubject.getPosts().size());
        forumSubject.addPost(p);
        list.addItem(p.toString());
    }
}
