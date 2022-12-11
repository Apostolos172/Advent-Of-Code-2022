package day11;

import java.util.Comparator;
import java.util.LinkedList;

public class Monkey implements Comparator<Monkey>, Comparable<Monkey>{

	private LinkedList<Long> startingItems = new LinkedList<>();
	private String startingItemsStr;
	private String operationStr;
	private long testOperand;
	private String testStr;
	private int truetestMonkey;
	private String truetestStr;
	private int falsetestMonkey;
	private String falsetestStr;
	private int inspectedItems;
	
	public Monkey(String startingItemsStr, String operationStr, String testStr, String truetestStr, String falsetestStr) {
		super();
		this.startingItemsStr = startingItemsStr;
		this.operationStr = operationStr;
		this.testStr = testStr;
		this.truetestStr = truetestStr;
		this.falsetestStr = falsetestStr;
	}

	public Monkey() {
		// TODO Auto-generated constructor stub
	}

	public void setStartingItemsStr(String startingItemsStr) {
		this.startingItemsStr = startingItemsStr;
	}

	public void setOperationStr(String operationStr) {
		this.operationStr = operationStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}

	public void setTruetestStr(String truetestStr) {
		this.truetestStr = truetestStr;
	}

	public void setFalsetestStr(String falsetestStr) {
		this.falsetestStr = falsetestStr;
	}
	
	public String getOperationStr() {
		return operationStr;
	}
	
	public String getStartingItemsStr() {
		return startingItemsStr;
	}

	public void build() {
		this.setInspectedItems(0);
		//String[] items = this.startingItemsStr.split(",");
		String[] items = getStartingItemsStr().split(",");
		for (int i = 0; i < items.length; i++) {
			this.getStartingItems().add(Long.parseLong((items[i].trim())));
		}
		String[] tempStrings = this.testStr.trim().split(" ");
		this.setTestOperand(Long.parseLong(this.testStr.trim().split(" ")[2]));
		this.setTruetestMonkey(Integer.parseInt(this.truetestStr.trim().split(" ")[3]));
		this.setFalsetestMonkey(Integer.parseInt(this.falsetestStr.trim().split(" ")[3]));
	}

	public int getTruetestMonkey() {
		return truetestMonkey;
	}

	public void setTruetestMonkey(int truetestMonkey) {
		this.truetestMonkey = truetestMonkey;
	}

	public int getFalsetestMonkey() {
		return falsetestMonkey;
	}

	public void setFalsetestMonkey(int falsetestMonkey) {
		this.falsetestMonkey = falsetestMonkey;
	}

	public Long getTestOperand() {
		return testOperand;
	}


	public void setTestOperand(long testOperand) {
		this.testOperand = testOperand;
	}

	public LinkedList<Long> getStartingItems() {
		return startingItems;
	}

	public int getInspectedItems() {
		return inspectedItems;
	}

	public void setInspectedItems(int inspectedItems) {
		this.inspectedItems = inspectedItems;
	}

	@Override
	public int compare(Monkey o1, Monkey o2) {
		// TODO Auto-generated method stub
		return o1.inspectedItems - o2.inspectedItems;
	}

	@Override
	public int compareTo(Monkey o) {

		if(this.inspectedItems < o.inspectedItems)
			return -1;
		else if(this.inspectedItems > o.inspectedItems)
			return 1;
		else
			return 0;
	}
		
}
