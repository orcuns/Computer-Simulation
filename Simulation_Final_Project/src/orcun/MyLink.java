package orcun;

public class MyLink {

	public int linkID;
	public int dataRate;
	
	
	
	public MyLink(int bottleNeckLinkId, int dataRate){
		
		this.dataRate = dataRate;
		this.linkID = bottleNeckLinkId;
		
	}



	
	
	
	
	// GETLER SETLER
	
	public int getLinkID() {
		return linkID;
	}



	public void setLinkID(int linkID) {
		this.linkID = linkID;
	}



	public int getDataRate() {
		return dataRate;
	}



	public void setDataRate(int dataRate) {
		this.dataRate = dataRate;
	}
	
	
	
	
	
}
