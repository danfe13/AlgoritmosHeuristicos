package br.ufs.benchmark;

import br.ufs.algorithm.HillClimbing;

public class SphereFunctionHillClimbing extends HillClimbing{

	public double[] initSolution(int length) {
		double[] s = new double[length];
		for (int i = 0; i < s.length; i++) {
			s[i] = Math.random() * 100;
		}
		return s;
	}

	public double[] tweak(double[] s) {
		int i = (int) (Math.random() * ((s.length - 1) + 1));
		s[i] = Math.random() * 100;
		return s;
	}

	public double quality(double[] s) {
		double sum = 0.0;
		for (int i = 0; i < s.length; i++) {
			sum += s[i] * s[i];
		}
		return (sum);
	}

}
