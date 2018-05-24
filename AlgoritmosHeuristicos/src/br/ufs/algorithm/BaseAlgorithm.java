package br.ufs.algorithm;

import java.util.Random;

public abstract class BaseAlgorithm {
	
	//Tamanho do Array Solu��o
	protected int lengthArray;
	//Probabilidade de adicionar ru�do a um elemento no Array
	protected double p;
	//Range de cada elemento do Array Solu��o
	protected int range;
	//Valor min do Array Solu��o
	protected int min;
	//Valor max do Array Solu��o
	protected int max;
	//range dos vizinhos
	protected int r;
	
	public BaseAlgorithm(int lengthArray, double p, int min, int max, int r) {
		this.lengthArray = lengthArray;
		this.p = p;
		this.min = min;
		this.max = max;
		this.r = r;
	}

	public double[] initSolution(int length) {
		
		double[] s = new double[length];
		for (int i = 0; i < s.length; i++) {
			s[i] = randomTotal();
		}
		return s;
		
	}
	
	public double[] worseSolution(int length) {
		
		double[] s = new double[length];
		for (int i = 0; i < s.length; i++) {
			s[i] = max;
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
		System.out.println(result);
		
	}
	
	//Algorithm 8 Bounded Uniform Convolution
	public double[] tweak(double[] s) {
		double n;
		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = random(r);
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
	
	public double randomTotal() {
		double num;
		double sinal = Math.random();
		if (sinal < 0.5 && min < 0) {
			num = Math.random() * -1;
		} else {
			num = Math.random() * 1;
		}
		return num;
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
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	
}
