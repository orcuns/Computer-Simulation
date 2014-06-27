package orcun;

public class Calculations {

	float sum1 = 0;
	float sum2 = 0;
	int sum3 = 0;
	int sum4 = 0;
	float counterWaitingCustomers = 0;

	// CONSTRUCTORE
	public Calculations(Customer[] customer, int numberOfCustomers) {

		// pre calculations:
		serviceBeginsCalculate(customer, numberOfCustomers);
		waitInQueueCalculation(customer, numberOfCustomers);
		serviceEndsCalculation(customer, numberOfCustomers);
		customerSpendsInSystem(customer, numberOfCustomers);
		idleTimeOfService(customer, numberOfCustomers);

		System.out
				.println("--------------------------------------------\nRESULTS:");

		System.out.println("Average Time a Customer Spends in the System: "
				+ averageTimeOfCustomerSpendsInSystem(customer,
						numberOfCustomers) + " minute\n");
		System.out.println("Average Waiting Time of a Customer: "
				+ averageWaitingTimeOfCustomer(customer, numberOfCustomers)
				+ " minute\n");
		System.out.println("Average Service Time of a Customer: "
				+ averageServiceTimeOfCustomer(customer, numberOfCustomers)
				+ " minute\n");
		System.out
				.println("Percentage of the Customers wait at the Checkout Counter: %"
						+ percentageOfCustomersWaitAtCheckout(customer,
								numberOfCustomers) + "\n");
		System.out.println("Average Waiting Time of Customers who Wait: "
				+ averageWaitingTimeOfCustomerWhoWait(customer,
						numberOfCustomers) + " minute\n");
		System.out.println("Percentage of the Checkout Counter is Idle: %"
				+ percentageOfTheCheckoutCounterIsIdle(customer,
						numberOfCustomers) + "\n");
		System.out
				.println("Average Time Between Arrivals to Checkout Counter: "
						+ averageTimeBetweenArrivalToCheckout(customer,
								numberOfCustomers) + " minute\n");

	}

	// // 7 METHODS FOR QUESTIONS
	public float averageTimeOfCustomerSpendsInSystem(Customer[] customer,
			int numberOfCustomers) {

		float result = 0;

		for (int i = 0; i < customer.length; i++)
			result += customer[i].customerInSystem;

		return result / numberOfCustomers;
	}

	public float averageWaitingTimeOfCustomer(Customer[] customer,
			int numberOfCustomers) {
		/***
		 * FORMULATION: when checkout counter start to care that customer[i] -
		 * arrival time of that customer[i]
		 * 
		 * 
		 * int innerSum = 0; //finding dynamic arrival times of all customers
		 * for (int i = 0; i < numberOfCustomers; i++) {
		 * 
		 * //ilk deger herzaman 0 olacak if(i==0) innerSum = 0; else innerSum +=
		 * customer[i-1].service;
		 * 
		 * 
		 * if((innerSum - customer[i].arrival) >= 0) sum1 += innerSum -
		 * customer[i].arrival;
		 * 
		 * //serviceten arrival çýkarýnca eksili deger olursa, arada boþluk var
		 * demektir. ozaman son arrival deðerinden devam edilir. else innerSum =
		 * customer[i].arrival;
		 * 
		 * }
		 * 
		 * 
		 * float result = (float)(sum1/numberOfCustomers);
		 * 
		 * return result;
		 */
		float result = 0;
		float sum = 0;
		for (int i = 0; i < customer.length; i++)
			sum += customer[i].waitInQueue;

		result = sum / numberOfCustomers;

		return result;
	}

	private float averageServiceTimeOfCustomer(Customer[] customer,
			int numberOfCustomers) {

		for (int i = 0; i < numberOfCustomers; i++)
			sum2 += customer[i].service;

		float result = sum2 / numberOfCustomers;

		return result;
	}

