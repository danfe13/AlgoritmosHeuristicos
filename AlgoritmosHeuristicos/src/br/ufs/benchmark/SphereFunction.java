package br.ufs.benchmark;

public class SphereFunction implements IBenchmark {

	public double quality(double[] s) {
		double sum = 0.0;
		for (int i = 0; i < s.length; i++) {
			sum += s[i] * s[i];
		}
		return (sum);
	}

}
