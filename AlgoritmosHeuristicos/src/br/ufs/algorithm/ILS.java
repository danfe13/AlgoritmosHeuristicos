package br.ufs.algorithm;

import br.ufs.benchmark.IBenchmark;

public class ILS extends BaseAlgorithm {

	//Número de Iterações do Algoritmo
	protected int iterations;
	
	private double distancePertub;
	
	public ILS(int lengthArray, double p, int rangeSolution, int min, int max, double rangeTweak, int iterations, double distancePertub) {
		super(lengthArray, p, rangeSolution, min, max, rangeTweak);
		this.iterations = iterations;
		this.distancePertub = distancePertub;
	}

	public double[] execute(IBenchmark b) {
		
		double[] s = initSolution();
		double[] h = s; //O "Home Base" corrente
		double[] best = s;
		
		double[] evolutionQuality = new double[iterations];
		
		int cont = 0;
		while(cont < iterations) {
			evolutionQuality[cont] = b.quality(best);
			int time  = (int) (Math.random() * lengthArray);
			for (int i = 0; i < time; i++) {
				double[] r = tweak(copy(s));
				if(b.quality(r) < b.quality(s)) 
					s = r;
			}
			if(b.quality(s) < b.quality(best))
				best = s;
			h = newHomeBase(h, s, b);
			s = perturb(h);
			cont++;
		}
		
		return evolutionQuality;
	
	}
	
	public double[] perturb(double[] s) {
		return tweak(copy(s), distancePertub);
	}
	
	public double[] newHomeBase(double[] h, double[] s, IBenchmark b) {
		if(b.quality(s) < b.quality(h))
			return s;
		else
			return h;
	}

}
