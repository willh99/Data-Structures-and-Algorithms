/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willy
 */
public class DFS {
    
    int[][] a;
    
    public DFS(int size){
        a = new int[size][size];
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[0].length; j++)
            {
                if(i%2 == 0)
                    a[i][j]=1;
                else
                    a[i][j]=0;
            }
        }
    }
    
    // Utility method to check if array index is inbounds
    private boolean isInBounds(int x, int y){
        return ( (x>=0 && x<a.length) && (y>=0 && y<a[0].length) );
    }

    // Does a DFS for a 2D boolean matrix
    // It only considers the 4 neighbors as adjacent cells
    public void DFS(int row, int col, int num, boolean visited[][])
    {
        // These arrays are used to get row and column numbers
        // of 4 neighbors of a given cell
        int rowNbr[] = new int[]{-1, 0, 1, 0};
        int colNbr[] = new int[]{0, 1, 0, -1};
        
        // Mark this cell as visited
        visited[row][col] = true;
        
        // Recursive check for all connected neighbors
        for(int i=0; i<rowNbr.length; i++){
            int nbrX = row+rowNbr[i];
            int nbrY = col+colNbr[i];
            if(isInBounds(nbrX,nbrY) && !visited[nbrX][nbrY]  && a[nbrX][nbrY]==num)
                DFS(nbrX, nbrY, num, visited);
        }
    }
    
    // Function to return the number of clusters of a given
    // number in a 2D array
    public int getNumClusters(int num)
    {
        // Boolean array to keep track of visited cells
        boolean visited[][] = new boolean[a.length][a.length];
        
        // Initialized count as 0 and traverse through the array
        int count =0;
        for(int i=0; i<a.length; ++i){
            for(int j=0; j<a[0].length; ++j){
                if(a[i][j]==num && !visited[i][j])
                {
                    DFS(i, j, num, visited);
                    count++;
                }
            }
        }
        return count;
    }
    
    public String toString()
    {
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[0].length; j++){                
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        return "";
    }
    
    public static void main(String[] args)
    {
        DFS d = new DFS(5);
        System.out.println(d);
        System.out.println(d.getNumClusters(1));
    }
}
