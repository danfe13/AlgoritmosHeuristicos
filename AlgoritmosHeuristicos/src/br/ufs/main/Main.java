package br.ufs.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.ufs.algorithm.BuscaTabu;
import br.ufs.algorithm.HillClimbing;
import br.ufs.algorithm.ILS;
import br.ufs.algorithm.IteratedLocal;
import br.ufs.algorithm.SimulatedAnnealing;
import br.ufs.benchmark.Benchmark;
import br.ufs.benchmark.Rastrigin;
import br.ufs.benchmark.Rosenbrocks;
import br.ufs.benchmark.Schwefels;
import br.ufs.benchmark.Sphere;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		//simulated(new Rastrigin(), 0.1);
		
		buscaTabu(new Sphere(), 0.2);
		
		//ils(new Rastrigin(), 0.1);
		
		//hill(new Rastrigin(), 0.1);
		
	}
	
	public static void simulated(Benchmark bench, double p) {
		SimulatedAnnealing simulated = new SimulatedAnnealing(100, p, -5, 5, 100, 100000, 1);
		report(simulated.execute(bench), 1);
		
		SimulatedAnnealing simulated2 = new SimulatedAnnealing(100, p, -5, 5, 100, 100000, 5);
		report(simulated2.execute(bench), 1);
		
		SimulatedAnnealing simulated3 = new SimulatedAnnealing(100, p, -5, 5, 100, 100000, 10);
		report(simulated3.execute(bench), 1);
	}
	
	public static void buscaTabu(Benchmark bench, double p) {
		BuscaTabu busca = new BuscaTabu(100, p, -100, 100, 100, 100, 100000, 1);
		report(busca.execute(bench), 1);
	}
	
	public static void hill(Benchmark bench, double p) {
		HillClimbing hill = new HillClimbing(100, p, -5, 5, 100000, 1);
		report(hill.execute(bench), 1);
		
		HillClimbing hill2 = new HillClimbing(100, p, -5, 5, 100000, 5);
		report(hill2.execute(bench), 1);
		
		HillClimbing hill3 = new HillClimbing(100, p, -5, 5, 100000, 10);
		report(hill3.execute(bench), 1);
	}
	
	public static void ils(Benchmark bench, double p) {
		ILS ils = new ILS(100, p, -5, 5, 5, 1000, 100, 0.1, 10);
		report(ils.execute(bench), 1);
		
	}
	
	
	public static void plotarGrafico(ArrayList<Double> evolutionQuality, String algoritmo) throws FileNotFoundException, IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		for (int i = 0; i < evolutionQuality.size(); i++) {
			ds.addValue(evolutionQuality.get(i), " ", i + "");
			System.out.println(evolutionQuality.get(i));
			if (i == evolutionQuality.size()-1)
				System.out.println(" FINAL");
		}					
		
		JFreeChart grafico = ChartFactory.createLineChart(algoritmo, "Iteração", "Valor", ds,
				PlotOrientation.VERTICAL, true, true, false);
		ChartUtilities.writeChartAsPNG(new FileOutputStream("grafico.png"), grafico, 1000, 500);

		ImageIcon imagem = new ImageIcon("grafico.png");
		JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imagem);	
		
	}
	
	public static void report(ArrayList<Double> evolutionQuality, int indice){
		System.out.println("======================="+indice+"==========================");
		System.out.println("Meta:"+evolutionQuality.get(evolutionQuality.size()-1));
		System.out.println("Vizinhos:"+evolutionQuality.size());
		
	}

}