	public float percentageOfCustomersWaitAtCheckout(Customer[] customer,
			int numberOfCustomers) {

		float result;
		for (int i = 0; i < customer.length; i++)
			if (customer[i].waitInQueue != 0)
				counterWaitingCustomers++;

		result = (counterWaitingCustomers / numberOfCustomers) * 100;

		return result;
	}

	public float averageWaitingTimeOfCustomerWhoWait(Customer[] customer,
			int numberOfCustomers) {

		float result = 0;
		float sum = 0;
		for (int i = 0; i < customer.length; i++)
			if (customer[i].waitInQueue != 0)
				sum += customer[i].waitInQueue;

		result = sum / counterWaitingCustomers;

		return result;
	}

	public float percentageOfTheCheckoutCounterIsIdle(Customer[] customer,
			int numberOfCustomers) {

		float sum = 0;
		float result = 0;

		for (int i = 0; i < customer.length; i++)
			sum += customer[i].idleTime;

		result = (sum / customer[numberOfCustomers - 1].serviceEnds) * 100f;

		return result;
	}

	public float averageTimeBetweenArrivalToCheckout(Customer[] customer,
			int numberOfCustomers) {

		float result = 0;
		float sum = 0; 
		float lastArrival = 0;

		for (int i = 0; i < customer.length; i++)
			lastArrival = customer[i].arrival;

		result = lastArrival / (numberOfCustomers - 1);

		return result;
	}

	// ////////////////////////////////////////// PRE Calculations:
	boolean lock = true;

	public void serviceBeginsCalculate(Customer[] customer,
			int numberOfCustomers) {

		for (int i = 0; i < numberOfCustomers-1 ; i++) {
			
		/*	if (customer[0] == customer[i])
				customer[0].serviceBegins = 0;
			else
				customer[i].serviceBegins = Math.max(customer[i].arrival, customer[i-1].serviceEnds);
			*/
			
			
			

			if (lock == false) {

				if ((sum3 + customer[i].service) > customer[i + 1].arrival) {

					sum3 += customer[i].service;
					customer[i + 1].serviceBegins = sum3;
				} else
					customer[i + 1].serviceBegins = customer[i + 1].arrival;

			}

			if (lock == true) {

				if (customer[0] == customer[i])
					customer[0].serviceBegins = 0;

				if ((customer[i].service + customer[i].arrival) <= customer[i + 1].arrival) {
					customer[i + 1].serviceBegins = customer[i + 1].arrival;
				} else {
					sum3 += customer[i].service + customer[i].arrival;
					customer[i + 1].serviceBegins = sum3;
					lock = false;
				}

			}
		}
	}

	public void waitInQueueCalculation(Customer[] customer,
			int numberOfCustomers) {

		for (int i = 0; i < numberOfCustomers; i++) {

			
				customer[i].waitInQueue = customer[i].serviceBegins - customer[i].arrival;
			
		}
	}

	public void serviceEndsCalculation(Customer[] customer,
			int numberOfCustomers) {

		for (int i = 0; i < numberOfCustomers; i++) {

			customer[i].serviceEnds = customer[i].service + customer[i].serviceBegins;
		}
	}

	public void customerSpendsInSystem(Customer[] customer,
			int numberOfCustomers) {

		for (int i = 0; i < customer.length; i++) {

			customer[i].customerInSystem = customer[i].serviceEnds
					- customer[i].arrival;
		}
	}

	public void idleTimeOfService(Customer[] customer, int numberOfCustomers) {

		for (int i = 0; i < customer.length; i++) {

			if (customer[0] == customer[i])
				customer[0].idleTime = 0;

			else if (customer[i].arrival > customer[i - 1].service
					+ customer[i - 1].serviceBegins) {

				customer[i].idleTime = customer[i].arrival
						- (customer[i - 1].service + customer[i - 1].serviceBegins);
			} else
				customer[i].idleTime = 0;

		}
	}
}
