/**
 * Test of CS 142 Assignment 1 by Martin Hock (Version of 11:15 PM 1/10/2019)
 * 
 * This code is for personal educational use only and may not be redistributed
 * without permission of the author.
 */
public class BakingCalculatorTest {
	public static void main(String[] args) {
		int[] cookieCounts = { 24, 39, 80, 96, 97, 9000 };
		int[] loafCounts = { 1, 1, 8, 10, 10, 100 };
		int[][] ingredientCounts = { { 1, 1, 1, 1, 1, 1, 1, 3, 1 }, { 1, 1, 1, 1, 1, 1, 1, 3, 2 },
				{ 1, 1, 1, 2, 1, 5, 3, 24, 3 }, { 2, 1, 1, 2, 2, 6, 3, 30, 3 }, { 2, 1, 1, 3, 2, 6, 3, 30, 3 }, { 35, 2, 3, 48, 40, 179, 111, 300, 235 } };
		double[] finalPrices = { 20.88, 24.17, 55.11, 63.00, 66.99, 2070.97};
		int[] scores = { 0, 60, 70, 80, 90, 95 };
		int i = 0;
		int j = 0;
		for (; i < cookieCounts.length; i++) {
			j = 0;
			int count;
			System.out.println("Testing cookieCount = "+cookieCounts[i]+" and loafCount = "+loafCounts[i]+".");
			count = BakingCalculator.bagsOfFlour(cookieCounts[i], loafCounts[i]);
			if (ingredientCounts[i][0] != count) {
				System.out.println("Incorrect bagsOfFlour calculation! Should be "+ingredientCounts[i][0]+", got "+count);
				break;
			}
			j++;
			count = BakingCalculator.containersOfSalt(cookieCounts[i], loafCounts[i]);
			if (ingredientCounts[i][1] != count) {
				System.out.println("Incorrect containersOfSalt calculation! Should be "+ingredientCounts[i][1]+", got "+count);
				break;
			}
			j++;
			count = BakingCalculator.boxesOfBakingSoda(cookieCounts[i], loafCounts[i]);
			if (ingredientCounts[i][2] != count) {
				System.out.println("Incorrect boxesOfBakingSoda calculation! Should be "+ingredientCounts[i][2]+", got "+count);
				break;
			}
			j++;
			count = BakingCalculator.bottlesOfVanilla(cookieCounts[i], loafCounts[i]);
			if (ingredientCounts[i][3] != count) {
				System.out.println("Incorrect bottlesOfVanilla calculation! Should be "+ingredientCounts[i][3]+", got "+count);
				break;
			}
			j++;
			count = BakingCalculator.cartonsOfEggs(cookieCounts[i], loafCounts[i]);
			if (ingredientCounts[i][4] != count) {
				System.out.println("Incorrect cartonsOfEggs calculation! Should be "+ingredientCounts[i][4]+", got "+count);
				break;
			}
			j++;
			count = BakingCalculator.bagsOfSugar(cookieCounts[i], loafCounts[i]);
			if (ingredientCounts[i][5] != count) {
				System.out.println("Incorrect bagsOfSugar calculation! Should be "+ingredientCounts[i][5]+", got "+count);
				break;
			}
			j++;
			count = BakingCalculator.packagesOfButter(cookieCounts[i], loafCounts[i]);
			if (ingredientCounts[i][6] != count) {
				System.out.println("Incorrect packagesOfButter calculation! Should be "+ingredientCounts[i][6]+", got "+count);
				break;
			}
			j++;
			count = BakingCalculator.bananas(cookieCounts[i], loafCounts[i]);
			if (ingredientCounts[i][7] != count) {
				System.out.println("Incorrect bananas calculation! Should be "+ingredientCounts[i][7]+", got "+count);
				break;
			}
			j++;
			count = BakingCalculator.bagsOfChocolateChips(cookieCounts[i], loafCounts[i]);
			if (ingredientCounts[i][8] != count) {
				System.out.println("Incorrect bagsOfChocolateChips calculation! Should be "+ingredientCounts[i][8]+", got "+count);
				break;
			}
			j++;
			if (Math.abs(finalPrices[i] - BakingCalculator.totalCost(cookieCounts[i], loafCounts[i])) > 0.001) {
				System.out.println("Incorrect totalCost calculation! Should be "+finalPrices[i]+", got "+BakingCalculator.totalCost(cookieCounts[i], loafCounts[i]));
				break;
			}
		}
		if (i < cookieCounts.length) {
			int score = scores[i];
			if (score == 0) score += 2*j;
			else score += j/2;
			System.out.println("You only earned "+score+" points out of 100.");
			System.out.println("Please fix the above error in your code and run this tester again - there may be other problems!");
		} else {
			System.out.println("You earned 100 / 100 points!");
		}
		System.out.println(
				"NOTE: This program doesn't check for lateness or academic dishonesty which could affect your score. Please make sure you know the syllabus policy.");

	}
}
