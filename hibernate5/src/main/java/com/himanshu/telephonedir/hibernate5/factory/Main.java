package com.himanshu.telephonedir.hibernate5.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static Logger LOGGER = LoggerFactory.getLogger(Main.class);
  public static void main(String[] args) {
    LOGGER.info("Started main. SessionFactory silently initialized in background.");
    LOGGER.info("Destroying SessionFactory.");
    SessionFactory.destroySessionFactory();
  }
}
