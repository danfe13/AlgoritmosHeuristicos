package br.ufs.algorithm;

import java.util.ArrayList;

import br.ufs.benchmark.Benchmark;

public class SimulatedAnnealing extends BaseAlgorithm {

	//Temperatura
	private int temperature;
	
	public SimulatedAnnealing(int lengthArray, double p, int min, int max, int temperature) {
		super(lengthArray, p, min, max);
		this.temperature = temperature;
	}
	
	/**
	 * Executa o Simulated Annealing
	 * @return Evolução da Qualidade da Solução
	 * */
	public ArrayList<Double> execute(Benchmark function) {

		double[] s = initSolution(lengthArray);
		double[] best = s;
		ArrayList<Double> bests = new ArrayList<Double>();
		double[] evolutionQuality = new double[temperature];
		bests.add(function.quality(s));
		int cont = 0;

		while (temperature > 0 || function.quality(best) == 0) {
			evolutionQuality[cont] = function.quality(s);
			double[] r = tweak(copy(s));
			if ((function.quality(r) < function.quality(s)) || (Math.random() < Math.pow(Math.E, ((function.quality(r) - function.quality(s)) / temperature))))
				s = r;
			temperature--;
			if (function.quality(s) < function.quality(best)) {
				best = s;
				bests.add(function.quality(s));
			}
			cont++;
		}
		
		return bests;

	}


}
