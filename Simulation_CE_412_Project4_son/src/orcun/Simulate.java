package orcun;

import java.util.Scanner;

public class Simulate {

	public static int washingTime, dryingTime;
	public static int maximumClock;
	public static int totalCar; 
	public static float nextWashingArrival_FEL;
	public static float nextWashingDeparture_FEL;
	public static float nextDryingDeparture_FEL;
	
	public static float washingQueue = 0;
	public static float dryingQueue = 0;
	
	public static float washingUtilization = 0;
	public static float dryingUtilization = 0;
	
	public static float washerWaited = 0;
	public static float dryerWaited= 0;
	
	public static boolean isDryerBusy = false;
	public static boolean isWasherBusy = false;
	
	public static float clock = 0;
	
	public static int event;
			
	
    public static void main(String[] args) {   
    	
    	inputs();
    	maximumClock = totalCar*10;
    	
       nextWashingArrival_FEL = maximumClock;
       nextWashingDeparture_FEL = maximumClock;
       nextDryingDeparture_FEL = maximumClock;
       
       
       
       while (clock<maximumClock){
    	   
    	   
    	   
    	   if (event == 0) {
    		   
    		  // totalCar++;
    		   
    		   washingTime = RandomServiceTime.generate_WashingUnit();
   			   dryingTime = RandomServiceTime.generate_DryingUnit();
    		   
    		   nextWashingArrival_FEL = generate_Car_Arrival() + clock;
    		   
    		   if (isWasherBusy == false) {
				isWasherBusy = true;
				nextWashingDeparture_FEL = washingTime + clock;
				
				washingUtilization += nextWashingDeparture_FEL -clock;
			}
    		   else{
    			   washingQueue++;
    			   
    			   washerWaited += nextWashingDeparture_FEL -clock;
    		   }
			
		}
    	   
    	   
    	   if (event == 1) {
    		   
    		   isWasherBusy = false;
    		   
    		   // busy queue
    		   if (washingQueue > 0) {
				isWasherBusy = true;
				washingQueue--;
				nextWashingDeparture_FEL = washingTime + clock;
				
				washingUtilization += (nextWashingDeparture_FEL - clock); 
			}
    		   // empty queue
    		   else {
    			   nextWashingDeparture_FEL = maximumClock;
			}
    		// dryer empty
               if(isDryerBusy==false){
                   
                   nextDryingDeparture_FEL = dryingTime + clock;
                   
                   dryingUtilization += nextDryingDeparture_FEL - clock;
                   
                   isDryerBusy=true;
                                  
               } 
               // dryer has a queue
               else {
                   dryingQueue++;
                  
                   
                   dryerWaited += (nextDryingDeparture_FEL - clock);
               }
		}
    	   
    	   
    	   if (event == 2) {
    		   
    		   isDryerBusy = false;
    		   // dryer has queue
               if (dryingQueue>0) {
                   
                   isDryerBusy=true;
                   dryingQueue--;
                   nextDryingDeparture_FEL = dryingTime + clock;
                   
                   dryingUtilization += nextDryingDeparture_FEL - clock;
                   
                   
               }
               
               // dry queue bossa bisi yapma
               else { 
            	   nextDryingDeparture_FEL = maximumClock; 
                   
               }
    		   
			
		}
    	   
    	   //finding first event
    	   findFirstEvent();
       }// end of while loop
       
       print();
    	
    }
	
    
    
    public static void print(){
    	
    	System.out.println(" --------------------- "); 
    	System.out.println("Clock : " + clock);
        System.out.println("Total Car : " + totalCar);
       
        System.out.println("average time a car spends at his company : " +( ( washerWaited + washingUtilization + dryerWaited + dryingUtilization ) / totalCar )  ) ;
        
        System.out.println("the utilization of the washer : %"  + ( ( washingUtilization / clock) * 100 ) );
        System.out.println("the utilization of the dryer : %"  + ( ( dryingUtilization / clock) * 100 ) );
        
        System.out.println("the average waiting time for the washer : "  + ( washerWaited / totalCar) );
        System.out.println("the average waiting time for the dryer : "  + (  dryerWaited / totalCar) );
        
        System.out.println(" --------------------- "); 
        
      
        
        
        
      
    }
    
    public static void findFirstEvent(){
    	
    	  if( nextWashingArrival_FEL <= nextWashingDeparture_FEL && nextWashingArrival_FEL <= nextDryingDeparture_FEL ) {
              
              event = 0;
              clock = nextWashingArrival_FEL;
          }
    	  
          else if ( nextWashingDeparture_FEL <= nextWashingArrival_FEL && nextWashingDeparture_FEL <= nextDryingDeparture_FEL ) {
              
              event = 1;
              clock = nextWashingDeparture_FEL;
          } 
          
          else if ( nextDryingDeparture_FEL<=nextWashingArrival_FEL && nextDryingDeparture_FEL<=nextWashingDeparture_FEL ) {
          
              event = 2;
              clock = nextDryingDeparture_FEL;
          }
    }
    
    
    public static float generate_Car_Arrival(){
		
		float result = RandomCarArrival.randomArrival();
		
		return result;
	}

    
	public static void inputs(){
		
		Scanner sc_Car = new Scanner(System.in);
		
		System.out.println("Enter the car Number: ");
		
		totalCar = sc_Car.nextInt();
		
		
		sc_Car.close();
	}
	
}
