package br.ufs.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import br.ufs.benchmark.IBenchmark;

public class TabuSearch extends BaseAlgorithm {
	
	//Comprimento máximo da lista tabu
	private int lengthTabu;
	//Numero de Tweak Desejado
	private int nTweak;
	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public TabuSearch(int lengthArray, double p, int rangeSolution, int min, int max, int rangeTweak, int lengthTabu, int iterations, int nTweak) {
		super(lengthArray, p, rangeSolution, min, max, rangeTweak);
		this.lengthTabu = lengthTabu;
		this.iterations = iterations;
		this.nTweak = nTweak;
	}
	
	public double[] execute(IBenchmark b) {
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		Queue<Integer> tabu = new LinkedList<>(); 
		tabu.add((int) b.quality(s));
		double[] evolutionQuality = new double[iterations];
		
		int cont = 0;
		while(cont < iterations) {
			
			evolutionQuality[cont] = b.quality(best);
			
			if(tabu.size() > lengthTabu) {
				tabu.remove();
			}
			
			double[] r = tweak(copy(s));
			
			for (int i = 0; i < nTweak; i++) {

				double[] w = tweak(copy(s));
	
				if((!tabu.contains((int) b.quality(w))) && (b.quality(w) < b.quality(r) || tabu.contains((int) b.quality(r))))
					r = w;

			}
			
			if(!tabu.contains((int) b.quality(r))) {
				s = r;
				tabu.add((int) b.quality(r));
			}
			
			if(b.quality(s) < b.quality(best))
				best = s;
			
			cont++;
			
		}
		
		return evolutionQuality;
		
	}

}
