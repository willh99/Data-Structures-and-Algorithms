/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
public class MergeSort {
    
    // Merges two subarrays of given array
    private void mergeArray(int array[], int left, int mid, int right)
    {
        // Sizes of each subarray to be merged
        int n1 = mid-left+1;
        int n2 = right-mid;
        
        // Create temporary arrays
        int L[] = new int [n1];
        int R[] = new int [n2];
        
        // Copy data into temp arrays
        for(int i=0; i<L.length; i++)
            L[i] = array[left + i];
        for(int j=0; j<R.length; j++)
            R[j] = array[mid + 1 + j];
        
        // Start merging temporary arrays
        // Initial indexes of respective arrays
        int i=0, j=0, k=left;
        
        while(i<L.length && j<R.length )
        {
            if(L[i] <= R[j]){
                array[k] = L[i];
                i++;
            }
            else{
                array[k] = R[j];
                j++;
            }
            k++;
        }
        
        // Copy Remaining elements into the array
        while(i<L.length)
        {
            array[k] = L[i];
            i++;
            k++;
        }
        while(j<R.length)
        {
            array[k] = R[j];
            j++;
            k++;
        }
    }
    
    public void sortArray(int array[], int l, int r)
    {
        if(l < r){
            // Find middle point of array
            int m = (l+r)/2;
            
            // Sort first each sub-array
            sortArray(array, l, m);
            sortArray(array, m+1, r);
            
            // Merge the sorted sub-arrays
            mergeArray(array, l, m, r);
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
        MergeSort m = new MergeSort();
        int array[] = {2, 12, 3, 20, 15, 33, 7, 42, 27};
        
        m.printArray(array);
        m.sortArray(array, 0, array.length-1);
        m.printArray(array);
    }
    
}
