import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation 
{
    private int size;
    private  int grid[][];
    private WeightedQuickUnionUF uf;
    public Percolation(int n)
    {
        if (n<=0)
            throw new IllegalArgumentException("Wrong Argument Value!");
        size=n;
        grid=new int[n+1][n+1];
        uf=new WeightedQuickUnionUF(size*size+2);
        for(int i=1;i<=n;i++)
            for(int j=1;j<=n;j++)
            grid[i][j]=0;        
    }
    
    public void open(int row,int col)                                     //along with opening, also connect it to open sites
    { 
        if(row<1 || row>size || col<1 || col>size)
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        
        int p=(row-1)*size+col;
        grid[row][col]=1;
        
        if(row==1)
            uf.union(0,p);
        
        if ( row<size)
        {
            if(grid[row+1][col]==1)
                uf.union(p,p+size);
        }
        if(row>1)
        {
            if(grid[row-1][col]==1)
                uf.union(p-size,p);
        }
        if( col<size)
        {
            if(grid[row][col+1]==1)
                uf.union(p,p+1);
        }
        if( col>1)
        {
            if(grid[row][col-1]==1)
                uf.union(p-1,p);
        }
        for(int i=size*size-size+1;i<=size*size;i++)
            if(uf.connected(i,0))
            uf.union(i,(size*size)+1);
    }
    public boolean isOpen(int row, int col)
    { 
        if(row<1 || row>size || col<1 || col>size)
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        if(grid[row][col]==1)
            return true;
        return false;
    }
    
    public boolean isFull(int row, int col)  
    {
        
        if(row<1 || row>size || col<1 || col>size)
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        if(row==1)
        { if(grid[row][col]==1)
            return true;
        return false;
        }
        
        int p=((row-1)*size)+col;
        
        if(grid[row][col]==0)
            return false;
        
        if(uf.connected(p,0))
            return true;
        return false;
    } 
    
    public  int numberOfOpenSites()
    {   
        int n=0; 
        for(int i=1;i<=size;i++)
            for(int j=1;j<=size;j++)
            if(isOpen(i,j))
            n++;
        return n;
    }    
    public boolean percolates()             
    { 
        if(uf.connected(0,(size*size)+1))
            return true;
        return false;
    }
    
    
}
/*public class Percolation {
 public Percolation(int n)                // create n-by-n grid, with all sites blocked
 public    void open(int row, int col)    // open site (row, col) if it is not open already
 public boolean isOpen(int row, int col)  // is site (row, col) open?
 public boolean isFull(int row, int col)  // is site (row, col) full?
 public     int numberOfOpenSites()       // number of open sites
 public boolean percolates()              // does the system percolate?
 
 public static void main(String[] args)   // test client (optional)
 }*/