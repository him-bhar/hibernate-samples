package com.hibernate.dao;

import java.io.Serializable;

@FunctionalInterface
public interface Keyable<T extends Serializable> {
  T getKey();
}
