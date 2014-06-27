package orcun;

import java.util.ArrayList;

public class AdditiveCongruential {

	public static int[] arrayRange = new int[20];
	public static int[] array;

	public static void main(String[] args) {
		// SEEDS
		int n = 5;
		int m = 100;

		compute(1000, n, m);
		PrintOutput.printResults(arrayRange);
	}

	private static void compute(int size, int n, int m) {

		float result;
		array = new int[size + n];
		array[0] = 57;
		array[1] = 34;
		array[2] = 89;
		array[3] = 92;
		array[4] = 16;

		for (int i = 0; i < size; i++) {

			array[i + n] = (array[i + n - 1] + array[i]) % m;

			result = (float) array[i + n] / m;
			System.out.println(i + " number: " + result);

			// hangi aralýkta kaç tane random deger var..
			int temp = (array[i + n] / 5);
			arrayRange[temp]++;
		}
	}
}
