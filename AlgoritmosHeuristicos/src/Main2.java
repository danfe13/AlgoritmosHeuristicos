import javax.swing.JOptionPane;

import br.ufs.algorithm.HillClimbing;
import br.ufs.algorithm.ILS;
import br.ufs.algorithm.SimulatedAnnealing;
import br.ufs.algorithm.TabuSearch;
import br.ufs.benchmark.RastriginFunction;
import br.ufs.benchmark.RosenbrockFunction;
import br.ufs.benchmark.SchwefelsProblem;
import br.ufs.benchmark.SphereFunction;

public class Main2 {

	public static void main(String[] args) {

		int benchmark = 1;
		int algorithm = 2;

		for (int i = 0; i < 20; i++) {
		
			HillClimbing hillClimbing;
			SimulatedAnnealing simulatedAnnealing;
			TabuSearch tabuSearch;
			ILS ils;
			
			double[] evolutionQuality = null;
			
			if (benchmark == 1) {
				if (algorithm == 1) {
					hillClimbing = new HillClimbing(100, 0.01, 100, -100, 100, 1, 100000);
					evolutionQuality = hillClimbing.execute(new SphereFunction());
	
				} else if (algorithm == 2) {
					simulatedAnnealing = new SimulatedAnnealing(100, 0.01, 100, -100, 100, 1, 100000, 1000);
					evolutionQuality = simulatedAnnealing.execute(new SphereFunction());
	
				} else if (algorithm == 3) {
					//tabuSearch = new TabuSearch(100, 0.01, 100, -100, 100, 1, 10, 10000, 10);
					//tabuSearch = new TabuSearch(100, 0.2, 100, -100, 100, 1, 10, 1000, 100);
					tabuSearch = new TabuSearch(100, 0.01, 100, -100, 100, 1, 1, 100000, 1);
					evolutionQuality = tabuSearch.execute(new SphereFunction());
	
				} else if (algorithm == 4) {
					ils = new ILS(100, 0.01, 100, -100, 100, 1, 100000, 0.01);
					evolutionQuality = ils.execute(new SphereFunction());
	
				}
			} else if (benchmark == 2) {
				if (algorithm == 1) {
					hillClimbing = new HillClimbing(100, 0.1, 100, -100, 100, 5, 100000);
					evolutionQuality = hillClimbing.execute(new SchwefelsProblem());
	
				} else if (algorithm == 2) {
					simulatedAnnealing = new SimulatedAnnealing(100, 0.1, 100, -100, 100, 4, 100000, 1000);
					evolutionQuality = simulatedAnnealing.execute(new SchwefelsProblem());
	
				} else if (algorithm == 3) {
					tabuSearch = new TabuSearch(100, 0.01, 100, -100, 100, 1, 10, 10000, 10);
					evolutionQuality = tabuSearch.execute(new SchwefelsProblem());
	
				} else if (algorithm == 4) {
					ils = new ILS(100, 0.01, 100, -100, 100, 1, 100000, 0.1);
					evolutionQuality = ils.execute(new SchwefelsProblem());
	
				}
			} else if (benchmark == 3) {
				if (algorithm == 1) {
					hillClimbing = new HillClimbing(100, 0.01, 100, -100, 100, 1, 100000);
					evolutionQuality = hillClimbing.execute(new RosenbrockFunction());
	
				} else if (algorithm == 2) {
					simulatedAnnealing = new SimulatedAnnealing(100, 0.01, 100, -100, 100, 1, 100000, 2000);
					evolutionQuality = simulatedAnnealing.execute(new RosenbrockFunction());
	
				} else if (algorithm == 3) {
					tabuSearch = new TabuSearch(100, 0.01, 100, -100, 100, 1, 10, 10000, 10);
					evolutionQuality = tabuSearch.execute(new RosenbrockFunction());
	
				} else if (algorithm == 4) {
					ils = new ILS(100, 0.01, 100, -100, 100, 1, 100000, 0.03);
					evolutionQuality = ils.execute(new RosenbrockFunction());
	
				}
			} else if (benchmark == 4) {
				if (algorithm == 1) {
					hillClimbing = new HillClimbing(100, 0.01, 100, -100, 100, 1, 100000);
					evolutionQuality = hillClimbing.execute(new RastriginFunction());
	
				} else if (algorithm == 2) {
					simulatedAnnealing = new SimulatedAnnealing(100, 0.01, 100, -100, 100, 1, 100000, 100);
					evolutionQuality = simulatedAnnealing.execute(new RastriginFunction());
	
				} else if (algorithm == 3) {
					tabuSearch = new TabuSearch(100, 0.01, 100, -100, 100, 1, 10, 10000, 10);
					evolutionQuality = tabuSearch.execute(new RastriginFunction());
	
				} else if (algorithm == 4) {
					ils = new ILS(100, 0.01, 100, -100, 100, 1, 100000, 0.03);
					evolutionQuality = ils.execute(new RastriginFunction());
	
				}
			}
			
			System.out.println((evolutionQuality[evolutionQuality.length-1] + "").replace(".", ","));
		
		}

	}

}
