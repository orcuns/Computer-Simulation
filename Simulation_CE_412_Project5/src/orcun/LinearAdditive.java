package orcun;

import java.math.BigInteger;

public class LinearAdditive {

	public static int[] arrayRange = new int[20];
	

	public static void main(String[] args) {
		// SEEDS
		int a = 16807;
		int c = 0;
		BigInteger m = BigInteger.valueOf(2147483647);

		compute(100, a, c, m);
		PrintOutput.printResults(arrayRange);
	}

	private static void compute(int size, int a, int c, BigInteger m) {
		float result;
	  int[]	array = new int[size + 1];
		array[0] = 123457;

		for (int i = 0; i < size; i++) {

			int taa = array[i];
			BigInteger kaa = BigInteger.valueOf((a * taa)+c);
			BigInteger baa = kaa.mod(m);
			
			array[i + 1] =  baa.intValue(); 
		

			result = (float) array[i + 1] / m.intValue();
			System.out.println(i + " number: " + result);

			// hangi aralýkta kaç tane random deger var..
			int temp = (array[i + 1] / 500000000);
			arrayRange[temp]++;
		}
	}
}
 