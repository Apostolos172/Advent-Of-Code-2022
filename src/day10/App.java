package day10;

import java.util.ArrayList;
import java.util.Arrays;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day10/day10.txt";
		//path = "src/input/day10/day10Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		System.out.println(inputAsStrings+"\n");

		int result = puzzle10part1(inputAsStrings);
		System.out.println("Result part 1: " + result);
		
		String result2 = puzzle10part2(inputAsStrings);
		System.out.println("Result part 2: \n" + result2);
				
	}
	
	private static String puzzle10part2(ArrayList<String> inputAsStrings) {
		int x = 1;
		int cycle = 0;
		int rowIndex = 0;
		String ctr = "";
		for(int i = 0; i< inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			
			cycle++;
			rowIndex = getRowIndex(rowIndex, cycle);
			ctr = ctr.concat(getPixel(cycle, x, rowIndex));
			
			if(line.split(" ")[0].equals("addx")) {
				cycle++;
				rowIndex = getRowIndex(rowIndex, cycle);
				ctr = ctr.concat(getPixel(cycle, x, rowIndex));

				x+=Integer.parseInt(line.split(" ")[1]);
			}
		}
		return ctr;
	}

	private static int getRowIndex(int rowIndex, int cycle) {
		
		if(cycle<=40) {
			return cycle;
		} else if (cycle<=80) {
			return cycle-40;
		} else if (cycle<=120) {
			return cycle-80;
		} else if (cycle<=160) {
			return cycle-120;
		} else if (cycle<=200) {
			return cycle-160;
		} else if (cycle <= 240) {
			return cycle-200;
		} else {
			return cycle-240;
		}
	}

	private static String getPixel(int cycle, int x, int rowIndex) {
		String temp = "";
		if(x==rowIndex-1||x-1==rowIndex-1||x+1==rowIndex-1) {
			temp = temp.concat("#");
		} else {
			temp = temp.concat(".");
		}
		ArrayList<Integer> acceptedCycles = new ArrayList<>(Arrays.asList(40, 80, 120, 160, 200, 240));
		if(acceptedCycles.contains(cycle)) {
			temp = temp.concat("\n");
		}
		return temp;
	}

	private static int puzzle10part1(ArrayList<String> inputAsStrings) {
		int x = 1;
		int sum = 0;
		int cycle = 0;
		for(int i = 0; i< inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			cycle++;
			sum+=getSignalStrength(cycle, x);
			if(line.split(" ")[0].equals("addx")) {
				cycle++;
				sum+=getSignalStrength(cycle, x);
				x+=Integer.parseInt(line.split(" ")[1]);
			}
		}
		return sum;
	}
	
	private static int getSignalStrength(int cycle, int x) {
		ArrayList<Integer> acceptedCycles = new ArrayList<>(Arrays.asList(20, 60, 100, 140, 180, 220));
		if(acceptedCycles.contains(cycle)) {
			return cycle*x;
		}
		return 0;
	}

}
