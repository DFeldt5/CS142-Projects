/*
 * Dustin Feldt
 * CS 142 Assignment 1
 * 10/13/2022
 */


//Given two recipes and prices for the ingredients used, determines the total cost based on the quantities being made
public class BakingCalculator {
	
	public static void main(String[] args) {
		//Input desired number of cookies and banana bread loaves
		int cookieCount = 48;
		int loafCount = 1;
		
		System.out.println("Total Cost for " + cookieCount + " cookies and " + loafCount + " loaves of banana bread is $" + totalCost(cookieCount, loafCount));
	}
	
	//Returns the number of bags of flour required
	public static int bagsOfFlour(int cookieCount, int loafCount) {
		//calculate cups of flour needed for cookies
		double cookiesPerBatch = 48.0;
		double cupsFlourPerBatch = 2.25;
		double cupsFlourForCookies = cookieCount / cookiesPerBatch * cupsFlourPerBatch;
		
		//calculate cups of flour needed for bread
		double cupsFlourPerLoaf = 1.5;
		double cupsFlourForBread = loafCount * cupsFlourPerLoaf;
		
		//calculate total amount of flour needed and convert to number of bags
		double totalCupsFlour = cupsFlourForCookies + cupsFlourForBread;
		double cupsFlourInPound = 3.33;
		double poundsFlourPerBag = 5.0;
		
		return (int) Math.ceil(totalCupsFlour / cupsFlourInPound / poundsFlourPerBag);
	}

	//Returns the number of salt containers required
	public static int containersOfSalt(int cookieCount, int loafCount) {
		//calculate tsp salt needed for cookies
		double cookiesPerBatch = 48.0;
		double tspSaltPerBatch = 1.0;
		double tspSaltForCookies = cookieCount / cookiesPerBatch * tspSaltPerBatch;
		
		//calculate tsp salt needed for bread
		double tspSaltPerLoaf = 0.125;
		double tspSaltForBread = loafCount * tspSaltPerLoaf;
		
		//calculate total amount of salt needed and convert to containers
		double totalTspSalt = tspSaltForCookies + tspSaltForBread;
		double tspSaltInOz = 6.0;
		double ozSaltInContainer = 26.0;
		
		return (int) Math.ceil(totalTspSalt / tspSaltInOz / ozSaltInContainer);
	}

	//Returns the number of baking soda boxes requires
	public static int boxesOfBakingSoda(int cookieCount, int loafCount) {
		//calculate tsp baking soda needed for cookies
		double cookiesPerBatch = 48.0;
		double tspBakingSodaPerBatch = 1.0;
		double tspBakingSodaForCookies = cookieCount / cookiesPerBatch * tspBakingSodaPerBatch;
		
		//calculate tsp baking soda needed for bread
		double tspBakingSodaPerLoaf = 1.0;
		double tspBakingSodaForBread = loafCount * tspBakingSodaPerLoaf;
		
		//calculate total amount of baking soda needed and convert to boxes
		double totalTspBakingSoda = tspBakingSodaForCookies + tspBakingSodaForBread;
		double tspBakingSodaInOz = 6.0;
		double ozBakingSodaInBox = 16.0;
		
		return (int) Math.ceil(totalTspBakingSoda / tspBakingSodaInOz / ozBakingSodaInBox);
	}

	//Returns the number of bottles of vanilla extract requires
	public static int bottlesOfVanilla(int cookieCount, int loafCount) {
		//calculate tsp vanilla needed for cookies
		double cookiesPerBatch = 48.0;
		double tspVanillaPerBatch = 1.0;
		double tspVanillaForCookies = cookieCount / cookiesPerBatch * tspVanillaPerBatch;
				
		//calculate tsp vanilla needed for bread
		double tspVanillaPerLoaf = 1.0;
		double tspVanillaForBread = loafCount * tspVanillaPerLoaf;
				
		//calculate total amount of vanilla needed and convert to oz
		double totalTspVanilla = tspVanillaForCookies + tspVanillaForBread;
		double tspVanillaInOz = 6.0;
				
		return (int) Math.ceil(totalTspVanilla / tspVanillaInOz);		
	}

