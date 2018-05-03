package br.ufs.algorithm;

public abstract class BaseAlgorithm {
	
	//Tamanho do Array Solução
	protected int lengthArray;
	//Probabilidade de adicionar ruído a um elemento no Array
	protected double p;
	//Range de cada elemento do Array Solução
	protected int range;
	//Valor min do Array Solução
	protected int min;
	//Valor max do Array Solução
	protected int max;
	
	public BaseAlgorithm(int lengthArray, double p, int min, int max) {
		this.lengthArray = lengthArray;
		this.p = p;
		this.min = min;
		this.max = max;
	}

	public double[] initSolution(int length) {
		
		double[] s = new double[length];
		for (int i = 0; i < s.length; i++) {
			s[i] = random();
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
	
	//Algorithm 8 Bounded Uniform Convolution
	public double[] tweak(double[] s) {
		
		double n;
		
		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = random();
					
				} while ((s[i] + n < min) || (s[i] + n > max));
				s[i] = s[i] + n;
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
	
	public double random() {
		double num;
		double sinal = Math.random();
		if (sinal < 0.5 && min < 0) {
			num = Math.random() * range * -1;
		} else {
			num = Math.random() * range * 1;
		}
		return num;
	}
	
	public abstract double quality(double[] s);
	
	public abstract double[] execute();
	
}
