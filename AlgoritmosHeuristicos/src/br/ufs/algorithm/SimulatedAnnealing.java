package br.ufs.algorithm;

import java.util.ArrayList;

import br.ufs.benchmark.Benchmark;

public class SimulatedAnnealing extends BaseAlgorithm {

	//Temperatura
	private double temperature;
	private int interaction;
	
	public SimulatedAnnealing(int lengthArray, double p, int min, int max, int temperature, int interaction ,int r) {
		super(lengthArray, p, min, max, r);
		this.temperature = temperature;
		this.interaction = interaction;
	}
	
	/**
	 * Executa o Simulated Annealing
	 * @return Evolução da Qualidade da Solução
	 * */
	public ArrayList<Double> execute(Benchmark function) {

		double[] s = initSolution(lengthArray);
		double[] best = s;
		ArrayList<Double> bests = new ArrayList<Double>();
		bests.add(function.quality(s));
		int cont = 0;

		while (cont < interaction || function.quality(best) == 0) {
			double[] r = tweak(copy(s));
			if ((function.quality(r) < function.quality(s)) || (Math.random() < Math.pow(Math.E, ((function.quality(s) - function.quality(r)) / temperature))))
				s = r;
			temperature = temperature > 0 ? temperature-0.01 : 0;
			if (function.quality(s) < function.quality(best)) {
				best = s;
				bests.add(function.quality(s));
			}
			cont++;
		}
		
		return bests;

	}


}
