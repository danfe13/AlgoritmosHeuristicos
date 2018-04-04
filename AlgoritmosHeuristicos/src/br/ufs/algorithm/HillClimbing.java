package br.ufs.algorithm;

public abstract class HillClimbing {
	
	public void execute(int i) {

		double[] s = initSolution(5);

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
