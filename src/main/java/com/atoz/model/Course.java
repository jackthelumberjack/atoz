package com.atoz.model;

import java.util.Date;

/**
 * Created by Sergiu on 01.12.2015.
 */
public class Course {
  private int id;
  private String name;
  private String code;
  private int departmentId;
  private Date startDate;
  private Date stopDate;
  private String content;

  public Course(int id, String name, String code, int departmentId, Date startDate, Date stopDate, String content) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.departmentId = departmentId;
    this.startDate = startDate;
    this.stopDate = stopDate;
    this.content = content;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public int getDepartmentId() {
    return departmentId;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getStopDate() {
    return stopDate;
  }

  public String getContent() {
    return content;
  }
}
