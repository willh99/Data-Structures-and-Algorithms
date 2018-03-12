
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
class TrieNode{
    private HashMap<Character, TrieNode> children;
    private String content="";
    private boolean isWord;
    
    public TrieNode(){
        children = new HashMap<>();
        content ="";
        isWord=false;
    }
    
    public TrieNode(String c){
        children = new HashMap<>();
        content = content + c;
        isWord=false;
    }
    
    public String getWord(){
        return content;
    }
    
    public HashMap<Character, TrieNode> getChildren(){
        return children;
    }
    
    public void setChildren(HashMap<Character, TrieNode> children) {

        this.children = children;
    }
    
    public boolean isWord(){
        return isWord;
    }
    
    public void setIsWord(boolean word){
        this.isWord = word;
    }
}

public class Trie {
    private TrieNode root;
    
    public Trie(){
        root = new TrieNode();
    }
    
    // Insert a word into the tree
    public void insert(String word){
        TrieNode current = root;
        
        for(int i=0; i<word.length(); i++)
        {
            char ch = word.charAt(i);
            // If the current pattern exists, move on to that node
            if(current.getChildren().containsKey(ch))
                current = current.getChildren().get(ch);
            // Otherwise, create a new node with that pattern
            else{
                current.getChildren().put(
                        word.charAt(i), new TrieNode(current.getWord()+ch));
                current = current.getChildren().get(ch);
            }
        }
        current.setIsWord(true);
    }
    
    // Find a word in the tree
    public boolean find(String word){
        TrieNode current = root;
        for(int i=0; i<word.length(); i++)
        {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if(node == null)
                return false;
            current = node;
        }
        return current.isWord();
    }
    
    public boolean delete(String word){
        return delete(root, word, 0);
    }
    
    private boolean delete(TrieNode current, String word, int index){
        if(index == word.length()){
            if(!current.isWord())
                return false;
            current.setIsWord(false);
            return current.getChildren().isEmpty();
        }
        
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if(node == null)
            return false;
        
        boolean shouldDelete = delete(node, word, index++);
        
        if(shouldDelete){
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }   
        return false;
    }
    
    public static void main(String[] args){
        Trie t = new Trie();
        
        t.insert("Hello");
        t.find("yes");
        t.find("Hello");
    }
}
