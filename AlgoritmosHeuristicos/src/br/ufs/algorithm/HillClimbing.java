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
	
	public double[] initSolution(int length) {
		double[] s = new double[length];
		for (int i = 0; i < s.length; i++) {
			s[i] = Math.random() * 100;
		}
		print(s);
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
					System.out.println(s[i] + n);
				} while ((min >= s[i] + n) && (max <= s[i] + n));
				s[i] = s[i] + n;
			}
				
		}
		print(s);
		return s;
	}
	
	public abstract double quality(double[] s);
	
	public void print(double[] s) {
		String result = "[";
		for (int i = 0; i < s.length; i++) {
			if(i == s.length - 1)
				result = result + s[i] + "]";
			else
				result = result + s[i] + ", ";
		}
		System.out.println(result + " - " + quality(s));
	}
	
}
