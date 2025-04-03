package com.example.dao;

import org.hibernate.Session;
import com.example.model.*;
import com.example.config.HibernateUtil;
import org.hibernate.Transaction;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;



@Repository
@Transactional
public class TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveTask(Task task) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(task);
        tx.commit();
        session.close();
    }

    public List<Task> getAllTasks()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            return session.createQuery("FROM Task",Task.class).list();
        }
    }

    public Task getTaskById(int id)
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            return session.get(Task.class,id);
        }
    }

    public void updateTask(Task task)
    {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
            session.close();
        }
        catch (Exception e) 
        {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void deleteTask(Integer id)
    {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            Task task = session.get(Task.class,id);
            if(task != null)
            {
                session.delete(task);
                transaction.commit();
            }
        }
        catch(Exception e)
        {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


}
