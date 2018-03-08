/*public class PercolationStats {
 public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
 public double mean()                          // sample mean of percolation threshold
 public double stddev()                        // sample standard deviation of percolation threshold
 public double confidenceLo()                  // low  endpoint of 95% confidence interval
 public double confidenceHi()                  // high endpoint of 95% confidence interval
 
 public static void main(String[] args)        // test client (described below)
 }
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
public class PercolationStats
{   private static double thres[];
    private int t;
    public PercolationStats(int n, int trials)
    {
        if(n<=0 || trials<=0)
            throw new IllegalArgumentException("Wrong Argument Value!");
        
        thres=new double[trials];
        for(int i=0;i<trials;i++)
        {
        Percolation p= new Percolation(n);
            while(p.percolates()==false)
            {
                int x=StdRandom.uniform(1,n+1);
                int y=StdRandom.uniform(1,n+1);
                if(p.isOpen(x,y)==false)
                p.open(x,y);
            }
       thres[i]=(double)(p.numberOfOpenSites())/(double)(n*n);
        } 
       t=trials;
    }
    public double mean() 
    {       
        return StdStats.mean(thres);
    }
    public double stddev()
    { 
        return StdStats.stddev(thres);
    }
    public double confidenceLo()
    {       
        return StdStats.mean(thres)-(1.96*StdStats.stddev(thres)/java.lang.Math.sqrt(t));
    }
    public double confidenceHi()
    {       
        return StdStats.mean(thres)+(1.96*StdStats.stddev(thres)/java.lang.Math.sqrt(t));
    }
    public static void main(String[] args)
    {
        
        int n=Integer.parseInt(args[0]);
       
        int T=Integer.parseInt(args[1]);
        
        
            PercolationStats ps=new PercolationStats(n,T);
            
        
       
        StdOut.println("mean"+"\t\t\t"+"="+ps.mean());
        StdOut.println("stddev"+"\t\t\t"+"="+ps.stddev());
        StdOut.println("95% confidence interval"+"\t"+"=["+ps.confidenceLo()+","+ps.confidenceHi()+"]");
        }
}