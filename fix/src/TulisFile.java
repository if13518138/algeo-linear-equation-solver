package tubessantuy;
import java.io.*;

public class TulisFile {

	/*Instance variable*/
	private String filename;
	private int opsiTulis;

	/*Opsi Tulis*/
	/*
	1. keluaran SPL ( Y x ^ 4 bla blabla)
	2. baca determinan (matriks nxn) (sama juga buat matriks balikan dan kofaktor, adjoin juga) ( keluaran bentuk matriks)
	3. baca interpolasi (kumpulan titik" x y) ( keluaran : polinom dan titik)
	*/

	/*Fungsi - fungsi*/

	/*Catetan:*/
	// contoh cara nulis
	//  bufferedWriter.write(" here is some text.");
	// contoh cara kasi newline
	//bufferedWriter.newLine();

	public void tulisHasilPersamaan() {
		/*Tanya Zunan keluarannya gimana*/
	}

	public static void tulisHasilMatriks(String filename, Matrix matrix) {
		try {
			FileWriter fileWriter = new FileWriter(filename + ".txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// do all in here
			int nRow = matrix.getRow();
			int nCol = matrix.getColumn();

			double[][] arr = matrix.getMatrix();

			for (int i = 0; i < nRow; i++) {
				for (int j = 0; j < nCol; j++) {
					bufferedWriter.write(String.format("%.3f ", arr[i][j]));
				}
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			System.out.println("Success ..");
		} catch (FileNotFoundException ex) {
			System.out.println("File tidak ditemukan");
		} catch ( IOException e) {
			System.out.println("Error dalam penulisan file");
		}
	}

	/*Dilakukan  sesuai opsi*/
	public static void tulisHasilInterpolasi(String filename, String polinom, double X, double Y) {
		try {
			FileWriter fileWriter = new FileWriter(filename + ".txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// ambil polinom dari fungsi getOutputPolinom dari class Interpolasi
			bufferedWriter.write("Polinom: ");
			bufferedWriter.write(polinom);
			bufferedWriter.newLine();

			bufferedWriter.write("Nilai keluaran untuk X = ");
			String x = String.format("%.3f", X);
			String y = String.format("%.3f", Y);
			bufferedWriter.write(x);
			bufferedWriter.write(" adalah ");
			bufferedWriter.write(y);
			System.out.println("Success ..");
			bufferedWriter.close();


		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan");
		} catch (IOException er) {
			System.out.println("Terjadi error dalam penulisan file");
		}

	}

	public static void main(String[] args) {


		String polinom = "5 x^4 + 4 x^3 + 10 x^2 + 5 x + 9 = 0";
		double X = 5.0;
		double Y = 10.0;
		tulisHasilInterpolasi("testx", polinom, X, Y);


		// double[][] arr = {{1,1,1,1,1,1,1,1},{3,4,7,8,3,4,7,5},{1,5,7,5,3,6,8,9},{1,9,8,7,6,5,4,3},{4,2,5,7,4,1,3,5},{6,9,8,3,5,7,5,3},{1,5,7,8,5,3,3,6}};
		// Matrix matrix = new Matrix(arr);
		// file2.tulisHasilMatriks(matrix);
	}

}
