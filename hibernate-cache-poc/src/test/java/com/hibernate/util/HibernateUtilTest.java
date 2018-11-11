package com.hibernate.util;

import org.junit.Assert;
import org.junit.Test;

public class HibernateUtilTest {
  @Test
  public void testSessionFactory() {
    Assert.assertNotNull(HibernateUtil.getSessionFactory());
  }
}
