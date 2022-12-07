package day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import general.ReadFile;

public class App {

	public static void main(String[] args) {
		String path = "src/input/day7/day7.txt";
		//path = "src/input/day7/day7Small.txt";
		
		ArrayList<String> inputAsStrings = new ReadFile(path).getContentAsArrayList();
		System.out.println(inputAsStrings+"\n");

		//int result = puzzle7part1(inputAsStrings);
		//System.out.println("Result part 1: " + result);
		
		int result2 = puzzle7part2(inputAsStrings);
		System.out.println("Result part 2: " + result2);
				
	}
	
	private static int puzzle7part2(ArrayList<String> inputAsStrings) {
		HashMap<String, ArrayList<ArrayList<Object>>> directories = new HashMap<>();
		directories.put("/", new ArrayList<>());
		String currendDirectory = ""; 
		String situation = "cd";
		for(int i =0; i< inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			System.out.println(line);
			
			switch (situation) {
			case "ls":
				if(line.split(" ")[0].equals("dir")) {
					// directory
					String name = line.split(" ")[1];
					directories.put(currendDirectory+"_"+name, new ArrayList<>());
				} else if (line.split(" ")[0].equals("$")) {
					break;
				} else {
					// file
					String name = line.split(" ")[1];
					Integer size = Integer.parseInt(line.split(" ")[0]);
					System.out.println("currendDirectory " +currendDirectory);
					ArrayList<ArrayList<Object>> filesOfCurrendDirectory = directories.get(currendDirectory);
					filesOfCurrendDirectory.add(new ArrayList<>(Arrays.asList(name, size)));
					directories.put(currendDirectory, filesOfCurrendDirectory);
				}
				break;
			case "cd":
				
				break;
			default:
				break;
			}
			
			if(line.startsWith("$")) {
				// command
				if(line.contains("cd")) {
					situation = "cd";
					switch (line.split(" ")[2]) {
					case "..":
						//currendDirectory.split("_").length-1;
						String targetOfReplacement = currendDirectory.split("_")[currendDirectory.split("_").length-1]; 
						//currendDirectory = currendDirectory.replace("_"+targetOfReplacement, "");
						currendDirectory = currendDirectory.replaceAll("_"+targetOfReplacement+"$", "");
						// currendDirectory /_jbt_bbm_nsshzppb_jlcqcb_nzpvt_fct_fct !CAUTION
						break;
					case "/":
						currendDirectory = "/";
						break;
					default:
						// a name of directory
						String name = line.split(" ")[2];
						currendDirectory = currendDirectory.concat("_"+name);
						break;
					}
				} else if (line.contains("ls")) {
					situation = "ls";
				}
			}
		}
		System.out.println(directories);
		// here we have our filesystem
		/*
		 * now
		 * 
		 * 
		 */
		
		//Object test = 0;
		//Integer test = 0;
		HashMap<String, Integer> directoriesSize = new HashMap<>();
		directories.forEach((key, value)->{
			System.out.println(key + value);
			directories.forEach((keyInside, valueInside)->{
				//if(!keyInside.equals(key) && keyInside.startsWith(key)) {
				if(keyInside.startsWith(key)) {
					//test.toString();
					//test +=1;
					valueInside.forEach(arrayOfFile->{
						Integer sum = directoriesSize.get(key);
						if(sum!=null) {
							sum+= (Integer) arrayOfFile.get(1);
						} else {
							sum = (Integer) arrayOfFile.get(1);
						}
						directoriesSize.put(key, sum);
					});
				}
			});
		});
		System.out.println(directoriesSize);
		System.out.println();
		
		int finalSum = 0;
		// Iterator
		Iterator<Entry<String, Integer> > new_Iterator = directoriesSize.entrySet().iterator();
		// Iterating every set of entry in the HashMap
		while (new_Iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Integer> entry = (Map.Entry<java.lang.String, java.lang.Integer>) new_Iterator
					.next();
			if(entry.getValue()<100000)
				finalSum += entry.getValue();
		}
//		directoriesSize.forEach((key, value)->{
//			if(value<100000) {
//				int sum = (int) finalSum;
//				sum +=value;
//				finalSum = sum;
//				//finalSum = finalSum + value;
//			}
//		});
		
		// new data
		int total_disk_space = 70000000;
		int need_unused_space = 30000000;
		int total_amount_of_used_space = directoriesSize.get("/");
		int size_of_unused_space_currently = total_disk_space-total_amount_of_used_space;
		int size_of_file_must_be_deleted = need_unused_space -size_of_unused_space_currently; 
		//TreeMap<Integer, String> directoriesSizeSorted = new TreeMap<Integer,String>();
		ArrayList<Integer> directoriesSizeSorted = new ArrayList<Integer>();
		directoriesSize.forEach((key, value)->{
			directoriesSizeSorted.add(value);
		});
		System.out.println(directoriesSizeSorted);
		directoriesSizeSorted.sort(null);
		System.out.println(directoriesSizeSorted.size() + " " + directoriesSize.size());
		System.out.println(directoriesSizeSorted);
//		directoriesSizeSorted.forEach((key, value)->{
//			if(key>=size_of_file_must_be_deleted) {
//				
//			}
//		});
		
		int size = 0;

		for(int i =0; i<directoriesSizeSorted.size(); i++) {
			if(directoriesSizeSorted.get(i)>=size_of_file_must_be_deleted) {
				size = directoriesSizeSorted.get(i);
				break;
			}
		}
		
		return size;
	}

