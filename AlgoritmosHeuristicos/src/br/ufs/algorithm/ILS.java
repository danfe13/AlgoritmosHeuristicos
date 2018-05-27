package br.ufs.algorithm;

public class ILS extends BaseAlgorithm {

	private int iterations;
	private int[] maxValues;
	private int[] minValues;
	private int time;
	public ILS(int lengthArray, double p, int min, int max, int range, int iteration, int time) {
		super(lengthArray, p, min,max, range);
		// TODO Auto-generated constructor stub
		this.iterations = iterations;
		this.time = time;


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
			
			while(count2 < time) {
				double[] R = tweak(copy(S));
				if (quality(R,option) < quality(S,option))
					S = R;
				count2++;
			}
			
			if (quality(S,option) < quality(bestSolution,option))
				bestSolution = S;
			H = newHomeBase(H, S, option);
			S = perturb(H);
			count++;
		}

		return evolutionQuality;
	}

	public double[] tweak(double[] s) {

		double n;
		double Pd = Math.random() ;
		double Prange = Math.random()*10;
		for (int i = 0; i < s.length; i++) {
			if (Pd >= Math.random()) {
				do {
					n = Prange+s[i];

				} while ((s[i] + n < min) || (s[i] + n > max));
				s[i] = s[i] + n;
			}

		}
		return s;

	}
	
	public double[] perturb(double[] s) {
		return tweak(copy(s));
	}
	
	public double[] newHomeBase(double[] h, double[] s, int option) {
		if(quality(s,option) < quality(h,option))
			return s;
		else
			return h;
	}



}
