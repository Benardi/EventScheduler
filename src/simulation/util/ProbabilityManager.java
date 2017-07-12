package simulation.util;

import java.util.Random;

public class ProbabilityManager {
	
	private Random rand;
	public ProbabilityManager() {
		this.rand = new Random();
		}
	
	public int generateValue(int lowerBound, int upperBound){
		return  rand.ints(lowerBound, upperBound + 1).findFirst().getAsInt();
	
	}

	public static void main(String[] args) {
		ProbabilityManager pm = new ProbabilityManager();
		for(int i = 0; i < 1000; i++){
			System.out.println(pm.generateValue(3,7));
		}
	}
}
