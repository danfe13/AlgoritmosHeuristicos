package br.ufs.algorithm;

public abstract class BuscaTabu extends BaseAlgorithm {
	
	//Comprimento máximo da lista tabu
	private int lengthTabu;
	//Numero de Tweak Desejado
	private int nTweak;
	
	public BuscaTabu(int lengthArray, double p, int range, int min, int max, int lengthTabu) {
		super(lengthArray, p, range, min, max);
		this.lengthTabu = lengthTabu;
	}
	
	public double[] execute() {
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		double[] tabu = new double[lengthTabu];
		
		
		
		return null;
		
	}

}
