/* Melecia Young
 * Main.java
 * QuickSort project- this project required us to test out 4 different pivots of the quicksort
 * algorithm and calculate the run time for each of those methods
 * This class contains the methods that calculate the run time, select the pivot, and do the quicksort
 * or insertion sort depending on the size. 
 */
import java.io.File;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//tested a hard entered array 
		//ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 7, 8, 9, 1, 5));
		
		QuickSorter qs = new QuickSorter();
		int sizeOfArray = Integer.parseInt(args[0]);
		
		//copies of the lists for sorting
		ArrayList<Integer> list = qs.generateRandomList(sizeOfArray);
		ArrayList<Integer> list1 = list;
		ArrayList<Integer> list2 = list;
		ArrayList<Integer> list3 = list;
		
		//unsorted array
		try {
			//System.out.println ("unsorted opened");
			File unsortedFile = new File(args[2]);
			PrintWriter out = new PrintWriter(unsortedFile);
			
			for(int i = 0; i < list.size(); i++) {
				out.println(list.get(i));
			}
			out.close();
			//System.out.println("unsorted closed");
		} catch (Exception e) {
			System.out.println("ERROR: unsortedFile");
		}
		
		//quicksorter algorithms
		QuickSorter.PivotStrategy ps = QuickSorter.PivotStrategy.FIRST_ELEMENT;
		Duration duration = QuickSorter.timedQuickSort(list, ps);
		String firstElement = "FIRST_ELEMENT : PT" + duration.getNano();
		//System.out.println("FIRST_ELEMENT : PT" + duration.getNano());
		
		ps = QuickSorter.PivotStrategy.RANDOM_ELEMENT;
		duration = QuickSorter.timedQuickSort(list1, ps);
		String randomElement = "RANDOM_ELEMENT : PT" + duration.getNano();
		//System.out.println("RANDOM_ELEMENT : PT" + duration.getNano());
		
		ps = QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS;
		duration = QuickSorter.timedQuickSort(list2, ps);
		String medianOfThree = "MEDIAN_OF_THREE_ELEMENTS : PT" + duration.getNano();
		//System.out.println("MEDIAN_OF_THREE_ELEMENTS : PT" + duration.getNano());
		
		ps = QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS;
		duration = QuickSorter.timedQuickSort(list3, ps);
		String medianOfThreeRandom = "MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT" + duration.getNano();
		//System.out.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT" + duration.getNano());
		
		//sorted file
		try {
			//System.out.println("sorted file open");
			File sortedFile = new File(args[3]);
			PrintWriter out = new PrintWriter(sortedFile);
			
			for(int i = 0; i < list.size(); i++) {
				out.println(list.get(i));
			}
			out.close();
			//System.out.println("sorted file closed");
		} catch (Exception e) {
			System.out.println("ERROR: sortedFile");
		}
		
		//report file
		try {
			//System.out.println("report file open");
			File reportFile = new File(args[1]);
			PrintWriter out = new PrintWriter(reportFile);
			
			if(list.size() < 20) {
				out.println("Insertion sort done.");
				Duration d = qs.timedQuickSort(list, ps);
				out.println("Insertion sort done: PT" + d);
			} else {
				out.println("Array Size = " + list.size());
				out.println(firstElement);
				out.println(randomElement);
				out.println(medianOfThree);
				out.println(medianOfThreeRandom);
			}
			
			out.close();
			//System.out.println("report file closed");
		} catch (Exception e) {
			System.out.println("ERROR: reportFile");
		}
		
		

	}

}
