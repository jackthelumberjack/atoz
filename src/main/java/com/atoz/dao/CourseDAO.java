package com.atoz.dao;

import com.atoz.model.Course;

/**
 * Created by Sergiu on 01.12.2015.
 */
public interface CourseDAO {

  public void saveCourse(Course course, String userName);

}
