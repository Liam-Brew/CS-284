
/**
 * Class representing time complexities of different methods.
 * @author Liam Brew
 * @section CS 284 A
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */

public class Complexity {

	/**
	 * Example method with time complexity of O(n).
	 * 
	 * @param n Dictates number of times the body is run.
	 */
	public void method0(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}

	/**
	 * Method with time complexity of O(n^2). Accomplished through nesting 2 for
	 * loops with independent and ++ incrementation.
	 * 
	 * @param n Dictates number of times the body is run.
	 */
	public void method1(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}

	/**
	 * Method with time complexity of O(log(n)). Accomplished through incrementation
	 * by *=2 in a single for loop.
	 * 
	 * @param n Dictates number of times the body is run.
	 */
	public void method2(int n) {
		int counter = 0;
		for (int i = 1; i < n; i *= 2) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}

	/**
	 * Method with time complexity of O(nlog(n)). Accomplished through nesting a
	 * single for loop of ++ incrementation within a for loop of *=2 incrementation.
	 * Both for loops have independent variables.
	 * 
	 * @param n Dictates number of times the body is run.
	 */
	public void method3(int n) {
		int counter = 0;
		for (int i = 1; i < n; i *= 2) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}

	/**
	 * Method with time complexity of O(n^3). Accomplished through nesting 3 for
	 * loops with independent variables and ++ incrementation.
	 * 
	 * @param n Dictates number of times the body is run.
	 */
	public void method4(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					System.out.println("Operation " + counter);
					counter++;
				}
			}
		}
	}

	/**
	 * Method with time complexity of O(log(log(n))). Accomplished through a single
	 * for loop with i^2 incrementation.
	 * 
	 * @param n Dictates number of times the body is run.
	 */
	public void method5(int n) {
		int counter = 0;
		for (int i = 0; i < n; i *= 2) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}

	/**
	 * Method with time complexity of O(2^n). Accomplished through two recursive
	 * calls of the method (similar to Fibonacci sequence calculation). 
	 * 
	 * @param n Dictates number of times the body is run.
	 * @return Recursive calls n-1 and n-2.
	 */
	public int method6(int n) {
		if (n <= 0) {
			// Base case of recursion -> method call ends here.
			return n;
		} else {
			return method6(n - 2) + method6(n - 1);
		}
	}

}
