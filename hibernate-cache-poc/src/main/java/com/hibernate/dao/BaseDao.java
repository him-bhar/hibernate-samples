package com.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;

public class BaseDao<T extends Keyable<?>> {
  private SessionFactory sessionFactory;

  public BaseDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public T save(T t, Class<T> clazz) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      session.saveOrUpdate(t);
      transaction.commit();
      return session.get(clazz, t.getKey());
    }
  }

  public T findById(Serializable key, Class<T> clazz) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
       T t = session.get(clazz, key);
       transaction.commit();
       return t;
    }
  }
}
