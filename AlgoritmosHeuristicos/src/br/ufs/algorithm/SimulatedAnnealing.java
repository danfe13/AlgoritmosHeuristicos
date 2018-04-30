package br.ufs.algorithm;

import br.ufs.benchmark.IBenchmark;

public class SimulatedAnnealing extends BaseAlgorithm {

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
	public double[] execute(IBenchmark b) {

		double[] s = initSolution(lengthArray);
		double[] best = s;
		double[] evolutionQuality = new double[temperature];
		
		int cont = 0;

		while (temperature > 0 || b.quality(best) == 0) {
			evolutionQuality[cont] = b.quality(best);
			double[] r = tweak(copy(s));
			if ((b.quality(r) < b.quality(s)) || (Math.random() < Math.pow(Math.E, ((b.quality(r) - b.quality(s)) / temperature))))
				s = r;
			temperature--;
			if (b.quality(s) < b.quality(best))
				best = s;
			cont++;
		}
		
		return evolutionQuality;

	}


}
