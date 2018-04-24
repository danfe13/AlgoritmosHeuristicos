package br.ufs.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public abstract class BuscaTabu extends BaseAlgorithm {
	
	//Comprimento máximo da lista tabu
	private int lengthTabu;
	//Numero de Tweak Desejado
	private int nTweak;
	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public BuscaTabu(int lengthArray, double p, int range, int min, int max, int lengthTabu, int iterations) {
		super(lengthArray, p, range, min, max);
		this.lengthTabu = lengthTabu;
		this.iterations = iterations;
	}
	
	public double[] execute() {
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		Queue<Double> tabu = new LinkedList<>(); 
		tabu.add(quality(s));
		
		int cont = 0;
		while(cont < iterations) {
			
			if(tabu.size() > lengthTabu) {
				tabu.remove(0);
			}
			
			double[] r = tweak(copy(s));
			
			for (int i = 0; i < nTweak-1; i++) {

				double[] w = tweak(copy(s));
				
				if((!tabu.contains(quality(w))) && (quality(w) < quality(r) || tabu.contains(r)))
					r = w;

			}
			
			if(!tabu.contains(quality(r))) {
				s = r;
				tabu.add(quality(r));
			}
			
			if(quality(s) < quality(best))
				best = s;
			
		}
		
		return best;
		
	}

}
