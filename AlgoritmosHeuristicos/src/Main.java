import br.ufs.algorithm.HillClimbing;
import br.ufs.algorithm.SimulatedAnnealing;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*HillClimbing climbing = new HillClimbing(100, 0.01, -100, 100,1, 100000);
		imprimir(climbing.execute(1));
		
		HillClimbing climbing2 = new HillClimbing(100, 0.01, -100, 100,5, 100000);
		imprimir(climbing2.execute(1));
		
		HillClimbing climbing3 = new HillClimbing(100, 0.01, -100, 100,10, 100000);
		imprimir(climbing3.execute(1));*/
		
		
		SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(100, 0.01,-100, 100,1,100,100000);
		
		SimulatedAnnealing simulatedAnnealing2 = new SimulatedAnnealing(100, 0.01,-100, 100,5,100,100000);
		
		SimulatedAnnealing simulatedAnnealing3 = new SimulatedAnnealing(100, 0.01,-100, 100,10,100,100000);
		imprimir(simulatedAnnealing.execute(1));
		imprimir(simulatedAnnealing2.execute(1));
		imprimir(simulatedAnnealing3.execute(1));
	}
	
	public static void imprimir(double[] s) {
		System.out.println(s[s.length-1]);
	}

}
