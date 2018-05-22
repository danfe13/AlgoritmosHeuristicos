package br.ufs.algorithm;

public class HillClimbing extends BaseAlgorithm {

	//N�mero de Itera��es do Algoritmo
	protected int iterations;

	public HillClimbing(int lengthArray, double p, int min, int max,  int range, int iterations) {
		super(lengthArray, p, min, max, range);
		this.iterations = iterations;
		
		
		
	}

	/**
	 * Executa o HillClimbing
	 * 
	 * @return Evolu��o da Qualidade da Solu��o 
	 */
	public double[] execute(int option) {

		double[] s = initSolution(lengthArray);
		double[] evolutionQuality = new double[iterations];

		int cont = 0;
		while (cont < iterations) {
			System.out.println(quality(s,option)+" "+cont);
			evolutionQuality[cont] = quality(s,option);
			double[] r = tweak(copy(s));
			if (quality(r,option) < quality(s,option)) {
				s = r;
			}
			cont++;
		}

		return evolutionQuality;

	}

	

}
