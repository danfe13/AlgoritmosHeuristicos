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

import br.ufs.algorithm.BuscaTabu;
import br.ufs.algorithm.HillClimbing;
import br.ufs.algorithm.SimulatedAnnealing;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		do {
			
			int benchmark  = Integer.parseInt(JOptionPane.showInputDialog("Benchmark: \n1 - Sphere Function\n2 - SchwefelsProblem"));
			int algorithm = Integer.parseInt(JOptionPane.showInputDialog("Algotithm: \n1 - Hill Climbing\n2 - Simulated Annealing\n3 - Tabu Search"));
			
			if(benchmark == 1) {
				if(algorithm == 1) {
					HillClimbing climbing = new HillClimbing(100, 0.1, -100, 100,5, 100000);
					double[] s = climbing.execute(1);
					plotarGrafico(s, "Sphere Function with Hill Climbing");
				}else if(algorithm == 2) {
					SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(100, 0.1, 100, -100, 100, 100);;
					double[] s = simulatedAnnealing.execute(1);
					plotarGrafico(s, "Sphere Function with Simulated Annealing");
				}else if(algorithm == 3) {
					BuscaTabu tabu = new BuscaTabu(100,0.1,-100,100,5,100,10000);
					double[] s = tabu.execute(1);
					plotarGrafico(s, "Sphere Function with Tabu Search");
				}
				
			}else if(benchmark == 2) {
				if(algorithm == 1) {
					
				}else if(algorithm == 2) {
					
				}
			}
		} while (JOptionPane.showConfirmDialog(null, "Deseja Continuar?") == 0);
		
	}
	
	public static void plotarGrafico(double[] s, String algoritmo) throws FileNotFoundException, IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		for (int i = 0; i < s.length; i++) {
			ds.addValue(s[i], " ", i + "");
			//System.out.println(s[i]);
		}					
		
		JFreeChart grafico = ChartFactory.createLineChart(algoritmo, "Iteração", "Valor", ds,
				PlotOrientation.VERTICAL, true, true, false);
		ChartUtilities.writeChartAsPNG(new FileOutputStream("grafico.png"), grafico, 1000, 500);

		ImageIcon imagem = new ImageIcon("grafico.png");
		JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imagem);	
		
	}

}
