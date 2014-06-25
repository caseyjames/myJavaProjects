package assignment1;

/**
 * This Class represents 2D mathematical matrices
 * 
 * @author Casey Nordgran
 * @version 1.0
 */
public class Matrix {
	int numRows;		// field for number of rows in this matrix
	int numColumns;		// field for number of columns in this matrix
	int data[][];		// 2-dimensional array that represents the 2D matrix

	/**
	 * The sole constructor for this class
	 * 
	 * @param d
	 *            2D array assigns the elements to the rows & columns of this matrix.
	 */
	public Matrix(int d[][]) {
		numRows = d.length; 	// d.length is the number of 1D arrays in the 2D array

		if (numRows == 0)
			numColumns = 0;
		else
			numColumns = d[0].length; 	// d[0] is the first 1D array

		data = new int[numRows][numColumns]; 	// create a new matrix to hold the data

		// copy the data over
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				data[i][j] = d[i][j];
	}

	@Override
	// instruct the compiler of intent to override superclass (Object) method
	public boolean equals(Object o) {
		// make sure the Object we're comparing to is a Matrix
		if (!(o instanceof Matrix))
			return false;

		// if the above was not true, we know it's safe to treat 'o' as a Matrix
		Matrix m = (Matrix) o;

		// if compared matrices have a different number of rows or columns return false.
		if ((m.numRows != this.numRows) || (m.numColumns != this.numColumns))
			return false;

		// finally, test equality element by element
		for (int i = 0; i < m.numRows; i++)
			for (int j = 0; j < m.numColumns; j++)
				if (!(m.data[i][j] == this.data[i][j]))
					return false;

		// return true if all above test pass
		return true;
	}

	@Override
	// instruct the compiler of intent to override superclass (Object) method
	public String toString() {
		String out = new String("");  	// string object to concatenate and return

		// inner loop adds spaced row elements to same line of string, outer loop adds newline
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++)
				out += (this.data[i][j] + " ");
			out += "\n";
		}

		// returns out string of concatenation of formatted elements of this matrix
		return out;
	}

	/**
	 * Multiplies matrices if they are compatible. Checks for matrix multiplication compatibility.
	 * 
	 * @param m
	 *            The right-hand-side matrix to be multiplied.
	 * @return Resultant matrix of the two multiplied matrices if they are compatible for
	 *         multiplication, else returns null.
	 */
	public Matrix times(Matrix m) {

		// test for matrix multiplication compatibility.
		if (this.numColumns != m.numRows) {
			System.out.println("Error: matrix arguments are not compatible for multiplication!");
			return null;
		}

		// new Matrix object with correct number of rows and columns of resultant matrix
		Matrix productMatrix = new Matrix(new int[this.numRows][m.numColumns]);

		// to hold element product values before assignment to productMatrix
		int[] elementTemp = new int[this.numRows + m.numColumns];

		// holds value of lhs # of columns and rhs # of rows.
		int columnRows = this.numColumns;

		// local variable for counting to provide correct indexing for elementTemp.
		int countIndex = 0;

		// nested for loops for matrix multiplication
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < m.numColumns; j++) {
				for (int k = 0; k < columnRows; k++) {
					elementTemp[countIndex] += (this.data[i][k] * m.data[k][j]);
				}
				countIndex++;
			}
		}

		// reset countIndex to be used from 0 again
		countIndex = 0;

		// assigns resultant values from elementTemp array to productMatrix
		for (int i = 0; i < productMatrix.numRows; i++)
			for (int j = 0; j < productMatrix.numColumns; j++) {
				productMatrix.data[i][j] = elementTemp[countIndex];
				countIndex++;
			}

		// after loops assign values to productMatrix elements, return productMatrix.
		return productMatrix;
	}

	/**
	 * Performs addition of this Matrix and Matrix m, after checking that they are compatible for
	 * addition. If they are not compatible, returns null reference.
	 * 
	 * @param m
	 *            Matrix object on the right hand side, to be added to the left hand side if they
	 *            share the same number of rows and columns
	 * @return new Matrix object that is the sum of this matrix and matrix m.
	 */
	public Matrix plus(Matrix m) {
		// check for addition compatibility. Print an error and return null if not compatible.
		if (this.numRows != m.numRows || this.numColumns != m.numColumns) {
			System.out.println("Error: matrix arguments are not compatible for addition!");
			return null;
		}

		// to hold and return sum of this Matrix and m, all have same number of rows and columns
		Matrix sumMatrix = new Matrix(new int[this.numRows][this.numColumns]);

		// add each element of this Matrix and m. Assign the result to same element of sumMatrix
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++)
				sumMatrix.data[i][j] = this.data[i][j] + m.data[i][j];
		}

		// with new results assigned to sumMatrix, return sumMatrix
		return sumMatrix;
	}
}
