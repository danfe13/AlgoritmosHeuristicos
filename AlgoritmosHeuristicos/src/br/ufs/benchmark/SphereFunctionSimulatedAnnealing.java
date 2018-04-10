package br.ufs.benchmark;

import br.ufs.algorithm.SimulatedAnnealing;

public class SphereFunctionSimulatedAnnealing extends SimulatedAnnealing{

	public SphereFunctionSimulatedAnnealing(int lengthArray, double p, int range, int min, int max, int temperature) {
		super(lengthArray, p, range, min, max, temperature);
	}
	
	public double quality(double[] s) {
		double sum = 0.0;
		for (int i = 0; i < s.length; i++) {
			sum += s[i] * s[i];
		}
		return (sum);
	}

}
