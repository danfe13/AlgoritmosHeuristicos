package br.ufs.algorithm;

import java.util.Arrays;
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
	
	public TabuSearch(int lengthArray, double p, int rangeSolution, int min, int max, double rangeTweak, int lengthTabu, int iterations, int nTweak) {
		super(lengthArray, p, rangeSolution, min, max, rangeTweak);
		this.lengthTabu = lengthTabu;
		this.iterations = iterations;
		this.nTweak = nTweak;
	}
	
	public double[] execute(IBenchmark b) {
		
		double[] s = initSolution();
		double[] best = s;
		Queue<int[]> tabu = new LinkedList<>(); 
		tabu.add(converter(s));
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
	
				if(
					(!contains(w, tabu)) && 
					(b.quality(w) < b.quality(r) || (contains(r, tabu)))
				) {
					r = w;
				}

			}
			
			if(!contains(r, tabu)) {
				s = r;
				tabu.add(converter(r));
			}
			
			if(b.quality(s) < b.quality(best)) {
				best = s;
			}
			
			cont++;
			
		}
		
		return evolutionQuality;
		
	}
	
	public int[] converter(double[] s) {
		int[] sInt = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			sInt[i] = (int) s[i];
		}
		return sInt;
	}
	
	public boolean contains(double[] s, Queue<int[]> tabu) {
		boolean contain = false;
		int[] sInt = converter(s);
		for (int[] elementTabu : tabu) {
			Arrays.sort(sInt);
	        Arrays.sort(elementTabu);
	        int cont = 0;
	        for(int i=0;i<sInt.length;i++){
				if(elementTabu[i] == sInt[i]) {
					cont++;
				}
	        }
	        if (cont == s.length) return true;
		}
		return contain;
	}

}
