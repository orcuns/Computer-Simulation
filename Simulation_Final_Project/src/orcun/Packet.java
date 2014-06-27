package orcun;

public class Packet {

	int packetID;
	float packetSize;
	int flowID;
	int destinationNode;
	int currentNode;
	
	String nextOrPrev;
	
	
	public Packet(int packetID, float size , int flowID, int destinationNode, int currentNode,	String nextOrPrev){
		
		
		this.currentNode = currentNode;
		this.destinationNode = destinationNode;
		this.flowID = flowID;
		this.packetID = packetID;
		this.nextOrPrev = nextOrPrev;
		this.packetSize = size;
	}


	
	
	
	//GETTER SETTER
	public int getPacketID() {
		return packetID;
	}


	public void setPacketID(int packetID) {
		this.packetID = packetID;
	}





	public float getPacketSize() {
		return packetSize;
	}





	public void setPacketSize(float packetSize) {
		this.packetSize = packetSize;
	}





	public int getFlowID() {
		return flowID;
	}


	public void setFlowID(int flowID) {
		this.flowID = flowID;
	}


	public int getDestinationNode() {
		return destinationNode;
	}


	public void setDestinationNode(int destinationNode) {
		this.destinationNode = destinationNode;
	}


	public int getCurrentNode() {
		return currentNode;
	}


	public void setCurrentNode(int currentNode) {
		this.currentNode = currentNode;
	}


	public String getNextOrPrev() {
		return nextOrPrev;
	}


	public void setNextOrPrev(String nextOrPrev) {
		this.nextOrPrev = nextOrPrev;
	}
	
	
	
	
	
	
	
	
}
