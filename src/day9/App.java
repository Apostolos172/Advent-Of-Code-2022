package day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day9/day9.txt";
		path = "src/input/day9/day9Small.txt";
		path = "src/input/day9/day9SmallPart2.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		System.out.println(inputAsStrings+"\n");

		int result = puzzle9part1(inputAsStrings);
		System.out.println("Result part 1: " + result);
		
		int result2 = puzzle9part2(inputAsStrings);
		System.out.println("Result part 2: " + result2);
		
				
	}
	
	private static int puzzle9part2(ArrayList<String> inputAsStrings) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int puzzle9part1(ArrayList<String> inputAsStrings) {

		// 2 2d arrays
		ArrayList<ArrayList<String>> rows = new ArrayList<>();
		final int SIZE = 1000;
		//final int SIZE = 20;
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
				
				String newPositionOfHead = "";
				// current row of Head
				int indexH = Integer.parseInt(indexOfH.split(",")[0]);
				// current position of the Head in the row  
				int indexHinTheRow = Integer.parseInt(indexOfH.split(",")[1]);
				
				String currendIndexH_1; 
				String currendIndexH;
				
				switch (direction) {
				case "L": 
					// μένουμε στο ίδιο row, πάμε πίσω
					
					// current value of the future position of the Head
					currendIndexH_1 = rows.get(indexH).get(indexHinTheRow-1);
					// move head to the new position
					rows.get(indexH).set(indexHinTheRow-1, currendIndexH_1.concat("h"));
					
					newPositionOfHead = ""+indexH+","+(indexHinTheRow-1);

					break;
				case "R":
					// μένουμε στο ίδιο row, πάμε μπρος
					
					// current value of the future position of the Head
					currendIndexH_1 = rows.get(indexH).get(indexHinTheRow+1);
					// move head to the new position
					rows.get(indexH).set(indexHinTheRow+1, currendIndexH_1.concat("h"));
					
					newPositionOfHead = ""+indexH+","+(indexHinTheRow+1);
					break;
				case "U":
					// μένουμε στο ίδιο column, πάμε πάνω
					
					// current value of the future position of the Head
					currendIndexH_1 = rows.get(indexH-1).get(indexHinTheRow);
					// move head to the new position
					rows.get(indexH-1).set(indexHinTheRow, currendIndexH_1.concat("h"));
					
					newPositionOfHead = ""+(indexH-1)+","+(indexHinTheRow);
					break;
				case "D":
					// μένουμε στο ίδιο column, πάμε κάτω
					
					// current value of the future position of the Head
					currendIndexH_1 = rows.get(indexH+1).get(indexHinTheRow);
					// move head to the new position
					rows.get(indexH+1).set(indexHinTheRow, currendIndexH_1.concat("h"));
					
					newPositionOfHead = ""+(indexH+1)+","+(indexHinTheRow);
					break;

				default:
					break;
				}
				
				// now check the TAIL 
//MOVE THIS OUTSIDE OF SWITCH
				
				// current row of Tail
				int indexT = Integer.parseInt(indexOfT.split(",")[0]);
				// current position of the Tail in the row  
				int indexTinTheRow = Integer.parseInt(indexOfT.split(",")[1]);
				
				int indexNewRowHead = Integer.parseInt(newPositionOfHead.split(",")[0]);
				int indexNewColumnHead = Integer.parseInt(newPositionOfHead.split(",")[1]);
				
				HashSet<String> adjacents = new HashSet<>();
				adjacents.add(""+(indexNewRowHead-1)+","+indexNewColumnHead); //up
				adjacents.add(""+(indexNewRowHead-1)+","+(indexNewColumnHead+1)); //up and right
				adjacents.add(""+(indexNewRowHead)+","+(indexNewColumnHead+1)); //right
				adjacents.add(""+(indexNewRowHead+1)+","+(indexNewColumnHead+1)); //down and right
				adjacents.add(""+(indexNewRowHead+1)+","+(indexNewColumnHead)); //down 
				adjacents.add(""+(indexNewRowHead+1)+","+(indexNewColumnHead-1)); //down and left 
				adjacents.add(""+(indexNewRowHead)+","+(indexNewColumnHead-1)); //left
				adjacents.add(""+(indexNewRowHead-1)+","+(indexNewColumnHead-1)); //up and left

				adjacents.add(""+(indexNewRowHead)+","+(indexNewColumnHead)); //overlapping
				
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
				int indexRowTail = indexT;
				int indexColumnTail = indexTinTheRow;
				String newPositionOfTail = indexOfT;
				boolean var = Objects.equals(indexOfT, "5,5");
				if(adjacents.contains(indexOfT)) {
					// do nothing
				} else {
					if(indexT==indexH) {
						// same row
						String currendIndexT_1; 
						switch (direction) {
						case "R":
							// current value of the future position of the Tail
							currendIndexT_1 = rows.get(indexT).get(indexTinTheRow+1);
							// move tail to the new position
							rows.get(indexT).set(indexTinTheRow+1, currendIndexT_1.concat("t"));
							newPositionOfTail = ""+(indexT)+","+(indexTinTheRow+1);

							break;
						case "L":
							// current value of the future position of the Tail
							currendIndexT_1 = rows.get(indexT).get(indexTinTheRow-1);
							// move tail to the new position
							rows.get(indexT).set(indexTinTheRow-1, currendIndexT_1.concat("t"));
							newPositionOfTail = ""+(indexT)+","+(indexTinTheRow-1);

							break;

						default:
							break;
						}

						
					} else if(indexTinTheRow==indexHinTheRow) {
						// same column
						// THE THINGS THAT WILL DO HEAD FOR UP DOWN
						String currendIndexT_1;
						switch (direction) {
						case "U":
							// current value of the future position of the Tail
							currendIndexT_1 = rows.get(indexT-1).get(indexTinTheRow);
							// move tail to the new position
							rows.get(indexT-1).set(indexTinTheRow, currendIndexT_1.concat("t"));
							newPositionOfTail = ""+(indexT-1)+","+(indexTinTheRow);

							break;
						case "D":
							// current value of the future position of the Tail
							currendIndexT_1 = rows.get(indexT+1).get(indexTinTheRow);
							// move tail to the new position
							rows.get(indexT+1).set(indexTinTheRow, currendIndexT_1.concat("t"));
							newPositionOfTail = ""+(indexT+1)+","+(indexTinTheRow);

							break;

						default:
							break;
						}
						


					} else {
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
						int indexNewRowTail = -1;
						int indexNewColumnTail = -1;
						if(((indexRowTail==indexNewRowHead+1 || indexRowTail==indexNewRowHead-1) && indexColumnTail==indexNewColumnHead-2)) {
							// down left left
							// up left left
							indexNewRowTail = indexNewRowHead;
							indexNewColumnTail = indexColumnTail+1;
						}
						
						if(((indexRowTail==indexNewRowHead+1 || indexRowTail==indexNewRowHead-1) && indexColumnTail==indexNewColumnHead+2)) {
							// down right right
							// up right right
							indexNewRowTail = indexNewRowHead;
							indexNewColumnTail = indexColumnTail-1;
						}
						
						if(((indexColumnTail==indexNewColumnHead+1 || indexColumnTail==indexNewColumnHead-1) && indexRowTail==indexNewRowHead+2)) {
							// down down right
							// down down left
							indexNewRowTail = indexRowTail-1;
							indexNewColumnTail = indexNewColumnHead;
						}
						
						if(((indexColumnTail==indexNewColumnHead+1 || indexColumnTail==indexNewColumnHead-1) && indexRowTail==indexNewRowHead-2)) {
							// up up right
							// up up left
							indexNewRowTail = indexRowTail+1;
							indexNewColumnTail = indexNewColumnHead;
						}
						
						newPositionOfTail = ""+indexNewRowTail+","+indexNewColumnTail;

						// current value of the future position of the Tail
						String currendIndexT_1 = rows.get(indexNewRowTail).get(indexNewColumnTail);
						// move tail to the new position
						rows.get(indexNewRowTail).set(indexNewColumnTail, currendIndexT_1.concat("t"));

					}
					// current value of the current so far position of the Tail
					String currendIndexT = rows.get(indexT).get(indexTinTheRow);
					// remove tail from the previous position
					rows.get(indexT).set(indexTinTheRow, currendIndexT.replace("t", ""));
					
				}
				
				// current value of the current so far position of the Head
				currendIndexH = rows.get(indexH).get(indexHinTheRow);
				// remove head from the previous position
				rows.get(indexH).set(indexHinTheRow, currendIndexH.replace("h", ""));
				
				// update current position of Head
				indexOfH = newPositionOfHead;
				
				// update current position of Tail
				indexOfT = newPositionOfTail;
				
				// update rowsOfTail with the new position of tail
				int row = Integer.parseInt(newPositionOfTail.split(",")[0]);
				int column = Integer.parseInt(newPositionOfTail.split(",")[1]);
				rowsOfTail.get(row).set(column, 1);
			}
			
		}
		
		int count = 0;
		for(int i =0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				count += rowsOfTail.get(i).get(j);
			}
		}
		
		return count;
	}

}
