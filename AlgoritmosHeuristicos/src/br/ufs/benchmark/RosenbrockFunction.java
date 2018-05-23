package br.ufs.benchmark;

public class RosenbrockFunction implements IBenchmark{

	public Double quality(double[] s) {
		double sum = 0.0;

	    for (int i = 0; i < (s.length - 1); i++) {
	      sum += 100 * Math.pow((Math.pow(s[i], 2) - s[i+1]),2)
                    + Math.pow((s[i] -1),2);    
	    }

	    return (sum);
	}

}
