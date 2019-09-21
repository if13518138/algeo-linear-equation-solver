import java.io.*;
import java.util.*;

public class BacaFile {

	/*Instance variable*/
	private String filename;
	private int opsiBaca;

	/*Opsi Baca*/
	/*
	1. baca SPL (Matriks nxn+1)
	2. baca determinan (matriks nxn) (sama juga buat matriks balikan dan kofaktor, adjoin juga)
	3. baca interpolasi (kumpulan titik" x y)
	*/

	/*Konstruktor*/
	public BacaFile (String filename, int opsiBaca) {
		this.opsiBaca = opsiBaca;
		this.filename = filename;
	}

	public static int[] jumlahData(String filename) {

		/*Mengembalikan panjang data dengan dimensi(bentuk data)*/
		/*Nilai pertama mengembalikan nilai panjang file*/
		/*Nilai kedia mengembalikan nilai lebar file*/



		try {
			File file = new File (filename + ".txt");

			Scanner scanFileChecker = new Scanner(file);

			// do all in here
			int nLine = 0;
			String s = "";
			while (scanFileChecker.hasNextLine()) {
				nLine += 1;
				s = scanFileChecker.nextLine();
			}
			if (nLine > 0) {
				String[] sSplit = s.split(" ");
				int[] val = {nLine, sSplit.length};
				return val;
			}
			int[] val ={0,0};
			scanFileChecker.close();
			return val;

		} catch (FileNotFoundException ex) {
			System.out.println("File tidak ditemukan");
			int[] val = {0, 0};
			return val;
		}
	}

	/*Dilakukan  sesuai opsi*/
	public Matrix inputMatrix() {
		int[] jumlahData = BacaFile.jumlahData(filename);

		double[][] arr = new double[jumlahData[0]][jumlahData[1]];

		try {
			File file = new File(filename + ".txt");

			Scanner scanner = new Scanner(file);

			String s;
			String[] sSplit;
			double[] arrTemp = new double[jumlahData[1]];
			int count = 0;

			while (scanner.hasNextLine()) {
				System.out.println("Debug");
				s = scanner.nextLine();
				System.out.println(s);
				sSplit = s.split(" ");
				for (int i = 0; i < jumlahData[1]; i++) {
					arr[count][i] = Double.parseDouble(sSplit[i]);

				}
				// gunakan metode kopi array yang efektif
				count++;
			}
			Matrix matrix = new Matrix(arr);
			scanner.close();
			return matrix;

		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan");

			Matrix matrix = new Matrix(arr);
			return matrix;
		}

	}

	public double[][] inputTitik() {
		int[] jumlahData = BacaFile.jumlahData(filename);
		int banyakTitik = jumlahData[0];

		double[][] arr = new double[2][banyakTitik];

		try {
			File file = new File(filename + ".txt");

			Scanner scanner = new Scanner(file);

			String s;
			String[] sSplit;
			double[] arrX = new double[banyakTitik];
			double[] arrY = new double[banyakTitik];

			int i = 0;

			while (scanner.hasNextLine()) {
				s = scanner.nextLine();
				sSplit = s.split(" ");
				arrX[i] = Double.parseDouble(sSplit[0]);
				arrY[i] = Double.parseDouble(sSplit[1]);

				i++;
			}

			arr[0] = arrX; arr[1] = arrY;
			scanner.close();
			return arr;

		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan");
			return arr;

		}
	}


	// driver
	public static void main(String[] args) {
		BacaFile file1 = new BacaFile("test1",1);
		//BacaFile file2 = new BacaFile("test2",2);

		Matrix matrix = file1.inputMatrix();
		matrix.show();

		System.out.println("============================================================================");

		//double arr[][] = file2.inputTitik();
		//double[] arrX = arr[0];
		//double[] arrY = arr[1];

		//for (int i = 0; i < arrX.length; i++){
		//	System.out.printf("%.2f %.2f\n",arrX[i],arrY[i]);
		//}
		
		SPLGaussJordan.showResultGaussJordan(matrix);

		matrix.show();

	}
}


