/*public class Deque<Item> implements Iterable<Item> {
 public Deque()                           // construct an empty deque
 public boolean isEmpty()                 // is the deque empty?
 public int size()                        // return the number of items on the deque
 public void addFirst(Item item)          // add the item to the front
 public void addLast(Item item)           // add the item to the end
 public Item removeFirst()                // remove and return the item from the front
 public Item removeLast()                 // remove and return the item from the end
 public Iterator<Item> iterator()         // return an iterator over items in order from front to end
 public static void main(String[] args)   // unit testing (optional)
 }
 */
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item>
{  private Node first;
    private Node last;
    
    private class Node
    {
        private Item item;
        private Node next;
    }
    public Deque()
    {    first=null;
        last=null;       
    }
    public boolean isEmpty()
    {
        if(first==null && last==null)
            return true;
        return false;
    }
    
    public int size()
    { Node pointer=first; int n=0;
        if(isEmpty())
            return 0;
        while(pointer!=last)
        {    n++; 
            pointer=pointer.next;}
        return n+1;
    }
    public void addFirst(Item item)
    {
        if(item==null)
            throw  new IllegalArgumentException ("cannot add null item");
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        if(last==null)
            last=first;
    }
    public void addLast(Item item)
    {
        if(item==null)
            throw  new IllegalArgumentException ("cannot add null item");
        
        Node n=new Node();
        n.item=item;
        if(last==null)
        { last=n;
            first=last;
            return;
        }
        last.next=n;
        last=n;
        if(first==null)
            first=last;
    }
    public Item removeFirst()
    {
        if(isEmpty())
            throw new java.util.NoSuchElementException ("Dequeue is Empty!");
        if(first==last)
            last=null;
        Node removed=first;
        first=first.next;
        removed.next=null;
        return removed.item;
    }
    public Item removeLast()
    {
        if(isEmpty())
            throw new java.util.NoSuchElementException ("Dequeue is Empty!");
        if(first==last)
        {
            Node removed=last;   
            first=null;
            last=null;
            return removed.item;
        }
        Node pointer=first;
        while(pointer.next.next!=null)
            pointer=pointer.next;
        Node removed=last;
        last=pointer;
        last.next=null;
        return removed.item;
    }
    public Iterator<Item> iterator() 
    {
        return new ListIterator(); 
    }
    private class ListIterator implements Iterator<Item>
    {
        private Node current=first; 
        public ListIterator()
        {         
            current=first;
        }
        public boolean hasNext() { return current != null; }
        public void remove() {throw new UnsupportedOperationException("this method is not supported!"); }
        public Item next()
        {
            if(!hasNext())
                throw new java.util.NoSuchElementException ("No more items to return!"); 
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args)
    {
        Deque d=new Deque();
        Iterator i=d.iterator();
        while(i.hasNext())
        {
            Object element = i.next();
            StdOut.println(element);
        }
    }
    
}


