package com.hibernate.dao;

import com.hibernate.entity.Person;
import org.hibernate.SessionFactory;

public class PersonDao extends BaseDao<Person> {
  public PersonDao (SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
