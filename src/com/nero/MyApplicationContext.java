package com.nero;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

public class MyApplicationContext {
    Map<String, Object> bean;

    public void init() {
        SAXReader reader = new SAXReader();
        File file = new File("applicationContext.xml");
        try {
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Iterator<Element> beansIterator = rootElement.elementIterator();
            while (beansIterator.hasNext()) {
                Element ElementInBeans = beansIterator.next();
                String id = ElementInBeans.attribute("id").getValue();
                String className = ElementInBeans.attribute("class").getValue();
                Class clazz = Class.forName(className);
                Object instance = clazz.newInstance();
                Iterator<Element> beanIterator = ElementInBeans.elementIterator();
                while (beanIterator.hasNext()) {
                    Element elementInBean = beanIterator.next();
                    if (elementInBean.getName().equals("property")){
                        String name = elementInBean.attribute("name").getValue();
                        String value = elementInBean.attribute("value").getValue();
                        Field field = clazz.getDeclaredField(name);
                        field.setAccessible(true);
                        field.set(instance, value);
                    }
                }
                System.out.println(instance);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
