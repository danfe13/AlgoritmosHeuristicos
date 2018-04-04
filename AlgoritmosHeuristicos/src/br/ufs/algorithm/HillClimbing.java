package br.ufs.algorithm;

public abstract class HillClimbing {
	
	/**
	 * Executa o HillClimbing
	 * @param i				Número de iterações do Algoritmo
	 * @param lengthArray 	Tamanho do Array de Solução
	 * */
	public void execute(int i, int lengthArray) {

		double[] s = initSolution(lengthArray);

		int cont = 0;
		while (cont < i) {
			double[] r = tweak(s);
			if (quality(s) > quality(r)) {
				s = r;
			}
			cont++;
		}

		System.out.println(quality(s));

	}
	
	public abstract double[] initSolution(int length);
	
	public abstract double[] tweak(double[] s);
	
	public abstract double quality(double[] s);
	
}
