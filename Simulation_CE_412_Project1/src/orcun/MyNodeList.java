package orcun;


public class MyNodeList {

	MyNode first;
	public int minLink;
	float dTrans;
	float dProp;
	
	String nextOrPrev;
	
		
	//Constructor
	public MyNodeList(int numberofNodes, int senderID, int receiveID, int numberOfPackets, float messageSize, float dataRate, float propagationDelay){
		
		
		//tüm nodelarý yaratýyor!
		for(int i = 1; i <=numberofNodes; i++ )
		{
			addLast(i);
		}
		
		//FIND mininum rout
		findShortestWay( numberofNodes,  senderID,  receiveID);  
		
		
		//FIND dtrans and dProp
		findTransmissionDelay(messageSize, dataRate, numberOfPackets);
		findPropagationDelay(propagationDelay);
		

		System.out.println("OUTPUT: ");
		
		calculateAndPrint(numberOfPackets, findGivenID_NODE(senderID), numberofNodes);
		
		
		
		
	}
	
	
		
	public void calculateAndPrint(int packets, MyNode mn, int numberOfNode){  
		
		for (int i = 1; i <= packets; i++) {
			
			float first_dTrans = i*dTrans;
			float constantDelay = dTrans + dProp;
			float sumTrans = first_dTrans;
			float sumReceive = first_dTrans + dProp;
			MyNode myNewNode = mn; 
			
			for (int j = 1; j <= (minLink*2); j++) {
								
				// TRANSMIT
				if (j % 2 == 1) {
					
						//sondaki Node, en baþtakine geçmesi için ve ilk Node'un sondakine geçmesi için gereken kod 2 tane if
						if(myNewNode == null  && nextOrPrev.equals("next"))
							myNewNode = first;
						if(myNewNode == null  && nextOrPrev.equals("prev"))
							myNewNode = findGivenID_NODE(numberOfNode);
						
						
						System.out.println("At t = " + sumTrans + " msec" + " Node "+ myNewNode.ID + " transmits packet: "+ i );
						sumTrans += constantDelay;
					
						
						//bir sonraki Node'u seçiyoruz, hangi yöne gidiyor ise.
						if(nextOrPrev.equals("next"))
							myNewNode = myNewNode.next;
						else
							myNewNode = myNewNode.prev;

				}
				
				// RECEIVE
				if (j % 2 == 0) {
																
							if(myNewNode == null  && nextOrPrev.equals("next"))
								myNewNode = first;
							if(myNewNode == null  && nextOrPrev.equals("prev"))
								myNewNode = findGivenID_NODE(numberOfNode);
								
							System.out.println("At t = " + sumReceive + " msec" + " Node "+ myNewNode.ID + " receives  packet: "+ i );
							
							sumReceive += constantDelay;
								  
				}
				
					
			}
			
			System.out.println("----------------------------------------------------");
		}
		
	}
	
	private void findShortestWay(int numberofNodes, int senderNodeID, int receiverNodeID){
		
		if(senderNodeID <= receiverNodeID){
			
			int a = receiverNodeID - senderNodeID;
			int b = numberofNodes - a;

			minLink = Math.min(a, b);
			
			// (next)
			if (a <= b) {
				
				 nextOrPrev = "next";
			}
			// (prev)
			else{
				nextOrPrev = "prev";
			}			
			
		}
		else if(senderNodeID > receiverNodeID){
			
			int a = senderNodeID - receiverNodeID;
			int b = numberofNodes - a;

			minLink = Math.min(a, b);
			
					// (prev)
						if (a <= b) {
							
							 nextOrPrev = "prev";
						}
						// (next)
						else{
							nextOrPrev = "next";
						}	
		}
		
	}

	
	
	    //Millisecond dTrans
		private float findTransmissionDelay(float messageSize, float dataRate, int numberOfPackets){
			
			dTrans = (messageSize/numberOfPackets)/dataRate;
			dTrans = dTrans*1000;
			return dTrans;
		}
		//Millisecond dProp
		private float findPropagationDelay(float propagationDelay){
			
			  dProp = propagationDelay / 1000;
			return dProp; 
		}
	
	
		
		
	public MyNode findGivenID_NODE(int findID){
		
		MyNode keyNode = null;
				
		
		for(keyNode = first; keyNode != null && keyNode.ID != findID ; keyNode = keyNode.next )
			;
		
		if (keyNode == null) {
			System.out.println("Couldn't find NODE "+ findID);
			return keyNode;
		}
		
		
		return keyNode;
		
	}
		
		
		
	public void addLast(int numberOfNodes){
		
		MyNode newNode = new MyNode(numberOfNodes);
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
