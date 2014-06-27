package orcun;

import java.util.Random;


public class ExponentialDistributionNumberGenerator {

	 private static Random random; 
	 private static long seed;
	
	 // static initializer
	    static {
	        // this is how the seed was set in Java 1.4
	        seed = System.currentTimeMillis();
	        random = new Random(seed);
	    }
	
	public static double uniform() {
	        return random.nextDouble();
	    }
	public static double random() {
	        return uniform();
	    }
	  
	 public static double exponentialDist(double lambda) {
	        if (!(lambda > 0.0))
	            throw new IllegalArgumentException("Rate lambda must be positive");
	        return -Math.log(1 - uniform()) / lambda;
	    }
}
