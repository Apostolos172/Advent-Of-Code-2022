package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import general.ReadFile;

public class App {
	
	public static void main(String[] args) {
		String path = "src/input/day11/day11.txt";
		path = "src/input/day11/day11Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		//System.out.println(inputAsStrings+"\n");

		//int result = puzzle11part1(inputAsStrings);
		//System.out.println("Result part 1: " + result);
		
		long result2 = puzzle11part2(inputAsStrings);
		System.out.println("Result part 2: " + result2);
				
	}
	
	private static int puzzle11part1(ArrayList<String> inputAsStrings) {
		return (int) puzzle11(inputAsStrings, 20, true);
	}
	
	private static long puzzle11part2(ArrayList<String> inputAsStrings) {
		return puzzle11(inputAsStrings, 10000, false);
	}
	
	private static long puzzle11(ArrayList<String> inputAsStrings, int rounds, boolean dividedBy3) {
		ArrayList<Monkey> monkeys = new ArrayList<Monkey>();

		Monkey curMonkey = null;
		for (int i = 0; i < inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			//System.out.println(line);
			String lineAttribute = (line.split(":")[0]).trim();
			String lineAttributeValue = "";
			if(line.split(":").length>1)
				lineAttributeValue = line.split(":")[1];
			if(line.contains("Monkey")) {
				curMonkey = new Monkey();
			}
			switch (lineAttribute) {
			case "Starting items":
				curMonkey.setStartingItemsStr(lineAttributeValue);
				break;
			case "Operation":
				curMonkey.setOperationStr(lineAttributeValue.trim());
				break;
			case "Test":
				curMonkey.setTestStr(lineAttributeValue);
				break;
			case "If true":
				curMonkey.setTruetestStr(lineAttributeValue);
				break;
			case "If false":
				curMonkey.setFalsetestStr(lineAttributeValue);
				break;
			default:
				break;
			}			
			if(line.equals("")) {
				curMonkey.build();
				monkeys.add(curMonkey);
			}
		}
		
		curMonkey.build();
		monkeys.add(curMonkey);
		
		//we have our monkeys
		//System.out.println(monkeys);
		
		for (int i = 0; i < rounds; i++) {
			for (Monkey monkey : monkeys) {
				LinkedList<Long> items = monkey.getStartingItems();
				//for (Iterator iterator = monkey.getStartingItems().iterator(); iterator.hasNext();) {
				for (Iterator<Long> iterator = items.iterator(); iterator.hasNext();) {
					Long item =  iterator.next();
					long newItem = doTheOperation(item, monkey.getOperationStr());
//					if(dividedBy3)
//						newItem = (int) Math.floor(newItem/3);
					long temp =  (newItem%monkey.getTestOperand());
					if((newItem%monkey.getTestOperand())==0) {
						monkeys.get(monkey.getTruetestMonkey()).getStartingItems().add(newItem);
					} else {
						monkeys.get(monkey.getFalsetestMonkey()).getStartingItems().add(newItem);						
					}
					monkey.setInspectedItems(monkey.getInspectedItems()+1);
				}
				items.removeAll(items);
				//System.out.println();

			}
			// get the two max
			ArrayList approved = new ArrayList(Arrays.asList(0, 19, 999, 1999, 2999));
			if(approved.contains(i)) {
				int monkey1st = monkeys.get(0).getInspectedItems();
				int monkey2st = monkeys.get(1).getInspectedItems();
				int monkey3st = monkeys.get(2).getInspectedItems();
				int monkey4th = monkeys.get(3).getInspectedItems();
				System.out.println();
			}

		}

		// check operations
		// sort monkeys 
		Collections.sort(monkeys);
		// get the two max
		int monkey1st = monkeys.get(monkeys.size()-1).getInspectedItems();
		int monkey2nd = monkeys.get(monkeys.size()-2).getInspectedItems();
		// multiply
		long monkeyBusiness = monkey1st * monkey2nd;
		// return
		System.out.println();
		
		return monkeyBusiness;
	}

	private static long doTheOperation(Long item, String operationStr) {

		String operator = operationStr.split(" ")[3];
		String operand = operationStr.split(" ")[4];
		//System.out.println(operator);
		//System.out.println(operand);
		
		switch (operator) {
		case "+":
			if(operand.equals("old")) {
				return item + item;
			} else {
				return item + Long.parseLong(operand);
			}
//		case "-":
//			if(operand.equals("old")) {
//				return item - item;
//			} else {
//				return item - Integer.parseInt(operand);
//			}
		case "*":
			if(operand.equals("old")) {
				return item * item;
			} else {
				return item * Long.parseLong(operand);
			}
//		case "/":
//			if(operand.equals("old")) {
//				return item / item;
//			} else {
//				return item / Integer.parseInt(operand);
//			}
		default:
			break;
		}
		
		return item;
	}

}
