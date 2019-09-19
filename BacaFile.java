import java.io.*;

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
	public BacaFile (String filename) {
		this.filename = filename;
	}

	/*Fungsi - fungsi*/
	// private static boolean isFileValid(String filename) {
	// 	try {
	// 		FileReader fileReaderValidate = new FileReader(filename);
	// 		BufferedReader bufferedReaderValidate = new BufferedReader(fileReaderValidate);
	// 		return true;
	// 	} catch (FileNotFoundException ex) {
	// 		System.out.println("File tidak ditemukan");
	// 		return false;
	// 	}

	// }

	public void inisiasiBacaFile() {
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// do all in here

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File tidak ditemukan");
		} catch ( IOException e) {
			System.out.println("Error");
		}

		/*Dilakukan  sesuai opsi*/

	}
}


