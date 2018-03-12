
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
public class Heap {
    private static final int c = 2;
    private int heapSize;
    private int[] heap;
    
    public Heap(int size){
        heapSize=0;
        heap = new int[size+1];
        for(int i=0; i<heap.length; i++)
            heap[i] = -1;
    }
    
    public boolean isEmpty(){
        return heapSize==0;
    }
    
    public boolean isFull(){
        return heapSize==heap.length;
    }
    
    public void emptyHeap(){
        heapSize=0;
    }
    
    // Get index of parent of i
    private int parent(int i){
        return (i-1)/c;
    }
    
    // Get index of kth child of node i
    private int childIndex(int i, int k){
        return c*i+k;
    }
    
    // Insert an element into the heap
    public void insert(int x)
    {
        if(isFull())
            throw new NoSuchElementException();
        heap[heapSize++] = x;
        heapifyUp(heapSize-1);
    }
    
    public int findMin()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        return heap[0];
    }
    
    public int deleteMin()
    {
        int root = heap[0];
        delete(0);
        return root;
    }
    
    // Delete an element from an index
    public int delete(int index){
        if(isEmpty())
            throw new NoSuchElementException();
        int key = heap[index];
        heap[index] = heap[heapSize-1];
        heapSize--;
        heapifyDown(index);
        return key;
    }
    
    
    private void heapifyUp(int index)
    {
        int tmp = heap[index];
        while(index>0 && tmp < heap[parent(index)])
        {
            heap[index] = heap[parent(index)];
            index = parent(index);
        }
        heap[index] = tmp;
    }
    
    
    private void heapifyDown(int index)
    {
        int  child;
        int tmp = heap[index];
        while(childIndex(index,1) < heapSize)
        {
            child = minChild(index);
            if(heap[child] < tmp)
                heap[index] = heap[child];
            else
                break;
            index = child;
        }
    }
    
    private int minChild(int index)
    {
        int bChild = childIndex(index, 1);
        int k = 2;
        int pos = childIndex(index, k);
        while((k<=c) && (pos<heapSize))
        {
            if( heap[pos] < heap[bChild])
                bChild=pos;
            pos = childIndex(index, k++);
        }
        return bChild;
    }
    
    private void printHeap()
    {
        for(int i=0; i<heapSize; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }
    
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Binary Heap Test\n\n");
        System.out.println("Enter size of Binary heap");
        /** Make object of BinaryHeap **/
        Heap bh = new Heap(scan.nextInt() );
 
        char ch;
        /**  Perform Binary Heap operations  **/
        do    
        {
            System.out.println("\nBinary Heap Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete min");
            System.out.println("3. check full");            
            System.out.println("4. check empty");
            System.out.println("5. clear");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                try
                {
                    System.out.println("Enter integer element to insert");
                    bh.insert( scan.nextInt() ); 
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage() );
                }
                break;                          
            case 2 : 
                try
                {
                    System.out.println("Min Element : "+ bh.deleteMin()); 
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage() );
                }                 
                break;                                
            case 3 : 
                System.out.println("Full status = "+ bh.isFull());
                break;                                   
            case 4 : 
                System.out.println("Empty status = "+ bh.isEmpty());
                break; 
            case 5 : 
                bh.emptyHeap();
                System.out.println("Heap Cleared\n");
                break;         
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
            /** Display heap **/
            bh.printHeap();  
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
    
}
