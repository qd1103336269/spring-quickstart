package org.example.spring;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 模拟spring的实现
// 1. 通过带参构造器得到对应的配置文件
// 2. 通过dom4J解析配置文件拿到bean的id和class
// 3. 通过反射拿到对应的实例对象（遍历list集合， 通过对应的class属性， 利用class。forName(class).newInstance）
// 4. 通过id获取指定的实例化对象
public class MyClassPathXmlApplicationContext implements MyFactory{
    private List<MyBean> beanList;
    private Map<String, Object> beanMap = new HashMap<>();  // 存放实例化好的对象， 通过id获取指定的实例化对象
    // 1. 通过带参构造器得到对应的配置文件
    public MyClassPathXmlApplicationContext (String fileName) {
        // 2. 通过dom4J解析配置文件拿到bean的id和class
        this.parseXml(fileName);
        // 3. 通过反射拿到对应的实例对象（遍历list集合， 通过对应的class属性， 利用class。forName(class).newInstance）
        this.instanceBean();
    }


    // 2. 通过dom4J解析配置文件拿到bean的id和class
    // 获取解析器
    // 获取配置文件的url
    // 通过解析器解析配置文件
    // 通过xpath语法解析， 获取Beans标签下的bean标签
    // 返回一个集合
    // 判断集合是否为空
    // 不为空遍历集合
    // 获取bean标签的属性（id和class）
    // 获取mybean对象， 将id和class属性值设置到对象中， 再将对象设置到myBean的集合中
    private void parseXml (String fileName) {
        SAXReader saxReader = new SAXReader();
        URL url = this.getClass().getClassLoader().getResource(fileName);
        try {
            Document document = saxReader.read(url);
            XPath xPath = document.createXPath("beans/bean");
            List<Element> elementList = xPath.selectNodes(document);
            if(elementList != null && elementList.size() > 0) {
                beanList = new ArrayList<>();
                for (Element el: elementList) {
                    String id = el.attributeValue("id");
                    String clazz = el.attributeValue("class");
                    MyBean myBean = new MyBean(id, clazz);
                    beanList.add(myBean);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void instanceBean () {
        if(beanList != null && beanList.size() > 0) {
            for (MyBean bean: beanList) {
                String id = bean.getId();
                String clazz = bean.getClazz();
                try {
                    Object object = Class.forName(clazz).newInstance();
                    beanMap.put(id, object);
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public Object getBean (String id) {
        Object object = beanMap.get(id);
        return object;
    }
}
