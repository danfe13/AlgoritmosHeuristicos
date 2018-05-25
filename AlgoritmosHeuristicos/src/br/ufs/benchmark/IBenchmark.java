package br.ufs.benchmark;

import br.ufs.algorithm.BaseAlgorithm;

public interface IBenchmark {
	
	public Double quality(double[] s);
	
	public Double getQualityWorseSolution(BaseAlgorithm baseAlgorithm);
	
}
