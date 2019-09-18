import java.util.*;

/*TODO : Buat suatu fungsi buat masukin sistem persamaan menjadi augmented matrix*/

public class Gauss {
	// class variable

	private Matrix coeficientMatrix; 
	private double[] yVector;
	private double[] constants;
	private double[] solution;
	private int numEq;

	Scanner scanner = new Scanner(System.in);

	public Gauss (Matrix matrix){
		/*Input : Augmented Matrix*/
		double[][] arrX = new double[matrix.getRow()][matrix.getColumn() - 1];
		double[] arrY = new double[matrix.getRow()];

		numEq = matrix.getRow();
		for (int i = 0; i < matrix.getRow(); i++){
			for (int j = 0; j < matrix.getColumn(); j++){
				if (j != (matrix.getColumn() - 1)) {
					arrX[i][j] = matrix.getMatrix()[i][j];
				} else {
					arrY[i] = matrix.getMatrix()[i][j];
				}
			}
		}

		Matrix newM = new Matrix(arrX);
		coeficientMatrix = newM;
		yVector = arrY;
		constants = new double [numEq];
		solution = new double[numEq];
	}

	public void cetakSolusi() {
		// buat fungsi keluaran hasil solusi
	}

	private void solve() {
		for (int k = 0; k < numEq; k++) {

			/*finding pivot row*/
			int max = k;
			for (int i = k+1; i < numEq; i++ ) {
				if (Math.abs(coeficientMatrix.getMatrix()[i][k]) > coeficientMatrix.getMatrix()[max][k]) {
					max = i;
				}
			}
			/*swapping the row based on the pivot column*/
			coeficientMatrix.swap_row(k,max);
			/*swapping answer*/
			double t = yVector[k];
			yVector[k] = yVector[max];
			yVector[max] = t;

			/*pivot between Coefficient matrix and y*/
			for (int i = k + 1; i < coeficientMatrix.getRow(); i++){
				double factor = coeficientMatrix.getMatrix()[i][k] / coeficientMatrix.getMatrix()[k][k];
				yVector[i] -= factor * yVector[k];
				for(int j = k; j < coeficientMatrix.getRow(); j++){
					coeficientMatrix.getMatrix()[i][j] -= factor * coeficientMatrix.getMatrix()[k][j];
				}
			}

		}
		/*Created an row echelon form*/

		/*TODO : Buat fungsi validasi */
		/*Lakukan pengecekan terhadap ada tidaknya solusi*/

		/*Back Substitution*/
		int n = yVector.length;
		double[] solution = new double[n];
		for (int i = n - 1; i >= 0; i--){
			double sum = 0.0;
			for (int j = i + 1; j < n; j++) {
				sum += coeficientMatrix.getMatrix()[i][j] * solution[j];
			}
			solution[i] = (yVector[i] - sum)/ coeficientMatrix.getMatrix()[i][i];
		}	
	}		
}
