package bin;

import java.io.*;

public class TulisFile {

    /* Instance variable */
	private String filename;
	private int opsiTulis;


	public static void tulisHasilMatriks(String filename, Matrix matrix) {
		/* ALGORITMA */
		try {
			FileWriter fileWriter = new FileWriter(filename+ ".txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// do all in here
			int nRow = matrix.getRow();
			int nCol = matrix.getColumn();

			double[][] arr = matrix.getMatrix();

			for (int i = 0; i < nRow; i++){
				for (int j = 0; j < nCol; j++){
					bufferedWriter.write(String.format("%.3f ",arr[i][j]));
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

	public static void tulisHasilInterpolasi(String filename,String polinom,double X,double Y){
		/* ALGORITMA */
		try {
			FileWriter fileWriter = new FileWriter(filename + ".txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// ambil polinom dari fungsi getOutputPolinom dari class Interpolasi
			bufferedWriter.write("Polinom: ");
			bufferedWriter.write(polinom);
			bufferedWriter.newLine();

			bufferedWriter.write("Nilai keluaran untuk X = ");
			String x = String.format("%.2f",X);
			String y = String.format("%.2f",Y);
			bufferedWriter.write(x);
			bufferedWriter.write(" adalah ");
			bufferedWriter.write(y);
			System.out.println("Success ..");
			bufferedWriter.close();


		} catch (FileNotFoundException e){
			System.out.println("File tidak ditemukan");
		} catch (IOException er){
			System.out.println("Terjadi error dalam penulisan file");
		}
	}
}
