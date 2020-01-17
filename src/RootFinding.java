import java.math.BigDecimal;
import java.util.function.Function;

public class RootFinding {


	public static void bracketMethod(double a, double b, Function<Double, Double> f) {
		System.out.println("--------------Bracket Method [" + a + " , " + b + "]");

		if( !(f.apply(a) * f.apply(b) < 0)) {
			System.exit(1);
		}

		double c = 0;

		for(int i = 0; i < 10; i++) {
			double fa = f.apply(a), fb = f.apply(b);
			c = (a + b) / 2;
			double fc = f.apply(c);

			System.out.println("a: " + a + " b: " + b + " fa: " + fa + " fb: " + fb);

			if( fb * fc < 0) {
				a = c;
			} else if (fb == 0 || fc == 0){
				break;
			} else {
				b = c;
			}
		}

		System.out.println("Approximate root: " + c + " f(x): " + f.apply(c));
	}

	public static void fixedPoint(double a, double b, double c, Function<Double, Double> f, Function<Double, Double> g, int precision) {
		System.out.println("--------------Fixed Point Method [" + a + " , " + b + "] with x = " + c);

		System.out.println("fa: " + f.apply(a));
		System.out.println("fb: " + f.apply(b));

		if( !(f.apply(a) * f.apply(b) < 0)) {
			System.exit(1);
		}

		System.out.println("x_0: " + c);
		for(int i = 1; i < 30; i++) {
			System.out.println("x" + i + ": " + roundOff(g.apply(c), precision) + " diff: " + (g.apply(c) - c) + " f(a): " + f.apply(c));
			c = g.apply(c);
		}

		System.out.println("Approximate root: " + c + " f(x): " + f.apply(c));
	}
	

	public static void fixedPointErr(double a, double b, double c, double root, Function<Double, Double> f, Function<Double, Double> g) {
		System.out.println("--------------Fixed Point Method [" + a + " , " + b + "] with x = " + c);

		System.out.println("fa: " + f.apply(a));
		System.out.println("fb: " + f.apply(b));

		if( !(f.apply(a) * f.apply(b) < 0)) {
			System.exit(1);
		}

		System.out.println("x_0: " + c + " error: " + Math.abs(c - root));
		for(int i = 1; i < 30; i++) {
			System.out.println("x" + i + ": " + g.apply(c) + " diff: " + (g.apply(c) - c) + " f(a): " + f.apply(c) 
			+ " error: " + Math.abs(g.apply(c) - root));
			c = g.apply(c);
		}

		System.out.println("Approximate root: " + c + " f(x): " + f.apply(c));
	}
	
	public static String roundOff(double round, int precision) {
		double b = round;
		for(int i = 0; i < precision; i++) {
			b *= 10.0;
		}
		
		b = Math.round(b);
		
		for(int i = 0; i < precision; i++) {
			b /= 10.0;
		}
		
		return Double.toString(b);
	}

	public static void main(String[] args) {
		bracketMethod(0, 1, x -> Math.pow(x, 3) - 4 * x + 2);
		fixedPoint(0, 1, 0.75, x -> Math.pow(x, 3) + 5 * x - 5,
				x -> (5 - Math.pow(x, 3)) / 5, 5);

		fixedPoint(1, Math.PI / 2, 1.4, x -> Math.sin(x) - x / 1.4,
				x -> 1.4 * Math.sin(x), 5);
		fixedPoint(0, 0.25, 0, x -> Math.pow(x, 4) - x + 0.2,
				x -> Math.pow(x, 4) + 0.2, 5);
		fixedPoint(0.1, 1, 1, x -> Math.sin(x) - Math.pow(Math.E, -0.5 * x),
				x -> Math.asin(Math.pow(Math.E, -0.5 * x)), 5);

		fixedPoint(0.1, 1, 1, x -> Math.cos(x) - x, x -> Math.cos(x), 5);
		fixedPoint(0.98, 1.02, 0.99, x -> Math.pow(Math.E, -2 * x) * (x - 1),
				x -> Math.pow(Math.E, -2 * x) * (x - 1) + x , 5);
		fixedPoint(0.98, 1.02, 1.01, x -> Math.pow(Math.E, -2 * x) * (x - 1),
				x -> Math.pow(Math.E, -2 * x) * (x - 1) + x , 5);

		// using newtons formula for g(x) = x - f(x)/f`(x)
		fixedPoint(0.1, 1, 1, x -> Math.cos(x) - x, 
				x -> x + (Math.cos(x) - x) / (Math.sin(x) + 1.0), 5);
		fixedPointErr(0.1, 2, 1.7, Math.sqrt(3), x -> Math.pow(x,2) - 3, 
				x -> x - (Math.pow(x, 2) - 3) / (2 * x));
		
		// a1
		fixedPoint(0, 1, 0.75, x->Math.pow(x, 3) + 8 * x - 7, x->(7 - Math.pow(x, 3))/8, 5);
		fixedPoint(0, 1, 1, x->Math.pow(x, 3) - Math.cos(x), x->(2 * Math.pow(x, 3) + x * Math.sin(x) + Math.cos(x))/
				(3 * Math.pow(x, 2) + Math.sin(x)), 6);
		
		
		// owl/mouse
		double mouse = 1200;
		double owl = 100;
		
		for(int i = 0; i < 100; i++) {
			System.out.println("m_" + i + ": " + mouse + " o_" + i + ": " + owl);
			double newMouse = 1.3 * mouse - 0.002 * owl * mouse;
			double newOwl = 0.6 * owl + 0.0004 * owl * mouse;
			
			mouse = Math.max(0, newMouse);
			owl = Math.max(0, newOwl);
		}
	}
}
