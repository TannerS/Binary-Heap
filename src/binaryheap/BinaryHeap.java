package binaryheap;

import java.util.Random;
import java.util.Scanner;


public class BinaryHeap 
{
/**
    * main method for two options of heaping an array and finding
    * the number of swaps needed for each method
    * 
    * @param  args      arguments, this method does not need it
    * @return          
    */
    public static void main(String[] args) 
    {
        // create variables 
        Heap heap = new Heap();
        final int SIZE = 100;
        int optarr[] = new int[SIZE];
        int seqarr[] = new int[SIZE];
        int seqswaps = 0;
        int optswaps = 0;
        Scanner input = new Scanner(System.in);
        int choice = 0;
        
        // start do loop for menu selection, user must type in proper selection
        do{
            System.out.println();
            System.out.println("Please select how to test the program: ");
            System.out.println("(1) 20 sets of 100 randomly generated integers ");
            System.out.println("(2) Fixed integer values 1-100 ");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            System.out.println(choice);
        }while((choice != 1) && (choice != 2));
        
        System.out.println();
        // switch statement for user selection
        switch(choice)
        {
            // choice for random generated numbers
            case 1:
                // for loop of 20 iterations for the 20 sets 
                for(int i = 0; i < 20; i++)
                {
                    // I had to re-created the array during each loop
                    // due to an error that was discovered and unexplainable 
                    optarr = new int[SIZE];
                    seqarr = new int[SIZE];
                    // fill up current array with the range of 100 numbers
                    ramdonFillArray(optarr, 100);
                    // copy into second array
                    System.arraycopy(optarr, 0, seqarr, 0, optarr.length);
                    // use both arrays to be heaped and return the number of swaps
                    // the number of swaps will be added up each loop
                    optswaps += heap.optimalReheapification(optarr);
                    seqswaps += heap.sequentialReheapification(seqarr);
                }
                
                // Display average of both methods
                System.out.println("Average swaps for series of insertions: " + seqswaps/20);
                System.out.println("Average swaps for optimal method: " + optswaps/20);

                break;
            case 2:
                // fill array with numbers 1-100
                seqFill(optarr);
                // copy contents into other array
                System.arraycopy(optarr, 0, seqarr, 0, optarr.length);
                // heap the array and return swap count
                seqswaps += heap.sequentialReheapification(seqarr);
                // display first 10 numbers and swaps
                System.out.print("Heap built using series of insertions: ");
                System.out.println(heap.displayFirstTen(seqarr));
                System.out.print("Number of swaps: " + seqswaps + "\n");
                System.out.print("Heap after 10 removals: ");   
                // loop to remove 10 numbers
                for(int i = 0; i < 10; i++)
                {
                    // delete root and reutnr new array
                    seqarr = heap.deleteRoot(seqarr);
                    // heap it
                    heap.sequentialReheapification(seqarr);
                }
                // display new first 10 numbers
                System.out.println(heap.displayFirstTen(seqarr)); 
                // heap second array
                optswaps += heap.optimalReheapification(optarr);
                System.out.println();
                // display first 10 numbers and swaps
                System.out.print("Heap built using optimal method: ");
                System.out.println(heap.displayFirstTen(optarr));
                System.out.print("Number of swaps: " + optswaps + "\n");
                System.out.print("Heap after 10 removals: ");
                // loop to remove 10 numbers
                for(int i = 0; i < 10; i++)
                {
                    // delete root and reutnr new array
                    optarr = heap.deleteRoot(optarr);
                    // heap it
                    heap.optimalReheapification(optarr);
                }
                // display new first ten numbers
                System.out.println(heap.displayFirstTen(optarr)); 
                break;
        }
    } 
    /**
    * fill up an array sequentially with a selected ranged of 1 - array.size
    * 
    * @param    arr     array to fill up
    * @return        
    */
    public static void seqFill(int arr[])
    {
        // loop array filling it with i+1
        for(int i = 0; i < arr.length; i++)
            arr[i] = i + 1;
    }
    /**
    * fill up an array of random numbers  with a selected range
    * 1 - range
    * 
    * @param    arr     array to fill up
    *           range   range of numbers (1 - range)
    * @return         
    */
    public static void ramdonFillArray(int arr[], int range)
    {
        
        Random ran = new Random();
        int curr = 0;
        
        //if array does not exist, return
        if(arr == null)
            return;
        
        //set first element to a random number
        arr[0] = ran.nextInt(range)+ 1;
        
        // loop array filling it with random numbers
        for(int i = 1; i < arr.length; i++)
        {
            // generate random number
            curr = ran.nextInt(range)+ 1;
            // check for a duplicate
            if(!(checkDuplicateArrayValue(curr, arr)))
                // if duplicate, -1 i to redo that loop
                i--;
            else
                // set the array to that number
                arr[i] = curr;
        }
    }
    /**
    *  check array if it has a specific value or not
    * 
    * @param    value   value to check for
    *           arr     array to check for value
    * @return   boolean true boolean if no duplicate was found, false for 
    *           opposite
    */
    private static boolean checkDuplicateArrayValue(int value, int arr[])
    {
        int counter = 0;
        // do loop to check for duplicates 
        do{
            //if array element is = to the tested value
            if(arr[counter] == value)
                // means duplicate, return false
                return false;
            // increase count as long as thier are no duplicates
            counter++;
        // end loop it hit end of array or hit a 0 meaning the rest of array is empty
        }while(!(counter == arr.length-1) && !(arr[counter] == 0));
        // return true if no duplicates found
        return true;
    }  
}

