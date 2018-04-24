package br.ufs.benchmark;

import br.ufs.algorithm.TabuSearch;

public class SchwefelsProblemTabuSearch extends TabuSearch{

	public SchwefelsProblemTabuSearch(int lengthArray, double p, int range, int min, int max, int lengthTabu, int iterations, int nTweak) {
		super(lengthArray, p, range, min, max, lengthTabu, iterations, nTweak);
	}

	public double quality(double[] s) {
		double prev_sum, curr_sum, outer_sum;

	    curr_sum = s[0];
	    outer_sum = (curr_sum * curr_sum);

	    for (int i = 1; i < s.length; i++) {
	      prev_sum = curr_sum;
	      curr_sum = prev_sum + s[i];
	      outer_sum += (curr_sum * curr_sum);
	    }

	    return (outer_sum);
	}

}
