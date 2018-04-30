package br.ufs.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import br.ufs.benchmark.Benchmark;

public class IteratedLocal extends BaseAlgorithm {

	//Número de Iterações do Algoritmo
	protected int iterations;
	//Lista de rages
	protected LinkedList<Integer> mins;
	protected LinkedList<Integer> maxs;
	
	
	public IteratedLocal(int lengthArray, double p, int min, int max, int iterations) {
		super(lengthArray, p, min, max);
		this.iterations = iterations;
	}
	
	/**
	 * Executa o HillClimbing
	 * 
	 * @return Evolução da Qualidade da Solução 
	 */
	public ArrayList<Double> execute(Benchmark function) {

		double[] s = initSolution(lengthArray);
		//double[] evolutionQuality = new double[iterations];
		ArrayList<Double> evolutionQuality = new ArrayList<Double>();
		ArrayList<Double> bests = new ArrayList<Double>();
		double[] h = s;
		double[] best = s;
		mins = new LinkedList<Integer>();
		maxs = new LinkedList<Integer>();
		mins.add(min(s));
		maxs.add(max(s));
		bests.add(function.quality(s));
		System.out.println(function.quality(s));
		int cont = 0;
		do{
			evolutionQuality.add(function.quality(s));
			for(int i = 0; i<iterations; i++) {
				double[] r = tweak(copy(s), mins.getLast(), maxs.getLast());
				if (function.quality(r) < function.quality(s)) {
					System.out.println(function.quality(s));
					s = r;
				}
			}
			if(function.quality(best) > function.quality(s)) {
				bests.add(function.quality(s));
				best = copy(s);
			}
			evolutionQuality.add(function.quality(s));
			s = perturb(s);
			cont++;
		}while (!parada());
		return evolutionQuality;
	}
	
	public double[] tweak(double[] s, int min, int max) {
		double n;
		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = random();
				} while ((n < min) || (n > max));
				s[i] = n;
			}
				
		}
		return s;
	}
	
	public boolean parada() {
		int percorrido = 0;
		for(int i = 0; i<mins.size(); i++) {
			percorrido += maxs.get(i) - mins.get(i);
		}
		//verifica se foi percorrido 75% do rage;
		double total = (getMax()-getMin())*0.75;
		return  total <= percorrido;
		
	}
	
	public double[] perturb(double[] s) {
		boolean novorage;
		int n, n2;
		do {
			novorage = true;
			n = newlocal();
			n2 = newlocal();
			if(n>n2) {
				int aux = n2;
				n2 = n;
				n = aux;
			}
			for(int i = n; i<n2; i++) {
				if(mins.contains(i))
					novorage = false;
			}
		}while(!novorage);
		mins.add(n);
		maxs.add(n2);
		return tweak(s, mins.getLast(), maxs.getLast());
	}
	
	public int newlocal() {
		int n;
		boolean newlocal;
		do {
			n = (int)random();
			newlocal = true;
			for(int y = 0; y<mins.size(); y++) {
				if(n>mins.get(y) && n<maxs.get(y)) {
					newlocal = false;
					break;
				}
			}
		}while (!newlocal);
		return n;
	}
	
	
	// getting the maximum value
	public int max(double[] array) {
	    double maxValue = array[0];
	    for (int i = 1; i < array.length; i++) {
	        if (array[i] > maxValue) {
	            maxValue = array[i];
	        }
	    }
	    return (int)maxValue;
	}

	// getting the minimum value
	public int min(double[] array) {
	    double minValue = array[0];
	    for (int i = 1; i < array.length; i++) {
	        if (array[i] < minValue) {
	            minValue = array[i];
	        }
	    }
	    return (int)minValue;
	}

}
