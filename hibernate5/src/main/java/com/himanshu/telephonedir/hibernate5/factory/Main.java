package com.himanshu.telephonedir.hibernate5.factory;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.himanshu.telephonedir.hibernate5.dao.PersonDao;
import com.himanshu.telephonedir.hibernate5.domain.Person;
import com.himanshu.telephonedir.hibernate5.domain.PhoneDetails;

public class Main {
  private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    SessionFactory.getSessionFactory();
    Main m = new Main();
    LOGGER.info("Started main. SessionFactory silently initialized in background.");
    int choice = -1;
    PersonDao pDao = new PersonDao();
    try (Scanner scanner = new Scanner(System.in)) {
      while (choice != 0) {
        System.out.println("~~~~~~~~~~~~  MENU  ~~~~~~~~~~~~");
        System.out.println("1.  Enter new details");
        System.out.println("2.  View all details");
        System.out.println("3.  View details by id");
        System.out.println("0.  Exit Program");
        System.out.println("~~~~~~~~~~  END MENU  ~~~~~~~~~~");
        choice = scanner.nextInt(); // This won't consume newline character
        scanner.nextLine(); // Dummy call to bypass the previously left-out new
                            // line character
        switch (choice) {
          case 1:
            m.addPersons(scanner, pDao);
            break;
          case 2:
            m.viewPersons(scanner, pDao);
            break;
          case 3:
            System.out.println("Enter id to look for:");
            long id = scanner.nextLong();
            scanner.nextLine(); //Just for bypassing the newline not skipped previously
            m.viewPersonById(scanner, pDao, id);
            break;
          case 0:
            break;
          default:
            System.out.println("Not a valid choice! Please retry.");
            continue;
        }
      }
    } finally {
      LOGGER.info("Destroying SessionFactory.");
      SessionFactory.destroySessionFactory();
    }
  }

  public void addPersons(Scanner scanner, PersonDao pDao) {
    boolean addMorePersons = true;

    while (addMorePersons) {
      Person person = new Person();
      System.out.println("Enter the first name:");
      String firstName = scanner.nextLine();
      person.setFirstName(firstName.trim());
      System.out.println("Enter the last name:");
      String lastName = scanner.nextLine();
      person.setLastName(lastName.trim());
      boolean addMoreNumbers = true;
      while (addMoreNumbers) {
        PhoneDetails phoneDetails = new PhoneDetails();
        System.out.println("Enter the telephone number:");
        String telephoneNumber = scanner.nextLine();
        phoneDetails.setPhoneNum(telephoneNumber);
        System.out.println("Enter the number type:");
        String numberType = scanner.nextLine();
        phoneDetails.setNumberType(numberType);
        phoneDetails.setPerson(person);
        phoneDetails.setActive(true);
        person.addMorePhoneDetails(phoneDetails);
        System.out.println("Do you want to enter more numbers (Y/N):");
        addMoreNumbers = scanner.nextLine().equalsIgnoreCase("Y") ? true : false;
      }
      pDao.save(person);
      System.out.println("Do you want to enter more persons (Y/N):");
      addMorePersons = scanner.nextLine().equalsIgnoreCase("Y") ? true : false;
    }
  }

  public void viewPersons(Scanner scanner, PersonDao pDao) {
    List<Person> persons = pDao.getAll();
    if (persons != null && !persons.isEmpty()) {
      for (int i = 0; i < persons.size(); i++) {
        System.out.println(String.format("Displaying RECORD# %1$d", i + 1));
        displayRecord(persons.get(i));
        System.out.println(String.format("End of RECORD# %1$d", i + 1));
      }
    } else {
      System.out.println("Nothing to display!");
    }
  }
  
  public void viewPersonById(Scanner scanner, PersonDao pDao, long id) {
    Person person = pDao.getById(id);
    if (person != null) {
      displayRecord(person);
    } else {
      System.out.println("Nothing to display!");
    }
  }

  private void displayRecord(Person p) {
    System.out.println("First Name: " + p.getFirstName());
    System.out.println("Last Name: " + p.getLastName());
    System.out.println("\tPhone Details");
    for (PhoneDetails pd : p.getPhoneDetails()) {
      System.out.println("\t\t" + pd.getPhoneNum() + "\t" + pd.getNumberType());
    }
  }

}
