package br.ufs.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public abstract class BuscaTabu extends BaseAlgorithm {
	
	//Comprimento máximo da lista tabu
	private int lengthTabu;
	//Numero de Tweak Desejado
	private int nTweak;
	
	private int iteration;
	
	public BuscaTabu(int lengthArray, double p, int range, int min, int max, int lengthTabu, int iteration) {
		super(lengthArray, p, range, min, max);
		this.lengthTabu = lengthTabu;
		this.iteration = iteration;
	}
	
	public double[] execute() {
	
		double[] S = initSolution(lengthArray);
		double[] bestSolution = S;
		Queue<Object> tabu = new LinkedList<Object>();
		
		int count = 0;
		
		while (count++ < lengthTabu) {
		
			if (tabu.size() > lengthTabu)
				tabu.remove();
			
			double[] R = tweak(copy(S));
			
			for (int i = 0; i < nTweak; i++) {
				double[] W = tweak(copy(S));
				
				if (!tabu.contains(quality(W)) && (quality(W) > quality(R)) || tabu.contains(quality(R)))
					R = W;
			}
			
			if (!tabu.contains(quality(R))) {
				S = R;
				tabu.add(quality(R));
			}
			
			if (quality(S) > quality(bestSolution))
				bestSolution = S;
				
		}
		
		return bestSolution;
		
		
	}

}
