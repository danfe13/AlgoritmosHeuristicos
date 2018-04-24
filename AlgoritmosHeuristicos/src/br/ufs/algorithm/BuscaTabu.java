package br.ufs.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.DoubleStream;

import br.ufs.benchmark.Benchmark;

public class BuscaTabu extends BaseAlgorithm {
	
	//Comprimento máximo da lista tabu
	private int lengthTabu;
	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public BuscaTabu(int lengthArray, double p, int range, int min, int max, int lengthTabu, int iterations) {
		super(lengthArray, p, range, min, max);
		this.lengthTabu = lengthTabu;
		this.iterations = iterations;
	}
	
	public double[] execute(Benchmark function) {
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		double[] evolution = new double[iterations];
		Queue<Integer> l = new LinkedList<Integer>();
		Double xw = 0.0, xr = 0.0;
		
		int cont = 0;
		while (cont < iterations) {
			evolution[cont] = function.quality(best);
			if (l.size() > lengthTabu){
				l.remove();
			}
			double[] r = tweak(copy(s));
			for(int i=0; i<lengthTabu*100;i++){
				double[] w = tweak(copy(s));
				xw = function.quality(w);
				xr = function.quality(r);
				if (l.contains(xw.intValue())){
					continue;
				}
				else if(xw<xr || l.contains(xr.intValue())) {
					r = w;
					break;
				}
			}
			if(!l.contains(xr.intValue()))
				s = r;
			l.add(xr.intValue());
			if(function.quality(s) < function.quality(best)) {
				best = s;
			}
			cont++;
		}
		
		return evolution;
		
	}

}
