package br.ufs.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public abstract class TabuSearch extends BaseAlgorithm {
	
	//Comprimento máximo da lista tabu
	private int lengthTabu;
	//Numero de Tweak Desejado
	private int nTweak;
	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public TabuSearch(int lengthArray, double p, int range, int min, int max, int lengthTabu, int iterations, int nTweak) {
		super(lengthArray, p, range, min, max);
		this.lengthTabu = lengthTabu;
		this.iterations = iterations;
		this.nTweak = nTweak;
	}
	
	public double[] execute() {
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		Queue<Integer> tabu = new LinkedList<>(); 
		tabu.add((int) quality(s));
		double[] evolutionQuality = new double[iterations];
		
		int cont = 0;
		while(cont < iterations) {
			
			evolutionQuality[cont] = quality(best);
			
			if(tabu.size() > lengthTabu) {
				tabu.remove();
			}
			
			double[] r = tweak(copy(s));
			
			for (int i = 0; i < nTweak-1; i++) {

				double[] w = tweak(copy(s));
	
				if((!tabu.contains((int) quality(w))) && (quality(w) < quality(r) || tabu.contains((int) quality(r))))
					r = w;

			}
			
			if(!tabu.contains((int) quality(r))) {
				s = r;
				tabu.add((int) quality(r));
			}
			
			if(quality(s) < quality(best))
				best = s;
			
			cont++;
			
		}
		
		return evolutionQuality;
		
	}

}
