package com.atoz.model;

/**
 * Created by Raul Breje on 01/16/2016.
 */
public class QuestionImpl implements Question{

    private String name;
    private String description;
    private QuestionType type;
    private String value;

    public QuestionImpl(String name, String value, String description, QuestionType type){
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setType(QuestionType type){
        this.type = type;
    }

    public QuestionType getType(){
        return type;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }



}
