import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DataStructureADT
// Course: CS400, Spring 2019
//
// Author: Ajmain Naqib
// Email: naqib@wisc.edu
// Lecturer's Name: Deb Deppeler
//
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


/**
 * Test class for DataStructureADT that utilizes JUnit 5
 * 
 * @author: Ajmain Naqib
 * @email: naqib@wisc.edu
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance; // Instance of implmentated data structures

  protected abstract T createInstance();

  /**
   * Method for JUnit5
   * 
   * @throws Exception
   */
  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  /**
   * Method for JUnit5
   * 
   * @throws Exception
   */
  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  /**
   * Method for JUnit5 that runs before every new test class
   * 
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  /**
   * Method for JUnit5 runs after every new test class
   * 
   * @throws Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }

  /**
   * Tests if dataStructure's size is zero after initialization
   */
  @Test
  void test00_empty_ds_size() {
    // System.out.println(dataStructureInstance.size());
    if (dataStructureInstance.size() != 0)
      fail(
        "data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }


  /**
   * Insert one key value pair into the data structure and then confirm that size() is 1.
   */
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("second", "ABC");

    if (dataStructureInstance.size() != 1)
      fail("data structure should have 1 element, with size=1, but size="
        + dataStructureInstance.size());
  }


  /**
   * Insert one key,value pair and remove it, then confirm size is 0.
   * 
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_0() {
    dataStructureInstance.insert("second", "ABC");

    if (dataStructureInstance.size() != 1)
      fail("data structure should have 1 element, with size=1, but size="
        + dataStructureInstance.size());

    dataStructureInstance.remove("second");

    if (dataStructureInstance.size() != 0)
      fail(
        "data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }

  /**
   * insert a few key,value pairs such that one of them has the same key as an earlier one. Confirm
   * that a RuntimeException is thrown.
   */
  @Test
  void test03_duplicate_exception_is_thrown() {

    boolean pass = false;

    try {
      dataStructureInstance.insert("second", "ABC");
      dataStructureInstance.insert("second", "ABC");
    } catch (RuntimeException e) {
      pass = true;
    } catch (Exception e) {
      pass = false;
      fail("Should throw RuntimeException");
    }


    if (!pass)
      fail("Should throw RuntimeException");
  }


  /**
   * insert some key,value pairs, then try removing a key that was not inserted. Confirm that the
   * return value is false.
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {


    if (dataStructureInstance.remove("second"))
      fail("No element is inside. Remove should return false, but returned true instead.");
  }

  /**
   * Tests the functionality of inserting then removing and then inserting a new node.
   */
  @Test
  void test_addRemoveAdd() {

    try {
      dataStructureInstance.insert("second", "ABC");
      dataStructureInstance.remove("second");
      dataStructureInstance.insert("second", "ABC");
    } catch (RuntimeException e) {
      fail("Shouldn't throw RuntimeException");
    } catch (Exception e) {
      fail("Shouldn't throw any Exception");
    }
  }

  /**
   * Tests the functionality of inserting null key expecting IllegalArgumentException
   */
  @Test
  void test_insertNull() {
    boolean pass = false;

    try {
      dataStructureInstance.insert(null, "ABC");
    } catch (IllegalArgumentException e) {
      pass = true;
    } catch (Exception e) {
      pass = false;
      fail("Should throw IllegalArgumentException");
    }


    if (!pass)
      fail("Should throw IllegalArgumentException");

  }

  /**
   * Tests the functionality of inserting 600 elemnts and removing them
   */
  @Test
  void test_insert600andRemove() {

    try {
      for (int i = 0; i < 600; i++) {

        String key = i + "_key";

        dataStructureInstance.insert(key, "ABC");
        // System.out.println("Added " + key);
      }

    } catch (Exception e) {
      fail("Can't add 500+ elements");
    }

    try {
      for (int i = 0; i < 600; i++) {
        String key = i + "_key";



        if (!dataStructureInstance.remove(key)) {
          fail("Coudln't remove key: " + key);
        } ;
        // System.out.println("Removed " + key);
      }

    } catch (Exception e) {
      fail("Can't remove 500+ elements");
    }

    if (dataStructureInstance.size() != 0)
      fail(
        "data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }
}


