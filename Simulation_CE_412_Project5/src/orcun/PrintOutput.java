package orcun;

import java.math.BigDecimal;

public class PrintOutput {

	public static void printResults(int[] arrayRange) {

		float range1 = 0f;
		float range2 = 0.04f;

		System.out.println("\nHistogram: ");
		for (int i = 0; i < arrayRange.length; i++) {

			System.out.print(precision(2, range1) + " - " + precision(2, range2) + ": 	" + arrayRange[i] + "		");

			printHistogram(arrayRange, i);
			System.out.println();

			range1 += 0.05f;
			range2 += 0.05f;
		}

	}

	private static void printHistogram(int[] histogram, int i) {

		for (int j = 0; j < histogram[i]; j++) {
			System.out.print("+");

		}

	}

	public static Float precision(int decimalPlace, Float d) {

		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_UP);
		return bd.floatValue();
	}

}
