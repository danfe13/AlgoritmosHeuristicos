import br.ufs.benchmark.SphereFunctionHillClimbing;
import br.ufs.hillclimbing.SphereFunction;

public class Main {

	public static void main(String[] args) {
		SphereFunctionHillClimbing sphereFunction = new SphereFunctionHillClimbing();
		sphereFunction.execute(1000, 5);

	}

}
