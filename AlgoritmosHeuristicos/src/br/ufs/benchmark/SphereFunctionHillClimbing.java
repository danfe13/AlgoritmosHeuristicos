package br.ufs.benchmark;

import br.ufs.algorithm.HillClimbing;

public class SphereFunctionHillClimbing extends HillClimbing{

	public SphereFunctionHillClimbing(int lengthArray, double p, int range, int min, int max, int iterations) {
		super(lengthArray, p, range, min, max, iterations);
	}
	
	public double quality(double[] s) {
		double sum = 0.0;
		for (int i = 0; i < s.length; i++) {
			sum += s[i] * s[i];
		}
		return (sum);
	}

}
