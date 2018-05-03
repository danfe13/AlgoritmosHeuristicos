package br.ufs.algorithm;

public class ILS extends BaseAlgorithm {

	//Numero de Tweak Desejado

	private int iteration;
	private int[] maxValues;
	private int[] minValues;

	public ILS(int lengthArray, double p, int range, int min, int max) {
		super(lengthArray, p, range, min);
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
		double[] H = S;
		double[] bestSolution = S;

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

	public double[] tweak(double[] s, int minValue, int maxValue) {

		double n;

		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = random();

				} while ((s[i] + n < minValue) || (s[i] + n > minValue));
				s[i] = s[i] + n;
			}

		}
		return s;

	}

	public int getMaxValue(int[] maxValues) {
		int maxValue = maxValues[0];

		for (int i = 1; i < maxValues.length; i++) {

			if (maxValue < maxValues[i]) {
				maxValue = maxValues[i];
			}
		}
		return maxValue;
	}

	public int getMinValue(int[] minValues) {
		int minValue = minValues[0];

		for (int i = 1; i < minValues.length; i++) {

			if (minValue < minValues[i]) {
				minValue = minValues[i];
			}
		}
		return minValue;
	}



}
