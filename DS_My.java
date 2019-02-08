
public class DS_My<K extends Comparable<K>, V> implements DataStructureADT {

  // TODO may wish to define an inner class
  // for storing key and value as a pair
  // such a class and its members should be "private"

  protected class node {
    private node next;
    private node prev;
    private Comparable key;
    private Object value;

    public node(Comparable k, Object v) {
      this.key = k;
      this.value = v;
      this.prev = null;
      this.next = null;
    }

    public void setPrev(node prev) {
      this.prev = prev;
    }
    
    public node getPrev() {
      return this.prev;
    }
    
    public void setNext(node next) {
      this.next = next;
    }

    public node getNext() {
      return this.next;
    }

    public Object getValue() {
      return this.value;
    }
    
    public Comparable getKey() {
      return this.key;
    }
  }



  // Private Fields of the class
  private int size;
  private node head;
  private node tail;

  public DS_My() {
    this.size = 0;
    this.head = null;
  }


  // Add the key,value pair to the data structure and increases size.
  // If key is null, throws IllegalArgumentException("null key");
  // If key is already in data structure, throws RuntimeException("duplicate key");
  // can accept and insert null values

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
      this.tail = newNode;
      newNode.setPrev(null);
    } else if(this.head.getNext()== this.tail) {
      newNode.setPrev(head);
    }
    else {
      newNode.setPrev(tail);
      tail.setNext(newNode);
    }
    
    this.size++;

  }

  // If key is found, Removes the key from the data structure and decreases size
  // If key is null, throws IllegalArgumentException("null key") without decreasing size
  // If key is not found, returns false.
  @Override
  public boolean remove(Comparable k) {
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

    if (contains(k)) {
      //change reference
      node deleteNode = nodeAtKey(k);
        
      if(deleteNode.equals(this.head)) {
        this.head = null;
      } else {
        deleteNode.getPrev().setNext(deleteNode.getNext());
        deleteNode.getNext().setPrev(deleteNode.getPrev());
      }
      
     
      this.size--;
      
      return true;
    } else    
    return false;
  }

  // Returns true if the key is in the data structure
  // Returns false if key is null or not present
  @Override
  public boolean contains(Comparable k) {
    //if nodeAtKey is not null, then true else false
    if(nodeAtKey(k) == null) return false;
    else return true;
  }

  // Returns the value associated with the specified key
  // get - does not remove key or decrease size
  // If key is null, throws IllegalArgumentException("null key") 
  //THIS ASSUMES KEY EXISTS
  @Override
  public Object get(Comparable k) {
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }
    
    return nodeAtKey(k).getValue();
  }

  @Override
  public int size() {
    return this.size;
  }
  
  public node nodeAtKey(Comparable k) {
    
    node currNode = this.head;
    
    
while(currNode != null){
      


      if(currNode.getKey() == k) {
        return currNode;
      }
      currNode = currNode.next;
      
}
    
    return null;
    
  }

}
