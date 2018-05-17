package br.ufs.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.DoubleStream;

import br.ufs.benchmark.Benchmark;

public class BuscaTabu extends BaseAlgorithm {
	
	//Comprimento máximo da lista tabu
	private int lengthTabu;
	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public BuscaTabu(int lengthArray, double p, int min, int max, int lengthTabu, int iterations, int r) {
		super(lengthArray, p, min, max, r);
		this.lengthTabu = lengthTabu;
		this.iterations = iterations;
	}
	
	public ArrayList<Double> execute(Benchmark function) {
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		double[] evolution = new double[iterations];
		Queue<Integer> l = new LinkedList<Integer>();
		ArrayList<Double> bests = new ArrayList<Double>();
		bests.add(function.quality(s));
		Double xw = 0.0, xr = 0.0;
		
		int cont = 0;
		while (cont < iterations) {
			evolution[cont] = function.quality(best);
			if (l.size() > lengthTabu){
				l.remove();
			}
			double[] r = tweak(copy(s));
			for(int i=0; i<lengthTabu;i++){
				double[] w = tweak(copy(s));
				xw = function.quality(w);
				xr = function.quality(r);
				if (l.contains(xw.intValue())){
					continue;
				}
				else if(xw<xr || l.contains(xr.intValue())) {
					r = w;
				}
			}
			if(!l.contains(xr.intValue()))
				s = r;
			l.add(xr.intValue());
			if(function.quality(s) < function.quality(best)) {
				bests.add(function.quality(s));
				System.out.println(function.quality(s));
				best = s;
			}
			cont++;
		}
		
		return bests;
		
	}

}
