package br.ufs.benchmark;

public class GriewankFunction implements IBenchmark {

	static private double[] m_iSqrt;
	
	public GriewankFunction() {
		m_iSqrt = new double[100];
		for (int i = 0; i < 100; i++) {
	      m_iSqrt[i] = Math.sqrt(((double) i) + 1.0);
	    }
	}
	
	public double quality(double[] s) {
		
		double sum = 0.0;
	    double product = 1.0;

	    for (int i = 0; i < s.length; i++) {
	      sum += ((s[i] * s[i]) / 4000.0);
	      product *= Math.cos(s[i] / m_iSqrt[i]);
	    }

	    return (sum - product + 1.0);
		
	}

	
	
}
