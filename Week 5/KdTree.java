import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.ArrayList;
public class KdTree
{
    private ArrayList<Point2D> arr ;
    private node root;
    private node champion;
    public KdTree()
    {
    }
    private static class node
    {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private node lb;        // the left/bottom subtree
        private node rt;        // the right/top subtree
        private int size;
        public node(Point2D p1, RectHV r, int s)
        {
            p = p1;
            rect = r;
            size = s;
        }
    }
    public boolean isEmpty()
    {
        if ( root == null)
            return true;
        return false;
    }
    public int size()
    {
        return size(root);
    }
    private int size(node cur)
    {
        if(cur == null)
            return 0;
        else
            return cur.size;
    }
    public void insert(Point2D p)
    {
        if( p == null)
            throw new java.lang.IllegalArgumentException();
        root = put(root,p,1,new RectHV(0,0,1,1));
    }
    private node put(node cur, Point2D p, int align, RectHV r)
    {
        if(cur == null)
            return new node(p,r,1);
        if(p.equals(cur.p))
            return cur;
        if(align == 1)
        {
            if(p.x() < cur.p.x())
                cur.lb = put(cur.lb , p , align * (-1) ,new RectHV(r.xmin(),r.ymin(),cur.p.x(),r.ymax()));
            else
                cur.rt = put(cur.rt , p , align * (-1),new RectHV(cur.p.x(),r.ymin(),r.xmax(),r.ymax()) );
        }
        else if(align == -1)
        {
            if(p.y() < cur.p.y())
                cur.lb = put(cur.lb , p , align * (-1),new RectHV(r.xmin(),r.ymin(),r.xmax(),cur.p.y())) ;
            else
                cur.rt = put(cur.rt , p , align * (-1),new RectHV(r.xmin(),cur.p.y(),r.xmax(),r.ymax())) ;
        }
        cur.size = 1 + size(cur.lb) + size(cur.rt);
        return cur;
    }
    public boolean contains(Point2D p)
    {
        if(p == null)
            throw new java.lang.IllegalArgumentException();
        return get(root,p,1) != null;
    }
    private node get(node cur,Point2D p,int orient)
    {
        if(cur == null)
            return null;
        if(p.equals(cur.p))
            return cur;
        if(orient == 1)
        {
            if(p.x() < cur.p.x())
                return get(cur.lb , p ,orient * (-1));
            else 
                return get(cur.rt , p, orient * (-1));
        }
        else if(orient == -1)
        {
            if(p.y() < cur.p.y())
                return get(cur.lb , p ,orient * (-1));
            else 
                return get(cur.rt , p, orient * (-1));
        }
        return cur;
    }
    public void draw()
    {}
    public Iterable<Point2D> range(RectHV rect)
    {
        arr = new ArrayList<Point2D>();
        if(rect == null)
            throw new java.lang.IllegalArgumentException();
        rec(root,rect);
        return arr;
    }
    private void rec(node cur,RectHV rect)
    {
        if(cur != null && rect.intersects(cur.rect) )
        {
            if(rect.contains(cur.p))
                arr.add(cur.p);
            rec(cur.lb,rect);
            rec(cur.rt,rect);
        }
        else
            return ;
    }
    public Point2D nearest(Point2D p)
    {
        if(p == null)
            throw new java.lang.IllegalArgumentException();
        if(root == null)
            return null;
        champion = root;
        find(p,root,1);
        return champion.p;
    }
    private void find(Point2D p, node cur, int orient)
    {
        if(cur == null)
            return;
        
        if(cur.p.distanceSquaredTo(p) < champion.p.distanceSquaredTo(p))
        {
            champion = cur;
        }
        if( orient == 1)
        {
            if(p.x() < cur.p.x())
            {
                find(p , cur.lb , orient * (-1));
                if(cur.rt != null && cur.rt.rect.distanceSquaredTo(p) < champion.p.distanceSquaredTo(p))
                    find(p , cur.rt , orient * (-1));
            }
            else
            {
                find(p, cur.rt, orient * (-1));
                if(cur.lb != null && cur.lb.rect.distanceSquaredTo(p) < champion.p.distanceSquaredTo(p))
                    find(p, cur.lb ,orient * (-1));
            }
        }
        if( orient == -1)
        {
            if(p.y() < cur.p.y())
            {
                find(p , cur.lb , orient * (-1));
                if(cur.rt != null && cur.rt.rect.distanceSquaredTo(p) < champion.p.distanceSquaredTo(p))
                    find(p , cur.rt , orient * (-1));
            }
            else
            {
                find(p, cur.rt, orient * (-1));
                if(cur.lb != null && cur.lb.rect.distanceSquaredTo(p) < champion.p.distanceSquaredTo(p))
                    find(p, cur.lb ,orient * (-1));
            }
        }
        /*if(cur.lb == null)
        {
            if(champion.p.distanceSquaredTo(p) > cur.rt.p.distanceSquaredTo(p))
                champion = cur.rt;
            return;
        }
        if(cur.rt == null)
        {
            if(champion.p.distanceSquaredTo(p) > cur.lb.p.distanceSquaredTo(p))
                champion = cur.lb;
            return;
        }
        if(cur.lb.rect.distanceSquaredTo(p) > cur.rt.rect.distanceSquaredTo(p))
        {
            if(champion.p.distanceSquaredTo(p) > cur.rt.p.distanceSquaredTo(p))
            {
                champion = cur.rt;
                find(p, cur.rt);
            }
                if(champion.p.distanceSquaredTo(p) > cur.lb.rect.distanceSquaredTo(p) && cur!=null)
                    find(p, cur.lb);
            
        }
        else if(cur.lb.rect.distanceSquaredTo(p) < cur.rt.rect.distanceSquaredTo(p))
        {
            if(champion.p.distanceSquaredTo(p) > cur.lb.p.distanceSquaredTo(p))
            {
                champion = cur.rt;
                find(p, cur.lb);
            }
                if(champion.p.distanceSquaredTo(p) > cur.rt.rect.distanceSquaredTo(p) && cur!=null)
                    find(p, cur.rt);
          
        }*/   
        return;
    }
}