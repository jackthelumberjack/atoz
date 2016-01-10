package com.atoz.model;

/**
 * Created by andrei on 30-Dec-15.
 */
public class ForumPost {
    private String author;
    private String title;
    private String message;
    private int id;

    public ForumPost(String author, String title, String message, int id) {
        this.author = author;
        this.message = message;
        this.title = title;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return author + " : " + message;
    }

}
