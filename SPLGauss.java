import java.util.*;

/*TODO : Back Substitution*/

class SPLGauss {
	// class variable

	public static void printMatrix(double[][] arr) {
		/*For debugging purpose*/
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.printf("%.2f ", arr[i][j]);
			}
			System.out.println("");
		}

	}

	private static Matrix createEchelonForm(Matrix matrix) {
		/*Membuat echelon form dari augmented matrix yang menjadi masukan*/
		/*Kamus Lokal*/
		int i, j, k; // indexing dalam loop
		double [][] arr = matrix.getMatrix();
		int nBrs = matrix.getRow();
		int nKol = matrix.getColumn();
		double x; // faktor pembagi
		/*ALGORITMA : Membuat matrix echelon form*/
		// pengulangan 1 untuk setup koefisien
		for (i = 0; i < nBrs; i++) {
			System.out.println(i);

			// melakukan pengecekan terhadap pivot
			// dilakukan penyesuaian dan swapping row agar proses perhitungan lebih mudah
			double max = arr[i][i];
			int idxBrsMax = i;
			int a = i;  // a = row
			while (a < nBrs) {
				if (arr[a][i] > max) {
					max = arr[a][i];
					idxBrsMax = a;
				}
				a++; // pengecekan dilanjutin buat baris berikutnnya
				// hal ini membuat rapi untuk setiap pemrosesan
			}
			double[] temp = arr[i];

			arr[i] = arr[idxBrsMax];
			arr[idxBrsMax] = temp;
			printMatrix(arr);
			// pengulangan 2 agar koefisien pembagi dapat diterapkan ke seluruh baris lainnya
			for (j = i; j < nBrs; j++) {
				// kalo bukan kolom yang sedang diproses, maka kita cari faktor pembaginya
				if (j > i) {
					// kalo dia nol, maka kolom diskip
					if (arr[j][j] != 0 && (arr[i][i] != 0)) {
						x = arr[j][i] / arr[i][i]; // dicari faktor pembagi sesuai dengan baris yang sedang dibuat
						// implementasikan faktor pembagi ke seluruh elemen dalam kolom yang sama
						for (k = 0; k < nKol; k++) {
							arr[j][k] = arr[j][k] - x * arr[i][k];
						}
					}
				}
			}
			printMatrix(arr);
		}


		/*Dilakukan pengubahan ke bentuk echelon form*/
		for (int y = 0; y < nBrs; y++) {
			for (int z = 0; z < nKol; z++) {
				if (arr[y][y] != 0) {
					arr[y][z] = arr[y][z] / arr[y][y];
				}
			}
		}
		printMatrix(arr); // print Matrix

		Matrix echelonMatrix = new Matrix(arr);
		return echelonMatrix;
	}

	private static int solutionChecker(Matrix matrix) {
		/*Input : Matriks persamaan linear*/
		/*Output : -1 : Solusi banyak / infinite
					1 : Solusi unik
					0 : Tidak memiliki solusi
		*/
		int nBrs = matrix.getRow();
		int nKol = matrix.getColumn();

		Matrix echelon = createEchelonForm(matrix);

		boolean isInfinite = false;
		boolean isNoSolution = false;

		double[][] arrEchelon = echelon.getMatrix();

		for (int i = 0; i < nBrs; i++) {
			for (int j = 0; j < nBrs; j++) {
				if (arrEchelon[i][j] == Double.NaN) {
					isInfinite = true;
				}
				if ((arrEchelon[i][nKol - 1] != 0) && (arrEchelon[i][i] == 0)) {
					isNoSolution = true;
				}
			}
		}

		boolean allZero = true;
		for (int k = 0; k < nKol; k++) {
			if (arrEchelon[nBrs - 1][k] != 0) {
				allZero = false;
			}
		}
		if (allZero) {
			isInfinite = true;
		}

		if (isInfinite) {
			return -1;
		} else if (isNoSolution) {
			return 0;
		} else {
			return 1;
		}


	}

	public static double[] solveGaussUnique (Matrix matrix) {
		/* Return : array berisi kumpulan solusi untuk sistem persamaan dalam augmented matrix
		   Prekondisi : Array hanya memiliki solusi unik
		*/
		/*KAMUS LOKAL*/
		int option = solutionChecker(matrix);
		double[] placeholder = { -99999};	// mark
		Matrix echelon;
		int nBrs = matrix.getRow();
		int nKol = matrix.getColumn();

		if (option == 1) {
			echelon = createEchelonForm(matrix);
			double arr[][] = echelon.getMatrix();
			/*Dilakukan backsubstitution*/
			double sum;
			double[] solution = new double[nBrs];
			for (int c = nBrs - 1; c >= 0; c--) {
				sum = 0.0;
				for (int d = c + 1; d < nBrs; d++) {
					sum += arr[c][d] * solution[d];
				}
				solution[c] = (arr[c][arr[0].length - 1] - sum) / arr[c][c];
			}

			return solution;
		}
		return placeholder;
	}

	public static void outputResult(double[] solution) {
		/*
		I.S. : Menerima solusi yang terdefinisi, dengan catatan -99999 merupakan penanda bahwa solusi tidak unik (bisa infinite, bisa tidak ada solusi)
		F.S. : Mengeluarkan output / keterangan dari sistem persamaan linear
		*/
		if (solution[0] == -99999) {
			System.out.println("Solusi unik atau banyak solusi");
		} else {
			for (int i = 0; i < solution.length; i++) {
				System.out.printf("x%d = %.2f", solution.length - i, solution[i]);
				if (i + 1 != solution.length) {
					System.out.print(",");
				}
			}
			System.out.println("");
		}
	}

	// driver
	public static void main(String[] args) {
		double[] arrSolution = new double[4];

		double arr[][] = {{0,1,0,0,1,0,2},{0,0,0,1,1,0,-1},{0,1,0,0,0,1,1}};
		double arrs[][] = {{1,-1,0,0,1,3},{1,1,0,-3,0,6},{2,-1,0,1,-1,5},{-1,2,0,-2,-1,-1},{0,0,0,0,0,0}};
		Matrix matrix = new Matrix(arrs);
		matrix.show();
		System.out.println("\n===========================================");
		arrSolution = solveGaussUnique(matrix);
		matrix.show();
		outputResult(arrSolution);
	}
}
