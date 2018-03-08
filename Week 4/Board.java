/*
 * public class Board {
 public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
 // (where blocks[i][j] = block in row i, column j)
 public int dimension()                 // board dimension n
 public int hamming()                   // number of blocks out of place
 public int manhattan()                 // sum of Manhattan distances between blocks and goal
 public boolean isGoal()                // is this board the goal board?
 public Board twin()                    // a board that is obtained by exchanging any pair of blocks
 public boolean equals(Object y)        // does this board equal y?
 public Iterable<Board> neighbors()     // all neighboring boards
 public String toString()               // string representation of this board (in the output format specified below)
 
 public static void main(String[] args) // unit tests (not graded)
 }
 */
import java.util.ArrayList;
public class Board 
{
    private int[][] b;
    private final int manhat;
    private final int ham;
   // private int[][] copy;
    public Board(int[][] blocks)
    {
        b = new int [blocks.length][blocks.length];
        for(int i = 0 ; i < blocks.length ; i++)
            for(int j = 0;j < blocks.length ; j++)
            b[i][j] = blocks[i][j];
        
        int c = 0 ; int req = 1;
        int n = dimension();
        for(int i = 0; i < n; i++)
        {
            for(int j = 0;j < n; j++)
            {
                if(b[i][j] != req)
                    c++;
                req++;
            }
        }
        ham = c-1;
        
        c = 0 ;req =1; int ri= 0,rj = 0;
        n = dimension();
        while( req != (n * n ))
        {
            if(rj == n)
            {
                rj = 0;
                ri++;
            }
            for(int i = 0; i < n ; i++)
                for(int j = 0 ; j < n ; j++)
            {
                if(b[i][j] == req)
                {
                  c += Math.abs(ri - i) + Math.abs(rj - j); 
                  i=n;j=n;
                  break;
                }
            }
            rj++;
            req++;
        }
        manhat = c;
    }
    public int dimension()
    {
        return b.length;
    }
    public int hamming()
    {
        return ham;
    }
    public int manhattan()
    {
       return manhat;
    }
    public boolean isGoal()
    {
        int n = dimension();
        int req = 1;
        for(int i = 0; i < n ; i++)
        {
            for(int j = 0;j < n; j++)
            {
                if(i == n-1 && j == n-1)
                    break;
                if(b[i][j] != req )
                    return false;
                req++;
            }
        }
        return true;
    }
    public Board twin()
    {
        int n = dimension();
         int[][] copy = new int[b.length][b.length];
         for( int i = 0 ; i < n ; i++ )
             for( int j = 0 ; j < n ; j++ )
             copy[i][j] = b[i][j];
        int ri1=0,ri2=0,rj1=0,rj2=0,f=0;
        for( int i = 0; i < n; i++)
            for( int j = 0; j < n ;j++)
        {
             if(copy[i][j] != 0 && f == 1)
            {
                ri2 = i;
                rj2 =j ;
            }
            if(copy[i][j]!=0 && f == 0)
            {
                ri1 = i;
                rj1 = j;
                f = 1;
            }
        }
        int t = copy[ri1][rj1];
        copy[ri1][rj1] = copy[ri2][rj2];
        copy[ri2][rj2] = t;
      
        Board obj = new Board(copy);
        return obj;
    }
    public boolean equals(Object y)
    {
        if(y == this) return true;
        if(y == null) return false;
        if(y.getClass()  != this.getClass()) return false;
        Board obj = (Board)y;
        int n = dimension();
        if (n != obj.dimension())
            return false;
        for(int i = 0;i < n; i++)
            for(int j = 0 ; j <n ; j++)
            if(b[i][j] != obj.b[i][j])
            return false;
        return true;
    }
    public Iterable<Board> neighbors()
    {
        ArrayList<Board> pq = new ArrayList<Board>();
        int n = dimension();
        
        int[][] copy = new int[b.length][b.length];
         for( int i = 0 ; i < n ; i++ )
             for( int j = 0 ; j < n ; j++ )
             copy[i][j] = b[i][j];
        // FINDING THE BLANK 
        int ri = 0, rj = 0;
        for(int i = 0; i < n; i++)
            for( int j = 0 ;j < n; j++)
            if( b[i][j] == 0)
        {
            ri=i;
            rj=j;
            break;
        }
      if( ri-1 > -1 )
        {
            int t = copy[ri][rj];
            copy[ri][rj] = copy[ri-1][rj];
            copy[ri-1][rj] = t;
            Board c = new Board (copy);
            pq.add(c);
            for( int i = 0 ; i < n ; i++ )
             for( int j = 0 ; j < n ; j++ )
             copy[i][j] = b[i][j];
      }
      if( ri+1 < n )
      {
            int t = copy[ri][rj];
            copy[ri][rj] = copy[ri+1][rj];
            copy[ri+1][rj] = t;
            Board c = new Board (copy);
            pq.add(c);
           
         for( int i = 0 ; i < n ; i++ )
             for( int j = 0 ; j < n ; j++ )
             copy[i][j] = b[i][j];
      }
      if( rj-1 > -1 )
      {
            int t = copy[ri][rj];
            copy[ri][rj] = copy[ri][rj-1];
            copy[ri][rj-1] = t;
            Board c = new Board (copy);
            pq.add(c);
         
         for( int i = 0 ; i < n ; i++ )
             for( int j = 0 ; j < n ; j++ )
             copy[i][j] = b[i][j];
      }      
      if( rj+1 < n)
      {
            int t = copy[ri][rj];
            copy[ri][rj] = copy[ri][rj+1];
            copy[ri][rj+1] = t;
            Board c = new Board (copy);
            pq.add(c);   
          
         for( int i = 0 ; i < n ; i++ )
             for( int j = 0 ; j < n ; j++ )
             copy[i][j] = b[i][j];
        }
        return pq;
    }    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", b[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    public static void main(String args[])
    {
        int a[]= {1,3,5,7};
        int b[]= a.clone();
        for(int i=0;i<b.length;i++)
            System.out.println(b[i]);
    }
}