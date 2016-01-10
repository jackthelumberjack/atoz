package com.atoz.model;

import java.util.*;

/**
 * Created by andrei on 30-Dec-15.
 */
public class ForumSubject {
    private ArrayList<ForumPost> posts;
    private int id;
    private String title;

    public ForumSubject(int id, String title) {
        posts = new ArrayList<ForumPost>();
        this.id = id;
        this.title = title;
    }

    public void addPost(ForumPost p) {
        posts.add(p);
    }

    public void removePost(int id) {
        for (int i=0; i<posts.size(); i++) {
            if(posts.get(i).getId() == id) {
                posts.remove(i);
                return;
            }
        }
    }

    public int getId() {
        return id;
    }

    public ForumPost getById(int id) {
        for (int i=0; i<posts.size(); i++) {
            if(posts.get(i).getId() == id) {
                return posts.get(i);
            }
        }

        return null;
    }

    public ArrayList<ForumPost> getPosts() {
        return posts;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return id + " " + title;
    }
}
