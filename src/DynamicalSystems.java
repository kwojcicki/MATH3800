import java.util.function.Function;

public class DynamicalSystems {

	public static void system(double a0, double equilibria, Function<Double, Double> f,
			Function<Integer, Double> sol) {
		System.out.println("-----------------------");
		double an = a0;
		double eq = equilibria;
		for(int i = 0; i < 20; i++) {
			System.out.println("a_" + i + ": " + an + " expected: " + sol.apply(i) + " diff: " + (an - sol.apply(i)));
			System.out.println("eq: " + eq);
			an = f.apply(an);
			eq = f.apply(eq);
		}
	}
	
	public static void main(String[] args) {
		// a1
		system(10, 0, x->(2.0/5.0) * x,
				x->Math.pow(2.0/5.0, x) * 10.0);
		
		system(20, 250, x->(3.0/5.0) * x + 100,
				x->Math.pow(3.0/5.0, x) * (20 - 250) + 250);
		
		system(25,  300, x->(-2.0/3.0) * x + 500, x->Math.pow(-2.0/3.0, x) * (25 - 300) + 300);
		
		system(0, 15, x->3.0 * x - 30.0, x->Math.pow(3.0, x) * (0 - 15) + 15);
	}

}
