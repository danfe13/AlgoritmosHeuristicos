package br.ufs.algorithm;

public class SimulatedAnnealing extends BaseAlgorithm {

	//Temperatura
	private double temperature;

	private int iterations;
	

	public SimulatedAnnealing(int lengthArray, double p, int min, int max, int range, int temperature, int iterations) {
		super(lengthArray, p, min, max, range);
		this.temperature = temperature;
		this.iterations = iterations;
	}

	/**
	 * Executa o Simulated Annealing
	 * @return Evolução da Qualidade da Solução
	 * */
	
	public double[] execute(int option) {
		
		double worseSolution = this.quality(this.worseSolution(),option);
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		double[] evolutionQuality = new double[iterations];
		int cont = 0;
		double decreaseT = temperature/iterations;

		while ((cont < iterations) || (quality(best,option) == 0.000)) {
			evolutionQuality[cont] = quality(best,option);


			double[] r = tweak(copy(s));
			if (quality(r,option) < quality(s,option) || 
					Math.random() < Math.pow(Math.E, ((normalize(quality(s,option),worseSolution) - normalize(quality(r,option),worseSolution)) / temperature))) {
				s = r;
			}
			temperature = temperature > 0 ? temperature-decreaseT : 0;
			
			if (quality(s,option) < quality(best,option)) {
				best = s;
			}
			cont++;
		}
		return evolutionQuality;

	}
	
	public double normalize(double quality, double pior){
		return quality/pior;
	}


}
