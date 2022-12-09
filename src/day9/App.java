package day9;

import java.util.ArrayList;
import java.util.HashSet;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day9/day9.txt";
		path = "src/input/day9/day9Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		System.out.println(inputAsStrings+"\n");

		int result = puzzle9part1(inputAsStrings);
		System.out.println("Result part 1: " + result);
		
		int result2 = puzzle9part2(inputAsStrings);
		System.out.println("Result part 2: " + result2);
		
//		int result = puzzle8part1(inputAsStrings);
//		System.out.println("Result part 1: " + result);
//		
//		int result2 = puzzle8part2(inputAsStrings);
//		System.out.println("Result part 2: " + result2);
				
	}
	
	private static int puzzle9part2(ArrayList<String> inputAsStrings) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int puzzle9part1(ArrayList<String> inputAsStrings) {

		// 2 2d arrays
		ArrayList<ArrayList<String>> rows = new ArrayList<>();
		final int SIZE = 1000;
		for(int i =0; i<SIZE; i++) {
			ArrayList<String> seatsOfRow = new ArrayList<>();
			for(int j = 0; j<SIZE; j++) {
				seatsOfRow.add("");
			}
			rows.add(seatsOfRow);
		}
		rows.get(SIZE/2).set(SIZE/2, "sht");
		
		ArrayList<ArrayList<Integer>> rowsOfTail = new ArrayList<>();
		for(int i =0; i<SIZE; i++) {
			ArrayList<Integer> seatsOfRowOfTail = new ArrayList<>();
			for(int j = 0; j<SIZE; j++) {
				seatsOfRowOfTail.add(0);
			}
			rowsOfTail.add(seatsOfRowOfTail);
		}
		rowsOfTail.get(SIZE/2).set(SIZE/2, 1);
		
		String indexOfH = ""+SIZE/2+","+SIZE/2;
		String indexOfT = ""+SIZE/2+","+SIZE/2;
		for(int i =0; i<inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			System.out.println(line);
			
			String direction = line.split(" ")[0];
			Integer steps = Integer.parseInt(line.split(" ")[1]);
			
			for(int k = 0; k<steps; k++) {
				switch (direction) {
				case "L": // εκανα για δεξια
					// μένουμε στο ίδιο row, πάμε πίσω
					
					// current row of Head
					int indexH = Integer.parseInt(indexOfH.split(",")[0]);
					// current position of the Head in the row  
					int indexHinTheRow = Integer.parseInt(indexOfH.split(",")[1]);
					// current value of the future position of the Head
					String currendIndexH_1 = rows.get(indexH).get(indexHinTheRow+1);
					// move head to the new position
					rows.get(indexH).set(indexHinTheRow+1, currendIndexH_1.concat("h"));

					// current value of the current so far position of the Head
					String currendIndexH = rows.get(indexH).get(indexHinTheRow);
					// remove head from the previous position
					rows.get(indexH).set(indexHinTheRow, currendIndexH.replace("h", ""));
					
					String newPositionOfHead = ""+indexH+","+(indexHinTheRow+1);
					// now check the TAIL 
// MOVE THIS OUTSIDE OF SWITCH
					
					// current row of Tail
					int indexT = Integer.parseInt(indexOfT.split(",")[0]);
					// current position of the Tail in the row  
					int indexTinTheRow = Integer.parseInt(indexOfT.split(",")[1]);
					
					HashSet<String> adjacents = new HashSet<>();
					adjacents.add(""+(indexH-1)+","+indexHinTheRow); //up
					adjacents.add(""+(indexH-1)+","+(indexHinTheRow+1)); //up and right
					adjacents.add(""+(indexH)+","+(indexHinTheRow+1)); //right
					adjacents.add(""+(indexH+1)+","+(indexHinTheRow+1)); //down and right
					adjacents.add(""+(indexH+1)+","+(indexHinTheRow)); //down 
					adjacents.add(""+(indexH+1)+","+(indexHinTheRow-1)); //down and left 
					adjacents.add(""+(indexH)+","+(indexHinTheRow-1)); //left
					adjacents.add(""+(indexH-1)+","+(indexHinTheRow-1)); //up and left
					
					// αν η ουρά είναι σε μια απο τις γειτονικές 8 θεσεις μην το αλλάζεις
					// αν ΟΜΩς είναι αλλού άλλαξε
					/*
					 * 		αν είναι στην ίδια σειρα 
					 * 		ή στην ίδια στήλη
					 * 			μετακίνησε όπως το κεφάλι
					 * 		αν όχι 
					 * 			μετακίνησε διαγώνια
					 * 			πως
					 */
					if(adjacents.contains(indexOfT)) {
						// do nothing
					} else {
						if(indexT==indexH) {
							// same row
							
							// current value of the future position of the Tail
							String currendIndexT_1 = rows.get(indexT).get(indexTinTheRow+1);
							// move tail to the new position
							rows.get(indexT).set(indexTinTheRow+1, currendIndexT_1.concat("t"));

							// current value of the current so far position of the Tail
							String currendIndexT = rows.get(indexT).get(indexTinTheRow);
							// remove tail from the previous position
							rows.get(indexT).set(indexTinTheRow, currendIndexT.replace("t", ""));
						} else if(indexTinTheRow==indexHinTheRow) {
							// same column
							// THE THINGS THAT WILL DO HEAD FOR UP DOWN
							
							// current value of the future position of the Tail
							String currendIndexT_1 = rows.get(indexT).get(indexTinTheRow+1);
							// move tail to the new position
							rows.get(indexT).set(indexTinTheRow+1, currendIndexT_1.concat("t"));

							// current value of the current so far position of the Tail
							String currendIndexT = rows.get(indexT).get(indexTinTheRow);
							// remove tail from the previous position
							rows.get(indexT).set(indexTinTheRow, currendIndexT.replace("t", ""));
						} else {
							int indexRowTail = indexT;
							int indexColumnTail = indexTinTheRow;
							int indexNewRowHead = Integer.parseInt(newPositionOfHead.split(",")[0]);
							int indexNewColumnHead = Integer.parseInt(newPositionOfHead.split(",")[1]);
							// is
							/*
							 * up up and right
							 * up up and left
							 * down down and left
							 * down down and right
							 * down left left
							 * down right right
							 * up left left
							 * up right right
							 */
							if(((indexRowTail==indexNewRowHead+1 || indexRowTail==indexNewRowHead-1) && indexColumnTail==indexNewColumnHead-2)) {
								// down left left
								// up left left
								int indexNewRowTail = indexNewRowHead;
								int indexNewColumnTail = indexColumnTail+1;
							}
							
							if(((indexRowTail==indexNewRowHead+1 || indexRowTail==indexNewRowHead-1) && indexColumnTail==indexNewColumnHead+2)) {
								// down right right
								// up right right
								int indexNewRowTail = indexNewRowHead;
								int indexNewColumnTail = indexColumnTail-1;
							}
							
							if(((indexColumnTail==indexNewColumnHead+1 || indexColumnTail==indexNewColumnHead-1) && indexRowTail==indexNewRowHead+2)) {
								// down down right
								// down down left
								int indexNewRowTail = indexRowTail-1;
								int indexNewColumnTail = indexNewColumnHead;
							}
							
							if(((indexColumnTail==indexNewColumnHead+1 || indexColumnTail==indexNewColumnHead-1) && indexRowTail==indexNewRowHead-2)) {
								// up up right
								// up up left
								int indexNewRowTail = indexRowTail+1;
								int indexNewColumnTail = indexNewColumnHead;
							}
						}
						
					}
					
					// update current position of Head
					// update current position of Tail
					// update rowsOfTail with the new position of tail
					
					break;
				case "R":
					
					break;
				case "U":
					
					break;
				case "D":
					
					break;

				default:
					break;
				}
			}
			
		}
		return 0;
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

}
