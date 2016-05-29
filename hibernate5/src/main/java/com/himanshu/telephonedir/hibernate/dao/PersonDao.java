package com.himanshu.telephonedir.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.himanshu.telephonedir.hibernate.domain.Person;
import com.himanshu.telephonedir.hibernate5.factory.SessionFactory;

public class PersonDao {
  public void save(Person person) {
    /*Person p = new Person();
    p.setFirstName("Himanshu");
    p.setLastName("Bhardwaj");
    
    PhoneDetails p1 = new PhoneDetails();
    p1.setActive(true);
    p1.setPerson(p);
    p1.setNumberType("test");
    p1.setPhoneNum("1234567");
    
    PhoneDetails p2 = new PhoneDetails();
    p2.setActive(true);
    p2.setPerson(p);
    p2.setNumberType("test123");
    p2.setPhoneNum("12345671234");
    
    Set<PhoneDetails> dets = new HashSet<>();
    dets.add(p1);
    dets.add(p2);
    p.setPhoneDetails(dets);*/
    
    try (Session session = SessionFactory.getSessionFactory().openSession()) {
      session.getTransaction().begin();
      session.save(person);
      session.getTransaction().commit();
    }
    
  }
  
  public List<Person> getAll() {
    String getAllHql = "from Person";
    try (Session session = SessionFactory.getSessionFactory().openSession()) {
      List<Person> persons = session.createCriteria(Person.class).list();
      return persons;
    }
  }
  
  public Person getById(Long id) {
    String getAllHql = "from Person";
    try (Session session = SessionFactory.getSessionFactory().openSession()) {
      List<Person> persons = session.createCriteria(Person.class).add(Restrictions.eq("id", id)).list();
      return persons != null && !persons.isEmpty() ? persons.get(0) : null;
    }
  }
}
