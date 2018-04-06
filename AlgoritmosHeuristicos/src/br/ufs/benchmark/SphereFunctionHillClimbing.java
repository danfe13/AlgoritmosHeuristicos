package br.ufs.benchmark;

import br.ufs.algorithm.HillClimbing;

public class SphereFunctionHillClimbing extends HillClimbing{

	public double quality(double[] s) {
		double sum = 0.0;
		for (int i = 0; i < s.length; i++) {
			sum += s[i] * s[i];
		}
		return (sum);
	}

}
