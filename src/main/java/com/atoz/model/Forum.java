package com.atoz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 30-Dec-15.
 */
public class Forum {
  private List<ForumSubject> subjects;

  public Forum() {
    subjects = new ArrayList<>();
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

  public void setSubjects(List<ForumSubject> subjects) {
    this.subjects = subjects;
  }

  public List<ForumSubject> getSubjects() {
    return subjects;
  }

}
