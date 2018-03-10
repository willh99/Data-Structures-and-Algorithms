
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


public class BinarySearchTree {
    
    // Root node of the tree
    private BTNode root;
    
    public BinarySearchTree(int key){
        root = new BTNode(key);
    }
    
    public BinarySearchTree(){
        root = null;
    }
    
    public BTNode getRoot(){
        return root;
    }
    
    // Internal method to recursively add a node
    private BTNode addRecursive(BTNode current, int key)
    {
        if(current == null)
            return new BTNode(key);
        
        if(key < current.key)
            current.left = addRecursive(current.left, key);
        else if(key > current.key)
            current.right = addRecursive(current.right, key);
        else
            return current;
        
        return current;
    }
    
    // Utility method to start recursive addition of node
    public void add(int key){
        root = addRecursive(root, key);
    }
    
    // Internal method find a node
    private BTNode findRecursive(BTNode current, int key)
    {
        if(current == null)
            return null;
        
        if(key < current.key)
            current = findRecursive(current.left, key);
        else if(key > current.key)
            current = findRecursive(current.right, key);
        
        return current;
    }
    
    // Utility method to start recursive search of node
    public boolean find(int key){
        BTNode temp = findRecursive(root, key);
        if(temp == null)
            return false;
        return true;
    }
    
    // Internal method to recursively delete an element
    private BTNode deleteRecursive(BTNode current, int key)
    {
        if(current == null)
            return null;
            
        if(key == current.key)
        {
            // Node has not children
            if(current.left == null && current.right == null)
                return null;
            //Node has one child
            else if(current.right == null)
                return current.left;
            else if(current.left == null)
                return current.right;
            // Node has two children
            else{
                int replacer = findSmallestValue(current.right);
                current.key = replacer;
                current.right = deleteRecursive(current.right, replacer);
                return current;
            }
        }
        else if(key < current.key){
            current.left = deleteRecursive(current.left, key);
        }
        current.right = deleteRecursive(current.right, key);
        return current;
    }
    
    // Utility method to find smallest value of a tree
    // Useful for node deletion when getting smallest node
    // of right side sub-tree
    private int findSmallestValue(BTNode root){
        return root.left == null ? root.key : findSmallestValue(root.left);
    }
    
    // Method to delete a node with a certain key from the tree
    public void delete(int key){
        deleteRecursive(root, key);
    }
    
    // The next three methods are DFS implementations
    // Traverse the tree in order (left-root-right)
    public void traverseInOrder(BTNode node){
        if(node!=null){
            traverseInOrder(node.left);
            System.out.print(" " + node.key);
            traverseInOrder(node.right);
        }
    }
    
    // Traverse the tree starting with root, then left, then right
    public void traversePreOrder(BTNode node){
        if(node!=null){
            System.out.print(" " + node.key);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    
    // Traverse the tree starting with left, then right, then root
    public void traversePostOrder(BTNode node){
        if(node!=null){
            traversePreOrder(node.left);
            traversePreOrder(node.right);
            System.out.print(" " + node.key);
        }
    }
    
    // Do Breadth-First Search (BFS) of tree
    public void BFS(){
        if(root == null)
            return;
        
        Queue<BTNode> nodes = new LinkedList<>();
        nodes.add(root);
        
        while(!nodes.isEmpty()){
            BTNode node = nodes.remove();
            System.out.print(" " + node.key);
            if(node.left != null)
                nodes.add(node.left);
            if(node.right != null)
                nodes.add(node.right);
        }
        System.out.println();
    }
    
    public static void main(String[] args)
    {
        BinarySearchTree bst = new BinarySearchTree(6);
        bst.add(4);
        bst.add(8);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.add(9);
        
        System.out.println(bst.find(6));
        System.out.println(bst.find(7));
        System.out.println(bst.find(1));
        bst.traverseInOrder(bst.getRoot());
        System.out.println();
        bst.traversePreOrder(bst.getRoot());
        System.out.println();
        bst.traversePostOrder(bst.getRoot());
        System.out.println();
        bst.BFS();
        
        bst.delete(7);
        
         bst.traverseInOrder(bst.getRoot());
        System.out.println();
        bst.traversePreOrder(bst.getRoot());
        System.out.println();
        bst.traversePostOrder(bst.getRoot());
        System.out.println();
        bst.BFS();
    }
}
