package br.ufs.algorithm;

import java.util.ArrayList;

import br.ufs.benchmark.Benchmark;

public class ILS extends BaseAlgorithm {

	//Número de Iterações do Algoritmo
	protected int iterations;
	
	private double distancePertub;
	
	public ILS(int lengthArray, double p, int min, int max, int r, int iterations, double distancePertub) {
		super(lengthArray, p, min, max, r);
		this.iterations = iterations;
		this.distancePertub = distancePertub;
	}

	public ArrayList<Double> execute(Benchmark function) {
		
		double[] s = initSolution(lengthArray);
		double[] h = s; //O "Home Base" corrente
		double[] best = s;
		
		ArrayList<Double> bests = new ArrayList<Double>();
		bests.add(function.quality(s));
		
		int cont = 0;
		while(cont < iterations) {
			int time  = (int) (Math.random() * lengthArray);
			for (int i = 0; i < time; i++) {
				double[] r = tweak(copy(s));
				if(function.quality(r) < function.quality(s)){ 
					s = r;
					bests.add(function.quality(s));
				}
			}
			if(function.quality(s) < function.quality(best)){
				best = s;
			}
			h = newHomeBase(h, s, function);
			s = perturb(h);
			cont++;
		}
		
		return bests;
	
	}
	
	public double[] perturb(double[] s) {
		return tweak(copy(s), distancePertub);
	}
	
	public double[] newHomeBase(double[] h, double[] s, Benchmark function) {
		if(function.quality(s) < function.quality(h))
			return s;
		else
			return h;
	}
	
	//Algorithm 8 Bounded Uniform Convolution
	public double[] tweak(double[] s, double distance) {
		double n;
		for (int i = 0; i < s.length; i++) {
			if (distance >= Math.random()) {
				do {
					n = random(r) + s[i];
				} while ((n < min) || (n > max));
				s[i] = n;
			}
		}
		return s;
	}

}
