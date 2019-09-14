import java.util.Scanner;
import java.io.FileReader;
import java.util.*;

public class Matrix {

	private double matrix[][]; // matrix container with M-N size
	private int M; // row size
	private int N; // columns size

	/*Konstruktor*/
	public Matrix (int m, int n) {
		this.M = m;
		this.N = n;
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

	/*Getter*/
	public int getRow(){
		return this.M;
	}

	public int getColumn(){
		return this.N;
	}

	public double[][] getMatrix(){
		return this.matrix;
	}

	/*Setter*/
	public void setRow(int m){
		this.M = m;
	}

	public void setColumn(int n){
		this.N = n;
	}
	
	public void setMatrix(double[][] arr){
		this.matrix = arr;
	}
	
	// Fungsi swapping pada matrix
	public static void swap_row (Matrix m, int i, int j){
		/*Kamus Lokal*/
		int row,column;
		double arr[][];

		/*Inisialisasi*/
		row = m.getRow();
		column = m.getColumn();
		arr = m.getMatrix();

		/*Algoritma*/
		for (int k = 0;k<=row; k++){
			double temp = arr[i][k];
			arr[i][k] = arr[j][k];
			arr[j][k] = temp;
		}
	}

	// print matrix to standard output
    public void show() {
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) 
                System.out.printf("%9.4f ", matrix[i][j]);
            System.out.println();
        }
    }


}