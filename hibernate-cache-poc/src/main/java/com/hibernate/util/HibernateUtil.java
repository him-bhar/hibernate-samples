package com.hibernate.util;

import com.hibernate.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * To integrate the JCache API and Ehcache with Hibernate, you need to specify the following configuration properties.
 * <p>
 * 1 - Enable second level cache (you can skip this property because default value is true for it).
 * <p>
 * hibernate.cache.use_second_level_cache = true
 * 2 - Specify cache region factory class.
 * <p>
 * hibernate.cache.region.factory_class = org.hibernate.cache.jcache.JCacheRegionFactory
 * 3 - Specify cache provider.
 * <p>
 * hibernate.javax.cache.provider = org.ehcache.jsr107.EhcacheCachingProvider
 */

public class HibernateUtil {
  private static volatile SessionFactory sessionFactory;
  private static volatile StandardServiceRegistry registry;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      synchronized (HibernateUtil.class) {
        if (sessionFactory == null) {
          try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            /*Map<String, Object> settings = new HashMap<>();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/BORAJI?useSSL=false");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "admin");
            settings.put(Environment.HBM2DDL_AUTO, "update");*//*
            // Enable second level cache (default value is true)
            settings.put(Environment.USE_SECOND_LEVEL_CACHE, true);

            // Specify cache region factory class
            settings.put(Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.jcache.JCacheRegionFactory");

            // Specify cache provider
            settings.put("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");*/

            //registryBuilder.applySettings(settings);
            registryBuilder.loadProperties("db/db.properties");
            registry = registryBuilder.build();
            MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Person.class);
            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
    return sessionFactory;
  }

  public static void close() {
    sessionFactory.close();
    if (registry != null) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }
}
