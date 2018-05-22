package br.ufs.benchmark;

public class SchwefelsProblem implements IBenchmark {

	public double quality(double[] s) {
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

}
