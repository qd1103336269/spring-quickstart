package org.example;

import org.example.Dao.UserDao;
import org.example.service.UserService;
import org.example.spring.MyClassPathXmlApplicationContext;
import org.example.spring.MyFactory;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        MyFactory myFactory = new MyClassPathXmlApplicationContext("spring.xml");
        // IOC容器中 实例化对象为单例
        UserDao userDao = (UserDao) myFactory.getBean("userDao");
        userDao.testDao();

        UserService userService = (UserService) myFactory.getBean("userService");
        userService.testService();

        UserDao userDao1 = (UserDao) myFactory.getBean("userDao");
        userDao1.testDao();

        UserDao userDao2 = (UserDao) myFactory.getBean("userDao");
        userDao2.testDao();
        System.out.println(userDao);
        System.out.println(userDao1);
        System.out.println(userDao2);
    }
}
