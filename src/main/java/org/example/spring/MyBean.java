package org.example.spring;

/**
 * 1. 接收bean的class和id
 * */

public class MyBean {
    private String id;
    private String clazz;

    public MyBean () {
    }

    public MyBean (String id , String clazz) {
        this.id = id;
        this.clazz = clazz;
    }
    public String getId () {
        return id;
    }

    public String getClazz () {
        return clazz;
    }

    public void setId (String id) {
        this.id = id;
    }
    public void setClazz (String clazz) {
        this.clazz = clazz;
    }

}
