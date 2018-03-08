/*
 * public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()                 // construct an empty randomized queue
   public boolean isEmpty()                 // is the queue empty?
   public int size()                        // return the number of items on the queue
   public void enqueue(Item item)           // add the item
   public Item dequeue()                    // remove and return a random item
   public Item sample()                     // return (but do not remove) a random item
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing (optional)
}
 */
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item>
{
   private int n;
   private Item q[];
    public RandomizedQueue()
    {
        q=(Item[])new Object[2];
       n=0;
    }
    public boolean isEmpty()
    {
        return n==0;
    }
    public int size()
    {
        return n;
    }
    public void enqueue(Item item)
    {
        if(item==null)
            throw new IllegalArgumentException("Cannot enter a null input");
        if(n==q.length)
            resize(2*q.length);
        q[n++]=item;
    }
    public Item dequeue()
    {
        if(n==0)
            throw new java.util.NoSuchElementException("empty dequeue");
       int i=StdRandom.uniform(n);
       Item item=q[i];
       adjust(i);
       n--;
       if(n<q.length/4)
           resize(q.length/2);
       return item;
    }
    private void adjust(int i)
    {
        for(int j=i;j<n-1;j++)
            q[j]=q[j+1];
    }
    public Item sample()
    {
        if(n==0)
            throw new java.util.NoSuchElementException("empty dequeue");
       int i=StdRandom.uniform(n);
        return q[i];
    }
    private void resize(int x)
    {
        Item copy[]=(Item[]) new Object[x];
        int l = (x > q.length) ? q.length : x;
        for(int i=0;i<l;i++)
            copy[i]=q[i];
        q=copy;
    }
    public Iterator<Item> iterator() 
    { 
        
        return new ArrayIterator();
    }
    private class ArrayIterator implements Iterator<Item>
    {
        private int i;
           Item copy[];
        public ArrayIterator()
        {
             copy=(Item[])new Object[n]; 
        for(int i=0;i<n;i++)
            copy[i]=q[i];
        StdRandom.shuffle(copy);
            i=n;
        }
        public boolean hasNext(){ return i>0;}
        public void remove (){ throw new UnsupportedOperationException("this method is not supported!"); }
            public Item next()
            {
                 if(!hasNext())
                throw new java.util.NoSuchElementException ("No more items to return!");
              
                return copy[--i];
            }
        }
    }
            
        
           
       
        