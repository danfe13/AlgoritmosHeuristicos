import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

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
			
			HillClimbing hillClimbing;
			SimulatedAnnealing simulatedAnnealing;
			TabuSearch tabuSearch;
			ILS ils;
			
			if(benchmark == 1) {
				if(algorithm == 1) {
					hillClimbing = new HillClimbing(100, 0.01, 100, -100, 100, 1, 100000);
					double[] evolutionQuality = hillClimbing.execute(new SphereFunction());
					plotarGrafico(evolutionQuality, "Sphere Function witch Hill Climbing");
				}else if(algorithm == 2) {
					simulatedAnnealing = new SimulatedAnnealing(100, 0.01, 100, -100, 100, 1, 100000, 100);
					double[] evolutionQuality = simulatedAnnealing.execute(new SphereFunction());
					plotarGrafico(evolutionQuality, "Sphere Function witch Simulated Annealing");
				}else if(algorithm == 3) {
					tabuSearch = new TabuSearch(100, 0.01, 100, -100, 100, 5, 10, 100000, 4);
					double[] evolutionQuality = tabuSearch.execute(new SphereFunction());
					plotarGrafico(evolutionQuality, "Sphere Function witch Tabu Search");
				}else if(algorithm == 4) {
					ils = new ILS(100, 0.01, 100, -100, 100, 1, 100000, 0.1);	
					double[] evolutionQuality = ils.execute(new SphereFunction());
					plotarGrafico(evolutionQuality, "Sphere Function witch ILS");
				}
			}else if(benchmark == 2) {
				if(algorithm == 1) {
					hillClimbing = new HillClimbing(100, 0.1, 100, -100, 100, 5, 100000);
					double[] evolutionQuality = hillClimbing.execute(new SchwefelsProblem());
					plotarGrafico(evolutionQuality, "Schwefels Problem witch Hill Climbing");
				}else if(algorithm == 2) {
					simulatedAnnealing = new SimulatedAnnealing(100, 0.1, 100, -100, 100, 4, 100000, 100);
					double[] evolutionQuality = simulatedAnnealing.execute(new SchwefelsProblem());
					plotarGrafico(evolutionQuality, "SchwefelsProblem witch Simulated Annealing");
				}
				else if(algorithm == 3) {
					tabuSearch = new TabuSearch(100, 0.01, 100, -100, 100, 5, 10, 100000, 4);
					double[] evolutionQuality = tabuSearch.execute(new SchwefelsProblem());
					plotarGrafico(evolutionQuality, "SchwefelsProblem witch Tabu Search");
				}else if(algorithm == 4) {
					ils = new ILS(100, 0.01, 100, -100, 100, 1, 100000, 0.1);	
					double[] evolutionQuality = ils.execute(new SchwefelsProblem());
					plotarGrafico(evolutionQuality, "SchwefelsProblem witch ILS");
				}
			}else if(benchmark == 3) {
				if(algorithm == 1) {
					hillClimbing = new HillClimbing(100, 0.01, 100, -100, 100, 1, 100000);
					double[] evolutionQuality = hillClimbing.execute(new RosenbrockFunction());
					plotarGrafico(evolutionQuality, "Shifted Rosenbrock’s Function witch Hill Climbing");
				}else if(algorithm == 2) {
					simulatedAnnealing = new SimulatedAnnealing(100, 0.1, 100, -100, 100, 1, 100000, 1000);
					double[] evolutionQuality = simulatedAnnealing.execute(new RosenbrockFunction());
					plotarGrafico(evolutionQuality, "Shifted Rosenbrock’s Function witch Simulated Annealing");
				}
				else if(algorithm == 3) {
					tabuSearch = new TabuSearch(100, 0.01, 100, -100, 100, 5, 10, 100000, 4);
					double[] evolutionQuality = tabuSearch.execute(new RosenbrockFunction());
					plotarGrafico(evolutionQuality, "Shifted Rosenbrock’s Function witch Tabu Search");
				}else if(algorithm == 4) {
					ils = new ILS(100, 0.01, 100, -100, 100, 1, 100000, 0.03);	
					double[] evolutionQuality = ils.execute(new RosenbrockFunction());
					plotarGrafico(evolutionQuality, "Shifted Rosenbrock’s Function witch ILS");
				}
			}else if(benchmark == 4) {
				if(algorithm == 1) {
					hillClimbing = new HillClimbing(100, 0.01, 100, -100, 100, 1, 100000);
					double[] evolutionQuality = hillClimbing.execute(new RastriginFunction());
					plotarGrafico(evolutionQuality, "Shifted Rastrigin’s Function witch Hill Climbing");
				}else if(algorithm == 2) {
					simulatedAnnealing = new SimulatedAnnealing(100, 0.01, 100, -100, 100, 1, 100000, 100);
					double[] evolutionQuality = simulatedAnnealing.execute(new RastriginFunction());
					plotarGrafico(evolutionQuality, "Shifted Rastrigin’s Function witch Simulated Annealing");
				}
				else if(algorithm == 3) {
					tabuSearch = new TabuSearch(100, 0.01, 100, -100, 100, 5, 100, 100000, 30);
					double[] evolutionQuality = tabuSearch.execute(new RastriginFunction());
					plotarGrafico(evolutionQuality, "Shifted Rastrigin’s Function witch Tabu Search");
				}else if(algorithm == 4) {
					ils = new ILS(100, 0.01, 100, -100, 100, 1, 100000, 0.03);	
					double[] evolutionQuality = ils.execute(new RastriginFunction());
					plotarGrafico(evolutionQuality, "Shifted Rastrigin’s Function witch ILS");
				}
			}
			
			
			
		} while (JOptionPane.showConfirmDialog(null, "Deseja Continuar?") == 0);
		
	}
	
	public static void plotarGrafico(double[] evolutionQuality, String algoritmo) throws FileNotFoundException, IOException {
		/*
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		
		FileWriter arq = new FileWriter("resultado.txt");
	    PrintWriter gravarArq = new PrintWriter(arq);
		
	    DecimalFormat df = new DecimalFormat("#,###.00");
	    
		for (int i = 0; i < evolutionQuality.length; i++) {
			ds.addValue(evolutionQuality[i], " ", i + "");
			gravarArq.printf(i + " - " + df.format(evolutionQuality[i]) + "\n");
			System.out.println(evolutionQuality[i]);
		}					
		
		arq.close();
		
		System.out.println("RESULTADO: " + evolutionQuality[evolutionQuality.length-1]);
		
		JFreeChart grafico = ChartFactory.createLineChart(algoritmo, "Iteração", "Valor", ds,
				PlotOrientation.VERTICAL, true, true, false);
		ChartUtilities.writeChartAsPNG(new FileOutputStream("grafico.png"), grafico, 1000, 500);

		ImageIcon imagem = new ImageIcon("grafico.png");
		JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imagem);	
		*/
		
		System.out.println("RESULTADO: " + evolutionQuality[evolutionQuality.length-1]);
		
	}

}
