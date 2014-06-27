package orcun;

import java.util.ArrayList;

public class Midproduct {

	public static int[] arrayRange = new int[20];

	public static void main(String[] args) {
		// compute(# Random Numbers, initial Number1, initial Number2)
		compute(1000, 2938, 7229);
		PrintOutput.printResults(arrayRange);
	}

	public static void compute(int size, int n1, int n2) {

		int x0 = n1;
		int x1 = n2;
		int number = 0;
		float result;

		for (int i = 0; i < size; i++) {

			number = x0 * x1;
			number %= 1000000;
			number /= 100;

			result = (float) number / 10000;

			x0 = x1;
			x1 = number;

			// hangi aralýkta kaç tane random deger var..
			int temp = number / 500;
			arrayRange[temp]++;

			System.out.println(i + " number: " + result);
		}
	}
}
