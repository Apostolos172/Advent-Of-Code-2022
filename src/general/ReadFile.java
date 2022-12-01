package general;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	private String fileName;

	public ReadFile(String filename) {
		this.fileName = "filename.txt";
		this.fileName = filename;
	}

	public ArrayList<String> getContentAsArrayList() {
		ArrayList<String> tempArray = new ArrayList<String>();
		try {
			File myObj = new File(this.fileName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				//System.out.println(data);
				tempArray.add(data);
				
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return tempArray;
	}

}
