package day3;

import java.util.ArrayList;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day3/day3.txt";
		//path = "src/input/day3/day3Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		// System.out.println(inputAsStrings);

		int sumOfPriorities = getSumOfPriorities(inputAsStrings);
		System.out.println("sumOfPriorities " + sumOfPriorities);
		
		int sumOfPrioritiesOfBadgeItemTypes = getSumOfPrioritiesOfBadges(inputAsStrings);
		System.out.println("sumOfPrioritiesOfBadgeItemTypes " + sumOfPrioritiesOfBadgeItemTypes);
		
	}
	
	private static int getSumOfPriorities(ArrayList<String> inputAsStrings) {
		
		/*
		 * my approach part 1
		 * 
		 * split each line in the middle
		 * for each character in the first string check if it exists in the second string
		 * if exists, stop searching
		 * 
		 * αντιστοίχισε αυτόν τον χαρακτήρα με άλλον 
		 * Πως
		 * πάρε το ascii value του συγκεκριμένου χαρακτήρα, και έπειτα αφαίρεσε τόσα ώστε να φτάσεις στην 
		 * αντιστοίχιση της άσκησης 
		 * 
		 * όλο αυτό επαναληπτικά για τα line strings και κράτα sum from all of these. End
		*/
		
		int sum = 0;
		for(int i = 0; i<inputAsStrings.size(); i++) {
			String tempString = inputAsStrings.get(i);
			int lengthOfLine = tempString.length();
			int middle = lengthOfLine/2;
			String firstCompartment = tempString.substring(0, middle);
			String secondCompartment = tempString.substring(middle);
			// System.out.println(firstCompartment + " " + secondCompartment);
			
			int j;
			for(j = 0; j<firstCompartment.length(); j++) {
				if (secondCompartment.contains(""+firstCompartment.charAt(j))) {
					break;
				}
			}
			char itemChar = firstCompartment.charAt(j);
			int priorityOfItemChar = getPriorityOfItemChar(itemChar);
			sum += priorityOfItemChar;
		}
		return sum;
	}
	
	private static int getSumOfPrioritiesOfBadges(ArrayList<String> inputAsStrings) {
		/*
		 * my approach part 2
		 * 
		 * traverse the array input every 3 items
		 * 
		 * for each character in the first string check if it exists in the second string
		 * if exists, check if also exists in the third string
		 * if exists, stop searching
		 * αντιστοίχισε αυτόν τον χαρακτήρα με άλλον 
		 * Πως
		 * πάρε το ascii value του συγκεκριμένου χαρακτήρα, και έπειτα αφαίρεσε τόσα ώστε να φτάσεις στην 
		 * αντιστοίχιση της άσκησης 
		 * 
		 * όλο αυτό επαναληπτικά για τα line strings και κράτα sum from all of these. End
		*/
		
		int sum = 0;
		// System.out.println("length of array input " + inputAsStrings.size());
		for(int i = 0; i<inputAsStrings.size(); i=i+3) {
			String tempString1 = inputAsStrings.get(i);
			String tempString2 = inputAsStrings.get(i+1);
			String tempString3 = inputAsStrings.get(i+2);
			int j;
			for(j = 0; j<tempString1.length(); j++) {
				if (tempString2.contains(""+tempString1.charAt(j))) {
					if (tempString3.contains(""+tempString1.charAt(j))) {
						break;
					}
				}
			}
			
			char itemChar = tempString1.charAt(j);
			int priorityOfItemChar = getPriorityOfItemChar(itemChar);
			sum += priorityOfItemChar;
		}
		return sum;
	}
	
	private static int getPriorityOfItemChar(char itemChar) {
		int asciiRepresentationOfItemChar = itemChar;
		int priorityOfItemChar;
		if(Character.isUpperCase(itemChar)) {
			// A : 65 ascii
			// A : 27 puzzle
			// difference : 38
			priorityOfItemChar = asciiRepresentationOfItemChar-38;
		} else {
			// a : 97 ascii
			// a : 1 puzzle
			// difference : 96
			priorityOfItemChar = asciiRepresentationOfItemChar-96;
		}
		return priorityOfItemChar;
	}
}
