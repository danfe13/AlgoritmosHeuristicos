package br.ufs.algorithm;

public abstract class SimulatedAnnealing {

	public void execute(int t) {

		// some initial candidate solution
		double[] s = initSolution(5);
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

	public abstract double[] initSolution(int length);

	public abstract double[] tweak(double[] s);

	public abstract double quality(double[] s);

}
