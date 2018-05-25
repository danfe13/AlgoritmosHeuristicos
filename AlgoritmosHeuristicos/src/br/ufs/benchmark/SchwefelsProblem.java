package br.ufs.benchmark;

import br.ufs.algorithm.BaseAlgorithm;

public class SchwefelsProblem implements IBenchmark {

	private double qualityWorseSolution = 0;
	
	public Double quality(double[] s) {
		double F = Math.abs(s[0]);
	    double z;
	    for (int i = 1; i < s.length; i++) {
	    	z = Math.abs(s[i]);
	        F = max(F, z);            
	    }
	    return F;
	}
	
	public double max(double x, double y){
        return x>=y?x:y;
    }
	
	public Double getQualityWorseSolution(BaseAlgorithm baseAlgorithm) {
		if(qualityWorseSolution == 0) {
			qualityWorseSolution = quality(baseAlgorithm.worseSolution());
		}
		return qualityWorseSolution;
	}

}
