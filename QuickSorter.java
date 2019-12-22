/*
 * Melecia Young
 * QuickSorter.java
 * QuickSort project- this project required us to test out 4 different pivots of the quicksort
 * algorithm and calculate the run time for each of those methods
 * This class contains the methods that calculate the run time, select the pivot, and do the quicksort
 * or insertion sort depending on the size. 
 */
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuickSorter {
	
	/*
	 * this method will calculate the runtime for each of the pivots
	 */
	public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy ps) {
		if(list.isEmpty() == true) {
			throw new NullPointerException("EMPTY LIST"); //Null Pointer for the list
		}
		
		if(ps == null) {
			throw new NullPointerException("Invalid Pivot strategy"); //Null Pointer for the strategy
		}
		
		//if the size is < 20
		if(list.size() < 20) {
			long start = System.nanoTime(); //start time
			insertionSort(list, 0, list.size()); //do the insertion sort
			long end = System.nanoTime(); //end time
			Duration total = Duration.ofNanos(end-start); //total duration
			//System.out.println("Insertion sort done: PT" + total);
			return total; //return total time
			
		}
		
		long startTime = System.nanoTime(); //start the timer
		QuickSort(list, 0, list.size()-1, ps); //call the insertion sort
		long finishTime = System.nanoTime();//finish the timer
		Duration totalTime = Duration.ofNanos(finishTime - startTime); //calculate the timer
		
		return totalTime; // return total time
	}
	/*
	 * generate a random arraylist of integers
	 */
	public ArrayList<Integer> generateRandomList(int size){
		ArrayList<Integer> list = new ArrayList<>(size); //empty list according to size
		Random rand = new Random();
		try {
			for(int i = 0; i < size; i++) { //iterate through the list
				int insert = rand.nextInt(); //create random integers
				list.add(insert); //insert the random numbers into the list
				//System.out.println(insert); //print out integers to test 
			}
		} catch (IllegalArgumentException e) { //illegal argument exception
			System.out.println("ERROR: illegal arguement");
		}
		return list; //return the list that was created
	}
	
	/*
	 * quicksort algorithm that will sort depending on the pivot entered
	 */
	private static <E extends Comparable<E>> void QuickSort(ArrayList<E> list, int lowIndex, int highIndex, PivotStrategy ps) {
		int low = 0; 
		int high = 0;
		E pivot;
		if(lowIndex >= highIndex) { 
			return; 
		}
		
		low = lowIndex;
		high = highIndex;
		
		/*
		 * depending on the pivotstrategy, chose a pivot from the list
		 */
		int pivotIndex = setPivot(low,high,ps); //set a pivot depending on the ps
		pivot = list.get(pivotIndex); //find the element that the pivot is pointeing to 
		
		
		/*
		 * begin the insertion sort 
		 */
		while(low <= high) {
			while(pivot.compareTo(list.get(low) ) == 1) {
				low++;
			}
			while(pivot.compareTo(list.get(high) )== -1 ) {
				high--;
			}
			if(low <= high) {
				Collections.swap(list, low, high); //swap the elements
				low++;
				high--;
			}
		}
	
		//recursive part of the quicksort
		QuickSort(list,lowIndex, high, ps);
		QuickSort(list,low,highIndex,ps);
	}
	
	/*
	 * a switch to chose the pivot depending on the strategy 
	 */
	private static int setPivot(int low, int high, PivotStrategy ps) {
		int pivot = 0;
		Random rand = new Random();
		
		//switch
		switch(ps) {
		case FIRST_ELEMENT: //first element
			pivot = low; //set the low as the pivot
			break;
		case RANDOM_ELEMENT: //random element
			pivot = rand.nextInt(high); //calculate a random pivot
			break;
		case MEDIAN_OF_THREE_RANDOM_ELEMENTS: //median of three random elements
			pivot = (rand.nextInt((high-low) + low) + rand.nextInt((high-low) + low) + rand.nextInt((high-low) +low) / 3);
			//while loop to ensure that the pivot is within the indexes
			while(pivot > high) {
				pivot = (rand.nextInt((high-low) + low) + rand.nextInt((high-low) + low) + rand.nextInt((high-low) +low) / 3);
			}
			break;
		case MEDIAN_OF_THREE_ELEMENTS: //median of three elements
			int median = (high+low) /2; 
			pivot = (high+low+median) / 3;
			break;
		}
		
		return pivot;
	}
	
	/*
	 * insertion sort algorithm
	 */
	private static <E extends Comparable <E>> void insertionSort(ArrayList<E> list, int low, int high) {
		for(int i = 1; i < list.size(); i++) {
			E key = list.get(i);
			int j = i - 1;
			
			while(j >= 0 && (list.get(j).compareTo(key)) == 1) { //-1 sorts it in the opposite order
				Collections.swap(list, j, j+1); //swap the elements
				j--;
			}
			list.set(j+1, key);
		}
	}
	
	/*
	 * pivot strategy enum
	 */
	public static enum PivotStrategy{
		FIRST_ELEMENT,
		RANDOM_ELEMENT,
		MEDIAN_OF_THREE_RANDOM_ELEMENTS,
		MEDIAN_OF_THREE_ELEMENTS
	}

}
