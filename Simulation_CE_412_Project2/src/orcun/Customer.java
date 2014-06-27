package orcun;

public class Customer {

	int arrival = 0;
	int service = 0;

	int serviceBegins = 0;
	int serviceEnds = 0;
	int waitInQueue = 0;
	int customerInSystem = 0;
	int idleTime = 0;

	public Customer(int p_arrival, int p_service) {

		arrival = p_arrival;
		service = p_service;

	}
}
