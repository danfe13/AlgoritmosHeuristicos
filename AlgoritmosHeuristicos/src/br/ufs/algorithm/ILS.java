package br.ufs.algorithm;

public class ILS extends BaseAlgorithm {

	private int iterations;
	private int[] maxValues;
	private int[] minValues;

	public ILS(int lengthArray, double p, int min, int max, int range, int iterations) {
		super(lengthArray, p, min,max, range);
		// TODO Auto-generated constructor stub
		this.iterations = iterations;


	}

	public double[] execute(int option) {

		double[] S = initSolution(lengthArray);
		double[] H = S;
		double[] bestSolution = S;
		double[] evolutionQuality = new double[iterations];

		int count = 0; 
		

		while(count < iterations) {
			int count2 = 0;
			evolutionQuality[count] = quality(bestSolution, option);
			System.out.println(quality(bestSolution, option));
			int time = (int) (Math.random()*100);
			
			while(count2 < time) {
				System.out.println(count2+" "+time);
				double[] R = tweak(copy(S),getMinValue(S),getMaxValue(S));
				if (quality(R,option) > quality(S,option))
					S = R;
				count2++;
			}
			
			if (quality(S,option) > quality(bestSolution,option))
				bestSolution = S;
			H = newHomeBase(H, S, option);
			S = perturb(H);
			count++;
		}

		return evolutionQuality;
	}

	public double[] tweak(double[] s, int minValue, int maxValue) {

		double n;

		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = random(range);

				} while ((s[i] + n < minValue) || (s[i] + n > minValue));
				s[i] = s[i] + n;
			}

		}
		return s;

	}

	public int getMaxValue(double[] maxValues) {
		double maxValue = maxValues[0];

		for (int i = 1; i < maxValues.length; i++) {

			if (maxValue < maxValues[i]) {
				maxValue = maxValues[i];
			}
		}
		return (int) maxValue;
	}

	public int getMinValue(double[] minValues) {
		double minValue = minValues[0];

		for (int i = 1; i < minValues.length; i++) {

			if (minValue < minValues[i]) {
				minValue = minValues[i];
			}
		}
		return (int) minValue;
	}
	
	public double[] perturb(double[] s) {
		return super.tweak(copy(s));
	}
	
	public double[] newHomeBase(double[] h, double[] s, int option) {
		if(quality(s,option) < quality(h,option))
			return s;
		else
			return h;
	}



}
