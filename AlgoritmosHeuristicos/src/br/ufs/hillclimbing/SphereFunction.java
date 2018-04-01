package br.ufs.hillclimbing;

public class SphereFunction {
	
	public static void main(String[] args) {
		
		double[] x = initArray(5);
		double s = quality(x);
		
		int cont = 0;
		while(cont < 100){
			double[] y = tweak(x);
			if (quality(x) > quality(y)){
				x = y;
			}
			cont++;
		}
		
		System.out.println(s);
		System.out.println(quality(x));
	}
	
	
	public static double[] initArray(int d){
		double[] x = new double[d];
		for (int i = 0; i < x.length; i++) {
			x[i] = Math.random() * 100;
		}
		return x;
	}
	

	// Sphere function
	public static double quality(double[] x) {
		double sum = 0.0;
		for (int i = 0; i < x.length; i++) {
			sum += x[i] * x[i];
		}
		return (sum);
	}
	
	public static double[] tweak(double[] x){
		int i = (int) (Math.random() * ((x.length - 1) + 1));
		x[i] = Math.random() * 100;
		return x;
	}
	
	

}
