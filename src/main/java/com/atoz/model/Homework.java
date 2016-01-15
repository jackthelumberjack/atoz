package com.atoz.model;

import java.util.Date;

/**
 * Created by Raul Breje on 01/16/2016.
 */
public interface Homework {

    String getName();

    void setName(String name);

    Date getCreationDate();

    void setCreationDate(Date creationDate);

    String getDescription();

    void setDescription(String description);

    String getValue();

    void setValue(String value);

    Date getDeadline();

    void setDeadline(Date deadline);

    String getHwGroup();

    void setHwGroup(String hwGroup);

    HomeworkType getType();

    void setType(HomeworkType type);
}
