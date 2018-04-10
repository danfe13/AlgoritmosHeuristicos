package br.ufs.algorithm;

public abstract class SimulatedAnnealing extends BaseAlgorithm {

	//Temperatura
	private int temperature;
	
	public SimulatedAnnealing(int lengthArray, double p, int range, int min, int max, int temperature) {
		super(lengthArray, p, range, min, max);
		this.temperature = temperature;
	}
	
	/**
	 * Executa o Simulated Annealing
	 * @return Evolução da Qualidade da Solução
	 * */
	public double[] execute() {

		double[] s = initSolution(lengthArray);
		double[] best = s;
		double[] evolutionQuality = new double[temperature];
		
		int cont = 0;

		while (temperature > 0 || quality(best) == 0) {
			evolutionQuality[cont] = quality(best);
			double[] r = tweak(copy(s));
			if ((quality(r) < quality(s)) || (Math.random() < Math.pow(Math.E, ((quality(r) - quality(s)) / temperature))))
				s = r;
			temperature--;
			if (quality(s) < quality(best))
				best = s;
			cont++;
		}
		
		return evolutionQuality;

	}


}
