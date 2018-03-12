/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
public class QuickSort {
    
    private int divPoint(int array[], int left, int right)
    {
        // pivot point at right of array and index of smaller element
        int pivot = array[right];
        int index = left-1;
        for(int j=left; j<right; j++)
        {
            // If current element is <= to pivot point
            // then swap elements at index and j
            if(array[j] <= pivot)
            {
                index++;
                int tmp = array[index];
                array[index] = array[j];
                array[j] = tmp;
            }
        }
        
        // Swap index+1 and 'right' (aka pivot)
        int tmp = array[index+1];
        array[index+1] = array[right];
        array[right] = tmp;
        return index+1;
    }
    
    public void quickSort(int array[], int left, int right)
    {
        if(left<right)
        {
            // Get partition index (right of array here)
            int pIndex = divPoint(array, left, right);
            // Do recursive sort of elements around pIndex
            quickSort(array, left, pIndex-1);
            quickSort(array, pIndex+1, right);
        }
    }
    
    public void printArray(int array[])
    {
        for(int i=0; i<array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }
    
    public static void main(String[] args)
    {
        QuickSort q = new QuickSort();
        int array[] = {2, 12, 3, 20, 15, 33, 7, 42, 27};
        
        q.printArray(array);
        q.quickSort(array, 0, array.length-1);
        q.printArray(array);
    }
    
}
