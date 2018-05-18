package br.ufs.algorithm;

public class SimulatedAnnealing extends BaseAlgorithm {

	//Temperatura
	private int temperature;
	
	public SimulatedAnnealing(int lengthArray, double p, int min, int max, int range, int temperature) {
		super(lengthArray, p, min, max, range);
		this.temperature = temperature;
	}
	
	/**
	 * Executa o Simulated Annealing
	 * @return Evolução da Qualidade da Solução
	 * */
	public double[] execute(int option) {

		double[] s = initSolution(lengthArray);
		double[] best = s;
		double[] evolutionQuality = new double[temperature];
		
		int cont = 0;
		while (temperature > 0 || quality(best,option) == 0) {
			evolutionQuality[cont] = quality(best,option);
			double[] r = tweak(copy(s));
			if ((quality(r,option) < quality(s,option)) || (Math.random() < Math.pow(Math.E, ((quality(r,option) - quality(s,option)) / temperature))))
				s = r;
			temperature--;
			if (quality(s,option) < quality(best,option))
				best = s;
			//System.out.println(evolutionQuality[cont]);
			cont++;
		}
	
		return evolutionQuality;

	}


}
