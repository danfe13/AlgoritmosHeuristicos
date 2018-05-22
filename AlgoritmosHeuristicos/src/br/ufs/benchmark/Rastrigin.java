package br.ufs.benchmark;


public class Rastrigin implements Benchmark{

	public double quality(double[] s) {
		double sum = 0.0;

		for (int i = 0; i < s.length; i++) {
			sum += Math.pow(s[i], 2.0) - 10.0 * Math.cos(2 * Math.PI * s[i])+10;
		}
		return sum;
	}

}
