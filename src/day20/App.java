package day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day20/day20.txt";
		path = "src/input/day20/day20Small.txt";

		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		System.out.println(inputAsStrings + "\n");
		// System.out.println(inputAsStrings.size());

		int result = puzzle20part1(inputAsStrings);
		System.out.println("Result part 1: " + result);

		long result2 = puzzle20part2(inputAsStrings);
		System.out.println("Result part 2: " + result2);

	}

	private static int puzzle20part1(ArrayList<String> inputAsStrings) {
		LinkedList<Integer> nums = new LinkedList<>();
		for (int i = 0; i < inputAsStrings.size(); i++) {
			int curNum = Integer.parseInt(inputAsStrings.get(i));
			nums.add(curNum);
		}

		int i = -1;
		for (Iterator<String> iterator = inputAsStrings.iterator(); iterator.hasNext();) {
			Integer numForMovement = Integer.parseInt((String) iterator.next());
			i++;
			System.out.println(i);
			int indexOfNumForMovementInNumsList = find(nums, numForMovement);
			// System.out.println(numForMovement);

			if (indexOfNumForMovementInNumsList == -1) {
				System.out.println("not found the element " + numForMovement);
			} else {
				int newIndex = 0;
				boolean cond = true;
				while (cond) {
					newIndex = indexOfNumForMovementInNumsList + numForMovement;
					if (newIndex <= 0) {
						// newIndex = nums.size() - 1 + newIndex;
						newIndex = nums.size() - 1 + newIndex;
						if (newIndex < 0) {

							newIndex = (-1) * Math.abs(newIndex) % nums.size();
						} else {

							newIndex = Math.abs(newIndex) % nums.size();
						}
					}
					if (newIndex >= nums.size()) {
						newIndex = numForMovement - (nums.size() - (indexOfNumForMovementInNumsList + 1));
						if (newIndex < 0) {

							newIndex = (-1) * Math.abs(newIndex) % nums.size();
						} else {

							newIndex = Math.abs(newIndex) % nums.size();
						}
					}
					cond = (newIndex < 0 || newIndex >= nums.size());
					if (cond) {
						numForMovement = newIndex;
					}
				}

				// remove and insert
				nums.remove(indexOfNumForMovementInNumsList);
				nums.add(newIndex, numForMovement);
//				if (indexOfNumForMovementInNumsList < newIndex) {
////					nums.add(newIndex, numForMovement);
//					nums.remove(indexOfNumForMovementInNumsList);
//					nums.add(newIndex, numForMovement);
//
//				} else {
//					// indexOfNumForMovementInNumsList > newIndex and other
//					nums.remove(indexOfNumForMovementInNumsList);
//					nums.add(newIndex, numForMovement);
//					
//				}
			}
		}

		System.out.println(nums);
		int zeroIndex = find(nums, 0);
//		System.out.println("\n" + zeroIndex);
//		int thousandItem = nums.get(zeroIndex + 1000);
//		int twoThousandItem = nums.get(zeroIndex + 2000);
//		int threeThousandItem = nums.get(zeroIndex + 3000);
		int thousandItem = nums.get(find(nums, 4));
		int twoThousandItem = nums.get(find(nums, -3));
		int threeThousandItem = nums.get(find(nums, 2));

		// 2967 too low
		// 16570 too high
		// 14774 too high
		return thousandItem + twoThousandItem + threeThousandItem;
	}

	private static int find(LinkedList<Integer> nums, Integer num) {

		int pos = -1;
		// Java Program to find an element in a Linked List
		// Traversing through the Linked List
		for (int i = 0; i < nums.size(); i++) {

			// Extracting each element in
			// the Linked List
			int llElement = nums.get(i);

			// Checking if the extracted element is equal to
			// the element to be searched
			if (llElement == num) {

				// Assigning the index of the
				// element to answer
				pos = i;
				break;
			}
		}

		return pos;
	}

	private static long puzzle20part2(ArrayList<String> inputAsStrings) {
		// TODO Auto-generated method stub
		return 0;
	}

}