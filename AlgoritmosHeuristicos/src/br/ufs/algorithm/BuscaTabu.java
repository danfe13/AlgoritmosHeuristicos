package br.ufs.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class BuscaTabu extends BaseAlgorithm {
	
	//Comprimento máximo da lista tabu
	private int lengthTabu;
	//Numero de Tweak Desejado
	
	private int iterations;
	
	private int nTweaks;
	
	public BuscaTabu(int lengthArray, double p, int min, int max, int range, int lengthTabu, int iterations, int nTweaks) {
		super(lengthArray, p, min,max,range);
		this.lengthTabu = lengthTabu;
		this.iterations = iterations;
		this.nTweaks = nTweaks;
		
	}
	
	public double[] execute(int option) {
	
		double[] S = initSolution(lengthArray);
		double[] bestSolution = S;
		Queue<Object> tabu = new LinkedList<Object>();
		
		int count = 0;
		
		while (count++ < iterations) {
		
			if (tabu.size() > lengthTabu)
				tabu.remove();
			
			double[] R = tweak(copy(S));
			
			for (int i = 0; i < nTweaks; i++) {
				double[] W = tweak(copy(S));
				
				if (!tabu.contains(quality(W,option)) && (quality(W,option) > quality(R,option)) || tabu.contains(quality(R,option)))
					R = W;
			}
			
			if (!tabu.contains(quality(R,option))) {
				S = R;
				tabu.add(quality(R,option));
			}
			
			if (quality(S,option) > quality(bestSolution,option))
				bestSolution = S;
				
		}
		
		return bestSolution;		
		
	}

}
