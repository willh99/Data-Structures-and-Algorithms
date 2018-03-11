/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willy
 */
public class BTNode {
    int key;
    BTNode left, right;
    
    public BTNode(int item)
    {
        key = item;
        left=right=null;
    }
}