	//Returns the number of egg cartons required
	public static int cartonsOfEggs(int cookieCount, int loafCount) {
		//calculate number of eggs needed for cookies
		double cookiesPerBatch = 48.0;
		double eggsPerBatch = 2.0;
		double eggsForCookies = cookieCount / cookiesPerBatch * eggsPerBatch;
		
		//calculate number of eggs needed for bread
		double eggsPerLoaf = 1.0;
		double eggsForBread = loafCount * eggsPerLoaf;
		
		//calculate total number of eggs needed and convert to cartons
		double totalEggs = eggsForCookies + eggsForBread;
		double eggsInCarton = 12.0;
		
		return (int) Math.ceil(totalEggs / eggsInCarton);
	}

	//Returns the number of bags of sugar required
	public static int bagsOfSugar(int cookieCount, int loafCount) {
		//calculate cups of sugar needed for cookies
		double cookiesPerBatch = 48.0;
		double cupsSugarPerBatch = 1.5;
		double cupsSugarForCookies = cookieCount / cookiesPerBatch * cupsSugarPerBatch;
		
		//calculate cups of sugar needed for bread
		double cupsSugarPerLoaf = 0.75;
		double cupsSugarForBread = loafCount * cupsSugarPerLoaf;
		
		//calculate total cups sugar needed and convert to bags
		double totalCupsSugar = cupsSugarForCookies + cupsSugarForBread;
		double cupsSugarInLb = 2.0;
		
		return (int) Math.ceil(totalCupsSugar / cupsSugarInLb);
	}

	//Returns the number of packages of butter required
	public static int packagesOfButter(int cookieCount, int loafCount) {
		//calculate cups of butter needed for cookies
		double cookiesPerBatch = 48.0;
		double cupsButterPerBatch = 1.0;
		double cupsButterForCookies = cookieCount / cookiesPerBatch * cupsButterPerBatch;
		
		//calculate cups butter needed for bread
		double cupsButterPerLoaf = 0.33;
		double cupsButterForBread = loafCount * cupsButterPerLoaf;
				
		//calculate total cups butter needed and convert to packages
		double totalCupsButter = cupsButterForCookies + cupsButterForBread;
		double cupsButterPerPackage = 2.0;
				
		return (int) Math.ceil(totalCupsButter / cupsButterPerPackage);
	}

	//Returns the number of bananas needed
	public static int bananas(int cookieCount, int loafCount) {
		//no bananas needed for cookies
		//calculate number of bananas needed for bread
		int bananasPerLoaf = 3;
		
		return loafCount * bananasPerLoaf;
	}

	//Returns the number of chocolate chip bags required
	public static int bagsOfChocolateChips(int cookieCount, int loafCount) {
		//no chocolate chips needed for bread
		//calculate cups chocolate chips needed for cookies and convert to bags
		double cookiesPerBatch = 48.0;
		double cupsChocolateChipsPerBatch = 2.5;
		double cupsChocolateChipsForCookies = cookieCount / cookiesPerBatch * cupsChocolateChipsPerBatch;
		
		double cupsChocolateChipsInBag = 2.0;		
		
		return (int) Math.ceil(cupsChocolateChipsForCookies / cupsChocolateChipsInBag);
	}

	//Returns the total cost of the combined ingredients required
	public static double totalCost(int cookieCount, int loafCount) {
		//define cost of ingredients and determine total cost
		double priceFlour = 1.79;
		double priceSalt = 1.09;
		double priceBakingSoda = 1.09;
		double priceVanilla = 3.99;
		double priceEggs = 2.19;
		double priceSugar = 1.99;
		double priceButter = 4.49;
		double priceBanana = 0.32;
		double priceChocolateChips = 3.29;
		
		return bagsOfFlour(cookieCount, loafCount) * priceFlour
			 + containersOfSalt(cookieCount, loafCount) * priceSalt
			 + boxesOfBakingSoda(cookieCount, loafCount) * priceBakingSoda
			 + bottlesOfVanilla(cookieCount, loafCount) * priceVanilla
			 + cartonsOfEggs(cookieCount, loafCount) * priceEggs
			 + bagsOfSugar(cookieCount, loafCount) * priceSugar
			 + packagesOfButter(cookieCount, loafCount) * priceButter
			 + bananas(cookieCount, loafCount) * priceBanana
			 + bagsOfChocolateChips(cookieCount, loafCount) * priceChocolateChips;
	}
	
}
