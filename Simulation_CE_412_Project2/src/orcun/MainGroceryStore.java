package orcun;

import java.util.Arrays;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

public class MainGroceryStore {

	int counter1 = 0;
	int counter2 = 0;
	int counter3 = 0;
	int counter4 = 0;
	int counter5 = 0;
	int counter6 = 0;
	int acounter1 = 0;
	int acounter2 = 0;
	int acounter3 = 0;
	int acounter4 = 0;
	int acounter5 = 0;
	int acounter6 = 0;
	int acounter7 = 0;
	int acounter8 = 0;
	int sumArrival = 0;

	Calculations calculations;

	public static void main(String[] args) {

		MainGroceryStore mgs = new MainGroceryStore();

		int numOfCustomers = 1000;

		mgs.calculateProbabilities(numOfCustomers, mgs);

		mgs.printDistributions();
	}

	public void calculateProbabilities(int numOfCustomers, MainGroceryStore mgs) {

		Customer customer[] = new Customer[numOfCustomers];

		int[] serviceTimeToGenerate = new int[] { 1, 2, 3, 4, 5, 6 };
		double[] discreteProbabilities = new double[] { 0.1, 0.2, 0.3, 0.25, 0.1, 0.05 };

		int[] arrivalTimeToGenerate = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		double[] discreteProbabilities2 = new double[] { 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125 };

		EnumeratedIntegerDistribution distributionOfService = new EnumeratedIntegerDistribution(serviceTimeToGenerate, discreteProbabilities);
		EnumeratedIntegerDistribution distributionOfArrival = new EnumeratedIntegerDistribution(arrivalTimeToGenerate, discreteProbabilities2);

		int[] samplesOfArrival = distributionOfArrival.sample(numOfCustomers);
		int[] samplesOfService = distributionOfService.sample(numOfCustomers); 

		//Arrays.sort(samplesOfArrival);

		for (int i = 0; i < numOfCustomers; i++) {

			if (i == 0)
				samplesOfArrival[0] = 0;

			sumArrival += samplesOfArrival[i];
			customer[i] = new Customer(sumArrival, samplesOfService[i]);

			
			  System.out.print(customer[i].service + " service   ");
			  System.out.println(customer[i].arrival + " arrival");
			 

			mgs.counterService(samplesOfService[i]);
			mgs.counterArrival(samplesOfArrival[i]);

		}

		calculations = new Calculations(customer, numOfCustomers);

	}

	public void printDistributions() {
		System.out.println("\n---------Service Time Distribution---------- ");
		System.out.println("1 min: " + counter1 + "			%10");
		System.out.println("2 min: " + counter2 + "			%20");
		System.out.println("3 min: " + counter3 + "		 	%30");
		System.out.println("4 min: " + counter4 + "			%25");
		System.out.println("5 min: " + counter5 + "			%10");
		System.out.println("6 min: " + counter6 + "			%5" + "\n");
		int a = counter6 + counter1 + counter2 + counter3 + counter4 + counter5;
		System.out.println("Total: " + a + " people" + "\n");

		System.out
				.println("---------Distribution of Time between Arrivals---------- ");
		System.out.println("0 min: 1  (first customer)");
		System.out.println("1 min: " + acounter1);
		System.out.println("2 min: " + acounter2);
		System.out.println("3 min: " + acounter3);
		System.out.println("4 min: " + acounter4);
		System.out.println("5 min: " + acounter5);
		System.out.println("6 min: " + acounter6);
		System.out.println("7 min: " + acounter7);
		System.out.println("8 min: " + acounter8 + "\n");
		int b = acounter6 + acounter1 + acounter2 + acounter3 + acounter4
				+ acounter5 + acounter7 + acounter8 + 1;
		System.out.println("Total: " + b + " people");

	}

	public void counterService(int a) {

		if (a == 1)
			counter1++;

		if (a == 2)
			counter2++;

		if (a == 3) {
			counter3++;
		}
		if (a == 4) {
			counter4++;

		}

		if (a == 5) {
			counter5++;

		}

		if (a == 6) {
			counter6++;

		}

	}

	public void counterArrival(int a) {

		if (a == 1)
			acounter1++;

		if (a == 2)
			acounter2++;

		if (a == 3) {
			acounter3++;
		}
		if (a == 4) {
			acounter4++;

		}

		if (a == 5) {
			acounter5++;

		}

		if (a == 6)
			acounter6++;
		if (a == 7)
			acounter7++;
		if (a == 8)
			acounter8++;

	}

}
