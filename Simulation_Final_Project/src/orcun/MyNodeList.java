package orcun;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;




public class MyNodeList {

	MyNode maNode;
	MyNode first;
	public int minLink;
	float dTrans;
	float dProp;
	
	float clock = 0;
	boolean event;
	
	ArrayList<FutureEventList> eventArray = new ArrayList<FutureEventList>(); 
	ArrayList<Packet> packetList = new ArrayList<Packet>(); 
	ArrayList<MyNode> nodeList = new ArrayList<MyNode>(); 
	ArrayList<MyLink> linkList = new ArrayList<MyLink>(); 
	ArrayList<Packet> tempArray = new ArrayList<Packet>(); 
	
	String nextOrPrev;
	
	public float transmitFEL = 0;
	public float receiveFEL = -1;
	public boolean  isServerBusy=false;
	public int packets_in_Queue = 0;
	
	FutureEventList FEL;
	Packet tempCurrentPacket;
	Packet packet;
	MyLink link;
	MyNode node;
	
	//Constructor
	public MyNodeList(int numberofNodes, int senderID, int receiveID, int numberOfPackets, float messageSize, int dataRate, float propagationDelay,
					  int totalBottleNeckLinks, int bottleNeckLink_ID, float bottleNeck_DataRate, ArrayList<MyLink> myLinks){
		
		
		//FIND mininum rout
	//		findShortestWay( numberofNodes,  senderID,  receiveID);  
				
		
		//tüm nodelarý yaratýyor!
		for(int i = 1; i <=numberofNodes; i++ )
		{
			addLast(i, nextOrPrev);
		}
		

		//FIND dtrans and dProp
	//	findTransmissionDelay(messageSize, dataRate);
		findPropagationDelay(propagationDelay);
		
	
		
		
	//	calculateAndPrint(numberOfPackets, findGivenID_NODE(senderID), numberofNodes,myLinks, totalBottleNeckLinks, dataRate);
		

		System.out.println("OUTPUT: ");
		System.out.println("--------------------------------------------------------------------------------------------------------");
		
		
		calculations(numberOfPackets, findGivenID_NODE(senderID), numberofNodes,myLinks, totalBottleNeckLinks, dataRate, receiveID, messageSize, senderID);	
		
			
		
		
		System.out.println("--------------------------------------------------------------------------------------------------------");
		
		maNode = findGivenID_NODE(senderID);
/*		System.out.println("linknext: "+ maNode.linkIDNext + " linkPrev: "+ maNode.linkIDPrev+ " linkNextDataRate: "+ maNode.linkDataRateNext+ " linkPrevDataRate: "+ maNode.linkDataRatePrev 
				+ " NODEID: "+ maNode.Node_ID  );
		maNode = findGivenID_NODE(senderID).next;
		System.out.println("linknext: "+ maNode.linkIDNext + " linkPrev: "+ maNode.linkIDPrev+ " linkNextDataRate: "+ maNode.linkDataRateNext+ " linkPrevDataRate: "+ maNode.linkDataRatePrev 
				+ " NODEID: "+ maNode.Node_ID  );*/
	}
	
	

	
	boolean firstEventBool = false;
	boolean isLoop = true;
	int eventCounter = 0;
	int currentFlowID = 1;
	FutureEventList currentEvent;
	public void calculations(int numberOfPackets, MyNode mn, int numberOfNode, ArrayList<MyLink> myLinks, int totalBottleNeckLinks, int dataRate, int receiveID, float messageSize, int senderID){
		
		
		
		//myNodeTemp = mn;
		
		
		
		for (int i = 0; i < numberOfNode; i++) {
			
			link =  new MyLink(i, dataRate);
			linkList.add(i, link);
		}
		
		for (MyLink bottleNeck : myLinks) {
			if (bottleNeck == null) {
				break;
			}
			else {
				linkList.get(bottleNeck.getLinkID()).setDataRate(bottleNeck.getDataRate());
			}
		}
		
		for (int i = 0; i < numberOfNode; i++) {
			
			node = new MyNode(i, nextOrPrev);
			node.setLinkDataRateNext(linkList.get(i).dataRate);
			
			if (linkList.get(i) != null) {
				node.setLinkDataRatePrev(linkList.get(i).dataRate);
			}
			else
				node.setLinkDataRatePrev(linkList.get(numberOfNode).dataRate);
			
			nodeList.add(i, node);
		}
		
		
		
		int i = 0;
		for (Packet flow : RingNetwork.myFlows) {
			
			if (flow != null) {
				
				float packetSize = flow.getPacketSize()/numberOfPackets;
				findShortestWay( nodeList,  senderID,  receiveID);  
				String rot = nextOrPrev;
				packet = new Packet( flow.packetID , packetSize, flow.flowID, flow.destinationNode, flow.currentNode, rot);
				packetList.add(i, packet);
				//currentFlowID++;
				i++;
			}else
				continue;
			
		}
		
				
		// FEL
		for (Packet packet : packetList) {
				
			if (packet != null) {
				
				if (packet.packetID == 0) {
					
					FEL = new FutureEventList();
					
					FEL.setTransmit(true);
					FEL.setClock(FEL.getClock() + findTransmissionDelayByRoute2(packet, nodeList));
					FEL.setPacketNumber(packet.packetID);
					FEL.setFlowNumber(packet.flowID);
					FEL.setNode_Name(packet.currentNode);
							
					eventArray.add(eventCounter, FEL);
				
					continue;
				}
			//this line adds the packet to queue of Node
			nodeList.get(packet.currentNode).queue.add(packet);	
			}
			else {
				continue;
			}
			
		}
		
		
		
		while(eventArray.size() != 0){
			
		//	if(firstEventBool == true){
						// SORTS THE EVENT ORDER BY CLOCK
						Collections.sort(eventArray, new Comparator<FutureEventList>() {
							@Override
							public int compare(FutureEventList o1, FutureEventList o2) {
							
								int result = Float.compare(o1.getClock(), o2.getClock());
								if (result == 0)
									result = Float.compare(o1.getFlowNumber(), o2.getFlowNumber());
								return result;
							}

						});
		//	}firstEventBool = true;
						
						
			//setCurrent Event
			currentEvent = eventArray.get(0);
			eventArray.remove(0);    // this line removes current event from FEL
			
			clock = currentEvent.getClock();
			event = currentEvent.isTransmit();
			
			
			for (Packet myPacket : packetList) {
				if (myPacket.getPacketID() == currentEvent.getPacketNumber() && myPacket.getFlowID() == currentEvent.getFlowNumber()) {
					tempCurrentPacket = myPacket;
					break;
				}
			}
			
			
			
			// RECEIVE SCENARIO
			if (event == false) {
				
				int temp = currentEvent.getPacketNumber()+1;
				System.out.println("At t = " + clock + " msec	" + " Node "+ currentEvent.getNode_Name() + " 		receives 	packet	" + temp +"		(flow " + currentEvent.getFlowNumber()+ ")");
				//System.out.println("---------------0---------------");
				
				if (nodeList.get(currentEvent.getNode_Name()).isBusy == false) {
					
					nodeList.get(currentEvent.getNode_Name()).setBusy(true);
					currentEvent.setTransmit(true);
					currentEvent.setClock(currentEvent.getClock() + findTransmissionDelayByRoute2(tempCurrentPacket, nodeList));
					
					if (tempCurrentPacket.destinationNode != currentEvent.getNode_Name()) {
						eventArray.add(currentEvent);
					}
				}
				else
					nodeList.get(currentEvent.getNode_Name()).queue.add(tempCurrentPacket);
			}
			
			// TRANSMIT SCENARIO
			if (event == true) {
				int tempNodeIndex = currentEvent.getNode_Name();
				
				int temp = currentEvent.getPacketNumber()+1;
				System.out.println("At t = " + clock + " msec	" + " Node "+ currentEvent.getNode_Name() + " 		transmits 	packet " + temp +"		(flow " + currentEvent.getFlowNumber()+ ")");
				
				//System.out.println(nodeList.get(1).queue.size()); 
				
				currentEvent.setTransmit(false);
				currentEvent.setClock(currentEvent.getClock() + dProp);
				currentEvent.setNode_Name(findNextNode(nodeList, tempCurrentPacket));
				
				packetList.get(tempCurrentPacket.packetID).setCurrentNode(currentEvent.getNode_Name());
				
				eventArray.add(currentEvent);
				
				if (nodeList.get(tempNodeIndex).queue.size() > 0) {
					
					Packet nextPacketInTheQueue = nodeList.get(tempNodeIndex).queue.get(0);
			
					//remove transmitted packet from that Node
					nodeList.get(tempNodeIndex).queue.remove(0);
					
					FutureEventList eventTemplate = new FutureEventList();
					
					eventTemplate.setTransmit(true);
					eventTemplate.setClock(clock + findTransmissionDelayByRoute2(nextPacketInTheQueue, nodeList));
					eventTemplate.setNode_Name(nextPacketInTheQueue.currentNode);
					eventTemplate.setFlowNumber(nextPacketInTheQueue.flowID);
					eventTemplate.setPacketNumber(nextPacketInTheQueue.packetID);
					
					eventArray.add(eventTemplate);
					
				}
				else {
					nodeList.get(tempNodeIndex).isBusy = false;
				}
			
			}
										
			eventCounter++;
		}	
	}
	
	
	
		
	
	
	
	
	private void findShortestWay(ArrayList<MyNode> nodeList, int senderNodeID, int receiverNodeID){
		
		int nextCounter = 0;
		int prevCounter = 0;
		
		if (senderNodeID < receiverNodeID) {
			
			nextCounter = receiverNodeID - senderNodeID;
			prevCounter = ((nodeList.size()-1) - receiverNodeID) + senderNodeID;
			
			if (prevCounter > nextCounter) 
				nextOrPrev = "next";
			else
				nextOrPrev = "prev";
		}
		else {
			prevCounter = senderNodeID - receiverNodeID;
			nextCounter = ((nodeList.size()-1) - senderNodeID) + receiverNodeID;
			
			if(prevCounter > nextCounter)
				nextOrPrev = "next";
			else
				nextOrPrev = "prev";
		}
		
		
		
	}
	
	
	

	
		private float findTransmissionDelayByRoute2(Packet packet, ArrayList<MyNode> nodeList){
			
			if (packet.nextOrPrev == "next") {
				return findTransmissionDelay(packet.packetSize, nodeList.get(packet.currentNode).linkDataRateNext);
			}
			else
				return findTransmissionDelay(packet.packetSize, nodeList.get(packet.currentNode).linkDataRatePrev);
		
		}
	
