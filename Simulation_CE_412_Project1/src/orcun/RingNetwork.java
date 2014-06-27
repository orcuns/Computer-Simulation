package orcun;
import java.util.Scanner;


public class RingNetwork {

	
	// the  DATA we NEED!!
	
		int numberOfNodes = 7;  
		float dataRate = 10f;         // Mbps
		float propagationDelay = 500f;   // microSec
		int senderNodeID=4;				 
		int receiverNodeID=7;
		float messageSize = 0.2f;			 // Mb
		int numberOfPackets = 2;   
	
		
	// Take the Data inputs with Scanner	
		Scanner sc_numberOfNodes = new Scanner(System.in);
		Scanner sc_dataRate= new Scanner(System.in); 
		Scanner sc_propDelay= new Scanner(System.in); 
		Scanner sc_senderNode= new Scanner(System.in); 
		Scanner sc_receiverNode= new Scanner(System.in);
		Scanner sc_messageSize= new Scanner(System.in);
		Scanner sc_numberOfPackets = new Scanner(System.in);
	
	
	private  void takeInputs() {
		
		System.out.println("1) number of Nodes ");
		numberOfNodes = sc_numberOfNodes.nextInt();
		System.out.println("2) Data Rate (Mbps) ");
		dataRate = sc_dataRate.nextFloat();
		System.out.println("3) propagation Delay (micro sec)");
		propagationDelay = sc_propDelay.nextFloat();
		System.out.println("4) Sender ID ");
		senderNodeID = sc_senderNode.nextInt();
		System.out.println("5) Receiver ID");
		receiverNodeID = sc_receiverNode.nextInt();
		System.out.println("6) Message Size (MB) ");
		messageSize = sc_messageSize.nextFloat();
		System.out.println("7) Number of Packets ");
		numberOfPackets = sc_numberOfPackets.nextInt();
		
		//CLOSE inputs
		sc_numberOfNodes.close();
		sc_dataRate.close();
		sc_propDelay.close();
		sc_senderNode.close();
		sc_receiverNode.close();
		sc_messageSize.close();
		sc_numberOfPackets.close();

	}	

	
	
	public static void main(String[] args) {
		
		RingNetwork rn = new RingNetwork();
		MyNodeList mnl;
	
		System.out.println("Insert the Data of Ring Network: ");
			
		try {
			   rn.takeInputs();
			   mnl = new MyNodeList(rn.numberOfNodes, rn.senderNodeID, rn.receiverNodeID, rn.numberOfPackets, rn.messageSize, rn.dataRate, rn.propagationDelay);
		   
		} catch (Exception e) {
			
		}
		
		
		
	}
	
	
	

		
		
	

}
