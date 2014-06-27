package orcun;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

public class RandomServiceTime {

	public static int generate_WashingUnit() {

		int[] serviceTimeToGenerate = new int[] { 8, 10, 12 };
		double[] discreteProbabilities = new double[] { 0.3, 0.4, 0.3 };

		EnumeratedIntegerDistribution distributionOfService = new EnumeratedIntegerDistribution(
				serviceTimeToGenerate, discreteProbabilities);

		int randomWashingTime = distributionOfService.sample();

		return randomWashingTime;
	}
	public static int generate_DryingUnit() {

		int[] serviceTimeToGenerate = new int[] { 5, 8, 10 };
		double[] discreteProbabilities = new double[] { 0.2, 0.3, 0.5 };

		EnumeratedIntegerDistribution distributionOfService = new EnumeratedIntegerDistribution(
				serviceTimeToGenerate, discreteProbabilities);

		int randomDryingTime = distributionOfService.sample();

		return randomDryingTime;
	}
}
