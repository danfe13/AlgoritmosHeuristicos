package br.ufs.algorithm;

import br.ufs.benchmark.IBenchmark;

public class HillClimbing extends BaseAlgorithm {

	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public HillClimbing(int lengthArray, double p, int rangeSolution, int min, int max, int rangeTweak, int iterations) {
		super(lengthArray, p, rangeSolution, min, max, rangeTweak);
		this.iterations = iterations;
	}
	
	/**
	 * Executa o HillClimbing
	 * 
	 * @return Evolução da Qualidade da Solução 
	 */
	public double[] execute(IBenchmark b) {

		double[] s = initSolution(lengthArray);
		double[] evolutionQuality = new double[iterations];
		
		int cont = 0;
		while (cont < iterations) {
			evolutionQuality[cont] = b.quality(s);
			double[] r = tweak(copy(s));
			if (b.quality(r) < b.quality(s)) {
				s = r;
			}
			cont++;
		}

		return evolutionQuality;

	}

}
