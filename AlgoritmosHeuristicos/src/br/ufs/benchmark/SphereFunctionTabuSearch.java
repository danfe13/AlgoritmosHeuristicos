package br.ufs.benchmark;

import br.ufs.algorithm.TabuSearch;

public class SphereFunctionTabuSearch extends TabuSearch{

	public SphereFunctionTabuSearch(int lengthArray, double p, int range, int min, int max, int lengthTabu, int iterations, int nTweak) {
		super(lengthArray, p, range, min, max, lengthTabu, iterations, nTweak);
	}
	
	public double quality(double[] s) {
		double sum = 0.0;
		for (int i = 0; i < s.length; i++) {
			sum += s[i] * s[i];
		}
		return (sum);
	}

}
