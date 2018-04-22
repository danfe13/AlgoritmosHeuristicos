package br.ufs.algorithm;

import br.ufs.benchmark.Benchmark;

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
	public double[] execute(Benchmark function) {

		double[] s = initSolution(lengthArray);
		double[] best = s;
		double[] evolutionQuality = new double[temperature];
		
		int cont = 0;

		while (temperature > 0 || function.quality(best) == 0) {
			evolutionQuality[cont] = function.quality(best);
			double[] r = tweak(copy(s));
			if ((function.quality(r) < function.quality(s)) || (Math.random() < Math.pow(Math.E, ((function.quality(r) - function.quality(s)) / temperature))))
				s = r;
			temperature--;
			if (function.quality(s) < function.quality(best))
				best = s;
			cont++;
		}
		
		return evolutionQuality;

	}


}
