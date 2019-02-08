import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }


  @Test
  void test00_empty_ds_size() {
    // System.out.println(dataStructureInstance.size());
    if (dataStructureInstance.size() != 0)
      fail(
        "data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }


  // test01_after_insert_one_size_is_one
  // insert one key value pair into the data structure and then confirm that size() is 1.
  
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("second", "ABC");

    if (dataStructureInstance.size() != 1)
      fail("data structure should have 1 element, with size=1, but size="
        + dataStructureInstance.size());
  }

  // insert one key,value pair and remove it, then confirm size is 0.
  // test02_after_insert_one_remove_one_size_is_0
  
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

  // insert a few key,value pairs such that one of them has the same key as an earlier one. Confirm
  // that a RuntimeException is thrown.
  // test03_duplicate_exception_is_thrown
  
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

  // insert some key,value pairs, then try removing a key that was not inserted. Confirm that the
  // return value is false.
  // test04_remove_returns_false_when_key_not_present
  
  @Test
  void test04_remove_returns_false_when_key_not_present() {


    if (dataStructureInstance.remove("second"))
      fail("No element is inside. Remove should return false, but returned true instead.");
  }

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

@Test
void test_insert600andRemove() {

  try {
    for(int i=0; i< 600; i++) {
      
      String key = i+"_key";
      
      dataStructureInstance.insert(key, "ABC");
//      System.out.println("Added " + key);
    }
    
  } catch (Exception e) {
    fail("Can't add 500+ elements");
  }
  
  try {
    for(int i=0; i< 600; i++) {
      String key = i+"_key";
      
      dataStructureInstance.remove(key);
//      System.out.println("Remove " + key);
    }
    
  } catch (Exception e) {
    fail("Can't remove 500+ elements");
  }
  

}


  // TODO: add tests to ensure that you can detect implementation that fail

  // Tip: consider different numbers of inserts and removes and how different combinations of insert
  // and removes


  // consider different sequences of inserts and removes. Check results of method like: size(),
  // contains(), remove(). Can you figure out if those methods work or not?

}