	private static int puzzle7part22(ArrayList<String> inputAsStrings) {
		HashMap<String, ArrayList<ArrayList<Object>>> directories = new HashMap<>();
		directories.put("/", new ArrayList<>());
		String currendDirectory = ""; 
		String situation = "cd";
		for(int i =0; i< inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			System.out.println(line);
			
			switch (situation) {
			case "ls":
				if(line.split(" ")[0].equals("dir")) {
					// directory
					String name = line.split(" ")[1];
					directories.put(currendDirectory+"_"+name, new ArrayList<>());
				} else if (line.split(" ")[0].equals("$")) {
					break;
				} else {
					// file
					String name = line.split(" ")[1];
					Integer size = Integer.parseInt(line.split(" ")[0]);
					System.out.println("currendDirectory " +currendDirectory);
					ArrayList<ArrayList<Object>> filesOfCurrendDirectory = directories.get(currendDirectory);
					filesOfCurrendDirectory.add(new ArrayList<>(Arrays.asList(name, size)));
					directories.put(currendDirectory, filesOfCurrendDirectory);
				}
				break;
			case "cd":
				
				break;
			default:
				break;
			}
			
			if(line.startsWith("$")) {
				// command
				if(line.contains("cd")) {
					situation = "cd";
					switch (line.split(" ")[2]) {
					case "..":
						//currendDirectory.split("_").length-1;
						String targetOfReplacement = currendDirectory.split("_")[currendDirectory.split("_").length-1]; 
						//currendDirectory = currendDirectory.replace("_"+targetOfReplacement, "");
						currendDirectory = currendDirectory.replaceAll("_"+targetOfReplacement+"$", "");
						// currendDirectory /_jbt_bbm_nsshzppb_jlcqcb_nzpvt_fct_fct !CAUTION
						break;
					case "/":
						currendDirectory = "/";
						break;
					default:
						// a name of directory
						String name = line.split(" ")[2];
						currendDirectory = currendDirectory.concat("_"+name);
						break;
					}
				} else if (line.contains("ls")) {
					situation = "ls";
				}
			}
		}
		System.out.println(directories);
		// here we have our filesystem
		/*
		 * now
		 * 
		 * 
		 */
		
		//Object test = 0;
		//Integer test = 0;
		HashMap<String, Integer> directoriesSize = new HashMap<>();
		directories.forEach((key, value)->{
			System.out.println(key + value);
			directories.forEach((keyInside, valueInside)->{
				//if(!keyInside.equals(key) && keyInside.startsWith(key)) {
				if(keyInside.startsWith(key)) {
					//test.toString();
					//test +=1;
					valueInside.forEach(arrayOfFile->{
						Integer sum = directoriesSize.get(key);
						if(sum!=null) {
							sum+= (Integer) arrayOfFile.get(1);
						} else {
							sum = (Integer) arrayOfFile.get(1);
						}
						directoriesSize.put(key, sum);
					});
				}
			});
		});
		System.out.println(directoriesSize);
		System.out.println();
		
		int finalSum = 0;
		// Iterator
		Iterator<Entry<String, Integer> > new_Iterator = directoriesSize.entrySet().iterator();
		// Iterating every set of entry in the HashMap
		while (new_Iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Integer> entry = (Map.Entry<java.lang.String, java.lang.Integer>) new_Iterator
					.next();
			if(entry.getValue()<100000)
				finalSum += entry.getValue();
		}
//		directoriesSize.forEach((key, value)->{
//			if(value<100000) {
//				int sum = (int) finalSum;
//				sum +=value;
//				finalSum = sum;
//				//finalSum = finalSum + value;
//			}
//		});
		
		// new data
		int total_disk_space = 70000000;
		int need_unused_space = 30000000;
		int total_amount_of_used_space = directoriesSize.get("/");
		int size_of_unused_space_currently = total_disk_space-total_amount_of_used_space;
		int size_of_file_must_be_deleted = need_unused_space -size_of_unused_space_currently; 
		TreeMap<Integer, String> directoriesSizeSorted = new TreeMap<Integer,String>();
		directoriesSize.forEach((key, value)->{
			directoriesSizeSorted.put(value, key);
		});
		System.out.println(directoriesSizeSorted.size() + " " + directoriesSize.size());
		System.out.println(directoriesSizeSorted);
//		directoriesSizeSorted.forEach((key, value)->{
//			if(key>=size_of_file_must_be_deleted) {
//				
//			}
//		});
		
		int size = 0;
		// Iterator
		Iterator<Entry<Integer, String> > new_Iterator_ = directoriesSizeSorted.entrySet().iterator();
		// Iterating every set of entry in the HashMap
		while (new_Iterator_.hasNext()) {
			Map.Entry<java.lang.Integer, java.lang.String> entry = (Map.Entry<java.lang.Integer, java.lang.String>) new_Iterator_
					.next();
			System.out.println(entry.getKey()>=size_of_file_must_be_deleted);
			if(entry.getKey()>=size_of_file_must_be_deleted) {
				size = entry.getKey();
				break;
			}
			
		}
		
		return size;
	}
	
	

