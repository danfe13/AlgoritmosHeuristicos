package br.ufs.algorithm;

import br.ufs.benchmark.IBenchmark;

public abstract class BaseAlgorithm {
	
	//Tamanho do Array Solução
	protected int lengthArray;
	//Probabilidade de adicionar ruído a um elemento no Array
	protected double p;
	//Range de cada elemento do Array Solução
	protected int rangeSolution;
	//Valor min do Array Solução
	protected int min;
	//Valor max do Array Solução
	protected int max;
	protected int rangeTweak;
	
	public BaseAlgorithm(int lengthArray, double p, int rangeSolution, int min, int max, int rangeTweak) {
		this.lengthArray = lengthArray;
		this.p = p;
		this.rangeSolution = rangeSolution;
		this.min = min;
		this.max = max;
		this.rangeTweak = rangeTweak;
	}

	public double[] initSolution(int length) {
		
		double[] s = new double[length];
		for (int i = 0; i < s.length; i++) {
			s[i] = random(this.rangeSolution);
		}
		return s;
		
	}
	
	public void print(double[] s, IBenchmark b) {
		
		String result = "[";
		for (int i = 0; i < s.length; i++) {
			if(i == s.length - 1)
				result = result + s[i] + "]";
			else
				result = result + s[i] + ", ";
		}
		System.out.println(result + " - " + b.quality(s));
		
	}
	
	//Algorithm 8 Bounded Uniform Convolution
	public double[] tweak(double[] s) {
		
		double n;
		
		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = random(rangeTweak);
					
				} while ((s[i] + n < min) || (s[i] + n > max));
				s[i] = s[i] + n;
			}
				
		}
		
		return s;
		
	}
	
	//Algorithm 8 Bounded Uniform Convolution
	public double[] tweak(double[] s, double distance) {
		
		double n;
		
		for (int i = 0; i < s.length; i++) {
			if (distance >= Math.random()) {
				do {
					n = random(rangeTweak);
					
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
	
	public double random(int r) {
		double num;
		double sinal = Math.random();
		if (sinal < 0.5 && min < 0) {
			num = Math.random() * r * -1;
		} else {
			num = Math.random() * r * 1;
		}
		return num;
	}
	
	public abstract double[] execute(IBenchmark b);
	
}
