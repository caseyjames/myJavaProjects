package assignment1;

/**
 * This class is Tester for the Matrix class. It's only purpose is to create matrix objects and test
 * the methods Matrix objects as well as the Matrix class stability it errors
 * 
 * 
 * @author Casey Nordgran
 * @version 1.0
 */
public class MatrixTester {
	public static void main(String[] args) {

		// declaration of multiple matrix objects for testing.
		Matrix M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });

		// matrix with same amount of columns as M1 has rows.
		Matrix M2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });

		// same as M1 to test equality
		Matrix M3 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });

		// matrix with last element changed of M1, to test equals method.
		Matrix M4 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 7 } });

		// this is the known correct result of multiplying M1 by M2
		Matrix referenceMultiply = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });

/*
 * testing the equals method
 * ----------------------------------------------------------------------------------------------
 */
		System.out.println("equals Testing: begin\n--------------");
		boolean testing1 = M3.equals(M1); // should be 'true'
		boolean testing2 = M3.equals(M2); // should be 'false'
		boolean testing3 = M3.equals(M4); // should be 'false'
		int a = 1; // integer created to try the first test in the equals method
		boolean testing4 = M3.equals(a); // should be 'false'

		System.out.println("M1 = M3: " + testing1);
		System.out.println("M2 = M3: " + testing2);
		System.out.println("M3 = M4: " + testing3);
		System.out.println("M3 = a: " + testing4 + "\n");
		System.out.println("equals Testing: end\n--------------");
		System.out.println("\n\n\n\n");
/* ---------------------------------------------------------------------------------------------- */

/*
 * testing the toString method
 * ----------------------------------------------------------------------------------------------
 */
		// both calls toString print the returned string value of M1 first, then M2.
		System.out.println("toString Testing: begin\n--------------");
		System.out.println("M1 equals:\n" + M1.toString());
		System.out.println("M2 equals:\n" + M2.toString());
		System.out.println("toString Testing: end\n--------------\n\n");
/* ---------------------------------------------------------------------------------------------- */

/*
 * testing the times method
 * ----------------------------------------------------------------------------------------------
 */
		System.out.println("\n\nMatrix times method Testing: begin\n---------------------");
		// first check that the return matrix is not null
		if (M1.times(M2) != null) {

			// matrix object to recieve returned multiplied matrix from times() call
			Matrix referenceMultiply2 = M1.times(M2);

			// M1*M2 to print line should equal { 13, 12 }, { 29, 26 }
			System.out.println("M1*M2 equals:\n");
			System.out.println(referenceMultiply2.toString());

			// boolean variable for testing that M1*M2 does equal referenceMultiply in main
			boolean test = (M1.times(M2)).equals(referenceMultiply);

			// print results of test
			System.out.println("M1*M2 is equal to the \nreferenceMultiply Matrix?:   " + test
					+ "\n");

			// try passing incompatible matrix to through error
			System.out.println("passing non-compatible Matrix object test:\n");
			M1.times(M4);
		}
		System.out.println("\nMatrix times method Testing: end\n---------------------\n\n");

/* ---------------------------------------------------------------------------------------------- */

/*
 * testing the plus method
 * ----------------------------------------------------------------------------------------------
 */
		System.out.println("\n\nMatrix plus method Testing: begin\n---------------------");
		// first check that the return matrix is not null
		if (M1.plus(M1) != null) {
			// create new Matrix object to hold the sum of M1 + M1, or M1 + M4.
			Matrix plusMatrix1 = M1.plus(M1);
			// print results, elements of plusMatrix1 should be 2*M1
			System.out.println("Matrix M1 plus itself equals:\n");
			System.out.println(plusMatrix1.toString());

			// now add this new result to M1 again
			Matrix plusMatrix2 = M1.plus(plusMatrix1);
			// print results, elements of plusMatrix1 should be 3*M1
			System.out.println("Matrix M1 plus ( 2 * M1 ) equals:\n");
			System.out.println(plusMatrix2.toString());

			// now again to test passing the argument as a non compatible matrix for addition
			System.out.println("passing non-compatible Matrix object test:\n");
			M1.plus(M2);
		}
		System.out.println("\nMatrix plus method Testing: end\n---------------------\n\n");
/* ---------------------------------------------------------------------------------------------- */

	}
}
