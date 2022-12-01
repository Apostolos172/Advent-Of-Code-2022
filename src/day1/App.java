package day1;

import java.util.ArrayList;
import java.util.Collections;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day1/day1.txt";
		ArrayList<String> caloriesAsStrings = new ReadFile(path).getContentAsArrayList();
		System.out.println(caloriesAsStrings);
		
		/* keep track of myself
		 * 
		 * part 1
		 * I have my input
		 * I traverse through the array:
		 * sum until I find a blank element, then add it to new array of total calories per elve
		 * and at the same time keep track of the max if each result is greater than the previous one keep this
		 * after this traverse I will have the most calories
		 */
		
		int maxCalories = getMax(caloriesAsStrings);
		System.out.println("\nMax calories " + maxCalories);
	}
	
	private static int getMax(ArrayList<String> caloriesAsStrings) {
		// get the max of the arraylist based on the description of the puzzle day 1 part 1
		
		ArrayList<Integer> caloriesPerElve = new ArrayList<Integer>();
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < caloriesAsStrings.size(); i++) {
			if(caloriesAsStrings.get(i).equals("")) {
				caloriesPerElve.add(sum);
				if(sum>max) {
					max=sum;
				}
				sum=0;
			} else {
				sum+=Integer.parseInt(caloriesAsStrings.get(i));	
			}
		}
		ArrayList<Integer> threeMaxCalories = get3Max(caloriesPerElve);
		//System.out.println("\nthreeMaxCalories " +threeMaxCalories);
		
		return max;
	}

	private static ArrayList<Integer> get3Max(ArrayList<Integer> caloriesPerElve) {
		// get the three top elements of arraylist as an arraylist
		// for part 2 of the puzzle day 1
		
		Collections.sort(caloriesPerElve);
		// System.out.println(caloriesPerElve);
		ArrayList<Integer> list3max = new ArrayList<Integer>();
		int length = caloriesPerElve.size();
		list3max.add(caloriesPerElve.get(length-1));
		list3max.add(caloriesPerElve.get(length-2));
		list3max.add(caloriesPerElve.get(length-3));
		
		int max3sum = getSumOfMax3(list3max);
		System.out.println("\nmax3sum " + max3sum);
		
		return list3max;
	}

	private static int getSumOfMax3(ArrayList<Integer> list3max) {
		// get the sum of the elements in an arraylist
		// for part 2 of the puzzle day 1
		
		int sum = 0;
		for(int i=0; i<list3max.size(); i++) {
			sum+=list3max.get(i);
		}
		return sum;
	}
	
	

}
