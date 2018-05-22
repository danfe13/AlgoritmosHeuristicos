package br.ufs.benchmark;

public class RosenbrockFunction implements IBenchmark{

	public double quality2(double[] s) {
		double sum = 0.0;

	    for (int i = 0; i < (s.length - 1); i++) {
	      double temp1 = (s[i] * s[i]) - s[i + 1];
	      double temp2 = s[i] - 1.0;
	      sum += (100.0 * temp1 * temp1) + (temp2 * temp2);
	    }

	    return (sum);
	    
	}
	
	public double quality(double[] s) {
		double sum = 0.0;

	    for (int i = 0; i < (s.length - 1); i++) {
	      sum += 100 * Math.pow((Math.pow(s[i], 2) - s[i+1]),2)
                    + Math.pow((s[i] -1),2);    
	    }

	    return (sum);
	}

}
