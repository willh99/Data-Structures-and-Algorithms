/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class playground {
    
    public char findTheDifference(String s, String t) {
        HashMap<Character, Integer> sMap = new HashMap<Character,Integer>();
        HashMap<Character, Integer> tMap = new HashMap<Character,Integer>();
        int count;
        char retval=' ';
        
        for(int i=0; i<s.length(); i++)
        {
            count = sMap.containsKey(s.charAt(i)) ? 
                    sMap.get(s.charAt(i)) : 0;
            sMap.put(s.charAt(i), count+1);
            count = tMap.containsKey(t.charAt(i)) ? 
                    tMap.get(t.charAt(i)) : 0;
            tMap.put(t.charAt(i), count+1);
        }
        count = tMap.containsKey(t.charAt(t.length()-1)) ? tMap.get(t.charAt(t.length()-1)) : 0;
        tMap.put(t.charAt(t.length()-1), count+1);
        
        Iterator<Character> it = tMap.keySet().iterator();
        while(it.hasNext()){
            Character ch = it.next();
            if(!sMap.containsKey(ch))
                retval = ch;
            else if(sMap.get(ch) != tMap.get(ch))
                retval = ch;
        }
        return retval;
    }   
    
    
    public static void main(String[] args)
    {
        playground p = new playground();
        char e = p.findTheDifference("ae","aea");
        System.out.println(e);
    }
}
