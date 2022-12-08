package day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day8/day8.txt";
		// 384 no, too low
		// 2240 no, too low
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
			//rows.get(0).get(0);
		}
		//System.out.println(rows);
		// we have our forest

		int visibleTrees = 0;
		int scenicScoreMax = Integer.MIN_VALUE;
		//visibleTrees+= 2*(rows.get(0).size());
		//visibleTrees+= (2*(rows.size())-4);
		// on the edge added
		
		for(int i = 0; i<rows.size(); i++) {
			for(int j = 0; j<rows.get(i).size(); j++) {
				boolean visible = true;
				int scenicScore = 1;
				int currentTreeHeight = rows.get(i).get(j);
				//System.out.println(currentTreeHeight+"\n");
				//good
				//int currentTree = i;
				if(i==0 || j==0 || i==rows.size()-1 || j==rows.get(0).size()-1) {
					//visibleTrees+=1;
					continue;
				}
				ArrayList<Integer> currentRow = rows.get(i);
				int k;
				int count = 0;
				int previous = Integer.MIN_VALUE;
				//for(k=0; k<currentRow.size(); k++) {
				for(k=j-1; k>=0; k--) {
//					visible = true;
					// previous in the row
//					if(j==k) {
//						break;
//					}
					if(currentRow.get(k)==0) {
						count++;
						continue;
					} else {
						count++;
					}
					if(currentRow.get(k)>=previous) {
						//visible = false;
						//count++;
						
					} else {
						//break;
					}
					previous = currentRow.get(k);
					if(previous>=currentTreeHeight) {
						break;
					}
				}
				scenicScore *= count;
//				if(visible) {
//					visibleTrees+=1;
//					continue;
//				}
//				for(int m=k; m<currentRow.size(); m++) {
				count = 0;
				previous = Integer.MIN_VALUE;
				for(int m=j+1; m<currentRow.size(); m++) {
					//visible = true;
					if(currentRow.get(m)==0) {
						count++;
						continue;
					} else {
						count++;
					}
					// next in the row
//					if(j==m) {
//						break;
//					}
//					if(currentRow.get(m)>=currentTreeHeight) {
//						visible = false;
//						break;
//					}
					if(currentRow.get(m)>=previous) {
						//visible = false;
						//count++;
						
					} else {
						//break;
					}
					previous = currentRow.get(m);
					if(previous>=currentTreeHeight) {
						break;
					}
				}
				scenicScore *= count;
//				if(visible) {
//					visibleTrees+=1;
//					continue;
//				}
				count = 0;
				previous = Integer.MIN_VALUE;
//				for(int l = 0; l < rows.size(); l++) {
				for(int l = i-1; l >= 0; l--) {
					if(rows.get(l).get(j)==0) {
						count++;
						continue;
					} else {
						count++;
					}
					//visible = true;
					// previous in the column
//					if(l==i) {
//						break;
//					}
//					if(rows.get(l).get(j)>=currentTreeHeight) {
//						visible = false;
//						break;
//					}
					if(rows.get(l).get(j)>=previous) {
						//count ++;
					} else {
						//break;
					}
					previous = rows.get(l).get(j);
					if(previous>=currentTreeHeight) {
						break;
					}
				}
				scenicScore *= count;
//				if(visible) {
//					visibleTrees+=1;
//					continue;
//				}
				count = 0;
				previous = Integer.MIN_VALUE;
				//for(int n = 0; n < rows.size(); n++) {
				for(int n = i+1; n < rows.size(); n++) {
					//visible = true;
					if(rows.get(n).get(j)==0) {
						count++;
						continue;
					} else {
						count++;
					}
					// next in the column
//					if(n==i) {
//						break;
//					}
//					if(rows.get(n).get(j)>=currentTreeHeight) {
//						visible = false;
//						break;
//					}
					if(rows.get(n).get(j)>=previous) {
						//count ++;
					} else {
						//break;
					}
					previous = rows.get(n).get(j);
					if(previous>=currentTreeHeight) {
						break;
					}
				}
//				if(visible) {
//					visibleTrees+=1;
//					continue;
//				}
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
		//visibleTrees+= 2*(rows.get(0).size());
		//visibleTrees+= (2*(rows.size())-4);
		// on the edge added
		
		for(int i = 0; i<rows.size(); i++) {
			for(int j = 0; j<rows.get(i).size(); j++) {
				boolean visible = true;
				int currentTreeHeight = rows.get(i).get(j);
				//System.out.println(currentTreeHeight+"\n");
				//good
				//int currentTree = i;
				if(i==0 || j==0 || i==rows.size()-1 || j==rows.get(0).size()-1) {
					visibleTrees+=1;
					continue;
				}
				ArrayList<Integer> currentRow = rows.get(i);
				int k;
				for(k=0; k<currentRow.size(); k++) {
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
//				for(int m=k; m<currentRow.size(); m++) {
				for(int m=j+1; m<currentRow.size(); m++) {
					visible = true;
					// next in the row
//					if(j==m) {
//						break;
//					}
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
				//for(int n = 0; n < rows.size(); n++) {
				for(int n = i+1; n < rows.size(); n++) {
					visible = true;
					// next in the column
//					if(n==i) {
//						break;
//					}
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
