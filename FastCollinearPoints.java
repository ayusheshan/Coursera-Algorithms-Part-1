/*
 * public class FastCollinearPoints {
 public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
 public           int numberOfSegments()        // the number of line segments
 public LineSegment[] segments()                // the line segments
 }
 */
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
public class FastCollinearPoints 
{
    private  ArrayList<LineSegment> arr =new ArrayList<LineSegment>();
    
  
    public FastCollinearPoints(Point[] points)
    {
        if(points==null )
            throw new java.lang.IllegalArgumentException();
        
       for(int i=0;i<points.length;i++)
           if(points[i]==null)
           throw new java.lang.IllegalArgumentException();
        
        
        Point points1[]=points.clone();
          Arrays.sort(points1);
         
      if (hasDuplicate(points1)) 
            throw new java.lang.IllegalArgumentException();
        
                
        for (int i = 0; i < points1.length - 3; i++)
        {
            Arrays.sort(points1);
              
            Arrays.sort(points1,points1[i].slopeOrder());
          for(int p=0,first=1,last=2;last<points1.length;last++)
          {
              while(last < points1.length&& Double.compare(points1[p].slopeTo(points1[first]), points1[p].slopeTo(points1[last])) == 0) 
              last++;
              
                     if( last - first >= 3 && points1[p].compareTo ( points1[first]) < 0)
                    {                           
                            arr.add(new LineSegment( points1[p] , points1 [last-1] ) );
                       }
                  first = last;
          }
        }
    }
    
    public int numberOfSegments()
    {
        return arr.size();
    }
    public LineSegment[] segments() 
    {
        return arr.toArray(new LineSegment[arr.size()]);
        
    }
     private boolean hasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    }

    
 public static void main(String args[])
    {
        Point p[] =new Point[4];
        p[0]=new Point(2,1);
        p[1]=new Point(2,99);
        p[2]=new Point(2,8);
        p[3]=new Point(2,7);
        //Arrays.sort(p);
        ArrayList<Point> arr=new ArrayList<Point>();
        arr.add(p[0]);
        arr.add(p[1]);
        arr.add(p[2]);
        arr.add(p[3]);
        //Collections.sort(arr);
        System.out.println(arr.get(0).toString());
        System.out.println(arr.get(1).toString());
        System.out.println(arr.get(2).toString());
        System.out.println(arr.get(3).toString());
 }}