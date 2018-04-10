import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.ufs.benchmark.SphereFunctionHillClimbing;
import br.ufs.benchmark.SphereFunctionSimulatedAnnealing;

public class Main {

	public static void main(String[] args) throws Exception {
		
		//SphereFunctionHillClimbing sphereFunction = new SphereFunctionHillClimbing(5, 1, 100, -100, 100, 1000);
		SphereFunctionSimulatedAnnealing sphereFunction = new SphereFunctionSimulatedAnnealing(5, 0.2, 100, -100, 100, 10000);
		double[] evolutionQuality = sphereFunction.execute();
		plotarGrafico(evolutionQuality, "Simulated Annealing");
	}
	
	public static void plotarGrafico(double[] evolutionQuality, String algoritmo) throws FileNotFoundException, IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		for (int i = 0; i < evolutionQuality.length; i++) {
			ds.addValue(evolutionQuality[i], " ", i + "");
			System.out.println(evolutionQuality[i]);
		}					
		
		JFreeChart grafico = ChartFactory.createLineChart(algoritmo, "Iteração", "Valor", ds,
				PlotOrientation.VERTICAL, true, true, false);
		ChartUtilities.writeChartAsPNG(new FileOutputStream("grafico.png"), grafico, 1200, 800);

		ImageIcon imagem = new ImageIcon("grafico.png");
		JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imagem);	
		
	}

}
