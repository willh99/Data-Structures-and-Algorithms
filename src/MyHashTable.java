/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */

import java.util.ArrayList;

class HashNode<K,V>
{
    K key;
    V value;
    
    // Pointer to next node
    HashNode<K,V> next;
    
    public HashNode(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}

public class MyHashTable <K,V>
{
    // bucketArray is used to store chained hashes
    private ArrayList<HashNode<K,V>> bucketArray;
    
    // Current capacity of array list
    private int numBuckets;
    
    // Current size of array list
    private int size;
    
    public MyHashTable()
    {
        bucketArray = new ArrayList<>();
        numBuckets = 10;
        size = 0;
        
        // Create empty chains
        for(int i=0; i<numBuckets; i++){
            bucketArray.add(null);
        }
    }
    
    public int size() { return size; }
    public boolean isEmpty() { return size()==0; }
    
    // Implements hash function to find index for key
    // Index is equal to the hashcode % number of buckets
    private int getIndex(K key)
    {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return index;
    }
    
    private V remove(K key)
    {
        // Apply hash function to find index for key
        int bucketIndex = getIndex(key);
        
        // Get head of chain
        HashNode<K,V> head = bucketArray.get(bucketIndex);
        HashNode<K,V> prev = null;
        
        // Search for key in its chain
        while(head != null)
        {
            //If key is found
            if(head.key.equals(key))
                break;
            
            prev = head;
            head = head.next;
        }
        
        // If key is not found
        if(head == null)
            return null;
        
        size--;
        // Remove key
        if(prev!=null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);
        
        return head.value;   
    }
    
    // Returns a value for a given key
    public V get(K key)
    {
        int bucketIndex = getIndex(key);
        HashNode<K,V> head = bucketArray.get(bucketIndex);
        
        // Search chain for given key
        while(head!=null)
        {
            if(head.key.equals(key))
                return head.value;
            head = head.next;
        }
        
        // If key is not found
        return null;
    }

    // Add a key value pair to the hash table
    public void add(K key, V value)
    {
        // Apply hash function to find index for key
        int bucketIndex = getIndex(key);
        HashNode<K,V> head = bucketArray.get(bucketIndex);
        
        // Check if key is already present
        // If yes, overwrite the current value
        while(head!=null)
        {
            if(head.key.equals(key))
            {
                head.value = value;
                return;
            }
            head = head.next;
        }
        
        // Insert new key into as head of chain
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K,V> newNode = new HashNode<K,V>(key,value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);
        
        // Check if load factor is greater than 0.8
        // If yes, double size of the table
        if((1.0*size)/numBuckets >= 0.8)
        {
            ArrayList<HashNode<K,V>> temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = numBuckets*2;
            size = 0;
            
            // Create null entries for new doubled table
            for(int i=0; i<numBuckets; i++)
                bucketArray.add(null);
            
            // Fill table with entries from the old table
            // using new modulo factor for indexing
            for(HashNode<K,V> headNode : temp)
            {
                while(headNode!=null)
                {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
    
    // Internal main to test MyHashTable
    public static void main(String[] args)
    {
        MyHashTable<String,Integer> h = new MyHashTable<>();
        h.add("this", 1);
        h.add("is", 2);
        h.add("a", 3);
        h.add("test", 4);
        h.add("test", 18);
        
        System.out.println(h.size());
        System.out.println(h.remove("this"));
        System.out.println(h.remove("this"));
        System.out.println(h.get("test"));
        System.out.println(h.size());
        System.out.println(h.isEmpty());
        
    }
}
