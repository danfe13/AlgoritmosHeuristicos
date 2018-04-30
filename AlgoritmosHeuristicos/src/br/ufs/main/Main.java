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
import br.ufs.algorithm.IteratedLocal;
import br.ufs.algorithm.SimulatedAnnealing;
import br.ufs.benchmark.Rastrigin;
import br.ufs.benchmark.Rosenbrocks;
import br.ufs.benchmark.Sphere;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		IteratedLocal ils = new IteratedLocal(5, 1, -100, 100, 10000);
		plotarGrafico(ils.execute(new Sphere()), "ILS");
		
		//BuscaTabu tabu = new BuscaTabu(5, 1, -100, 100, 100, 100000);
		//plotarGrafico(tabu.execute(new Sphere()), "Tabu");
		
		/*HillClimbing hill = new HillClimbing(5, 1, -100, 100, 10);
		plotarGrafico(hill.execute(new Sphere()), "HillClimbing");
		
		SimulatedAnnealing simulated = new SimulatedAnnealing(5, 1, -100, 100, 10);
		plotarGrafico(simulated.execute(new Sphere()), "Simulated");*/
	}
	
	public static void plotarGrafico(ArrayList<Double> evolutionQuality, String algoritmo) throws FileNotFoundException, IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		for (int i = 0; i < evolutionQuality.size(); i++) {
			ds.addValue(evolutionQuality.get(i), " ", i + "");
			System.out.println(evolutionQuality.get(i));
		}					
		
		JFreeChart grafico = ChartFactory.createLineChart(algoritmo, "Iteração", "Valor", ds,
				PlotOrientation.VERTICAL, true, true, false);
		ChartUtilities.writeChartAsPNG(new FileOutputStream("grafico.png"), grafico, 1000, 500);

		ImageIcon imagem = new ImageIcon("grafico.png");
		JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imagem);	
		
	}

}
