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
	public TulisFile (String filename, int opsiTulis) {
		this.filename = filename;
		this.opsiTulis = opsiTulis;
	}

	/*Fungsi - fungsi*/

	/*Catetan:*/
	// contoh cara nulis
	//  bufferedWriter.write(" here is some text.");
	// contoh cara kasi newline
	//bufferedWriter.newLine();

	public void tulisHasilPersamaan(){
		/*Tanya Zunan keluarannya gimana*/
	}
	
	public void tulisHasilMatriks(Matrix matrix) {
		try {
			FileWriter fileWriter = new FileWriter(filename+ ".txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// do all in here
			int nRow = matrix.getRow();
			int nCol = matrix.getColumn();

			double[][] arr = matrix.getMatrix();

			for (int i = 0; i < nRow; i++){
				for (int j = 0; j < nCol; j++){
					bufferedWriter.write(String.format(".3f ",arr[i][j]));
				}
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File tidak ditemukan");
		} catch ( IOException e) {
			System.out.println("Error dalam penulisan file");
		}
	}

		/*Dilakukan  sesuai opsi*/
	public void tulisHasilInterpolasi(String polinom,double X,double Y){
		try {
			FileWriter fileWriter = new FileWriter(filename + ".txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// ambil polinom dari fungsi getOutputPolinom dari class Interpolasi
			bufferedWriter.write("Polinom: ");
			bufferedWriter.write(polinom);
			bufferedWriter.newLine();

			bufferedWriter.write("Nilai keluaran untuk X = ");
			String x = String.format(".2f",X);
			String y = String.format(".2f",Y);
			bufferedWriter.write(x);
			bufferedWriter.write("adalah ");
			bufferedWriter.write(y);

			bufferedWriter.close();


		} catch (FileNotFoundException e){
			System.out.println("File tidak ditemukan");
		} catch (IOException er){
			System.out.println("Terjadi error dalam penulisan file");
		}

	}

	public static void main(String[] args) {
		TulisFile file1 = new TulisFile("testtulis1",1);
		TulisFile file2 = new TulisFile("testtulis2",2);

		String polinom = "5 x^4 + 4 x^3 + 10 x^2 + 5 x + 9 = 0";
		double X = 5.0;
		double Y = 10.0;
		file1.tulisHasilInterpolasi(polinom,X,Y);


		double[][] arr = {{1,1,1,1,1,1,1,1},{3,4,7,8,3,4,7,5},{1,5,7,5,3,6,8,9},{1,9,8,7,6,5,4,3},{4,2,5,7,4,1,3,5},{6,9,8,3,5,7,5,3},{1,5,7,8,5,3,3,6}};
		Matrix matrix = new Matrix(arr);
		file2.tulisHasilMatriks(matrix); 
	}
	
}