package br.ufs.algorithm;

import br.ufs.benchmark.Benchmark;

public class InteratedLocal extends BaseAlgorithm {

	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public InteratedLocal(int lengthArray, double p, int range, int min, int max, int iterations) {
		super(lengthArray, p, range, min, max);
		this.iterations = iterations;
	}
	
	/**
	 * Executa o HillClimbing
	 * 
	 * @return Evolução da Qualidade da Solução 
	 */
	public double[] execute(Benchmark function) {

		double[] s = initSolution(lengthArray);
		double[] evolutionQuality = new double[iterations];
		double[] h = s;
		double[] best = s;
		
		int cont = 0;
		while (cont < iterations) {
			evolutionQuality[cont] = function.quality(s);
			int time = (int) (Math.random() * iterations+1);
			for(int i =0; i<time; i++) {
				double[] r = tweak(copy(s), min(s), max(s));
				if (function.quality(r) < function.quality(s)) {
					s = r;
				}
			}
			cont++;
		}

		return evolutionQuality;

	}
	
	public double[] tweak(double[] s, int min, int max) {
		double n;
		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = random();
					
				} while ((s[i] + n < min) || (s[i] + n > max));
				s[i] = s[i] + n;
			}
				
		}
		return s;
		
	}
	
	// getting the maximum value
	public int max(double[] array) {
	    double maxValue = array[0];
	    for (int i = 1; i < array.length; i++) {
	        if (array[i] > maxValue) {
	            maxValue = array[i];
	        }
	    }
	    return (int)maxValue;
	}

	// getting the miniumum value
	public int min(double[] array) {
	    double minValue = array[0];
	    for (int i = 1; i < array.length; i++) {
	        if (array[i] < minValue) {
	            minValue = array[i];
	        }
	    }
	    return (int)minValue;
	}

}
