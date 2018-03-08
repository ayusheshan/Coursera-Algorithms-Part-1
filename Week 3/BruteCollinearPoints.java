/*
 * public class BruteCollinearPoints {
 public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
 public           int numberOfSegments()        // the number of line segments
 public LineSegment[] segments()                // the line segments
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class BruteCollinearPoints
{
    private  ArrayList<Point> arr ;
     private LineSegment ls[];
    public BruteCollinearPoints(Point[] points)
    {
        if(points==null )
            throw new java.lang.IllegalArgumentException();
        for(int i=0;i<points.length;i++)
            if(points[i]==null)
            throw new java.lang.IllegalArgumentException();
       arr= new ArrayList<Point>(points.length);    int n=0;
        for(int i=0;i<points.length;i++)
            for(int j=i+1;j<points.length;j++)
        {
            if( points[i].compareTo( points[j])==0)
                throw new  java.lang.IllegalArgumentException();
        }
        Point points1[]=points.clone();
        //  Arrays.sort(points,Point.slopeOrder());
        Arrays.sort(points1);
        for(int i=0;i<points1.length;i++)
        {
            for(int j=i+1;j<points1.length;j++)
            {
                double itoj=points1[i].slopeTo(points1[j]);
                for(int k=j+1;k<points1.length;k++)
                {
                    double jtok=points1[j].slopeTo(points1[k]);
                    
                    if( itoj == jtok )
                        for(int l=k+1;l<points1.length;l++)
                    {
                        double ktol=points1[k].slopeTo(points1[l]);
                        if ( jtok == ktol )
                        {
                             n++;
                            arr.add(points1[i]);
                           arr.add(points1[j]);
                           arr.add(points1[k]);
                            arr.add(points1[l]);
                        }
                    }
                }
            }
        }
        int x=0;
        
        ls= new LineSegment[n];
       // ls1=ls.clone();
       for(int i=0;i<arr.size();i+=4)
        {  if(arr.get(i)==null)
           break;
       // Point pt= new Point(arr.get(i).x,arr.get(i).y);
        Point pt=new Point(0,0);
        Point ptt=arr.get(i);
           Collections.sort(arr.subList(i,i+3),pt.slopeOrder());
          /*int pointer=0;
           for(int j=i;j<i+2;j++)
               if(arr.get(i).compareTo(arr.get(i+1))<0)
               pointer=j+1;*/
           ls[x]=new LineSegment(ptt,arr.get(i+3));
            ls[x++].draw();
            
        }       
    }
    public int numberOfSegments()
    {
        return ls.length;
    }
    public LineSegment[] segments() 
    {
        return ls.clone();
            
    }
}