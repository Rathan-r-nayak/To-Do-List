package com.example.config;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateUtil {
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
    private static final SessionFactory sessionFactory = context.getBean(SessionFactory.class);

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    } 
}