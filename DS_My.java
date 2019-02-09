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
 * Implementation of DataStructureADT
 * 
 * @author: Ajmain Naqib
 * @email: naqib@wisc.edu
 */

public class DS_My<K extends Comparable<K>, V> implements DataStructureADT {

  /**
   * An inner class for for storing key and value as a pair
   * 
   * @author Naqib
   *
   */
  private class node {
    private node next; // Reference to next node
    private Comparable key; // reference to key
    private Object value; // reference to value

    /**
     * Creates a new Node
     * 
     * @param k
     * @param v
     */
    public node(Comparable k, Object v) {
      this.key = k;
      this.value = v;
      this.next = null;
    }

    /**
     * Sets reference of next node
     * 
     * @param next
     */
    public void setNext(node next) {
      this.next = next;
    }

    /**
     * Returns reference of next node
     * 
     */
    public node getNext() {
      return this.next;
    }

    /**
     * Returns reference of current value
     * 
     */
    public Object getValue() {
      return this.value;
    }

    /**
     * Returns reference of current key
     * 
     */
    public Comparable getKey() {
      return this.key;
    }


  }

  // Private Fields of the class
  private int size;
  private node head;
  private node tail;

  /**
   * Create new Datastructure implementation
   */
  public DS_My() {
    this.size = 0;
    this.head = null;
    this.tail = null;
  }



  /**
   * Add the key,value pair to the data structure and increases size. If key is null, throws
   * IllegalArgumentException("null key"); If key is already in data structure, throws
   * RuntimeException("duplicate key"); can accept and insert null values
   * 
   * param k, v
   */
  @Override
  public void insert(Comparable k, Object v) {
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

    if (contains(k)) {
      throw new RuntimeException("duplicate key");
    }

    node newNode = new node(k, v);

    if (this.head == null) {
      this.head = newNode;
    } else {

      this.tail.setNext(newNode);
    }

    this.tail = newNode;

    this.size++;
  }


  /**
   * If key is found, Removes the key from the data structure and decreases size If key is null,
   * throws IllegalArgumentException("null key") without decreasing size If key is not found, returns
   * false.
   * 
   * param k
   */

  @Override
  public boolean remove(Comparable k) {
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

     if (contains(k)) {
     // changes reference properly
     node deleteNode = nodeAtKey(k);
    
     if (deleteNode.equals(this.head)) {
       
     this.head = deleteNode.getNext();
     } else {
       
       node searchingNode = this.head.getNext();
       node prevNode = null;
       while(!searchingNode.equals(deleteNode)) {
         prevNode = searchingNode;
         searchingNode = searchingNode.getNext();
       }
       prevNode.setNext(searchingNode.getNext());
     }
    
     this.size--;
    
     return true;
     } else
    return false;
  }

  /**
   * Returns true if the key is in the data structure and Returns false if key is null or not present
   * 
   * param k
   */
  @Override
  public boolean contains(Comparable k) {
    // if nodeAtKey is not null, then true else false
    if (nodeAtKey(k) == null)
      return false;
    else
      return true;
  }


  /**
   * Returns the value associated with the specified key get - does not remove key or decrease size If
   * key is null, throws IllegalArgumentException("null key")
   * 
   * param k
   */
  @Override
  public Object get(Comparable k) {
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

    return nodeAtKey(k).getValue();
  }

  /**
   * Returns the number of nodes of current ds
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns the node at key k if found. Otherwise returns null
   * 
   * @param k
   * @returns the node at k
   */
  private node nodeAtKey(Comparable k) {

    node currNode = this.head;

    while (currNode != null) {

      if (currNode.getKey().compareTo(k) == 0) {
        return currNode;
      } else {
        currNode = currNode.getNext();
      }
    }
    
    return null;
    
  }

}
