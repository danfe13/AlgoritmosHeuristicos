package br.ufs.algorithm;

public abstract class BaseAlgorithm {

	//Tamanho do Array Solução
	protected int lengthArray;

	//Probabilidade de adicionar ruído a um elemento no Array
	protected double p;

	//Valor min do Array Solução
	protected int min;

	//Valor max do Array Solução
	protected int max;

	//Range de cada elemento do Array Solução
	protected int range;

	public BaseAlgorithm(int lengthArray, double p, int min, int max, int range) {
		this.lengthArray = lengthArray;
		this.p = p;
		this.min = min;
		this.max = max;
		this.range = range;
	}

	public double[] initSolution(int length) {

		double[] s = new double[length];
		for (int i = 0; i < s.length; i++) {
			s[i] = random(range);
		}
		return s;

	}

	public double[] worseSolution() {

		double[] s = new double[lengthArray];
		for (int i = 0; i < s.length; i++) {
			s[i] = max;
		}
		return s;

	}

	public void print(double[] s, int option) {

		String result = "[";
		for (int i = 0; i < s.length; i++) {
			if(i == s.length - 1)
				result = result + s[i] + "]";
			else
				result = result + s[i] + ", ";
		}
		System.out.println(result + " - " + quality(s,option));

	}

	//Algorithm 8 Bounded Uniform Convolution
	public double[] tweak(double[] s) {

		double n;

		for (int i = 0; i < s.length; i++) {
			if (p >= Math.random()) {
				do {
					n = random(range);

				} while ((s[i] + n < min) || (s[i] + n > max));
				s[i] = s[i] + n;
			}

		}
		return s;

	}

	public double[] copy(double[] s) {

		double[] r = new double[s.length];
		for (int i = 0; i < s.length; i++) {
			r[i] = s[i];
		}
		return r;

	}

	public double random(int range) {
		double num;
		double sinal = Math.random();
		if (sinal < 0.5 && min < 0) {
			num = Math.random() * range * -1;
		} else {
			num = Math.random() * range * 1;
		}
		return num;
	}

	public double quality(double[] s, int option) {
		switch (option) {
		case 1:
			return qualitySphere(s);
		case 2:
			return qualitySchwefels(s);
		case 3:
			return qualityRosenbrock(s);	
		default:
			return qualityRastrigin(s);
		}
	}

	public double qualitySchwefels(double[] s) {
		double F = Math.abs(s[0]);
		double z;
		for (int i = 1; i < s.length; i++) {
			z = Math.abs(s[i]);
			F = max(F, z);            
		}
		return F;
	}

	public double max(double x, double y){
		return x>=y?x:y;
	}

	public double qualitySphere(double[] s) {
		double sum = 0.0;
		for (int i = 0; i < s.length; i++) {
			sum += s[i] * s[i];
		}
		return (sum);
	}

	public double qualityRosenbrock(double[] s) {
		double sum = 0.0;

		for (int i = 0; i < s.length - 1; i++) {
			double temp1 = (s[i] * s[i]) - s[i + 1];
			double temp2 = s[i] - 1.0;
			sum += (100.0 * temp1 * temp1) + (temp2 * temp2);
		}
		return sum;
	}

	public double qualityRastrigin(double[] s) {
		double sum = 0.0;

		for (int i = 0; i < s.length; i++) {
			sum = (Math.pow(s[i], 2) - (10 * Math.cos(2 * Math.PI * s[i])))+10;
		}
		return sum;
	}


}
