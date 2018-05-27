package br.ufs.algorithm;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import br.ufs.benchmark.Benchmark;

public class BuscaTabu extends BaseAlgorithm {
	
	//Comprimento máximo da lista tabu
	private int lengthTabu;
	private int iExterno;
	private int iInterno;
	
	public BuscaTabu(int lengthArray, double p, int min, int max, int lengthTabu, int iExterno, int iInterno, int r) {
		super(lengthArray, p, min, max, r);
		this.lengthTabu = lengthTabu;
		this.iExterno = iExterno;
		this.iInterno = iInterno;
	}
	
	public ArrayList<Double> execute(Benchmark function) {
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		Queue<int[]> l = new LinkedList<int[]>();
		ArrayList<Double> bests = new ArrayList<Double>();
		bests.add(function.quality(s));
		Double xw = 0.0, xr = 0.0;
		
		int cont = 0;
		while (cont < iExterno) {
			if (l.size() > lengthTabu){
				l.remove();
			}
			double[] r = tweak(copy(s));
			for(int i=0; i<iInterno;i++){
				double[] w = tweak(copy(s));
				xw = function.quality(w);
				xr = function.quality(r);
				if(!contains(l, w) && xw < xr || contains(l, r)) {
					r = w;
				}
			}
			if(!contains(l, r)) {
				s = r;
				Arrays.sort(s);
				l.add(copyToInt(s));
			}
			if(function.quality(s) < function.quality(best)) {
				bests.add(function.quality(s));
				best = s;
			}
			cont++;
		}
		return bests;
	}
	
	public boolean contains(Queue<int[]> l, double[] s) {
		for(int [] tabu: l) {
			if(toCompare(s,tabu)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean toCompare(double[] s, int[] tabu) {
		Arrays.sort(s);
		for(int i = 0; i<s.length; i++) {
			if((int)s[i] <= tabu[i]) {
				return false;
			}
		}
		return true;
	}
	

}
