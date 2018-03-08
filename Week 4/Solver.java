/*public class Solver {
 public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
 public boolean isSolvable()            // is the initial board solvable?
 public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
 public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
 public static void main(String[] args) // solve a slider puzzle (given below)
 }*/
import edu.princeton.cs.algs4.MinPQ;
import java.util.Iterator;
import java.util.Stack;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;

public class Solver
{
    private Board sol;
    private node solnode;
    public Solver(Board initial)
    {
        if(initial == null)
            throw new java.lang.IllegalArgumentException();
        Comparator<node> comparator = new bypriority();
        MinPQ<node> pq = new MinPQ<node>( comparator);
        MinPQ<node> pq1 = new MinPQ<node>(comparator);
        pq.insert(new node(initial,0,null));
        pq1.insert(new node(initial.twin(),0,null));
        while(!pq.isEmpty() && !pq1.isEmpty())
        {
            node rem = pq.delMin();
            node rem1 = pq1.delMin();
            if ( rem.b.isGoal())
            {
                solnode = rem;
                sol = rem.b;
                break;
            }
            else if(rem1.b.isGoal())
            {
                sol = null;
                break;
            }
            Iterator<Board> i = rem.b.neighbors().iterator();
            Iterator<Board> i1 = rem1.b.neighbors().iterator();
            while(i.hasNext())
            {
                Object element = i.next();
                if(rem.moves==0 || !element.equals(rem.pre.b))
                {
                    node n = new node ((Board) element, rem.moves+1 , rem);
                    pq.insert(n);
                }
            }
            while( i1.hasNext())
            {
                Object element = i1.next();
                if(rem1.moves==0 || !element.equals(rem1.pre.b))
                {
                    node n = new node ((Board) element, rem1.moves+1 , rem1);
                    pq1.insert(n);
                }
            }
        }
    }
    private class node 
    {
        Board b;
        int moves;
        node pre;
        int manhat;
        node(Board b1, int m, node pre1)
        {
            b = b1;
            moves = m;
            pre = pre1;
            manhat = b.manhattan();
        }
    }
        private class bypriority implements Comparator<node>
        {
            public int compare(node one , node two)
            {
                if(one.manhat+one.moves > two.manhat+two.moves)
                    return 1;
                else if(one.manhat+one.moves < two.manhat+two.moves)
                    return -1;
                else
                    return 0;
            }
        }
    
    public boolean isSolvable()
    {
        if( sol == null)
            return false;
        return true;
    }
    public int moves()
    {
        if(!isSolvable())
            return -1;
        return solnode.moves;
    }
    public Iterable<Board> solution()
    {
        if(!isSolvable())
            return null;
        Stack<Board> st = new Stack<Board>();
        node cur = solnode;
        while(cur != null)
        {
            st.push(cur.b);
            cur = cur.pre;
        }
       Stack<Board> s = new Stack<Board>();
       while(!st.empty())
       {
           s.push(st.pop());
       }
        return s;
    }
    public static void main(String[] args) {

    // create initial board from file
    edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
}
}
         