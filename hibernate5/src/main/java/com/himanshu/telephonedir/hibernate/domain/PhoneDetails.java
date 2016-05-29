package com.himanshu.telephonedir.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "phone_details")
public class PhoneDetails {
  @Id
  @GeneratedValue(generator = "IdGenerator", strategy = GenerationType.TABLE)
  @TableGenerator(name = "IdGenerator", pkColumnValue = "PhoneId", table = "sequence_table", allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @Column(name = "phone_num")
  private String phoneNum;

  @Column(name = "num_type")
  private String numberType;

  @Column(name = "is_active")
  private boolean active;

  @ManyToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private Person person;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public String getNumberType() {
    return numberType;
  }

  public void setNumberType(String numberType) {
    this.numberType = numberType;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

}
