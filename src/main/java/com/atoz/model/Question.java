package com.atoz.model;

/**
 * Created by Raul Breje on 01/16/2016.
 */
public interface Question {

    void setName(String name);
    String getName();
    void setDescription(String description);
    String getDescription();
    void setType(QuestionType type);
    QuestionType getType();
    void setValue(String value);
    String getValue();

}
