package orcun;

import java.util.ArrayList;

public class Midsquare {

	public static int[] arrayRange = new int[20];

	public static void main(String[] args) {

		// compute(# Random Numbers, initial Number)
		compute(1000, 5497);

		PrintOutput.printResults(arrayRange);
	}

	private static void compute(int size, int numberz) {

		int number = numberz;
		float result;

		for (int i = 0; i < size; i++) {

			number *= number;
			number %= 1000000;
			number /= 100;

			// (0,1) arasý deðerler yapmaca
			result = (float) number / 10000;
			System.out.println(i + " number: " + result);

			// hangi aralýkta kaç tane random deger var..
			int temp = number / 500;
			arrayRange[temp]++;

		}

	}
}
