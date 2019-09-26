
import java.util.Scanner;
import java.lang.Math;
import java.util.*;
import java.io.*;
// import java.io.FileReader;

public class Interpolasi {

	Matrix matrix;		// augmented matrix dari titik"
	int N; 				// jumlah derajat polinom
	double[] polinom;	// polinomial hasil interpolasi
	// Cat : tingkat derajat polinom sesuai dengan n
	double[] arr_X;		// array untuk menyimpan nilai titik X
	double[] arr_Y;		// array untuk menyimpan nilai titik Y
	boolean allDefined = false;

	double x;
	double y;

	/*Konstruktor scanner utama*/

	Scanner scanner = new Scanner(System.in);

	/*Fungsi mengambil inputan dari user secara langsung*/
	private void inputTitik() {
		/*I.S. : Titik belum dimasukkan */
		/*F.S. : Titik telah terdefinisi */
		/*Kondisi Valid : Jumlah titik yang dimasukkan minimal 2, agar tidak menimbulkan keambiguan*/
		int n;
		// lakukan validasi terhadap jumlah titik
		do {
			System.out.printf("Masukkan jumlah titik : ");
			n = scanner.nextInt();
		} while (n < 2);

		// Inisialisasi nilai derajat polinom objek interpolarisasi
		this.N = n;

		double x, y;	// placeholder untuk nilai x dan y yang baru
		double[] arrX = new double[n];
		double[] arrY = new double[n];

		System.out.println("Masukkan nilai titik x dan y dipisahkan dengan spasi");

		int i = 0;
		while (i < n) {
			/*Buat validasi titik dengan pake java Array contains of*/
			do {
				System.out.printf("Titik ke-%d :", i + 1);
				x = scanner.nextDouble();
				y = scanner.nextDouble();
				if (Arrays.asList(arrX).contains(x) && Arrays.asList(arrY).contains(y)) {
					System.out.println("Titik telah dimasukkan sebelumnya");
				}
			} while ((Arrays.asList(arrX).contains(x) && Arrays.asList(arrY).contains(y)));
			// dilakukan validasi terhadap nilai x dan y (apakah sudah pernah ada atau belum titiknya)
			// masukkan nilai ke dalam array
			arrX[i] = x;
			arrY[i] = y;
			// masukkan nilai ke dalam array
			i++;
		}
		// kembalikan nilai kedalam properti dari Interpolasi
		this.arr_X = arrX;
		this.arr_Y = arrY;

	}

	/*Fungsi mengambil inputan melalui file*/
	public void inputTitikFile(String filename) {
		int jumlahData = BacaFile.jumlahData(filename)[0];

		this.N = jumlahData - 1;

		double[][] arr = new double[2][jumlahData];

		try {
			File file = new File(filename + ".txt");
			Scanner scanner = new Scanner(file);

			String s;
			String[] sSplit;
			double[] arrX = new double[jumlahData];
			double[] arrY = new double[jumlahData];

			int i = 0;

			while (scanner.hasNextLine()) {
				s = scanner.nextLine();
				sSplit = s.split(" ");
				arrX[i] = Double.parseDouble(sSplit[0]);
				arrY[i] = Double.parseDouble(sSplit[1]);

				i++;
			}

			this.arr_X = arrX;
			this.arr_Y = arrY;

		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan");
		}

	}

	/*Fungsi untuk mengambil nilai polinom*/
	public String getOutputPolinom () {
		// untuk ngeprint polinom
		double pol[] = reverse(polinom, polinom.length);
		String stringPolinom = "y = ";
		int i = pol.length - 1;
		int j;
		while (i > 0 && pol[i] == 0) { //cari koef pertama yang tidak nol
			i--;
		}
		stringPolinom += String.format("%.2f", pol[i]);
		stringPolinom += "x";
		if (i != 1) {
			stringPolinom += "^" + i;
		}
		for (j = i - 1; j >= 0; j--) {
			if (pol[j] != 0) {
				stringPolinom += " + ";
				stringPolinom += String.format("%.3f", pol[j]);
				if (j != 0) {
					stringPolinom += "x";
					if (j != 1) {
						stringPolinom += "^" + j;
					}
				}
			}
		}
		return stringPolinom;
	}

