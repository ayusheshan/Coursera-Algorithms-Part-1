/*
 * public class PointSET {
 public         PointSET()                               // construct an empty set of points 
 public           boolean isEmpty()                      // is the set empty? 
 public               int size()                         // number of points in the set 
 public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
 public           boolean contains(Point2D p)            // does the set contain point p? 
 public              void draw()                         // draw all points to standard draw 
 public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary) 
 public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
 
 public static void main(String[] args)                  // unit testing of the methods (optional) 
 }
 */
import java.util.TreeSet;
import java.util.Iterator;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
public class PointSET
{
    private TreeSet<Point2D> ts;
    public PointSET()
    {
        ts = new TreeSet<Point2D>();
    }
    public  boolean isEmpty()
    {
        return ts.isEmpty();
    }
    public int size()
    {
        return ts.size();
    }
    public void insert(Point2D p)
    {
        if( p == null )
            throw new java.lang.IllegalArgumentException();
        ts.add( p );
    }
    public boolean contains(Point2D p)
    {
        if( p == null )
            throw new java.lang.IllegalArgumentException();
        return ts.contains(p);
    }
    public void draw()
    {
        Iterator<Point2D> i = ts.descendingIterator();
        while ( i.hasNext() )
        {
            Point2D x = i.next();
            x.draw();
        }
    }
    public Iterable<Point2D> range(RectHV rect) 
    {
        if( rect == null)
            throw new java.lang.IllegalArgumentException();
        ArrayList<Point2D> q = new ArrayList<Point2D>();
        Iterator<Point2D> i = ts.iterator();
        while(i.hasNext())
        {
            Point2D p = i.next();
            if(rect.contains(p))
                q.add(p);
        }
        return q;
    }
    public Point2D nearest(Point2D p)
    {
        if(isEmpty())
            return null;
        if(p == null)
            throw new java.lang.IllegalArgumentException();
        Iterator<Point2D> i = ts.iterator();
        double min = Double.POSITIVE_INFINITY;
        Point2D near=null;
        while( i.hasNext())
        {
            Point2D pcur = i.next();
            if(p.distanceSquaredTo(pcur) < min)
            {
                min =  p.distanceSquaredTo(pcur);
                near = pcur;
            }
        }
        return near;
    }
}