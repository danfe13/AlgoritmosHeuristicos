package br.ufs.benchmark;

import br.ufs.algorithm.BaseAlgorithm;

public class RosenbrockFunction implements IBenchmark{

	private double qualityWorseSolution = 0;
	
	public Double quality(double[] s) {
		double sum = 0.0;

	    for (int i = 0; i < (s.length - 1); i++) {
	      sum += 100 * Math.pow((Math.pow(s[i], 2) - s[i+1]),2)
                    + Math.pow((s[i] -1),2);    
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
