package br.ufs.algorithm;

public abstract class BaseAlgorithm {

	public double[] initSolution(int length) {
		double[] s = new double[length];
		for (int i = 0; i < s.length; i++) {
			s[i] = Math.random() * 100;
		}
		return s;
	}
	
	public void print(double[] s) {
		String result = "[";
		for (int i = 0; i < s.length; i++) {
			if(i == s.length - 1)
				result = result + s[i] + "]";
			else
				result = result + s[i] + ", ";
		}
		System.out.println(result + " - " + quality(s));
	}
	
	public abstract double quality(double[] s);
	
	//Algorithm 8 Bounded Uniform Convolution
	public double[] tweak(double[] s) {
		//Probabilidade de adicionar ruído a um elemento no vetor
		double p = 1;
		//Range de cada elemento do vetor
		int r = 100;
		int min = -100;
		int max = 100;
		double n;
		
		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = Math.random() * r;
					
				} while ((s[i] - n < min) || (s[i] - n > max));
				s[i] = s[i] - n;
			}
				
		}
		return s;
	}
	
	public double[] copy(double[] s) {
		double[] r = new double[s.length];
		for (int i = 0; i < s.length; i++) {
			r[i] = s[i];
		}
		return r;
	}
	
}
