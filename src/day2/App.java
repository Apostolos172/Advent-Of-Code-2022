package day2;

import java.util.ArrayList;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day2/day2.txt";
		// path = "src/input/day2/day2Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		// System.out.println(inputAsStrings);
		
		int score = getScore(inputAsStrings);
		System.out.println("My score is " + score);
		int newScore = getNewScore(inputAsStrings);
		System.out.println("My NEW score is " + newScore);
	}
	
	private static int getScore(ArrayList<String> inputAsStrings) {

		/* keep track of myself
		 * 
		 * part 1
		 * 
		 * The first column is what your opponent is going to play: 
		 * A for Rock, B for Paper, and C for Scissors
		 * 
		 * The second column, you reason, must be what you should play in response: 
		 * X for Rock, Y for Paper, and Z for Scissors
		 * 
		 * Your total score is the sum of your scores for each round.
		 * 
		 * The score for a single round is the score for the shape you selected 
		 * (1 for Rock, 2 for Paper, and 3 for Scissors) 
		 * 
		 * + the score for the outcome of the round 
		 * (0 if you lost, 3 if the round was a draw, and 6 if you won).
		 */
		/*
		 * παίρνω κάθε στοιχείο του δυναμικού πίνακα
		 * τσεκάρω το πρώτο element του string με το 3ο και
		 * αν το πρωτο είναι Α τότε (πέτρα Α)
		 * 		αν το δευτερο είναι Χ 
		 * 			score 1+3
		 * 		αν το δεύτερο είναι Υ
		 * 			score 6+2
		 * 		αν το δεύτερο είναι Ζ
		 * 			score 0+3
		 * αν το πρώτο είναι Β τότε (Χαρτί Β)
		 * 		αν το δευτερο είναι Χ 
		 * 			score 1+0
		 * 		αν το δεύτερο είναι Υ
		 * 			score 3+2
		 * 		αν το δεύτερο είναι Ζ
		 * 			score 6+3
		 * αν το πρώτο είναι C τότε (Ψαλίδι C)
		 * 		αν το δευτερο είναι Χ 
		 * 			score 1+6
		 * 		αν το δεύτερο είναι Υ
		 * 			score 0+2
		 * 		αν το δεύτερο είναι Ζ
		 * 			score 3+3
		 *		
		 */
		int score = 0;
		for(int i = 0 ; i< inputAsStrings.size(); i++) {
			String element = inputAsStrings.get(i);
			// System.out.println(element.charAt(0) + " " + element.charAt(2));
			char firstPlayerChoice = element.charAt(0);
			char myChoice = element.charAt(2);
			if(Character.compare(firstPlayerChoice, 'A')==0) {
				// if the first player choice is A (rock Α) then:
				if (Character.compare(myChoice, 'X')==0) {
					score = score + (1+3);
				}
				if (Character.compare(myChoice, 'Y')==0) {
					score = score + (6+2);
				}
				if (Character.compare(myChoice, 'Z')==0) {
					score = score + (0+3);
				}
			}
			if(Character.compare(firstPlayerChoice, 'B')==0) {
				// if the first player choice is B (paper Β) then:
				if (Character.compare(myChoice, 'X')==0) {
					score = score + (1+0);
				}
				if (Character.compare(myChoice, 'Y')==0) {
					score = score + (3+2);
				}
				if (Character.compare(myChoice, 'Z')==0) {
					score = score + (6+3);
				}
			}
			if(Character.compare(firstPlayerChoice, 'C')==0) {
				// if the first player choice is C (scissors C) then:
				if (Character.compare(myChoice, 'X')==0) {
					score = score + (1+6);
				}
				if (Character.compare(myChoice, 'Y')==0) {
					score = score + (0+2);
				}
				if (Character.compare(myChoice, 'Z')==0) {
					score = score + (3+3);
				}
			}
		}
		return score;
	}
	
	private static int getNewScore(ArrayList<String> inputAsStrings) {
		
		/*
		 * part 2
		 * 
		 * X means you need to lose, 
		 * Y means you need to end the round in a draw, 
		 * and Z means you need to win
		 */
		int score = 0;
		for(int i = 0 ; i< inputAsStrings.size(); i++) {
			String element = inputAsStrings.get(i);
			// System.out.println(element.charAt(0) + " " + element.charAt(2));
			char firstPlayerChoice = element.charAt(0);
			char myChoice = element.charAt(2);
			if(Character.compare(firstPlayerChoice, 'A')==0) {
				// if the first player choice is A (rock Α) then:
				if (Character.compare(myChoice, 'X')==0) {
					// LOSE -> scissors
					score = score + (0+3);
				}
				if (Character.compare(myChoice, 'Y')==0) {
					// DRAW -> rock
					score = score + (3+1);
				}
				if (Character.compare(myChoice, 'Z')==0) {
					// WIN -> paper
					score = score + (6+2);
				}
			}
			if(Character.compare(firstPlayerChoice, 'B')==0) {
				// if the first player choice is B (paper Β) then:
				if (Character.compare(myChoice, 'X')==0) {
					// lose -> rock
					score = score + (0+1);
				}
				if (Character.compare(myChoice, 'Y')==0) {
					// draw -> paper
					score = score + (3+2);
				}
				if (Character.compare(myChoice, 'Z')==0) {
					// win -> scissors
					score = score + (6+3);
				}
			}
			if(Character.compare(firstPlayerChoice, 'C')==0) {
				// if the first player choice is C (scissors C) then:
				if (Character.compare(myChoice, 'X')==0) {
					// lose -> paper
					score = score + (0+2);
				}
				if (Character.compare(myChoice, 'Y')==0) {
					// draw -> scissors
					score = score + (3+3);
				}
				if (Character.compare(myChoice, 'Z')==0) {
					// win -> rock
					score = score + (6+1);
				}
			}
		}
		return score;
	}


}
