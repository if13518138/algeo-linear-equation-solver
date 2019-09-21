import java.util.Scanner;

// import java.util.Scanner;
// import java.io.FileReader;
// import java.util.*;

public class Matrix {

	private double matrix[][]; // matrix container with M-N size
	private int M; // row size
	private int N; // columns size

	/*** Konstruktor ***/
	public Matrix () {
		double[][] p = new double[1][1];
		this.M = 1;
		this.N = 1;
		this.matrix = p;
	}
	public Matrix (int m, int n) {
		double[][] p = new double[m][n];
		this.M = m;
		this.N = n;
		this.matrix = p;
	}

	public Matrix (double[][] data) {
		int m = data.length;
		int n = data[0].length;

		this.matrix = new double[m][n];

		// melakukan pengkopian data dari data masukan ke matrix
		for (int i = 0; i < m; i++){
			for (int j = 0; j < n; j++){
				this.matrix[i][j] = data[i][j];
			}
		}
		this.M = m;
		this.N = n;
	}

	/*** Getter ***/
	public int getRow(){
		return this.M;
	}

	public int getColumn(){
		return this.N;
	}

	public double[] getRowElmt(int i){
		double[] arr = new double[matrix.getColumn()];
		for (int j = 0; j < matrix.getRow(); i++){
			arr[j] = matrix.getMatrix()[i][j];
		}

		return arr;
	}

	public double[][] getMatrix(){
		return this.matrix;
	}

	/*** Setter ***/
	public void setRow(int m){
		this.M = m;
	}

	public void setColumn(int n){
		this.N = n;
	}
	
	public void setMatrix(double[][] arr){
		this.matrix = arr;
	}

	public void setElmtMatrix(double x, int i, int j){
		Matrix newMatrix = new Matrix(matrix);
		newMatrix.getMatrix()[i][j] = x;
		this.matrix = newMatrix.getMatrix(); 
	}
	
	public Boolean isSquare() {
		return (this.M == this.N);
	}


	/*Fungsi swapping pada matrix*/
	public void swap_row (int i, int j){
		/*
		I.S. :  Matriks terdefinisi
		F.S. : Matriks baris i telah bertukar tempat dengan matriks baris j

		*/
		/*Kamus Lokal*/
		int row;
		double arr[][];

		/*Inisialisasi*/
		row = M;
		arr = matrix;

		/*Algoritma*/
		for (int k = 0;k<=row; k++){
			double temp = arr[i][k];
			arr[i][k] = arr[j][k];
			arr[j][k] = temp;
		}
		matrix = arr;
	}

	// print matrix to standard output
    public void show() {
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) 
                System.out.printf("%9.4f ", matrix[i][j]);
            System.out.println();
        }
	}

	// input matrix dari stdin
	public static void input(Matrix M) {
	/* I.S. Matrix M sembarang */
	/* F.S. terbentuk matriks M terdefinisi */
		int row, col;
		Scanner scan = new Scanner(System.in);
		System.out.println("Masukkan jumlah baris:");
		row = scan.nextInt();
		System.out.println("Masukkan jumlah kolom:");
		col = scan.nextInt();
		double[][] matriks = new double[row][col];
		System.out.println("Masukkan elemen matriks:");
		for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) 
                matriks[i][j] = scan.nextDouble();
		}
		M.setMatrix(matriks);
		M.setRow(row);
		M.setColumn(col);
	}

	public static Matrix delBrsMatrix (Matrix M, int idx_brs) {
	/* Matrix M terdefinisi, idx_brs <= baris M*/
	/* untuk membuat matriks yang menghilangkan baris ke idx_brs */
		/* KAMUS LOKAL */
		int i, j;
		int new_b;
		double[][] matriks = new double[M.getRow()-1][M.getColumn()];
		Matrix result = new Matrix(matriks);
		/* ALGORITMA */
		new_b = 0;
		for (i=0 ; i < M.getRow() ; i++){
			if (i != idx_brs){
				for (j = 0 ; j <= M.getColumn() ; j++){
					matriks[new_b][j] = M.getMatrix()[i][j];
				}
				new_b++;
			}
		}
		result.setColumn(M.getColumn());
		result.setMatrix(matriks);
		result.setRow(M.getRow() - 1);
		return result;
	}
	
	public static Matrix delKolMatrix (Matrix M, int idx_kol) {
	/* Matrix M terdefinisi, idx_kol <= kolom M*/
	/* untuk membuat matriks yang menghilangkan kolom ke idx_kol */
		/* KAMUS LOKAL */
		int i, j;
		int new_k;
		double[][] matriks = new double[M.getRow()][M.getColumn()-1];
		Matrix result = new Matrix(matriks);
		/* ALGORITMA */
		for (i=0 ; i <= M.getRow() ; i++){
			new_k = 0;
			for (j = 0 ; j < M.getColumn() ; j++){
				if (j != idx_kol){
					matriks[i][new_k] = M.getMatrix()[i][j];
					new_k++;
				}
			}
		}
		result.setColumn(M.getColumn()-1);
		result.setMatrix(matriks);
		result.setRow(M.getRow());
		return result;
	}

	public static Matrix minor (Matrix M, int idx_brs, int idx_kol) {
	/* Matrix M terdefinisi dan harus nxn, idx_brs <= baris M, idx_kol <= kolom M */
	/* untuk membuat sub matriks/ minor matriks yang menghilangkan baris ke idx_brs dan kolom ke idx_kol */
		/* KAMUS LOKAL */
        int i, j;
        int new_b, new_k;
		double[][] matriks = new double[M.getRow()-1][M.getColumn()-1];
		int size = M.getRow();
		Matrix result = new Matrix(matriks);
		/* ALGORITMA */
        new_b = 0;
        for (i=0 ; i< size ; i++){
            new_k = 0;
            if (i != idx_brs){
                for (j = 0 ; j < size ; j++){
                    if (j != idx_kol){
                        matriks[new_b][new_k] = M.getMatrix()[i][j];
                        new_k++;
                    }
                }
                new_b++;
            }
        }
		result.setColumn(M.getColumn() - 1);
		result.setMatrix(matriks);
		result.setRow(M.getRow() - 1);
        return result;
	}
	
	public static Matrix transpose (Matrix M) {
	/* Matrix M terdefinisi, tidak harus nxn */
	/* mengeluarkan matriks transpose baris dan kolomnya */
	/* apabila M adalah matrix 2x3, maka keluarannya adalah matrix 3x2 */
		/* KAMUS LOKAL */
		Matrix matrix = new Matrix(M.getColumn(), M.getRow());
		int i, j;
		/* ALGORITMA */
		for (i = 0 ; i < M.getRow() ; i++){
            for (j = 0 ; j < M.getColumn() ; j++){
				matrix.getMatrix()[j][i] = M.getMatrix()[i][j];
				// baris dan kolom dipertukarkan
            }
		}
		matrix.setColumn(M.getRow());
		matrix.setRow(M.getColumn());
		return matrix;
	}

	// tambahin baca matriks
}