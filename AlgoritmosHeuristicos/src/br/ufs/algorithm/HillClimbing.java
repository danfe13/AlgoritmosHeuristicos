package br.ufs.algorithm;

public abstract class HillClimbing extends BaseAlgorithm {

	//Número de Iterações do Algoritmo
	protected int iterations;
	
	public HillClimbing(int lengthArray, double p, int range, int min, int max, int iterations) {
		super(lengthArray, p, range, min, max);
		this.iterations = iterations;
	}
	
	/**
	 * Executa o HillClimbing
	 * 
	 * @return Evolução da Qualidade da Solução 
	 */
	public double[] execute() {

		double[] s = initSolution(lengthArray);
		double[] evolutionQuality = new double[iterations];
		
		int cont = 0;
		while (cont < iterations) {
			evolutionQuality[cont] = quality(s);
			double[] r = tweak(copy(s));
			if (quality(r) < quality(s)) {
				s = r;
			}
			cont++;
		}

		return evolutionQuality;

	}

}
