
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
public class BFS {
    
    int a[][];
    int rNum[] = {-1, 0, 0, 1};
    int cNum[] = {0, -1, 1, 0};
    
    
    // inner class with current location and distance travelled
    private class queueNode{
        int x, y, count;
        
        queueNode(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count+1;
        }
    }
    
    public BFS(){
        a = new int[][]{
            {0, 1, 1, 0},
            {0, 0, 1, 2},
            {0, 1, 1, 0},
            {0, 0, 0, 0}};
        
    }
    
    private boolean isInBounds(int x, int y){
        return ( (x>=0 && x<a.length) && (y>=0 && y<a[0].length) );
    }
    
    // Note: Not yet finished
    public int shortestPath()
    {
        Queue<queueNode> nodes = new LinkedList<>();
        nodes.add(new queueNode(0,0,0));
        
        boolean visited[][] = new boolean[a.length][a[0].length];
        
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[0].length; j++){
                if(a[i][j] == 1)
                    visited[i][j] = true;
            }
        }
        
        while(!nodes.isEmpty()){
            queueNode node = nodes.remove();
            //System.out.print(" " + node);
            
            // Destination found
            if(a[node.x][node.y] == 2) 
                return node.count;
            
            // Move right one
            if(isInBounds(node.x -1, node.y) && !visited[node.x-1][node.y]){
                nodes.add(new queueNode(node.x-1, node.y, node.count+1));
                visited[node.x-1][node.y] = true;
            }
            
            // Move left one
            if(isInBounds(node.x+1, node.y) && !visited[node.x+1][node.y]){
                nodes.add(new queueNode(node.x+1, node.y, node.count+1));
                visited[node.x+1][node.y] = true;
            }
            
            // Move up one
            if(isInBounds(node.x, node.y+1) && !visited[node.x][node.y+1]){
                nodes.add(new queueNode(node.x, node.y+1, node.count+1));
                visited[node.x][node.y+1] = true;
            }
            
            // Move down one
            if(isInBounds(node.x, node.y-1) && !visited[node.x][node.y-1]){
                nodes.add(new queueNode(node.x, node.y-1, node.count+1));
                visited[node.x][node.y-1] = true;
            }
        }
        return -1;
    }
    
    public static void main(String[] args)
    {
        BFS bfs = new BFS();
        System.out.println(bfs.shortestPath());
    }
}
