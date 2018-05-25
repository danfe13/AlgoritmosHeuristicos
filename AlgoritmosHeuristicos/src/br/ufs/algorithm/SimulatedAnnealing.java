package br.ufs.algorithm;

import br.ufs.benchmark.IBenchmark;

public class SimulatedAnnealing extends BaseAlgorithm {

	//Numero de Iterações do Algoritmo
	private int iterations;
	//Temperatura
	private double temperature;
	
	public SimulatedAnnealing(int lengthArray, double p, int rangeSolution, int min, int max, double rangeTweak, int iterations, double temperature) {
		super(lengthArray, p, rangeSolution, min, max, rangeTweak);
		this.iterations = iterations;
		this.temperature = temperature;
	}
	
	/**
	 * Executa o Simulated Annealing
	 * @return Evolução da Qualidade da Solução
	 * */
	public double[] execute(IBenchmark b) {

		double[] s = initSolution();
		double[] best = s;
		double[] evolutionQuality = new double[iterations];

		//double decrease = temperature/iterations;
		//decrease = 0.1;
		
		int percent =(int) (iterations*0.5);
		double decrease = temperature/percent;
		
		int cont = 0;
		
		while (iterations > 0 || b.quality(best) == 0) {
			evolutionQuality[cont] = b.quality(best);
			double[] r = tweak(copy(s));
			if (b.quality(r) < b.quality(s) || 
					Math.random() < Math.pow(Math.E, (normalize(b.quality(s), b) - normalize(b.quality(r), b) / temperature))
				) {
				s = r;
			}
			temperature = temperature > 0 ? temperature-decrease : 0;
			iterations--;
			if (b.quality(s) < b.quality(best)) {
				best = s;
			}
			
			cont++;
		}
		
		return evolutionQuality;

	}
	
	public double normalize(double q, IBenchmark b) {
		return q / b.getQualityWorseSolution(this);
	}


}
