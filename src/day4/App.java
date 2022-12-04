package day4;

import java.util.ArrayList;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day4/day4.txt";
		//path = "src/input/day4/day4Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		// System.out.println(inputAsStrings);

		int assignmentsPairs = getAssignmentsPairs1RangeContain2nd(inputAsStrings);
		System.out.println("Assignment pairs where one range fully contain the other " + assignmentsPairs);
		
		int assignmentPairsTheRangesOverlap = getAssignmentsPairsRangesOverlap(inputAsStrings);
		System.out.println("Assignment pairs where ranges overlap " + assignmentPairsTheRangesOverlap);

	}

	private static int getAssignmentsPairs1RangeContain2nd(ArrayList<String> inputAsStrings) {
		/*
		 * my approach part 1
		 * 
		 * Read each line of inputs
		 * Split them into two ranges based on the comma
		 * and now
		 * 
		 * if EACH element of the first belongs to the second 
		 * or if EACH element of the second belongs to the first
		 * then true a boolean variable
		 * and add to the total sum
		 * 
		 * Repeatedly until the end of the inputs
		 * 
		 */
		
		boolean included;
		int sum=0;
		for(int i =0; i<inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			String [] ranges = line.split(",");
			String firstRange = ranges[0];
			String secondRange = ranges[1];
			//System.out.println(firstRange + " " + secondRange);

			included = true;
			
			String[] firstRangeNumbers = firstRange.split("-");
			int beginningOfFirstString = Integer.parseInt(firstRangeNumbers[0]);
			int endOfFirstString = Integer.parseInt(firstRangeNumbers[1]);

			String[] secondRangeNumbers = secondRange.split("-");
			int beginningOfSecondString = Integer.parseInt(secondRangeNumbers[0]);
			int endOfSecondString = Integer.parseInt(secondRangeNumbers[1]);
			
			for(int j = beginningOfFirstString; j<=endOfFirstString; j++) {
				if(j<beginningOfSecondString || j>endOfSecondString) {
					included = false;
					break;
				}
			}
			if(!included) {
				included = true;
				for(int j = beginningOfSecondString; j<=endOfSecondString; j++) {
					if(j<beginningOfFirstString || j>endOfFirstString) {
						included = false;
						break;
					}
				}
			}
			if (included) {
				sum++;
			}
			
		}
		
		return sum;
	}
	
	private static int getAssignmentsPairsRangesOverlap(ArrayList<String> inputAsStrings) {
		/*
		 * my approach part 2
		 * 
		 * Read each line of inputs
		 * Split them into two ranges based on the comma
		 * and now
		 * 
		 * if ANY element of the first belongs to the second
		 * or if ANY element of the second belongs to the first
		 * then true a boolean variable
		 * and add to the total sum
		 * 
		 * Repeatedly until the end of the inputs
		 * 
		 */
		
		boolean included;
		int sum=0;
		for(int i =0; i<inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			String [] ranges = line.split(",");
			String firstRange = ranges[0];
			String secondRange = ranges[1];
			// System.out.println(firstRange + " " + secondRange);

			included = false;
			
			String[] firstRangeNumbers = firstRange.split("-");
			int beginningOfFirstString = Integer.parseInt(firstRangeNumbers[0]);
			int endOfFirstString = Integer.parseInt(firstRangeNumbers[1]);

			String[] secondRangeNumbers = secondRange.split("-");
			int beginningOfSecondString = Integer.parseInt(secondRangeNumbers[0]);
			int endOfSecondString = Integer.parseInt(secondRangeNumbers[1]);
			
			for(int j = beginningOfFirstString; j<=endOfFirstString; j++) {
				if(j>=beginningOfSecondString && j<=endOfSecondString) {
					// at least one element to be inside
					included = true;
					break;
				}
			}

			if (included) {
				sum++;
			}
			
		}
		
		return sum;
	}

}
