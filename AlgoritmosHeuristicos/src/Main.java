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
import br.ufs.algorithm.ILS;
import br.ufs.algorithm.SimulatedAnnealing;
import br.ufs.algorithm.TabuSearch;
import br.ufs.benchmark.RastriginFunction;
import br.ufs.benchmark.RosenbrockFunction;
import br.ufs.benchmark.SchwefelsProblem;
import br.ufs.benchmark.SphereFunction;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		do {
			
			int benchmark  = Integer.parseInt(JOptionPane.showInputDialog("Benchmark: \n1 - Sphere Function\n2 - Schwefels Problem\n3 - Shifted Rosenbrock’s\n4 - Shifted Rastrigin’s Function "));
			int algorithm = Integer.parseInt(JOptionPane.showInputDialog("Algotithm: \n1 - Hill Climbing\n2 - Simulated Annealing\n3 - Busca Tabu\n4 - ILS"));
			
			HillClimbing hillClimbing = new HillClimbing(100, 0.1, 100, -100, 100, 100000);
			SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(100, 0.9, 100, -100, 100, 100000);
			TabuSearch tabuSearch = new TabuSearch(100, 0.5, 100, -100, 100, 10, 10000, 4);
			ILS ils = new ILS(100, 0.5, 100, -100, 100, 10000);	
			
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
				}else if(algorithm == 4) {
					double[] evolutionQuality = ils.execute(new SphereFunction());
					plotarGrafico(evolutionQuality, "Sphere Function witch ILS");
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
				}else if(algorithm == 4) {
					double[] evolutionQuality = ils.execute(new SchwefelsProblem());
					plotarGrafico(evolutionQuality, "SchwefelsProblem witch ILS");
				}
			}else if(benchmark == 3) {
				if(algorithm == 1) {
					double[] evolutionQuality = hillClimbing.execute(new RosenbrockFunction());
					plotarGrafico(evolutionQuality, "Shifted Rosenbrock’s Function witch Hill Climbing");
				}else if(algorithm == 2) {
					double[] evolutionQuality = simulatedAnnealing.execute(new RosenbrockFunction());
					plotarGrafico(evolutionQuality, "Shifted Rosenbrock’s Function witch Simulated Annealing");
				}
				else if(algorithm == 3) {
					double[] evolutionQuality = tabuSearch.execute(new RosenbrockFunction());
					plotarGrafico(evolutionQuality, "Shifted Rosenbrock’s Function witch Tabu Search");
				}else if(algorithm == 4) {
					double[] evolutionQuality = ils.execute(new RosenbrockFunction());
					plotarGrafico(evolutionQuality, "Shifted Rosenbrock’s Function witch ILS");
				}
			}else if(benchmark == 4) {
				if(algorithm == 1) {
					double[] evolutionQuality = hillClimbing.execute(new RastriginFunction());
					plotarGrafico(evolutionQuality, "Shifted Rastrigin’s Function witch Hill Climbing");
				}else if(algorithm == 2) {
					double[] evolutionQuality = simulatedAnnealing.execute(new RastriginFunction());
					plotarGrafico(evolutionQuality, "Shifted Rastrigin’s Function witch Simulated Annealing");
				}
				else if(algorithm == 3) {
					double[] evolutionQuality = tabuSearch.execute(new RastriginFunction());
					plotarGrafico(evolutionQuality, "Shifted Rastrigin’s Function witch Tabu Search");
				}else if(algorithm == 4) {
					double[] evolutionQuality = ils.execute(new RastriginFunction());
					plotarGrafico(evolutionQuality, "Shifted Rastrigin’s Function witch ILS");
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
		
		System.out.println(evolutionQuality[evolutionQuality.length-1]);
		
		JFreeChart grafico = ChartFactory.createLineChart(algoritmo, "Iteração", "Valor", ds,
				PlotOrientation.VERTICAL, true, true, false);
		ChartUtilities.writeChartAsPNG(new FileOutputStream("grafico.png"), grafico, 1000, 500);

		ImageIcon imagem = new ImageIcon("grafico.png");
		JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imagem);	
		
	}

}
