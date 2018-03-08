import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class Permutation 
{
   public static void main(String[] args)
   {
       int k=Integer.parseInt(args[0]);
       RandomizedQueue rq=new RandomizedQueue();
       int x=0;
      while(StdIn.isEmpty()==false)
          rq.enqueue(StdIn.readString());
       while(x<k)
       { StdOut.println(rq.dequeue());
           x++;}
   }
}