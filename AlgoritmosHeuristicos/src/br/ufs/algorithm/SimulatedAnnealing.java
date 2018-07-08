package br.ufs.algorithm;

import java.util.ArrayList;
import java.util.Random;

import br.ufs.benchmark.Benchmark;

public class SimulatedAnnealing extends BaseAlgorithm {

	private double temperature;
	private int interaction;
	private double pior;
	
	public SimulatedAnnealing(int lengthArray, double p, int min, int max, int temperature, int interaction ,int r) {
		super(lengthArray, p, min, max, r);
		this.temperature = temperature;
		this.interaction = interaction;
	}
	
	/**
	 * Executa o Simulated Annealing
	 * @return Evolução da Qualidade da Solução
	 * */
	public ArrayList<Double> execute(Benchmark function) {
		
		this.pior = function.quality(worseSolution(lengthArray));
		double[] s = initSolution(lengthArray);
		double[] best = s;
		ArrayList<Double> bests = new ArrayList<Double>();
		bests.add(function.quality(s));
		int cont = 0;
		int percent =(int) (interaction*0.5);
		double decreaseT = temperature/percent;
		Random random = new Random();

		while (cont < interaction || function.quality(best) == 0) {
			double[] r = tweak(copy(s));
			double q = normalize(function.quality(s));
			
			double ra = random.nextInt(1000) / 1000.0;
			if ((function.quality(r) < function.quality(s)) || (ra < Math.exp((normalize(function.quality(s)) - normalize(function.quality(r))) / temperature)))
				s = r;
			
			temperature = temperature > 0 ? temperature -decreaseT : 0;
			if (function.quality(s) < function.quality(best)) {
				best = s;
				bests.add(function.quality(s));
			}
			cont++;
		}
		
		return bests;

	}
	
	public double normalize(double quality){
		return quality/pior;
		//return quality;
	}

}
