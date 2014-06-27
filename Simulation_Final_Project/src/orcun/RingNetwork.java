package orcun;
import java.util.ArrayList;
import java.util.Scanner;


public class RingNetwork {

	
	// the  DATA we NEED!!
	
	public ArrayList<MyLink> myLinks = new ArrayList<MyLink>(); 
	public static ArrayList<Packet> myFlows = new ArrayList<Packet>(); 
	MyLink maLink;
	Packet maPacket;
	
		int numberOfNodes = 7;  
		int dataRate = 10;         // Mbps
		float propagationDelay = 500f;   // microSec
		int senderNodeID=2;				 
		int receiverNodeID=4;
		float messageSize = 20f;			 // Mb
		int numberOfPackets = 2;   
		
		int totalBottleNeckLinks;
		int bottleNeckLink_ID;
		int bottleNeck_DataRate;
		int totalFlow;
		
		int counter = 0;
	
		
	// Take the Data inputs with Scanner	
		Scanner sc_numberOfNodes = new Scanner(System.in);
		Scanner sc_dataRate= new Scanner(System.in); 
		Scanner sc_propDelay= new Scanner(System.in); 
		Scanner sc_senderNode= new Scanner(System.in); 
		Scanner sc_receiverNode= new Scanner(System.in);
		Scanner sc_messageSize= new Scanner(System.in);
		Scanner sc_numberOfPackets = new Scanner(System.in);
		Scanner sc_totalBottleNeckLinks = new Scanner(System.in);
		Scanner sc_link_ID = new Scanner(System.in);
		Scanner sc_bottleNeck_DataRate = new Scanner(System.in);
		Scanner sc_totalFlow = new Scanner(System.in);
	
	private  void takeInputs() {
		
		System.out.println("Enter the number of Nodes ");
		numberOfNodes = sc_numberOfNodes.nextInt();
		System.out.println("Enter the Data Rate (Mbps) ");
		dataRate = sc_dataRate.nextInt();
		System.out.println("Enter the Propagation Delay (micro sec)");
		propagationDelay = sc_propDelay.nextFloat();
		
		System.out.println("Enter the number of bottleneck links: ");
		totalBottleNeckLinks = sc_totalBottleNeckLinks.nextInt();
		
		for (int i = 1; i <= totalBottleNeckLinks; i++) {
			System.out.print("	Enter bottleneck links "+ i + ": ");
			bottleNeckLink_ID = sc_link_ID.nextInt();
			System.out.print("	Enter bottleneck Data Rate: ");
			bottleNeck_DataRate = sc_bottleNeck_DataRate.nextInt();
			System.out.println("");
			
			// BottleNeckleri arraye atmaca
			maLink = new MyLink(bottleNeckLink_ID, bottleNeck_DataRate);
			myLinks.add(i-1, maLink);
			
		}
		
		System.out.println("Enter number of Flows in Network: ");
		totalFlow = sc_totalFlow.nextInt();
		for (int i = 1; i <= totalFlow; i++) {
			
			System.out.print("	Sender ID: ");
			senderNodeID = sc_senderNode.nextInt();
			System.out.print("	Receiver ID: ");
			receiverNodeID = sc_receiverNode.nextInt();
			System.out.print("	Message Size (MB) ");
			messageSize = sc_messageSize.nextFloat();
			System.out.print("	Number of Packets: ");
			numberOfPackets = sc_numberOfPackets.nextInt();
			System.out.println("");
			
			
			for (int j = 0; j < numberOfPackets; j++) {
				maPacket = new Packet( j , messageSize, i, receiverNodeID, senderNodeID, "next");
				myFlows.add(counter, maPacket);
				counter++;
			}
			
		}
		
		
		
		//CLOSE inputs
		sc_numberOfNodes.close();
		sc_dataRate.close();
		sc_propDelay.close();
		sc_senderNode.close();
		sc_receiverNode.close();
		sc_messageSize.close();
		sc_numberOfPackets.close();
		sc_totalBottleNeckLinks.close();
		sc_link_ID.close();
		sc_bottleNeck_DataRate.close();
		sc_totalFlow.close();
	}	

	
	
	public static void main(String[] args) {
		
		RingNetwork rn = new RingNetwork();
		MyNodeList mnl;
	
		System.out.println("Insert the Data of Ring Network: ");
		System.out.println("--------------------------------------------------------------\n");  
			
//		try {
			   rn.takeInputs();
			   mnl = new MyNodeList(rn.numberOfNodes, rn.senderNodeID, rn.receiverNodeID, rn.numberOfPackets, rn.messageSize, rn.dataRate, rn.propagationDelay, rn.totalBottleNeckLinks, 
					   				rn.bottleNeckLink_ID, rn.bottleNeck_DataRate, rn.myLinks);
			   
			
		   
//		} catch (Exception e) {
			
			//System.out.println("SÝSTEM HAVAYA UCTU!"+ e.getMessage());
//		}
		
		
		
	}
	
	
	

		
		
	

}
