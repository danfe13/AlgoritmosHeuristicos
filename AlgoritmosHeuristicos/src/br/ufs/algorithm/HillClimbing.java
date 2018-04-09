package br.ufs.algorithm;

public abstract class HillClimbing extends BaseAlgorithm {
	
	/**
	 * Executa o HillClimbing
	 * @param i				Número de iterações do Algoritmo
	 * @param lengthArray 	Tamanho do Array de Solução
	 * */
	public void execute(int i, int lengthArray) {

		double[] s = initSolution(lengthArray);
		
		int cont = 0;
		while (cont < i) {
			System.out.println(quality(s));
			double[] r = tweak(copy(s));
			if (quality(r) < quality(s)) {
				s = r;
			}
			cont++;
		}

		System.out.println("Qualidade: " + quality(s));

	}
	
}
