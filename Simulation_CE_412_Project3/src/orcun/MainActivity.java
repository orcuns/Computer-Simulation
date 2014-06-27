package orcun;

import java.util.Scanner;

public class MainActivity {
	
	public static double total_snap_counter = 0;
	public static boolean  isServerBusy=false;
	public static double  arrivalRate = 0;
	public static double  serviceRate = 0;
	public static double  arrival_FEL = 0;
	public static double  departure_FEL = -1;
	public static double clock=0;
	public static double currentClock=0;
	public static double currentQueue=0;
	public static double packets_in_queue=0;
	public static double total_packet_counter=0;
	public static double total_packet_in_queue=0;
	public static double loss_packets_counter=0;
	public static double  total_service_time =0;
	public static double  total_serviced =0;
	public static double  capacity_of_queue = 0;
	
	public static double stoppingEvent = 10;
	
	
	

	public static void main(String[] args) {
	
		Inputs();
		
		while(clock<stoppingEvent){
			
			futureEventList_Calculation();	
				
			if(isServerBusy == 	true)	
				total_packet_counter += packets_in_queue + 1;
			else
				total_packet_counter += packets_in_queue;
			
			total_packet_in_queue += packets_in_queue;
				
			total_snap_counter++; 
			
			printSnapShots();
		}
		
		printStats();

		System.out.println("total snapshots: "+total_snap_counter+" \n totalserved: "+total_serviced);
	}

	
	
	
	public static void futureEventList_Calculation(){
		//ARRIVAL EVENT:
		if (is_arrival()) {			
			clock = arrival_FEL;
			
			//SERVER IS EMPTY
			if (isServerBusy==false) {
				isServerBusy=true;
				gen_departure_FEL(); // For current packet
				gen_arrival_FEL(); // For next packet 	
			}
			//SERVER IS WORKING 
			else{
				if (capacity_of_queue <= 0) 
					packets_in_queue++;
				else{
					if(packets_in_queue < capacity_of_queue)
						packets_in_queue++;
					else
						loss_packets_counter++;					
					}
				gen_arrival_FEL();
				}
		}
		//DEPARTURE EVENT OCCURS 
		else{
			clock = departure_FEL;
			departureEvent();
		    }
	}
	
	
	
	public static void departureEvent(){
		
		if (packets_in_queue > 0) {
			
			packets_in_queue--;
			gen_departure_FEL();
			total_serviced++;
		}
		// LAST PACKET DEPARTURED
		else{
			isServerBusy = false;
			total_serviced++;
			departure_FEL = -1;
		}
	}
	
	
	public static void gen_arrival_FEL() {

		double result = ExponentialDistributionNumberGenerator.exponentialDist(arrivalRate);
		arrival_FEL = clock + result; 
	}

	public static void gen_departure_FEL() {

		double serviceTime = ExponentialDistributionNumberGenerator.exponentialDist(serviceRate);
		
		total_service_time += serviceTime;
		
		departure_FEL = clock + serviceTime;
	}
	
	public static boolean is_system_busy() {

		return clock < departure_FEL;
	}
	
	public static boolean is_arrival() {

		return departure_FEL == -1 || arrival_FEL < departure_FEL;

	}
	
	public static void printSnapShots(){
		
		System.out.printf("CLOCK: %.2f",clock);
		System.out.print("    LS(t): "+isServerBusy+"	");
		System.out.print("LQ(t): "+ packets_in_queue+"       ");
		System.out.printf("A: %.2f",arrival_FEL);
		System.out.printf("  D: %.2f",departure_FEL);
		System.out.print("		Queue: "+total_packet_counter+"	");
		System.out.print("Loss: "+loss_packets_counter+"	\n");
		
	}
	
	public static void printStats(){
		
		double avg_number_of_packets = total_packet_counter / total_snap_counter;
		double avg_waiting_time_system = (total_service_time / total_serviced) * avg_number_of_packets;
		double avg_packet_queue = total_packet_in_queue / total_snap_counter;
		double avg_waiting_time_queue = (total_service_time / total_serviced)	* avg_packet_queue;
		double loss_ratio = (loss_packets_counter / (total_serviced + loss_packets_counter))*100;
		
		System.out.println("\n----------------------------------STATS------------------------------------------\n");
		System.out.println("The average number of packets in the system: "+ avg_number_of_packets);
		System.out.println("The average number of packets in the queue: "+ avg_packet_queue);
		System.out.println("The average waiting time in the system: "+ avg_waiting_time_system);
		System.out.println("The average waiting time in the queue "+ avg_waiting_time_queue); 
		System.out.println("The loss ratio= %"+ loss_ratio);
		
	}
	
	public static void Inputs(){
		
		Scanner sc_averageArrivalRate = new Scanner(System.in);
		Scanner sc_averageServiceRate = new Scanner(System.in);
		Scanner sc_capacity = new Scanner(System.in);
		
		
		System.out.println("1) The Average Arrival Rate: (packets/sec) ");
		arrivalRate = sc_averageArrivalRate.nextInt();
		System.out.println("2) The Average Service Rate: (packets/sec) ");
		serviceRate = sc_averageServiceRate.nextInt();
		
		System.out.println("3) Capacity of the System: (0 means infinite) ");
		capacity_of_queue = sc_capacity.nextInt();
		
			
		
		
		//CLOSE INPUTS 
		sc_averageArrivalRate.close();
		sc_averageServiceRate.close();
		sc_capacity.close();
		
	}
	
	
	
}
