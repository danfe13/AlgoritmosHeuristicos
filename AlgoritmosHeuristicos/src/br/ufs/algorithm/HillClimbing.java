package br.ufs.algorithm;

import java.util.ArrayList;

import br.ufs.benchmark.Benchmark;

public class HillClimbing extends BaseAlgorithm {

	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public HillClimbing(int lengthArray, double p, int min, int max, int iterations, int r) {
		super(lengthArray, p, min, max, r);
		this.iterations = iterations;
	}
	
	/**
	 * Executa o HillClimbing
	 * 
	 * @return Evolução da Qualidade da Solução 
	 */
	public ArrayList<Double> execute(Benchmark function) {

		double[] s = initSolution(lengthArray);
		double[] evolutionQuality = new double[iterations];
		ArrayList<Double> bests = new ArrayList<Double>();
		bests.add(function.quality(s));
		
		int cont = 0;
		while (cont < iterations) {
			evolutionQuality[cont] = function.quality(s);
			double[] r = tweak(copy(s));
			if (function.quality(r) < function.quality(s)) {
				s = r;
				bests.add(function.quality(r));
			}
			cont++;
		}

		return bests;

	}

}
