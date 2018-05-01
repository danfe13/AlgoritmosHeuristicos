package br.ufs.algorithm;

import br.ufs.benchmark.IBenchmark;

public class ILS extends BaseAlgorithm {

	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public ILS(int lengthArray, double p, int range, int min, int max, int iterations) {
		super(lengthArray, p, range, min, max);
		this.iterations = iterations;
	}

	public double[] execute(IBenchmark b) {
		
		double[] s = initSolution(lengthArray);
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
		return tweak(copy(s), 1);
	}
	
	public double[] newHomeBase(double[] h, double[] s, IBenchmark b) {
		if(b.quality(s) < b.quality(h))
			return s;
		else
			return h;
	}

}