	/*Fungsi mengubah titik" menjadi sistem persamaan linear(dalam bentuk augmented matrix)*/
	private void ubahPersLinear () {
		/*I.S. : Titik telah diinput kedalam objek*/
		/*F.S. : Terdapat augmented matrix dari persamaan garis nilai titik yang telah diinput */

		/*Kamus Lokal*/
		double arrTitik[][] = new double[N][N + 1];

		// inisialisasi ke dalam array
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arrTitik[i][j] = Math.pow(arr_X[i], j);
			}
			arrTitik[i][(N + 1) - 1] = arr_Y[i];
		}

		Matrix augmentedMatrix = new Matrix(arrTitik);
		this.matrix = augmentedMatrix;
	}


	private static double [] getResultGaussian(Matrix matrix) {
		SPL.solveGauss(matrix);
		SPL.solveGaussJordan(matrix);
		matrix.show();

		double arr[] = new double[matrix.getRow()];

		// create Gaussian solver
		/*Catatan, nanti ganti dengan solver punya kita sendiri*/
		for (int i = 0; i < matrix.getRow(); i++) {
			arr[i] = matrix.getMatrix()[i][matrix.getColumn() - 1];
		}

		// hitung hasil
		return arr;
	}

	/*Method untuk membalikkan isi array*/
	public static double[] reverse(double a[], int n) {
		double[] b = new double[n];
		int j = n;
		for (int i = 0; i < n; i++) {
			b[j - 1] = a[i];
			j = j - 1;
		}
		return b;
	}

	/*Output result dalam bentuk string*/
	private void polinomInterpolasi() {
		/*I.S. : Fungsi Polinom hasil interpolasi belum terdefinisi */
		/*F.S  : Fungsi Polinom hasil interpolasi telah terdefinisi*/
		double result[] = getResultGaussian(matrix);
		double result_reversed[] = reverse(result, result.length);
		this.polinom = result_reversed;
	}

	/*Fungsi Interpolasi */
	/*I.S. : Semua properti telah diisi */
	public static double getInterpolasi (boolean allDefined, double x, int N, double[] polinom) {
		// Public agar dapat diakses di tempat lain
		if (allDefined) {
			double result = polinom[polinom.length - 1];
			for (int i = 1; i < N; i++) {
				result += polinom[polinom.length - 1 - i] * Math.pow(x, i);
			}
			return result;
		} else {
			return -999; // means not all properties defined
		}

	}

	/*Fungsi baca titik */
	public static double bacaTitikBaru() {
		double x;
		Scanner scanner = new Scanner(System.in);

		System.out.printf("%s: ", "Masukkan suatu titik");
		x = scanner.nextDouble();

		return x;
	}

	public void writeInterpolasi(String filename) {
		try {
			FileWriter fileWriter = new FileWriter(filename + ".txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// ambil polinom dari fungsi getOutputPolinom dari class Interpolasi
			bufferedWriter.write("Polinom: ");
			bufferedWriter.write(getOutputPolinom());
			bufferedWriter.newLine();

			bufferedWriter.write("Nilai keluaran untuk X = ");
			String xs = String.format("%.3f", x);
			String ys = String.format("%.3f", y);
			bufferedWriter.write(xs);
			bufferedWriter.write(" adalah ");
			bufferedWriter.write(ys);
			System.out.println("Success ..");
			bufferedWriter.close();


		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan");
		} catch (IOException er) {
			System.out.println("Terjadi error dalam penulisan file");
		}
	}



	/*Gabungan semua fungsi menjadi 1 prosedur*/
	public void prosedurInterpolasi() {
		inputTitik();
		ubahPersLinear();
		polinomInterpolasi();
		this.allDefined = true;
		// baca input
		double x = bacaTitikBaru();
		double y = getInterpolasi(allDefined, x, N, polinom);

		this.x = x;
		this.y = y;

		// output keluaran
		System.out.print("Polinom yang diperoleh:");
		getOutputPolinom();
		System.out.println("Hasil keluaran interpolasi: " + String.format("%.3f", y));

		// agar scanner tidak bocor, ditutup

	}

}


