/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willy
 */
public class DoublyLinkedList<E>{
    
    private Node head;
    private Node tail;
    private int size;
    
    // Create empty List
    public DoublyLinkedList(){
        size=0;
        head=tail=null;
    }
    
    // Create list with an initial element
    public DoublyLinkedList(E element){
        size = 1;
        head = new Node(element);
        tail = head;
    }
    
    private class Node{
        Object data;
        Node next;
        Node prev;

        public Node(E d){
            this.data = d;
            this.next = null;
            this.prev = null;
        }

        public Node(E d, Node n, Node p){
            this.data = d;
            this.next = n;
            this.prev = p;
        }
        
        public String toString(){
            return this.data.toString();
        }
    }
    
    // Return size of the list
    public int size() { return size; }
    
    // Return if the list is empty
    public boolean isEmpty() { return size == 0; }
    
    // Adds a new element to the front of the list
    public void addFront(E element){
        System.out.println("Adding \'" + element + "\' to front of list");
        Node temp = new Node(element, head, null);
        if(head != null)
            head.prev = temp;
        head = temp;
        if(tail == null)
            tail = head;
        size++;
    }
    
    // Adds a new element to the end of the list
    public void addBack(E element){
        System.out.println("Adding \'" + element + "\' to back of list");
        Node temp = new Node(element, null, tail);
        if(tail != null)
            tail.next = temp;
        tail = temp;
        if(head == null)
            head = tail;
        size++;
    }
    
    // Walk forward through the list
    public void iterateForward(){
        System.out.println("Interating forward");
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    
    // Walk backward through the list
    public void iterateBackward(){
        System.out.println("Interating backward");
        Node temp = tail;
        while(temp!=null){
            System.out.print(temp.data + " ");
            temp = temp.prev;
        }
        System.out.println();
    }
    
    // Finds and removes and element from the list
    // Searches from both directions
    public Object removeElement(E element){
        // Remove an element from a list with one node
        if(head==tail && head!=null)
        {
            if(head.data.equals(element))
            {
                System.out.println("Found in 1 node list");
                Object retval = head.data;
                head = tail = null;
                return retval;
            }
            else{
                return null;
            }
        }
        
        Node tempf = head;
        Node tempb = tail;
        
        while(tempf!=null && tempb!=null && 
                tempf!=tempb && tempf.prev!=tempb){
            
            if(tempf.data.equals(element))
            {
                
                if(tempf == head){
                    head = tempf.next;
                    tempf.next = null;
                    head.prev = null;
                }
                else{
                    tempf.next.prev = tempf.prev;
                    tempf.prev.next = tempf.next;
                    tempf.next = tempf.prev = null;
                }
                System.out.println(element + " found at tempf");
                return tempf.data;
            }
            else if(tempb.data.equals(element))
            {
                if(tempb == tail)
                {
                    tail = tempb.prev;
                    tempb.prev = null;
                    tail.next = null;
                }
                else{
                    tempf.next.prev = tempf.prev;
                    tempf.prev.next = tempf.next;
                    tempf.next = tempf.prev = null;
                }
                System.out.println(element + " found at tempb");
                return tempb.data;
            }
            tempf = tempf.next;
            tempb = tempb.prev;
        }
        System.out.println(element +" not found");
        return null;
    }
    
    
    
    public static void main(String[] args){
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.addFront(10);
        dll.addFront(34);
        dll.addBack(56);
        dll.addBack(364);
        dll.iterateForward();
        dll.removeElement(100);
        dll.removeElement(56);
        dll.removeElement(34);
        dll.iterateBackward();
    }
}