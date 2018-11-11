package com.himanshu.telephonedir.hibernate5.dao;

import com.himanshu.telephonedir.hibernate5.domain.Person;
import com.himanshu.telephonedir.hibernate5.factory.SessionFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.StatelessSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class PersonDaoTest {
  private PersonDao personDao;

  @Before
  public void setup() {
    personDao = new PersonDao();
  }

  @Test
  public void testSessionFactory() {
    Assert.assertNotNull(SessionFactory.getSessionFactory());
  }

  @Test
  public void testSave() {
    Stream<Person> personStream = Stream.generate(new PersonSupplier());
    personStream.limit(100).forEach(p -> personDao.save(p));
    System.out.println(personDao.getAll());
    try (StatelessSession session = SessionFactory.getSessionFactory().openStatelessSession()) {
      ScrollableResults scrollableResults = session.createQuery("from Person p JOIN FETCH p.phoneDetails pd").setFetchSize(1).scroll(ScrollMode.FORWARD_ONLY);
      while(scrollableResults.next()) {
        Person p = (Person) scrollableResults.get()[0];
        System.out.println(p);
      }
    }

  }

  @AfterClass
  public static void cleanUp() {
    SessionFactory.getSessionFactory().openSession().createSQLQuery("SHUTDOWN").executeUpdate();
  }

  class PersonSupplier implements Supplier<Person> {
    @Override
    public Person get() {
      Person p = new Person();
      String nameString = RandomStringUtils.randomAlphanumeric(7);
      p.setFirstName(nameString);
      p.setLastName(nameString);
      return p;
    }
  }
}
