package orcun;

public class FutureEventList {

	public float clock;
	
	public int packetNumber =  1;
	public int flowNumber = 1;
	public int node_Name;
	public boolean isTransmit;
	public boolean nextOrPrev;
	public int node_Destination;
	
	//constructor
	public FutureEventList(){
		
		
		
	}

	
	
	
	
	//GETTLER SETTLER
	
	public float getClock() {
		return clock;
	}

	public void setClock(float clock) {
		this.clock = clock;
	}



	public int getPacketNumber() {
		return packetNumber;
	}

	public void setPacketNumber(int packetNumber) {
		this.packetNumber = packetNumber;
	}

	public int getFlowNumber() {
		return flowNumber;
	}

	public void setFlowNumber(int flowNumber) {
		this.flowNumber = flowNumber;
	}

	public int getNode_Name() {
		return node_Name;
	}

	public void setNode_Name(int node_Name) {
		this.node_Name = node_Name;
	}

	public boolean isTransmit() {
		return isTransmit;
	}

	public void setTransmit(boolean isTransmit) {
		this.isTransmit = isTransmit;
	}

	public boolean isNextOrPrev() {
		return nextOrPrev;
	}

	public void setNextOrPrev(boolean nextOrPrev) {
		this.nextOrPrev = nextOrPrev;
	}

	public int getNode_Destination() {
		return node_Destination;
	}

	public void setNode_Destination(int node_Destination) {
		this.node_Destination = node_Destination;
	}
	
	
	
	
	
	
}
