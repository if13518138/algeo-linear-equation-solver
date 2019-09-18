import java.util.*;

/*TODO : Back Substitution*/

class SPLGauss {
	// class variable

	public static void printMatrix(double[][] arr){
		for (int i = 0; i < arr.length;i++){
			for (int j = 0; j < arr[0].length; j++){
				System.out.printf("%.2f ", arr[i][j]);
			}
			System.out.println("");
		}

	}
	public static void solveGaussUnique (Matrix matrix) {
		/* Return : array berisi kumpulan solusi untuk sistem persamaan dalam augmented matrix
		   Prekondisi : Array hanya memiliki solusi unik
		*/

		/*Kamus Lokal*/
		int i,j,k; // indexing dalam loop
		double [][] arr = matrix.getMatrix();
		int nBrs = matrix.getRow();
		int nKol = matrix.getColumn();
		double x; // faktor pembagi
		/*ALGORITMA : Membuat matrix echelon form*/
		// pengulangan 1 untuk setup koefisien
		for (i = 0; i < nBrs; i++){
			System.out.println(i);

			// melakukan pengecekan terhadap pivot
			// dilakukan penyesuaian dan swapping row agar proses perhitungan lebih mudah
			double max = arr[i][i];
			int idxBrsMax = i;
			int a = i;  // a = row
			while (a < nBrs){
				if (arr[a][i] > max){
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
			for (j = i; j < nBrs; j++){
					// kalo bukan kolom yang sedang diproses, maka kita cari faktor pembaginya
					if (j > i) {
						// kalo dia nol, maka kolom diskip
						if (arr[j][j] != 0){
							x = arr[j][i] / arr[i][i]; // dicari faktor pembagi sesuai dengan baris yang sedang dibuat 
							// implementasikan faktor pembagi ke seluruh elemen dalam kolom yang sama
							for (k = 0; k < nKol; k++){
								arr[j][k] = arr[j][k] - x * arr[i][k];
							}
						}
					}
				}
			printMatrix(arr);
		}


		// ubah ke echelon form
		for (int y = 0; y < nBrs; y++){
			for (int z = 0;z < nKol;z++){
				arr[y][z] = arr[y][z] / arr[y][y];
			}
		}
				printMatrix(arr); // print Matrix

		/*Dilakukan backsubstitution*/
		

	}

		// driver
		public static void main(String[] args) {
			double arr[][] = {{0,1,2,3,4}, {0,0,5,6,10}, {0,6,3,2,1},{0,0,0,9,8}};
			Matrix matrix = new Matrix(arr);
			matrix.show();
			System.out.println("\n===========================================");
			solveGaussUnique(matrix);
			matrix.show();
		}
	}
