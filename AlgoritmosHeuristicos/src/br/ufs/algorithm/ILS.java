package br.ufs.algorithm;

import java.util.ArrayList;

import br.ufs.benchmark.Benchmark;

public class ILS extends BaseAlgorithm {

	//Número de Iterações do Algoritmo Laço Externo
	private int iExterno;
	//Número de Iterações do Algoritmo Laço Interno
	private int iInterno;
	private double pPertub;
	private int rPertub;
	
	public ILS(int lengthArray, double p, int min, int max, int r, int iExterno, int iInterno, double pPerturb, int rPerturb) {
		super(lengthArray, p, min, max, r);
		this.iExterno = iExterno;
		this.iInterno = iInterno;
		this.pPertub = pPerturb;
		this.rPertub = rPerturb;
	}

	public ArrayList<Double> execute(Benchmark function) {
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		
		ArrayList<Double> bests = new ArrayList<Double>();
		bests.add(function.quality(s));
		
		int cont = 0;
		while(cont < iExterno) {
			for (int i = 0; i < iInterno; i++) {
				double[] r = tweak(copy(s));
				if(function.quality(r) < function.quality(s)){ 
					s = r;
					bests.add(function.quality(s));
				}
			}
			if(function.quality(s) < function.quality(best)){
				best = s;
			}
			s = perturb(s);
			cont++;
		}
		
		return bests;
	
	}
	
	public double[] perturb(double[] s) {
		return tweak(copy(s), pPertub, rPertub);
	}
	
	public double[] newHomeBase(double[] h, double[] s, Benchmark function) {
		if(function.quality(s) < function.quality(h))
			return s;
		else
			return h;
	}
	
	//Algorithm 8 Bounded Uniform Convolution
	public double[] tweak(double[] s, double p, int r) {
		double n;
		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = random(r) + s[i];
				} while ((n < min) || (n > max));
				s[i] = n;
			}
		}
		return s;
	}

}
