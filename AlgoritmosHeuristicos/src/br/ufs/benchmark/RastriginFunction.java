package br.ufs.benchmark;

import br.ufs.algorithm.BaseAlgorithm;

public class RastriginFunction implements IBenchmark {

	private double qualityWorseSolution = 0;
	
	public Double quality(double[] s) {
		double sum = 0.0;

	    for (int i = 0; i < s.length; i++) {
	      sum += (s[i] * s[i]) - (10.0 * Math.cos((Math.PI * 2.0) * s[i])) + 10.0;
	    }

	    return (sum);
	}
	
	public Double getQualityWorseSolution(BaseAlgorithm baseAlgorithm) {
		if(qualityWorseSolution == 0) {
			qualityWorseSolution = quality(baseAlgorithm.worseSolution());
		}
		return qualityWorseSolution;
	}

}
