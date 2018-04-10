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

import br.ufs.benchmark.SchwefelsProblemHillClimbing;
import br.ufs.benchmark.SchwefelsProblemSimulatedAnnealing;
import br.ufs.benchmark.SphereFunctionHillClimbing;
import br.ufs.benchmark.SphereFunctionSimulatedAnnealing;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		do {
			
			int benchmark  = Integer.parseInt(JOptionPane.showInputDialog("Benchmark: \n1 - Sphere Function\n2 - SchwefelsProblem"));
			int algorithm = Integer.parseInt(JOptionPane.showInputDialog("Algotithm: \n1 - Hill Climbing\n2 - Simulated Annealing"));
			
			if(benchmark == 1) {
				if(algorithm == 1) {
					SphereFunctionHillClimbing sphereFunctionHC = new SphereFunctionHillClimbing(5, 1, 100, -100, 100, 10000);
					double[] evolutionQuality = sphereFunctionHC.execute();
					plotarGrafico(evolutionQuality, "Sphere Function witch Hill Climbing");
				}else if(algorithm == 2) {
					SphereFunctionSimulatedAnnealing sphereFunctionSA = new SphereFunctionSimulatedAnnealing(5, 1, 100, -100, 100, 10000);
					double[] evolutionQuality = sphereFunctionSA.execute();
					plotarGrafico(evolutionQuality, "Sphere Function witch Simulated Annealing");
				}
			}else if(benchmark == 2) {
				if(algorithm == 1) {
					SchwefelsProblemHillClimbing schwefelsProblemHC = new SchwefelsProblemHillClimbing(5, 1, 100, -100, 100, 10000);
					double[] evolutionQuality = schwefelsProblemHC.execute();
					plotarGrafico(evolutionQuality, "Schwefels Problem witch Hill Climbing");
				}else if(algorithm == 2) {
					SchwefelsProblemSimulatedAnnealing schwefelsProblemSA = new SchwefelsProblemSimulatedAnnealing(5, 1, 100, -100, 100, 10000);
					double[] evolutionQuality = schwefelsProblemSA.execute();
					plotarGrafico(evolutionQuality, "SchwefelsProblem witch Simulated Annealing");
				}
			}
		} while (JOptionPane.showConfirmDialog(null, "Deseja Continuar?") == 0);
		
	}
	
	public static void plotarGrafico(double[] evolutionQuality, String algoritmo) throws FileNotFoundException, IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		for (int i = 0; i < evolutionQuality.length; i++) {
			ds.addValue(evolutionQuality[i], " ", i + "");
			System.out.println(evolutionQuality[i]);
		}					
		
		JFreeChart grafico = ChartFactory.createLineChart(algoritmo, "Iteração", "Valor", ds,
				PlotOrientation.VERTICAL, true, true, false);
		ChartUtilities.writeChartAsPNG(new FileOutputStream("grafico.png"), grafico, 1000, 500);

		ImageIcon imagem = new ImageIcon("grafico.png");
		JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imagem);	
		
	}

}
