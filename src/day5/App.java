package day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day5/day5.txt";
		//path = "src/input/day5/day5Small.txt";
		// path = "src/input/day5/day5OtherAccount.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		// System.out.println(inputAsStrings+"\n");

		String result = puzzle5part1(inputAsStrings);
		System.out.println("Result part 1: " + result);
		
		String result2 = puzzle5part2(inputAsStrings);
		System.out.println("Result part 2: " + result2);
		
//		int assignmentsPairs = getAssignmentsPairs1RangeContain2nd(inputAsStrings);
//		System.out.println("Assignment pairs where one range fully contain the other " + assignmentsPairs);
		
//		int assignmentPairsTheRangesOverlap = getAssignmentsPairsRangesOverlap(inputAsStrings);
//		System.out.println("Assignment pairs where ranges overlap " + assignmentPairsTheRangesOverlap);

	}

	private static String puzzle5part1(ArrayList<String> inputAsStrings) {
		/*
		 * my approach part 1
		 * 
		 * Δημιούργησε τις στοίβες με το χέρι και έπειτα 
		 * Read each line of inputs 
		 * αφού έχεις περάσει μια κενή γραμμή
		 * άντλησε τους τρεις αριθμούς σπάζοντας το κάθε string στα κενά και κρατώντας 1, 3, 5 indexes στοιχείων
		 * 
		 * and now
		 * 
		 * pop (from 1 index) elements from (from 3 index) stack (find it from a switch), to (from 5 index) stack
		 * 		(find it from a switch)
		 * 
		 * ! Have an array of stacks in order to achieve easier access on each stack instead of switch
		 * 
		 * Repeatedly until the end of the inputs
		 * 
		 * return pop from 3 stacks as a string
		 * 
		 */
		
		/*
		 * input stacks
[G]                 [D] [R]        
[W]         [V]     [C] [T] [M]    
[L]         [P] [Z] [Q] [F] [V]    
[J]         [S] [D] [J] [M] [T] [V]
[B]     [M] [H] [L] [Z] [J] [B] [S]
[R] [C] [T] [C] [T] [R] [D] [R] [D]
[T] [W] [Z] [T] [P] [B] [B] [H] [P]
[D] [S] [R] [D] [G] [F] [S] [L] [Q]
 1   2   3   4   5   6   7   8   9 
		 */
		
		ArrayList<Stack> stacks = new ArrayList<Stack>();
		
		for(int i = 0; i < 9; i++) {
			stacks.add(new Stack<String>());
		}
//		ArrayList<String> stack1 = new ArrayList<String>(Arrays.asList("D", "T", "R", "B", "J", "L", "W", "G"));
//		ArrayList<String> stack2 = new ArrayList<String>(Arrays.asList("S", "W", "C"));
//		ArrayList<String> stack3 = new ArrayList<String>(Arrays.asList("R", "Z", "T", "M"));
//		ArrayList<String> stack4 = new ArrayList<String>(Arrays.asList("D", "T", "C", "H", "S", "P", "V"));
//		ArrayList<String> stack5 = new ArrayList<String>(Arrays.asList("G", "P", "T", "L", "D", "Z"));
//		ArrayList<String> stack6 = new ArrayList<String>(Arrays.asList("F", "B", "R", "Z", "J", "Q", "C", "D"));
//		ArrayList<String> stack7 = new ArrayList<String>(Arrays.asList("S", "B", "D", "J", "M", "F", "T", "R"));
//		ArrayList<String> stack8 = new ArrayList<String>(Arrays.asList("L", "H", "R", "B", "T", "V", "M"));
//		ArrayList<String> stack9 = new ArrayList<String>(Arrays.asList("Q", "P", "D", "S", "V"));

		stacks.get(0).addAll(Arrays.asList("D", "T", "R", "B", "J", "L", "W", "G"));
		//System.out.println(stacks.get(0).peek());
		stacks.get(1).addAll(Arrays.asList("S", "W", "C"));
		stacks.get(2).addAll(Arrays.asList("R", "Z", "T", "M"));
		stacks.get(3).addAll(Arrays.asList("D", "T", "C", "H", "S", "P", "V"));
		stacks.get(4).addAll(Arrays.asList("G", "P", "T", "L", "D", "Z"));
		stacks.get(5).addAll(Arrays.asList("F", "B", "R", "Z", "J", "Q", "C", "D"));
		stacks.get(6).addAll(Arrays.asList("S", "B", "D", "J", "M", "F", "T", "R"));
		stacks.get(7).addAll(Arrays.asList("L", "H", "R", "B", "T", "V", "M"));
		stacks.get(8).addAll(Arrays.asList("Q", "P", "D", "S", "V"));
		
		/*
		 * other account for checking the solution with other data
[P]     [C]         [M]            
[D]     [P] [B]     [V] [S]        
[Q] [V] [R] [V]     [G] [B]        
[R] [W] [G] [J]     [T] [M]     [V]
[V] [Q] [Q] [F] [C] [N] [V]     [W]
[B] [Z] [Z] [H] [L] [P] [L] [J] [N]
[H] [D] [L] [D] [W] [R] [R] [P] [C]
[F] [L] [H] [R] [Z] [J] [J] [D] [D]
 1   2   3   4   5   6   7   8   9 
		 */

		// other account for checking the solution with other data
//		stacks.get(0).addAll(Arrays.asList("F", "H", "B", "V", "R", "Q", "D", "P"));
//		stacks.get(1).addAll(Arrays.asList("L", "D", "Z", "Q", "W", "V"));
//		stacks.get(2).addAll(Arrays.asList("H", "L", "Z", "Q", "G", "R", "P", "C"));
//		stacks.get(3).addAll(Arrays.asList("R", "D", "H", "F", "J", "V", "B"));
//		stacks.get(4).addAll(Arrays.asList("Z", "W", "L", "C"));
//		stacks.get(5).addAll(Arrays.asList("J", "R", "P", "N", "T", "G", "V", "M"));
//		stacks.get(6).addAll(Arrays.asList("J", "R", "L", "V", "M", "B", "S"));
//		stacks.get(7).addAll(Arrays.asList("D", "P", "J"));
//		stacks.get(8).addAll(Arrays.asList("D", "C", "N", "W", "V"));
		
		// first sample input
//		for(int i = 0; i < 3; i++) {
//			stacks.add(new Stack<String>());
//		}
//		stacks.get(0).push("Z");
//		stacks.get(0).push("N");
//		stacks.get(1).push("M");
//		stacks.get(1).push("C");
//		stacks.get(1).push("D");
//		stacks.get(2).push("P");

		// System.out.println(stacks+"\n");
		
		boolean read= false;
		for(int i =0; i<inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			if(read) {
				// System.out.println(line);
				String[] lineArray = line.split(" ");
				int howMany = Integer.parseInt(lineArray[1]);
				int fromStack = Integer.parseInt(lineArray[3]);
				int toStack = Integer.parseInt(lineArray[5]);
				for(int j=0; j<howMany; j++) {
					Object poppedItem = (stacks.get(fromStack-1)).pop();
					stacks.get(toStack-1).push(poppedItem);
				}
			}
			if(line.equals("")) {
				read = true;
			}

		}
		//String message = ""+stacks.get(0).peek()+stacks.get(1).peek()+stacks.get(2).peek();
		String message = "";
		for(int i=0; i<stacks.size(); i++) {
			message+=stacks.get(i).peek();
		}
		
		return message;
	}
	
	private static String puzzle5part2(ArrayList<String> inputAsStrings) {

		/*
		 * my approach part 2
		 * 
		 * Δημιούργησε τις στοίβες με το χέρι και έπειτα 
		 * Read each line of inputs 
		 * αφού έχεις περάσει μια κενή γραμμή
		 * άντλησε τους τρεις αριθμούς σπάζοντας το κάθε string στα κενά και κρατώντας 1, 3, 5 indexes στοιχείων
		 * 
		 * and now
		 * 
		 * pop (from 1 index) elements from (from 3 index) stack (find it from a switch), to (from 5 index) stack
		 * 		(find it from a switch)
		 * 
		 * ! Have an array of stacks in order to achieve easier access on each stack instead of switch
		 * 
		 * Repeatedly until the end of the inputs
		 * 
		 * return pop from 3 stacks as a string
		 * 
		 */
		
		/*
		 * input stacks
[G]                 [D] [R]        
[W]         [V]     [C] [T] [M]    
[L]         [P] [Z] [Q] [F] [V]    
[J]         [S] [D] [J] [M] [T] [V]
[B]     [M] [H] [L] [Z] [J] [B] [S]
[R] [C] [T] [C] [T] [R] [D] [R] [D]
[T] [W] [Z] [T] [P] [B] [B] [H] [P]
[D] [S] [R] [D] [G] [F] [S] [L] [Q]
 1   2   3   4   5   6   7   8   9 
		 */
		
		ArrayList<Stack> stacks = new ArrayList<Stack>();
		
		for(int i = 0; i < 9; i++) {
			stacks.add(new Stack<String>());
		}

		stacks.get(0).addAll(Arrays.asList("D", "T", "R", "B", "J", "L", "W", "G"));
		stacks.get(1).addAll(Arrays.asList("S", "W", "C"));
		stacks.get(2).addAll(Arrays.asList("R", "Z", "T", "M"));
		stacks.get(3).addAll(Arrays.asList("D", "T", "C", "H", "S", "P", "V"));
		stacks.get(4).addAll(Arrays.asList("G", "P", "T", "L", "D", "Z"));
		stacks.get(5).addAll(Arrays.asList("F", "B", "R", "Z", "J", "Q", "C", "D"));
		stacks.get(6).addAll(Arrays.asList("S", "B", "D", "J", "M", "F", "T", "R"));
		stacks.get(7).addAll(Arrays.asList("L", "H", "R", "B", "T", "V", "M"));
		stacks.get(8).addAll(Arrays.asList("Q", "P", "D", "S", "V"));
		
		/*
		 * other account for checking the solution with other data
[P]     [C]         [M]            
[D]     [P] [B]     [V] [S]        
[Q] [V] [R] [V]     [G] [B]        
[R] [W] [G] [J]     [T] [M]     [V]
[V] [Q] [Q] [F] [C] [N] [V]     [W]
[B] [Z] [Z] [H] [L] [P] [L] [J] [N]
[H] [D] [L] [D] [W] [R] [R] [P] [C]
[F] [L] [H] [R] [Z] [J] [J] [D] [D]
 1   2   3   4   5   6   7   8   9 
		 */

		// other account for checking the solution with other data
//		stacks.get(0).addAll(Arrays.asList("F", "H", "B", "V", "R", "Q", "D", "P"));
//		stacks.get(1).addAll(Arrays.asList("L", "D", "Z", "Q", "W", "V"));
//		stacks.get(2).addAll(Arrays.asList("H", "L", "Z", "Q", "G", "R", "P", "C"));
//		stacks.get(3).addAll(Arrays.asList("R", "D", "H", "F", "J", "V", "B"));
//		stacks.get(4).addAll(Arrays.asList("Z", "W", "L", "C"));
//		stacks.get(5).addAll(Arrays.asList("J", "R", "P", "N", "T", "G", "V", "M"));
//		stacks.get(6).addAll(Arrays.asList("J", "R", "L", "V", "M", "B", "S"));
//		stacks.get(7).addAll(Arrays.asList("D", "P", "J"));
//		stacks.get(8).addAll(Arrays.asList("D", "C", "N", "W", "V"));
		
//		// first sample input
//		for(int i = 0; i < 3; i++) {
//			stacks.add(new Stack<String>());
//		}
//		stacks.get(0).push("Z");
//		stacks.get(0).push("N");
//		stacks.get(1).push("M");
//		stacks.get(1).push("C");
//		stacks.get(1).push("D");
//		stacks.get(2).push("P");

		// System.out.println(stacks+"\n");
		
		boolean read= false;
		for(int i =0; i<inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			if(read) {
				// System.out.println(line);
				String[] lineArray = line.split(" ");
				int howMany = Integer.parseInt(lineArray[1]);
				int fromStack = Integer.parseInt(lineArray[3]);
				int toStack = Integer.parseInt(lineArray[5]);
				Stack tempStack = new Stack();
				for(int j=0; j<howMany; j++) {
					Object poppedItem = (stacks.get(fromStack-1)).pop();
					tempStack.push(poppedItem);
				}
				for(int j=0; j<howMany; j++) {
					Object poppedItem = tempStack.pop();
					stacks.get(toStack-1).push(poppedItem);
				}
			}
			if(line.equals("")) {
				read = true;
			}

		}
		//String message = ""+stacks.get(0).peek()+stacks.get(1).peek()+stacks.get(2).peek();
		String message = "";
		for(int i=0; i<stacks.size(); i++) {
			message+=stacks.get(i).peek();
		}
		
		return message;
	}

}
