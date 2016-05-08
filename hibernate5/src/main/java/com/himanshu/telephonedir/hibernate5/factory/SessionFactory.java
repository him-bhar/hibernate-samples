package com.himanshu.telephonedir.hibernate5.factory;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactory {
  
  private static org.hibernate.SessionFactory sessionFactory;
  
  static {
    buildSessionFactory();
  }
  
  private synchronized static void buildSessionFactory() {
    if (sessionFactory != null) {
      //NOOP
    } else {
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
          .configure("db/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
          .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
  }
  
  public static org.hibernate.SessionFactory getSessionFactory() {
    return sessionFactory;
  }
  
  public static void destroySessionFactory() {
    sessionFactory.close();
  }
  
}
