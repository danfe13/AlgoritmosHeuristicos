package br.ufs.benchmark;

public class AckleyFunction implements IBenchmark{

	public double quality(double[] s) {
		double sum1 = 0.0;
	    double sum2 = 0.0;

	    for (int i = 0; i < s.length; i++) {
	      sum1 += (s[i] * s[i]);
	      sum2 += (Math.cos((Math.PI * 2.0) * s[i]));
	    }

	    return (-20.0 * Math.exp(-0.2 * Math.sqrt(sum1 / ((double) s.length))) - Math
	      .exp(sum2 / ((double) s.length)) + 20.0 + Math.E);
	}

}
