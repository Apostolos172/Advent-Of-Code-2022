package day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day6/day6.txt";
		//path = "src/input/day6/day6Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		System.out.println(inputAsStrings+"\n");

		int result = puzzle6part1(inputAsStrings);
		System.out.println("Result part 1: " + result);
		
		int result2 = puzzle6part2(inputAsStrings);
		System.out.println("Result part 2: " + result2);
		
	}

	private static int puzzle6part1(ArrayList<String> inputAsStrings) {
		
		/*
		 * part 1
		 * 
		 * I have the input
		 * 
		 * traverse the string and for each character 
		 * beginning from the fourth (index 3)
		 * check the previous 3 are all different with the fourth
		 * 
		 * keep four different variables each time
		 * or keep them in a linked list, fifo, input from behind, output from front
		 * 
		 * Then I can put them all four characters into set resetting it its time
		 * if set is 4 size exactly stop and return the index +1 
		 * 
		 */
		
		String input = inputAsStrings.get(0);
//		String input = inputAsStrings.get(1);
//		String input = inputAsStrings.get(2);
//		String input = inputAsStrings.get(3);
//		String input = inputAsStrings.get(4);
		// System.out.println(input);
		 
	    LinkedList<Character> ll = new LinkedList<Character>(Arrays.
	    		asList(input.charAt(0), input.charAt(1), input.charAt(2)));
		int fourthCharIndex = 0;
		for(int i = 3 ; i < input.length(); i++) {
//			char firstChar = input.charAt(i-3);
//			char secondChar = input.charAt(i-2);
//			char thirdChar = input.charAt(i-1);
			char fourthChar = input.charAt(i);
			ll.add(fourthChar);
						
			//Set<Character> current4Chars = new HashSet<Character>();
			Set<Character> current4Chars = new HashSet<Character>(ll);
//			current4Chars.add(firstChar);
//			current4Chars.add(secondChar);
//			current4Chars.add(thirdChar);
//			current4Chars.add(fourthChar);
			ll.remove();

			
			if(current4Chars.size()==4) {
				fourthCharIndex = i+1;
				break;
			}
		}
		
		return fourthCharIndex;
	}
	
	private static int puzzle6part2(ArrayList<String> inputAsStrings) {

		/*
		 * part 2
		 * 
		 * I have the input
		 * 
		 * traverse the string and for each character 
		 * beginning from the 14 item (index 13)
		 * check the previous 13 are all different with the 14
		 * 
		 * keep 14 different variables each time
		 * or keep them in a linked list fifo input from behind, output from front
		 * I can put them all 14 characters into set resetting it its time
		 * if set is 14 size exactly stop and return the index +1 
		 * 
		 */
		
		String input = inputAsStrings.get(0);
//		String input = inputAsStrings.get(1);
//		String input = inputAsStrings.get(2);
//		String input = inputAsStrings.get(3);
//		String input = inputAsStrings.get(4);
		// System.out.println(input);
		 
	    LinkedList<Character> ll = new LinkedList<Character>(Arrays.
	    		asList(input.charAt(0), input.charAt(1), input.charAt(2),
	    				input.charAt(3), input.charAt(4), input.charAt(5),
	    				input.charAt(6), input.charAt(7), input.charAt(8),
	    				input.charAt(9), input.charAt(10), input.charAt(11),
	    				input.charAt(12)));
		int char14Index = 0;
		for(int i = 13 ; i < input.length(); i++) {
			char char14 = input.charAt(i);
			ll.add(char14);
						
			Set<Character> current14Chars = new HashSet<Character>(ll);
			ll.remove();
			
			if(current14Chars.size()==14) {
				char14Index = i+1;
				break;
			}
		}
		
		return char14Index;
	}

}
