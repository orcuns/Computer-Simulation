package orcun;

import java.util.ArrayList;



public class MyNode {

	public int Node_ID;
	MyNode next;
	MyNode prev;
	

	public int node_Name;
	public boolean isBusy = false;

	public ArrayList<Packet> queue = new ArrayList<Packet>();
	
	public int linkIDNext;
	public int linkIDPrev;
	public float linkDataRateNext;
	public float linkDataRatePrev;	
	
	String nextOrPrev;
	
	public MyNode(int val, String nextOrPrev2){
		
		Node_ID = val;
		next = prev = null;
		
		this.nextOrPrev=nextOrPrev2;
		
	/*	if (val==1) {
			linkIDNext=val;
			linkIDPrev=numberofNodes2;
			linkDataRateNext = dataRate;
			linkDataRatePrev =  dataRate;				
		}
		else {		
			linkIDNext=val;
			linkIDPrev=val-1;	
			linkDataRateNext = dataRate;
			linkDataRatePrev =  dataRate;			
		}
		
		for (int j = 0; j < totalBottleNeckLinks; j++) {
			
			
			if (linkIDNext == myLinks.get(j).linkID) {
				linkDataRateNext = myLinks.get(j).getDataRate();
			}
			if (linkIDPrev == myLinks.get(j).linkID) {
				linkDataRatePrev = myLinks.get(j).getDataRate();
			}
			
		}*/
	}



	
	
	
	
	
	
	// GETTER SETTERS ARE HERE
	
	






	public boolean isBusy() {
		return isBusy;
	}









	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}









	public int getLinkIDNext() {
		return linkIDNext;
	}









	public void setLinkIDNext(int linkIDNext) {
		this.linkIDNext = linkIDNext;
	}









	public int getLinkIDPrev() {
		return linkIDPrev;
	}









	public void setLinkIDPrev(int linkIDPrev) {
		this.linkIDPrev = linkIDPrev;
	}









	public float getLinkDataRateNext() {
		return linkDataRateNext;
	}









	public void setLinkDataRateNext(float linkDataRateNext) {
		this.linkDataRateNext = linkDataRateNext;
	}









	public float getLinkDataRatePrev() {
		return linkDataRatePrev;
	}









	public void setLinkDataRatePrev(float linkDataRatePrev) {
		this.linkDataRatePrev = linkDataRatePrev;
	}






	public int getNode_Name() {
		return node_Name;
	}




	public void setNode_Name(int node_Name) {
		this.node_Name = node_Name;
	}


	public int getNode_ID() {
		return Node_ID;
	}

	public void setNode_ID(int node_ID) {
		Node_ID = node_ID;
	}


	
	
	
}
