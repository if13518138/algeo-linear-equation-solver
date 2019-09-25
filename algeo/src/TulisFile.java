package bin;

import java.io.*;

public class TulisFile {

	/* Opsi Tulis */ 
	/*
	1. keluaran SPL ( Y x ^ 4 bla blabla)
	2. baca determinan (matriks nxn) (sama juga buat matriks balikan dan kofaktor, adjoin juga) ( keluaran bentuk matriks)
	3. baca interpolasi (kumpulan titik" x y) ( keluaran : polinom dan titik)
	*/


    /* Catatan: */
    /*
	Contoh cara nulis:
	    bufferedWriter.write(" here is some text.");
	Contoh cara kasi newline:
	    bufferedWriter.newLine();
	*/
	
    /*** Instance variable ***/
	private String filename;
	private int opsiTulis;


	public static void tulisHasilMatriks(String filename, Matrix matrix) {
	/* I. S. filename sembarang, matrix terdefinisi yang akan disimpan */
    /* F. S. filename berisi matrix hasil */
    /* Mengeluarkan pesan error apabila filename tidak ada/tidak valid */
    /* Mengeluarkan pesan error apabila gagal menyimpan ke filename */
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
	/* I. S. filename sembarang, polinom hasil interpolasi terdefinisi yang akan disimpan */
    /* F. S. filename berisi polinom dan nilai hasil interpolasi */
    /* Mengeluarkan pesan error apabila filename tidak ada/tidak valid */
    /* Mengeluarkan pesan error apabila gagal menyimpan ke filename */
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
