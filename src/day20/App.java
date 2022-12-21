package day20;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day20/day20.txt";
		// path = "src/input/day20/day20Small.txt";

		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		System.out.println(inputAsStrings + "\n");
		// System.out.println(inputAsStrings.size());

		int result = puzzle20part1(inputAsStrings);
		System.out.println("Result part 1: " + result);

		long result2 = puzzle20part2(inputAsStrings);
		System.out.println("Result part 2: " + result2);

	}

	private static int puzzle20part1(ArrayList<String> inputAsStrings) {

		// you must check for dublicates, use Num
		LinkedList<Num> nums = new LinkedList<>();
		for (int i = 0; i < inputAsStrings.size(); i++) {
			int curNum = Integer.parseInt(inputAsStrings.get(i));
			Num curNumObj = new Num(curNum, i); // second is the id
			nums.add(curNumObj);
		}

		int mixCount = 1;
		for (int j = 0; j < mixCount; j++) {
			for (int i1 = 0; i1 < inputAsStrings.size(); i1++) {
				final Num node = new Num(Integer.parseInt(inputAsStrings.get(i1)), i1);
				long currentIndex = nums.indexOf(node);
				nums.remove(node);
				currentIndex += node.getValue();
				currentIndex = modulo(inputAsStrings, currentIndex);
				nums.add((int) currentIndex, node);
			}
		}

		//System.out.println(nums);
		int zeroIndex = find(nums, 0);
		//System.out.println("\n" + zeroIndex);

		// for real input
		int thousandItem = (int) nums.get((zeroIndex + 1000) % nums.size()).getValue();
		int twoThousandItem = (int) nums.get((zeroIndex + 2000) % nums.size()).getValue();
		int threeThousandItem = (int) nums.get((zeroIndex + 3000) % nums.size()).getValue();

		// for sample input
//		int thousandItem = nums.get(nums.indexOf(new Num(4, 6))).getValue();
//		int twoThousandItem = nums.get(nums.indexOf(new Num(-3, 2))).getValue();
//		int threeThousandItem = nums.get(nums.indexOf(new Num(2, 1))).getValue();

		return thousandItem + twoThousandItem + threeThousandItem;
	}

	private static long modulo(ArrayList<String> originalList, long index) {
		int denominator = originalList.size() - 1;
		long modulo = index % denominator;
		if (modulo < 0) {
			modulo += denominator;
		}
		return modulo;
	}

	private static int find(LinkedList<Num> nums, int n) {
		int i = -1;
		for (Iterator<Num> iterator = nums.iterator(); iterator.hasNext();) {
			Num num = (Num) iterator.next();
			i++;
			if (num.getValue() == n) {
				return i;
			}
		}
		return -1;
	}

	private static long puzzle20part2(ArrayList<String> inputAsStrings) {
		long key = 811589153;

		LinkedList<Num> nums = new LinkedList<>();
		for (int i = 0; i < inputAsStrings.size(); i++) {
			long curNum = Integer.parseInt(inputAsStrings.get(i))*key;
			Num curNumObj = new Num(curNum, i); // second is the id

			nums.add(curNumObj);
		}

		int mixCount = 10;
		for (int j = 0; j < mixCount; j++) {
			for (int i1 = 0; i1 < inputAsStrings.size(); i1++) {
				//System.out.println(i1);
				final Num node = new Num(Integer.parseInt(inputAsStrings.get(i1))*key, i1);
				long currentIndex = nums.indexOf(node);
				nums.remove(node);
				currentIndex += node.getValue();
				currentIndex = modulo(inputAsStrings, currentIndex);
				nums.add((int) currentIndex, node);
			}
		}

		//System.out.println(nums);
		int zeroIndex = find(nums, 0);
		
		// for real input
		long thousandItem = nums.get((zeroIndex + 1000) % nums.size()).getValue();
		long twoThousandItem = nums.get((zeroIndex + 2000) % nums.size()).getValue();
		long threeThousandItem = nums.get((zeroIndex + 3000) % nums.size()).getValue();

		return thousandItem + twoThousandItem + threeThousandItem;
	}

}