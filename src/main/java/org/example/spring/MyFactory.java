package org.example.spring;

public interface MyFactory {
    // 通过id获取对象
    public Object getBean(String id);
}
