package orcun;

import java.util.Random;

public class RandomCarArrival {

	private static Random random;
	private static long seed;

	// static initializer
	static {
		// this is how the seed was set in Java 1.4
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}

	public static int randomArrival() {
		int result = random.nextInt(11) + 5;

		return result;
	}
}
