package day8;

import java.util.ArrayList;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day8/day8.txt";
		//path = "src/input/day8/day8Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		//System.out.println(inputAsStrings+"\n");

		int result = puzzle8part1(inputAsStrings);
		System.out.println("Result part 1: " + result);
		
		int result2 = puzzle8part2(inputAsStrings);
		System.out.println("Result part 2: " + result2);
				
	}
	
	private static int puzzle8part2(ArrayList<String> inputAsStrings) {
		ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
		for(int i =0; i<inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			ArrayList<Integer> treesOfRow = new ArrayList<>();
			for(int j = 0; j<line.length(); j++) {
				treesOfRow.add(Integer.parseInt(String.valueOf(line.charAt(j))));
			}
			rows.add(treesOfRow);
		}
		//System.out.println(rows);
		// we have our forest

		int scenicScoreMax = Integer.MIN_VALUE;
		
		for(int i = 0; i<rows.size(); i++) {
			for(int j = 0; j<rows.get(i).size(); j++) {
				int scenicScore = 1;
				int currentTreeHeight = rows.get(i).get(j);
				if(i==0 || j==0 || i==rows.size()-1 || j==rows.get(0).size()-1) {
					continue;
				}
				ArrayList<Integer> currentRow = rows.get(i);
				int count = 0;
				for(int k=j-1; k>=0; k--) {
					// previous in the row
					count++;
					if(currentRow.get(k)>=currentTreeHeight) {
						break;
					}
				}
				scenicScore *= count;
				count = 0;
				for(int m=j+1; m<currentRow.size(); m++) {
					// next in the row
					count++;
					if(currentRow.get(m)>=currentTreeHeight) {
						break;
					}
				}
				scenicScore *= count;
				count = 0;
				for(int l = i-1; l >= 0; l--) {
					// previous in the column
					count++;
					if(rows.get(l).get(j)>=currentTreeHeight) {
						break;
					}
				}
				scenicScore *= count;
				count = 0;
				for(int n = i+1; n < rows.size(); n++) {
					// next in the column
					count++;					
					if(rows.get(n).get(j)>=currentTreeHeight) {
						break;
					}
				}
				scenicScore *= count;
				
				if(scenicScore>scenicScoreMax) {
					scenicScoreMax = scenicScore;
				}
			}
			
		}
		
		return scenicScoreMax;
	}
	
	private static int puzzle8part1(ArrayList<String> inputAsStrings) {

		ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
		for(int i =0; i<inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			ArrayList<Integer> treesOfRow = new ArrayList<>();
			for(int j = 0; j<line.length(); j++) {
				treesOfRow.add(Integer.parseInt(String.valueOf(line.charAt(j))));
			}
			rows.add(treesOfRow);
			//rows.get(0).get(0);
		}
		//System.out.println(rows);
		// we have our forest

		int visibleTrees = 0;
		
		for(int i = 0; i<rows.size(); i++) {
			for(int j = 0; j<rows.get(i).size(); j++) {
				boolean visible = true;
				int currentTreeHeight = rows.get(i).get(j);
				//System.out.println(currentTreeHeight+"\n");
				if(i==0 || j==0 || i==rows.size()-1 || j==rows.get(0).size()-1) {
					visibleTrees+=1;
					continue;
				}
				ArrayList<Integer> currentRow = rows.get(i);
				for(int k=0; k<currentRow.size(); k++) {
					visible = true;
					// previous in the row
					if(j==k) {
						break;
					}
					if(currentRow.get(k)>=currentTreeHeight) {
						visible = false;
						break;
					}
				}
				if(visible) {
					visibleTrees+=1;
					continue;
				}
				for(int m=j+1; m<currentRow.size(); m++) {
					visible = true;
					// next in the row
					if(currentRow.get(m)>=currentTreeHeight) {
						visible = false;
						break;
					}
				}
				if(visible) {
					visibleTrees+=1;
					continue;
				}
				for(int l = 0; l < rows.size(); l++) {
					visible = true;
					// previous in the column
					if(l==i) {
						break;
					}
					if(rows.get(l).get(j)>=currentTreeHeight) {
						visible = false;
						break;
					}
				}
				if(visible) {
					visibleTrees+=1;
					continue;
				}
				for(int n = i+1; n < rows.size(); n++) {
					visible = true;
					// next in the column
					if(rows.get(n).get(j)>=currentTreeHeight) {
						visible = false;
						break;
					}
				}
				if(visible) {
					visibleTrees+=1;
					continue;
				}
			}
			
		}
		
		return visibleTrees;
	}

}
