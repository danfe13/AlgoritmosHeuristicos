import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.ufs.algorithm.HillClimbing;
import br.ufs.algorithm.SimulatedAnnealing;
import br.ufs.algorithm.TabuSearch;
import br.ufs.benchmark.SchwefelsProblem;
import br.ufs.benchmark.SphereFunction;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		do {
			
			int benchmark  = Integer.parseInt(JOptionPane.showInputDialog("Benchmark: \n1 - Sphere Function\n2 - SchwefelsProblem"));
			int algorithm = Integer.parseInt(JOptionPane.showInputDialog("Algotithm: \n1 - Hill Climbing\n2 - Simulated Annealing\n3 - Busca Tabu"));
			
			HillClimbing hillClimbing = new HillClimbing(5, 1, 100, -100, 100, 10000);
			SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(5, 1, 100, -100, 100, 10000);
			TabuSearch tabuSearch = new TabuSearch(5, 1, 100, -100, 100, 10, 10000, 4);
					
			if(benchmark == 1) {
				if(algorithm == 1) {
					double[] evolutionQuality = hillClimbing.execute(new SphereFunction());
					plotarGrafico(evolutionQuality, "Sphere Function witch Hill Climbing");
				}else if(algorithm == 2) {
					double[] evolutionQuality = simulatedAnnealing.execute(new SphereFunction());
					plotarGrafico(evolutionQuality, "Sphere Function witch Simulated Annealing");
				}else if(algorithm == 3) {
					double[] evolutionQuality = tabuSearch.execute(new SphereFunction());
					plotarGrafico(evolutionQuality, "Sphere Function witch Tabu Search");
				}
			}else if(benchmark == 2) {
				if(algorithm == 1) {
					double[] evolutionQuality = hillClimbing.execute(new SchwefelsProblem());
					plotarGrafico(evolutionQuality, "Schwefels Problem witch Hill Climbing");
				}else if(algorithm == 2) {
					double[] evolutionQuality = simulatedAnnealing.execute(new SchwefelsProblem());
					plotarGrafico(evolutionQuality, "SchwefelsProblem witch Simulated Annealing");
				}
				else if(algorithm == 3) {
					double[] evolutionQuality = tabuSearch.execute(new SchwefelsProblem());
					plotarGrafico(evolutionQuality, "SchwefelsProblem witch Tabu Search");
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
		
		JFreeChart grafico = ChartFactory.createLineChart(algoritmo, "Itera��o", "Valor", ds,
				PlotOrientation.VERTICAL, true, true, false);
		ChartUtilities.writeChartAsPNG(new FileOutputStream("grafico.png"), grafico, 1000, 500);

		ImageIcon imagem = new ImageIcon("grafico.png");
		JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imagem);	
		
	}

}
