package br.ufs.algorithm;

public abstract class SimulatedAnnealing {

	/**
	 * Executa o Simulated Annealing
	 * @param t				Temperatura
	 * @param lengthArray 	Tamanho do Array de Solução
	 * */
	public void execute(int t, int lengthArray) {

		// some initial candidate solution
		double[] s = initSolution(lengthArray);
		double[] best = s;

		while (t <= 0 || quality(best) == 0) {
			double[] r = tweak(s);
			if ((quality(r) > quality(s)) || (Math.random() < Math.pow(Math.E, ((quality(r) - quality(s)) / t))))
				s = r;
			t--;
			if (quality(s) < quality(best))
				best = s;
		}

		System.out.println(quality(s));

	}

	public double[] initSolution(int length) {
		double[] s = new double[length];
		for (int i = 0; i < s.length; i++) {
			s[i] = Math.random() * 100;
		}
		return s;
	}
	
	//Algorithm 8 Bounded Uniform Convolution
	public double[] tweak(double[] s) {
		//Probabilidade de adicionar ruído a um elemento no vetor
		int p = 1;
		//Range de cada elemento do vetor
		int r = 100;
		int min = -100;
		int max = 100;
		double n;
		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = Math.random() * r;
				} while ((min <= s[i] + n) && (max >= s[i] + n));
				s[i] = s[i] + n;
			}
				
		}
		return s;
	}

	public abstract double quality(double[] s);

}
