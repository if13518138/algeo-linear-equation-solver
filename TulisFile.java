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

	/*Konstruktor*/
	public TulisFile (String filename) {
		this.filename = filename;
	}

	/*Fungsi - fungsi*/

	/*Catetan:*/
	// contoh cara nulis
	//  bufferedWriter.write(" here is some text.");
	// contoh cara kasi newline
	//bufferedWriter.newLine();
	
	public void inisiasiBacaFile() {
		try {
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// do all in here

			bufferedWriter.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File tidak ditemukan");
		} catch ( IOException e) {
			System.out.println("Error");
		}

		/*Dilakukan  sesuai opsi*/

	}
}