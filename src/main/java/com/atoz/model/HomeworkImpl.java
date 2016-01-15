package com.atoz.model;

import java.util.Date;

/**
 * Created by Raul Breje on 01/16/2016.
 */
public class HomeworkImpl implements Homework {

    private String name;
    private Date creationDate;
    private String description;
    private String value;
    private Date deadline;
    private String hwGroup;
    private HomeworkType type;

    public HomeworkImpl(String name, Date creationDate, String description, String value, Date deadline, String hwGroup, HomeworkType type) {
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
        this.value = value;
        this.deadline = deadline;
        this.hwGroup = hwGroup;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getHwGroup() {
        return hwGroup;
    }

    public void setHwGroup(String hwGroup) {
        this.hwGroup = hwGroup;
    }

    public HomeworkType getType() {
        return type;
    }

    public void setType(HomeworkType type) {
        this.type = type;
    }
}
