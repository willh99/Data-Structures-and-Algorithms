/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */

class BTNode
{
    int key;
    BTNode left, right;
    
    public BTNode(int item)
    {
        key = item;
        left=right=null;
    }
}

public class BinaryTree {
    
    // Root of the tree
    BTNode root;
    
    BinaryTree(int key){
        root = new BTNode(key);
    }
    
    BinaryTree(){
        root = null;
    }
    
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();
    }
}
