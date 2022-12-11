package day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import general.ReadFile;

public class App {
	
	public static void main(String[] args) {
		String path = "src/input/day11/day11.txt";
		//path = "src/input/day11/day11Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		//System.out.println(inputAsStrings+"\n");

		int result = puzzle11part1(inputAsStrings);
		System.out.println("Result part 1: " + result);
		
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
	
		long modulus = 1;
		for (Iterator<Monkey> iterator = monkeys.iterator(); iterator.hasNext();) {
			Monkey monkey = (Monkey) iterator.next();
			long divisor = monkey.getTestOperand();
			modulus = modulus * divisor;
		}
		
		for (int i = 0; i < rounds; i++) {
			for (Monkey monkey : monkeys) {
				LinkedList<Long> items = monkey.getStartingItems();
				for (Iterator<Long> iterator = items.iterator(); iterator.hasNext();) {
					Long item =  iterator.next();
					long newItem = doTheOperation(item, monkey.getOperationStr());
					if(dividedBy3) {
						newItem = (long) Math.floor(newItem/3);	
					} else {
						newItem = (long) (newItem%modulus);
					}
					if((newItem%monkey.getTestOperand())==0) {
						monkeys.get(monkey.getTruetestMonkey()).getStartingItems().add(newItem);
					} else {
						monkeys.get(monkey.getFalsetestMonkey()).getStartingItems().add(newItem);						
					}
					monkey.setInspectedItems(monkey.getInspectedItems()+1);
				}
				items.removeAll(items);

			}

		}

		// sort monkeys 
		Collections.sort(monkeys);
		// get the two max
		long monkey1st = monkeys.get(monkeys.size()-1).getInspectedItems();
		int monkey2nd = monkeys.get(monkeys.size()-2).getInspectedItems();
		long monkeyBusiness = (long)((monkey1st) * monkey2nd);
		
		return monkeyBusiness;
	}

	private static long doTheOperation(Long item, String operationStr) {

		String operator = operationStr.split(" ")[3];
		String operand = operationStr.split(" ")[4];

		
		switch (operator) {
		case "+":
			if(operand.equals("old")) {
				return item + item;
			} else {
				return item + Long.parseLong(operand);
			}
		case "*":
			if(operand.equals("old")) {
				return item * item;
			} else {
				return item * Long.parseLong(operand);
			}
		default:
			break;
		}
		
		return item;
	}

}