	    //Millisecond dTrans
		private float findTransmissionDelay(float packetSize, float dataRate){
			
			/*dTrans = (packetSize)/dataRate;
			dTrans = dTrans*1000;*/
			return ((packetSize)/dataRate)*1000;
		}
		//Millisecond dProp
		private float findPropagationDelay(float propagationDelay){
			
			  dProp = propagationDelay / 1000;
			return dProp; 
		}
	
	
	public int findNextNode(ArrayList<MyNode> nodeList , Packet packet){
		
		if (packet.getNextOrPrev().equals("next")) {
			
			if (nodeList.get((packet.currentNode+1)% (nodeList.size())) == null) 
				return (nodeList.get((packet.currentNode+2)% (nodeList.size()))).getNode_ID();
			else 
				return (nodeList.get((packet.currentNode+1)% (nodeList.size()))).getNode_ID();
			
			
		}
		else if (packet.getNextOrPrev().equals("prev")) {
			
			if (nodeList.get((packet.currentNode-1)% (nodeList.size())) == null) 
				return (nodeList.get((packet.currentNode-2)% (nodeList.size()))).getNode_ID();
			else
				return (nodeList.get((packet.currentNode-1)% (nodeList.size()))).getNode_ID();
		}
		
		else{
			return -1;
		}
		
	}
		
		
		
	public MyNode findGivenID_NODE(int findID){
		
		MyNode keyNode = null;
				
		
		for(keyNode = first; keyNode != null && keyNode.Node_ID != findID ; keyNode = keyNode.next )
			;
		
		if (keyNode == null) {
			System.out.println("Couldn't find NODE "+ findID);
			return keyNode;
		}
		
		
		return keyNode;
		
	}
		
		
		
	public void addLast(int numberOfNodes, String nextOrPrev2){
		
		MyNode newNode = new MyNode(numberOfNodes, nextOrPrev2);
		if (first == null) {
			first = newNode;
			return;
		}
		MyNode lastNode;
		
		for(lastNode = first; lastNode.next != null; lastNode = lastNode.next)
			;
				
		lastNode.next = newNode;
		newNode.prev = lastNode;
				
		
	}
	
	
	
	
}
