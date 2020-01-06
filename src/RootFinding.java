import java.util.function.Function;

public class RootFinding {
	
	
	public static void bracketMethod() {
		System.out.println("--------------Bracket Method");
		double a = 0;
		double b = 1;
		double c = 0;
		Function<Double, Double> f = x -> Math.pow(x, 3) - 4 * x + 2;

		for(int i = 0; i < 10; i++) {
			double fa = f.apply(a);
			double fb = f.apply(b);
			c = (a + b) / 2;
			double fc = f.apply(c);

			System.out.println("a: " + a + " b: " + b + " fa: " + fa + " fb: " + fb);
			
			if( fb * fc < 0) {
				a = c;
			} else {
				b = c;
			}
		}
		
		System.out.println("Approximate root: " + c + " f(x): " + f.apply(c));
	}
	
	public static void fixedPoint() {
		System.out.println("--------------Fixed Point Method");
		double a = 0;
		double b = 1;
		double c = 0.75;
		Function<Double, Double> f = x -> Math.pow(x, 3) + 5 * x - 5;
		Function<Double, Double> g = x -> (5 - Math.pow(x, 3)) / 5;
		
		System.out.println("fa: " + f.apply(a));
		System.out.println("fb: " + f.apply(b));
		
		for(int i = 1; i < 20; i++) {
			System.out.println("x" + i + ": " + g.apply(c) + " diff: " + (g.apply(c) - c));
			c = g.apply(c);
		}
		
		System.out.println("Approximate root: " + c + " f(x): " + f.apply(c));
	}
	
	public static void main(String[] args) {
		bracketMethod();
		fixedPoint();
	}
}
