
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
class AVLNode{
    int key, height;
    AVLNode left, right;
    
    AVLNode(int k){
        key = k;
        height = 1;
    }
}

public class AVLTree {
    
    // Root node of the tree
    AVLNode root;
    
    public AVLTree(int key){
        root = new AVLNode(key);
    }
    
    public AVLTree(){
        root = null;
    }
    
    public AVLNode getRoot(){
        return root;
    }
    
    // Utility function to get height of tree
    public int height(AVLNode N){
        if(N == null)
            return 0;
        return N.height;
    }
    
    // Utility function to retrieve larger of two int's
    public int biggerNum(int a, int b){
        return (a>b) ? a : b;
    }
    
    // Utility function to perform a right rotation
    private AVLNode rightRotate(AVLNode a){
        AVLNode b = a.left;
        AVLNode temp = b.right;
        
        // Perform rotation
        b.right = a;
        a.left = temp;
        
        // Update depths of rotated nodes
        a.height = biggerNum(height(a.left), height(a.right)) + 1;
        b.height = biggerNum(height(b.left), height(b.right)) + 1;
        
        return b;
    }
    
    // Utility function to perform a left rotation
    private AVLNode leftRotate(AVLNode a){
        AVLNode b = a.right;
        AVLNode temp = b.left;
        
        // Perform rotation
        b.left = a;
        a.right = temp;
        
        // Update depths of rotated nodes
        a.height = biggerNum(height(a.left), height(a.right)) + 1;
        b.height = biggerNum(height(b.left), height(b.right)) + 1;
        
        return b;
    }
    
    // Get balance factor of a node
    private int getBalance(AVLNode node){
        if(node == null)
            return 0;
        return (height(node.left) - height(node.right));
    }
    
    // Internal method to recursively add a node
    private AVLNode addRecursive(AVLNode node, int key)
    {
        // Perform normal BST insertion
        if(node == null)
            return (new AVLNode(key));
        
        if(key < node.key)
            node.left = addRecursive(node.left, key);
        else if(key > node.key)
            node.right = addRecursive(node.right, key);
        else
            return node;
        
        // Update height of given node
        node.height = 1 + biggerNum(height(node.left), 
                                    height(node.right));
        
        // Get the balance factor of this node
        int balance = getBalance(node);
        
        // If unbalance, check which rotation case is necessary
        // Left-Left Case
        if(balance > 1 && key < node.left.key)
            return rightRotate(node);
        // Right-Right Case
        if(balance < -1 && key > node.right.key)
            return leftRotate(node);
        // Left-Right Case
        if(balance > 1 && key > node.left.key){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right-Left Case
        if(balance < -1 && key < node.right.key){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    
    public void add(int key){
        root = addRecursive(root, key);
    }
    
    // Internal method find a node
    private AVLNode findRecursive(AVLNode current, int key)
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
        AVLNode temp = findRecursive(root, key);
        if(temp == null)
            return false;
        return true;
    }
    
    // Internal method to recursively delete an element
    private AVLNode deleteRecursive(AVLNode current, int key)
    {
        // First do normal BST Deletion
        if(current == null)
            return current;
            
        if(key < current.key)
            current.left = deleteRecursive(current.left, key);
        else if(key > current.key)
            current.right = deleteRecursive(current.right, key);
        else{
            // Node has no children
            if(current.left == null && current.right == null)
                current = null;
            //Node has one child
            else if(current.right == null)
                current = current.left;
            else if(current.left == null)
                current = current.right;
            // Node has two children
            else{
                int replacer = findSmallestValue(current.right);
                current.key = replacer;
                current.right = deleteRecursive(current.right, replacer);
            }
        }
        
        if(current == null)
            return current;
        
        // Next step: update the height of the current node
        current.height = biggerNum(height(current.left),height(current.right));
        
        // Next: get the balance factor of this node to check
        // if the tree has become imbalanced
        int balance = getBalance(current);
        
        // Left-Left Case
        if(balance > 1 && getBalance(current.left)>=0)
            return rightRotate(current);
        // Right-Right Case
        if(balance < -1 && getBalance(current.right)<=0)
            return leftRotate(current);
        // Left-Right Case
        if(balance > 1 && getBalance(current.left)<0){
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }
        // Right-Left Case
        if(balance < -1 && getBalance(current.right)>0){
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }
        
        return current;
    }
    
    // Utility method to find smallest value of a tree
    // Useful for node deletion when getting smallest node
    // of right side sub-tree
    private int findSmallestValue(AVLNode root){
        return root.left == null ? root.key : findSmallestValue(root.left);
    }
    
    // Find the minimum value node of a given tree
    private AVLNode minValueNode(AVLNode node)
    {
        while(node.left != null)
            node = node.left;
        return node;
    }
    
    // Method to delete a node with a certain key from the tree
    public void delete(int key){
        deleteRecursive(this.root, key);
    }
    
    // The next three methods are DFS implementations
    // Traverse the tree in order (left-root-right)
    public void traverseInOrder(AVLNode node){
        if(node!=null){
            traverseInOrder(node.left);
            System.out.print(" " + node.key);
            traverseInOrder(node.right);
        }
    }
    
    // Traverse the tree starting with root, then left, then right
    public void traversePreOrder(AVLNode node){
        if(node!=null){
            System.out.print(" " + node.key);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    
    // Traverse the tree starting with left, then right, then root
    public void traversePostOrder(AVLNode node){
        if(node!=null){
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.key);
        }
    }
    
    // Do Breadth-First Search (BFS) of tree
    public void BFS(){
        if(root == null)
            return;
        
        Queue<AVLNode> nodes = new LinkedList<>();
        nodes.add(root);
        
        while(!nodes.isEmpty()){
            AVLNode node = nodes.remove();
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
        AVLTree avl = new AVLTree();
        avl.add(9);
        avl.add(5);
        avl.add(10);
        avl.add(0);
        avl.add(6);
        avl.add(11);
        avl.add(-1);
        avl.add(1);
        avl.add(2);
        
        System.out.println(avl.find(66));
        System.out.println(avl.find(-1));
        System.out.println(avl.find(10));
        
        System.out.println("Inorder traversal:");
        avl.traverseInOrder(avl.getRoot());
        System.out.println();
        
        System.out.println("Preorder traversal:");
        avl.traversePreOrder(avl.getRoot());
        System.out.println();
        
        System.out.println("Postorder traversal:");
        avl.traversePostOrder(avl.getRoot());
        System.out.println();
        
        System.out.println("BFS traversal:");
        avl.BFS();
        System.out.println();
        
        avl.delete(10);
        
        System.out.println(avl.find(66));
        System.out.println(avl.find(70));
        System.out.println(avl.find(9));
        
        System.out.println("Inorder traversal:");
        avl.traverseInOrder(avl.getRoot());
        System.out.println();
        
        System.out.println("Preorder traversal:");
        avl.traversePreOrder(avl.getRoot());
        System.out.println();
        
        System.out.println("Postorder traversal:");
        avl.traversePostOrder(avl.getRoot());
        System.out.println();
        
        System.out.println("BFS traversal:");
        avl.BFS();
        System.out.println();
    }
}
