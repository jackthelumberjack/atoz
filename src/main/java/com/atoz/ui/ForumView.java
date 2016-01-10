package com.atoz.ui;

import com.atoz.model.Forum;
import com.atoz.model.ForumSubject;
import com.vaadin.client.metadata.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;
import com.vaadin.data.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 30-Dec-15.
 */
public class ForumView extends VerticalLayout {
    private Forum forum;
    private ListSelect list;
    private Button button;
    private TextField subjectTitle;
    private HorizontalLayout horizontal;
    private IndexedContainer container;
    private String userName;

    public ForumView(String userName) {
        forum = new Forum();
        button = new Button("Add topic");
        subjectTitle = new TextField();
        horizontal = new HorizontalLayout();
        list = new ListSelect();
        container = new IndexedContainer();
        this.userName = userName;
        initListeners();
        initContainer();
    }


    public void initListeners() {
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (subjectTitle.getValue().length() != 0) {
                    postSubject(forum.getSubjects().size(), subjectTitle.getValue());
                }

                constructForum();
            }
        });

        list.addValueChangeListener(new com.vaadin.data.Property.ValueChangeListener() {
            @Override
            public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
                goToForumTopic(event.getProperty().toString());

            }
        });

    }

    public void goToForumTopic(String topic) {
        ForumSubject subject = null;
        ArrayList<ForumSubject> subjects = forum.getSubjects();

        for(int i=0;i<subjects.size();i++) {
            if(subjects.get(i).getId() == Integer.parseInt(topic.split(" ")[0])) {
                subject = subjects.get(i);
            }
        }

        Notification.show(subject.toString());
        TopicView topics = new TopicView(subject,userName);
        topics.constructView();

        this.removeAllComponents();
        this.addComponent(topics);

    }

    public void initContainer() {
        ArrayList<ForumSubject> subj = forum.getSubjects();
        for(int i=0;i<subj.size();i++) {
            list.addItem(subj.get(i).toString());
        }
    }

    public void constructForum() {
        this.removeAllComponents();
        horizontal.removeAllComponents();
        horizontal.addComponent(button);
        horizontal.addComponent(subjectTitle);
        this.addComponent(horizontal);

        list.setSizeFull();
        list.setWidth("105%");
        list.setNullSelectionAllowed(false);
        list.setImmediate(true);
        this.addComponent(list);
    }

    public void postSubject(int id,String title) {
        ForumSubject forumSubject = new ForumSubject(id,title);
        forum.addSubject(forumSubject);
        list.addItem(forumSubject.toString());
    }

}
