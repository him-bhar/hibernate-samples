package com.hibernate.dao;

import com.hibernate.entity.Person;
import com.hibernate.util.HibernateUtil;
import net.sf.ehcache.CacheManager;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.StreamSupport;

public class PersonDaoTest {
  private static final Logger logger = LoggerFactory.getLogger(PersonDaoTest.class);
  private PersonDao personDao;

  @Before
  public void setupDao() {
    personDao = new PersonDao(HibernateUtil.getSessionFactory());
  }

  @Test
  public void save() {
    Person p = new Person();
    p.setFirstName("Himanshu");
    p.setLastName("Bhardwaj");
    Person savedPerson = personDao.save(p, Person.class);
    Assert.assertNotNull(savedPerson);
    Assert.assertNotNull(savedPerson.getId());
  }

  @Test
  public void saveAndFind() {

    Person p = new Person();
    p.setFirstName("Himanshu");
    p.setLastName("Bhardwaj");
    Person savedPerson = personDao.save(p, Person.class);
    //Arrays.stream(CacheManager.ALL_CACHE_MANAGERS.get(0).getCacheNames()).forEach(logger::info);
    Assert.assertThat(CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("personCache").getSize(), Matchers.equalTo(0));
    logger.info(savedPerson.toString());
    Assert.assertNotNull(savedPerson);
    Assert.assertNotNull(savedPerson.getId());

    //We are fetching for the first time here, hence a DB Lookup is required and entry will be persisted in L2C (Level 2 Cache).
    Person loadedPerson = personDao.findById(p.getKey(), Person.class);
    logger.info(loadedPerson.toString());
    Assert.assertNotNull(loadedPerson);
    Assert.assertNotNull(loadedPerson.getId());
    Assert.assertThat(CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("personCache").getSize(), Matchers.equalTo(1));

    //All these 100 lookups does not go into DB and are returned from L2C itself.
    for (int i = 0;i<100;i++) {
      loadedPerson = personDao.findById(p.getKey(), Person.class);
      logger.info(loadedPerson.toString());
      Assert.assertNotNull(loadedPerson);
      Assert.assertNotNull(loadedPerson.getId());
    }

  }
}
