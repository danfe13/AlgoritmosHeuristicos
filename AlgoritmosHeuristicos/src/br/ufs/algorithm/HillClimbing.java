package br.ufs.algorithm;

import br.ufs.benchmark.Benchmark;

public class HillClimbing extends BaseAlgorithm {

	//N�mero de Itera��es do Algoritmo
	protected int iterations;
	
	public HillClimbing(int lengthArray, double p, int range, int min, int max, int iterations) {
		super(lengthArray, p, range, min, max);
		this.iterations = iterations;
	}
	
	/**
	 * Executa o HillClimbing
	 * 
	 * @return Evolu��o da Qualidade da Solu��o 
	 */
	public double[] execute(Benchmark function) {

		double[] s = initSolution(lengthArray);
		double[] evolutionQuality = new double[iterations];
		
		int cont = 0;
		while (cont < iterations) {
			evolutionQuality[cont] = function.quality(s);
			double[] r = tweak(copy(s));
			if (function.quality(r) < function.quality(s)) {
				s = r;
			}
			cont++;
		}

		return evolutionQuality;

	}

}