	private static int puzzle7part1(ArrayList<String> inputAsStrings) {
		
		HashMap<String, ArrayList<ArrayList<Object>>> directories = new HashMap<>();
		directories.put("/", new ArrayList<>());
		String currendDirectory = ""; 
		String situation = "cd";
		for(int i =0; i< inputAsStrings.size(); i++) {
			String line = inputAsStrings.get(i);
			System.out.println(line);
			
			switch (situation) {
			case "ls":
				if(line.split(" ")[0].equals("dir")) {
					// directory
					String name = line.split(" ")[1];
					directories.put(currendDirectory+"_"+name, new ArrayList<>());
				} else if (line.split(" ")[0].equals("$")) {
					break;
				} else {
					// file
					String name = line.split(" ")[1];
					Integer size = Integer.parseInt(line.split(" ")[0]);
					System.out.println("currendDirectory " +currendDirectory);
					ArrayList<ArrayList<Object>> filesOfCurrendDirectory = directories.get(currendDirectory);
					filesOfCurrendDirectory.add(new ArrayList<>(Arrays.asList(name, size)));
					directories.put(currendDirectory, filesOfCurrendDirectory);
				}
				break;
			case "cd":
				
				break;
			default:
				break;
			}
			
			if(line.startsWith("$")) {
				// command
				if(line.contains("cd")) {
					situation = "cd";
					switch (line.split(" ")[2]) {
					case "..":
						//currendDirectory.split("_").length-1;
						String targetOfReplacement = currendDirectory.split("_")[currendDirectory.split("_").length-1]; 
						//currendDirectory = currendDirectory.replace("_"+targetOfReplacement, "");
						currendDirectory = currendDirectory.replaceAll("_"+targetOfReplacement+"$", "");
						// currendDirectory /_jbt_bbm_nsshzppb_jlcqcb_nzpvt_fct_fct !CAUTION
						break;
					case "/":
						currendDirectory = "/";
						break;
					default:
						// a name of directory
						String name = line.split(" ")[2];
						currendDirectory = currendDirectory.concat("_"+name);
						break;
					}
				} else if (line.contains("ls")) {
					situation = "ls";
				}
			}
		}
		System.out.println(directories);
		// here we have our filesystem
		/*
		 * now
		 * 
		 * 
		 */
		
		//Object test = 0;
		//Integer test = 0;
		HashMap<String, Integer> directoriesSize = new HashMap<>();
		directories.forEach((key, value)->{
			System.out.println(key + value);
			directories.forEach((keyInside, valueInside)->{
				//if(!keyInside.equals(key) && keyInside.startsWith(key)) {
				if(keyInside.startsWith(key)) {
					//test.toString();
					//test +=1;
					valueInside.forEach(arrayOfFile->{
						Integer sum = directoriesSize.get(key);
						if(sum!=null) {
							sum+= (Integer) arrayOfFile.get(1);
						} else {
							sum = (Integer) arrayOfFile.get(1);
						}
						directoriesSize.put(key, sum);
					});
				}
			});
		});
		System.out.println(directoriesSize);
		System.out.println();
		
		int finalSum = 0;
		// Iterator
		Iterator<Entry<String, Integer> > new_Iterator = directoriesSize.entrySet().iterator();
		// Iterating every set of entry in the HashMap
		while (new_Iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Integer> entry = (Map.Entry<java.lang.String, java.lang.Integer>) new_Iterator
					.next();
			if(entry.getValue()<100000)
				finalSum += entry.getValue();
		}
//		directoriesSize.forEach((key, value)->{
//			if(value<100000) {
//				int sum = (int) finalSum;
//				sum +=value;
//				finalSum = sum;
//				//finalSum = finalSum + value;
//			}
//		});
		return finalSum;
	}

}
