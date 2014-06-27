package orcun;

import java.util.ArrayList;

public class ConstantMultiplier {

	public static int[] arrayRange = new int[20];

	public static void main(String[] args) {

		// compute(# Random Numbers, Constant, initial Number2)
		compute(1000, 3987, 7223);
		PrintOutput.printResults(arrayRange);
	}

	public static void compute(int size, int constant, int n1) {

		int constante = constant;
		int x1 = n1;
		int number = 0;
		float result;

		for (int i = 0; i < size; i++) {

			number = constante * x1;

			number %= 1000000;
			number /= 100;

			result = (float) number / 10000;
			System.out.println(i + " number: " + result);
			x1 = number;

			// hangi aralıkta kaç tane random deger var..
			int temp = number / 500;
			arrayRange[temp]++;
		}
	}
}
