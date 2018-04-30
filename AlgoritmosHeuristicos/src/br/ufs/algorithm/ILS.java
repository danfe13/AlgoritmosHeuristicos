package br.ufs.algorithm;

public class ILS extends BaseAlgorithm {

	//Numero de Tweak Desejado

	private int iteration;

	public ILS(int lengthArray, double p, int range, int min, int max) {
		super(lengthArray, p, range, min, max);
		// TODO Auto-generated constructor stub
		this.iteration = (int) (Math.random()*1000);
	}

	@Override
	public double quality(double[] s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] execute() {

		double[] S = initSolution(lengthArray);
		double[] H = S, bestSolution = S;
		int count = 0, count2 = 0;
		int time = (int) (Math.random()*1000);
		while(count++ < iteration) {
			while(count2++ < time) {
				double[] R = tweak(copy(S));
				if (quality(R) > quality(S))
					S = R;
			}
			if (quality(S) > quality(bestSolution))
				bestSolution = S;
		}
		return bestSolution;
	}

}
