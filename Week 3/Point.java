/*
 * public class Point implements Comparable<Point> {
 public Point(int x, int y)                         // constructs the point (x, y)
 
 public   void draw()                               // draws this point
 public   void drawTo(Point that)                   // draws the line segment from this point to that point
 public String toString()                           // string representation
 
 public               int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
 public            double slopeTo(Point that)       // the slope between this point and that point
 public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
 }
 */
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;

public class Point implements Comparable<Point> 
{
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
 public Point(int x, int y)
 {
        this.x = x;
        this.y = y;
    }
  public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

  public void drawTo(Point that) 
      {   
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
   public String toString() 
   {
        return "(" + x + ", " + y + ")";
    }
public int compareTo(Point that)
{
    if ( this.y < that.y )
        return -1;
    else if ( this.y == that.y && this.x < that.x)
        return -1;
    else if( this.y == that.y && this.x == that.x )
        return 0;
    else 
        return 1;
}
public double slopeTo (Point that)
{
    double slope=0.0;
    if( that.x == this.x )
    {
        if ( this.y != that.y)
            slope = Double.POSITIVE_INFINITY;
        else if( this.y == that.y)
           slope = Double.NEGATIVE_INFINITY;
           
        
    }
    else
   slope = (double)(that.y - this.y)/(double)(that.x - this.x);
    if(slope==-0.0)
        slope+=0.0;
    return slope;
}
public Comparator<Point> slopeOrder()  
{
    return new byslope();
}
private class byslope implements Comparator<Point>
{
   public int compare(Point one , Point two)
    {
        if( slopeTo(one) < slopeTo(two) )
            return -1;
        else if ( slopeTo(one) > slopeTo(two) )
            return 1;
        else
            return 0;
    }
}
}
        