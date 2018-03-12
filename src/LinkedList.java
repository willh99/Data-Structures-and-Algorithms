/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willy
 */
public class LinkedList {
    private node head;
    
    public LinkedList(){
        head = null;
    }
    
    private class node
    {
        private int data;
        node next;
        
        public node(int data){
            this.data = data;
        }
        
        public int getData(){
            return data;
        }
    }
    
    // Merge two lists into sorted lists
    private node sortedMerge(node a, node b)
    {
        node result = null;
        if(a == null)
            return b;
        if(b == null)
            return a;
        
        // Choose lower value of a or b and recursively merge
        if(a.getData() <= b.getData())
        {
            result = a;
            result.next = sortedMerge(a.next, b);
        }
        else
        {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }
    
    // Split up list into sub-lists, sort, and merge
    public node mergeSort(node h)
    {
        if(head == null || head.next == null)
            return head;
        
        // get middle node in list
        node mid = getMiddle(head);
        node midNext = mid.next;
        
        // set mid node to null (split the list in two)
        mid.next = null;
        
        // recursively mergeSort left and right lists
        node left = mergeSort(head);
        node right = mergeSort(midNext);
        
        node sortedList = sortedMerge(left, right);
        return sortedList;
    }
    
    // Utility method to get middle node in a list
    private node getMiddle(node head)
    {
        if(head == null)
            return head;
        
        node toEnd = head.next;
        node toMid = head;
        
        // Move 'toEnd' node 2 nodes at a time while
        // 'toMid' moves 1 space at a time so that
        // the former reaches the end when the latter
        // reaches the middle
        while(toEnd !=null)
        {
            toEnd = toEnd.next;
            if(toEnd != null)
            {
                toMid = toMid.next;
                toEnd = toEnd.next;
            }
        }
        return toMid;
    }
    
    // Add new value to the list
    public void add(int d)
    {
        node newNode = new node(d);
        newNode.next = head;
        head = newNode;
    }
    
    // Get value at head of list
    public Integer remove(int d)
    {
        if(head.getData() == d){
            node temp = head;
            head = temp.next;
            return temp.getData();
        }
        
        node temp = head;
        while(temp.next != null)
        {
            if(temp.next.getData() == d)
            {
                int retval = temp.next.getData();
                temp.next = temp.next.next;
                return retval;
            }
        }
        return null;
    }
}
