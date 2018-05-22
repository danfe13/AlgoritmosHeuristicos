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
<<<<<<< HEAD
			System.out.println(quality(bestSolution, option)+" "+count);
			int time = (int) (Math.random()*10);
			
			while(count2 < time) {
				double[] R = tweak(copy(S));
				if (quality(R,option) < quality(S,option))
=======
			System.out.println(quality(bestSolution, option));
			int time = (int) (Math.random()*100);
			
			while(count2 < time) {
				System.out.println(count2+" "+time);
				double[] R = tweak(copy(S),getMinValue(S),getMaxValue(S));
				if (quality(R,option) > quality(S,option))
>>>>>>> branch 'antonio' of https://github.com/danfe13/AlgoritmosHeuristicos.git
					S = R;
				count2++;
			}
			
<<<<<<< HEAD
			if (quality(S,option) < quality(bestSolution,option))
=======
			if (quality(S,option) > quality(bestSolution,option))
>>>>>>> branch 'antonio' of https://github.com/danfe13/AlgoritmosHeuristicos.git
				bestSolution = S;
			H = newHomeBase(H, S, option);
			S = perturb(H);
			count++;
		}

		return evolutionQuality;
	}

	public double[] tweak(double[] s, double d) {

		double n;

		for (int i = 0; i < s.length; i++) {
			if (d >= Math.random()) {
				do {
					n = random(range);

				} while ((s[i] + n < min) || (s[i] + n > max));
				s[i] = s[i] + n;
			}

		}
		return s;

	}
	
	public double[] perturb(double[] s) {
<<<<<<< HEAD
		return tweak(copy(s),0.1);
=======
		return super.tweak(copy(s));
>>>>>>> branch 'antonio' of https://github.com/danfe13/AlgoritmosHeuristicos.git
	}
	
	public double[] newHomeBase(double[] h, double[] s, int option) {
		if(quality(s,option) < quality(h,option))
			return s;
		else
			return h;
	}



}
