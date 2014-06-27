package orcun;

public class MyNode {

	public int ID;
	MyNode next;
	MyNode prev;
	
	
	public MyNode(int val){
		
		ID = val;
		next = prev = null;
	}
	
}
