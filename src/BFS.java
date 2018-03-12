
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willy
 */
public class BFS {
    
    int a[][];
    int rowNum[] = {-1, 0, 0, 1};
    int colNum[] = {0, -1, 1, 0};
    
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
    public void shortestPath()
    {
        Queue<queueNode> nodes = new LinkedList<>();
        nodes.add(new queueNode(0,0,0));
        
        boolean visited[][] = new boolean[a.length][a[0].length];
        
        while(!nodes.isEmpty()){
            queueNode node = nodes.remove();
            System.out.print(" " + node);
            if(a[node.x][node.y] == 2) {
                //pathExists = true;
                break;
            }
        }
        System.out.println();
    }
}
