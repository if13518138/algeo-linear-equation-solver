package bin;

import java.io.*;
import java.util.*;

public class BacaFile {

	/*** Instance variable ***/
	private String filename;
	private int opsiBaca;

	/*** Konstruktor ***/
	public BacaFile (String filename, int opsiBaca) {
    /* Untuk membentuk BacaFile kosong dari komponen komponennya */
		this.opsiBaca = opsiBaca;
		this.filename = filename;
	}

	public static int[] jumlahData(String filename) {
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
			int[] val = {0, 0};
			scanFileChecker.close();
			return val;

		} catch (FileNotFoundException ex) {
			System.out.println("File tidak ditemukan");
			int[] val = {0, 0};
			return val;
		}
	}

	public Matrix inputMatrix() {
		int[] jumlahData = BacaFile.jumlahData(filename);
		System.out.println(jumlahData[0]);
		System.out.println(jumlahData[1]);
		
		double[][] arr = new double[jumlahData[0]][jumlahData[1]];

		try {
			File file = new File(filename + ".txt");

			Scanner scanner = new Scanner(file);

			String s;
			String[] sSplit;
			double[] arrTemp = new double[jumlahData[1]];
			int count = 0;

			while (scanner.hasNextLine()) {
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

	public Matrix inputPersamaanMatrix() {
		Matrix matrix = inputMatrix();

		/*Dilakukan penambahan apabila bentuk tidak */
		while (matrix.getRow() < matrix.getColumn() - 1) {
			matrix.setRow(matrix.getRow() + 1);
			double[][] arr = new double[matrix.getRow()][matrix.getColumn()];

			for (int i = 0; i < matrix.getRow(); i++) {
				if (i != matrix.getRow() - 1) {
					for (int j = 0; j < matrix.getColumn(); j++){
						arr[i][j] = matrix.getMatrix()[i][j];
					}
				} else {
					for (int j = 0; j < matrix.getColumn(); j++){
						arr[i][j] = 0;
					}
				} 
			}
			matrix.setMatrix(arr);
		}
		return matrix;
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
}


