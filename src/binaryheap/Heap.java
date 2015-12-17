package binaryheap;



public class Heap 
{
    /**
    * creates a heap using the sequential method
    * 
    * @param  oldarr    this is the array you are working on
    * @return count     return count of swaps          
    */
    public int sequentialReheapification(int oldarr[])
    {
        // create new array with same size as old array
        // set variables
        int arr[] = new int[oldarr.length];
        int count = 0;
        // set new arr to old arr first element
        arr[0] = oldarr[0];
        // loop array to do the heaping
        for(int i = 1; i < arr.length; i++)
        {
            // if array hits a 0, end since it is a partially filled array
            if(oldarr[i] == 0)
                break;
            // set new arr elm from the old arr elem
            arr[i] = oldarr[i];
            // call method to search parents for any values smaller then current 
            // value and return swap count
            count += searchParentTree(arr, i);
        }
       
        //copy old arr to new arr so arr outside main will have new stuff
        System.arraycopy(arr, 0, oldarr, 0, arr.length);
        // return count
        return count;
    }
    /**
    * creates a heap using the optimal method
    * 
    * @param    arr    this is the array you are working on
    * @return   count     return count of swaps          
    */
    public int optimalReheapification(int arr[])
    {
        // create variables 
        int size = 0; 
        int cursor = 0;
        int count = 0;
        
        // loop array to count size
        for(int i = 0; i < arr.length; i++)
        {
            // if not a 0 element, increase size
            if(arr[i] != 0) // since array is 1-100 (not including 0) but arrays can me partial, and empty are 0's
                size++;
            else
                // if hit 0, it is partial array, break
                break;
        }
        
        // find the first node (at end of tree) that has atmost, one child 
        // (if one child, wil lalways be a left child)
        cursor = ((int)Math.floor(size/2)-1); // get the last element which a child
        // loop tree passing in current element to do checks
        do{
            // search cursor subtree to do any needed swaping
            count += searchSubTree(arr, cursor, size);
            // cursor-- to go to next array element that will end at root
            cursor--;
        // end loop after checking root
        }while(cursor >= 0);
    // return count
    return count;
    }
    /**
    * recursive method to search the parent ancestry for any swaps
    * 
    * @param    arr   array to be worked on  
    *           cursor  current element  
    * @return   count of swaps       
    */
    private int searchParentTree(int arr[], int cursor)
    {
        // create variables
        int count = 0;
        // find current parent
        int parent = ((int)Math.floor((cursor-1)/2));
        // if parent is smaller than child
        if(arr[parent] < arr[cursor])
        {
            // swap them both
            swap(arr, parent, cursor);
            // increase count
            count++;
            // recursive call to check the parents above parent for any needed
            // swaps and add up count
            count += searchParentTree(arr, parent);  
        }
        // return count
        return count;
    }
    /**
    *  recursive method to search the child ancestry for any swaps
    * 
    * @param    arr     array to be worked on
    *           cursor  current element
    *           size    size of array
    * @return   count of swaps       
    */
    private int searchSubTree(int arr[], int cursor, int size)
    {
        // get current two child of element
        int lchild = ((cursor*2)) + 1;
        int rchild = ((cursor*2)) + 2;
        int count = 0;
        
        // if it has two valid children
        if(!(rchild > size-1) && (!(lchild > size-1)))
        {
            /// if both children are greater than root
            if((arr[rchild] > arr[cursor]) && (arr[lchild] > arr[cursor]))
            {
                // if left chold is greater than right
                if(arr[lchild] > arr[rchild])
                {
                    //swap them
                    swap(arr, lchild, cursor);
                    count++;
                    count += searchSubTree(arr, lchild, size);
                }
                // if left chold is less than right
                else if(arr[lchild] < arr[rchild])
                {
                    //swap them
                    swap(arr, rchild, cursor);
                    count++;
                    count += searchSubTree(arr, rchild, size);
                }  
            }
            // if right child is greater than cursor
            if(arr[rchild] > arr[cursor])
            {
                swap(arr, rchild, cursor);
                count++;
                count += searchSubTree(arr, rchild, size); 
            }
            // or left child
            if (arr[lchild] > arr[cursor])
            {
                swap(arr, lchild, cursor);
                count++;
                count += searchSubTree(arr, lchild, size); 
            }
        }
        // if a valid lef tchild, 
        if(!(lchild > size-1))
        {
            // swap them  if greater than cursor
            if(arr[lchild] > arr[cursor])
             {
                 swap(arr, lchild,cursor);
                 count++;
                 count += searchSubTree(arr, lchild, size);
             }
        }
        // return count
        return count;
    }
    /**
    * swap too array elements
    * 
    * @param    arr     array to be worked on
    *           elm1    first element
    *           elem2   second element to swap with first
    * @return          
    */
    private void swap(int arr[], int elem1, int elem2)
    {
        // simple swap method
        int temp = 0;
        temp = arr[elem1];
        arr[elem1] = arr[elem2];
        arr[elem2] = temp;
    }
    /**
    * delete the current root of the tree
    * 
    * @param    arr     array to be worked on
    * @return   int[]   new array with a new root and size-1
    */
    public int[] deleteRoot(int arr[])
    {
        // set size to 0
        int size = 0;
        // loop array recording size
        for(int i = 0; i < arr.length; i++)
        {
            // if not a zero, increase size
            if(arr[i] != 0) // since array is 1-100 (not including 0) but arrays can me partial, and empty are 0's
                size++;
            else
                // if hit a zero, partial array, break;
                break;
        }
        // set root as the last element
        arr[0] = arr[size-1];
        // create temp array with new size
        int[] temp = new int[arr.length-1];
        // copy contents into new array
        System.arraycopy( arr, 0, temp, 0, arr.length-1);
        // return new array
        return temp;
    }
    /**
    * display first 10 elements in array
    * 
    * @param    arr     array to be worked on
    * @return   string  string of the first 10 elements
    */
    public String displayFirstTen(int arr[])
    {
        String ret = new String();
        
        // loop and create string with first 10 numbers
        for(int i = 0; i < 10; i++)
        {
            if(i == 9)
                ret += (arr[i] + ",...");
            else
                ret += (arr[i] + ", ");
        }
        
        return ret;  
    }
}
    

