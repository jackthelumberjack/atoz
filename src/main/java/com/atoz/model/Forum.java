package com.atoz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 30-Dec-15.
 */
public class Forum {
    private ArrayList<ForumSubject> subjects;

    public Forum() {
        subjects = new ArrayList<ForumSubject>();
    }

    public void addSubject(ForumSubject s) {
        subjects.add(s);
    }

    public void removeSubject(int id) {
        for(int i=0;i<subjects.size();i++) {
            if(subjects.get(i).getId() == id) {
                subjects.remove(i);
                return;
            }
        }
    }

    public ForumSubject getById(int id) {
        for(int i=0;i<subjects.size();i++) {
            if(subjects.get(i).getId() == id) {
                return subjects.get(i);
            }
        }

        return null;
    }

    public ArrayList<ForumSubject> getSubjects() {
        return subjects;
    }

}
