
import java.util.Scanner;
import java.lang.Math;
import java.util.*;
import java.io.*;

public class Interpolasi {

	Matrix matrix; // augmented matrix dari titik"
	int N; // jumlah derajat polinom
	double[] polinom; // polinomial hasil interpolasi
	// Cat : tingkat derajat polinom sesuai dengan n
	double[] arr_X; // array untuk menyimpan nilai titik X
	double[] arr_Y; // array untuk menyimpan nilai titik Y
	boolean allDefined = false;

	double x;
	double y;

	/* Konstruktor scanner utama */

	Scanner scanner = new Scanner(System.in);

	/* Fungsi mengambil inputan dari user secara langsung */
	private void inputTitik() {
		/* I.S. : Titik belum dimasukkan */
		/* F.S. : Titik telah terdefinisi */
		/*
		 * Kondisi Valid : Jumlah titik yang dimasukkan minimal 2, agar dapat terbentuk
		 * garis yang terhubung melalui 2 titik atau lebih
		 */
		int n;
		do {
			System.out.printf("Masukkan jumlah titik : ");
			n = scanner.nextInt();
		} while (n < 2);

		// Inisialisasi nilai derajat polinom objek interpolarisasi
		this.N = n;

		double x, y; // placeholder untuk nilai x dan y yang baru
		double[] arrX = new double[n];
		double[] arrY = new double[n];

		System.out.println("Masukkan nilai titik x dan y dipisahkan dengan spasi");

		int i = 0;
		while (i < n) {
			/* Buat validasi titik dengan pake java Array contains of */
			System.out.printf("Titik ke-%d :", i + 1);
			x = scanner.nextDouble();
			y = scanner.nextDouble();

			arrX[i] = x;
			arrY[i] = y;
			i++;

			this.arr_X = arrX;
			this.arr_Y = arrY;
		}

	}

	/* Fungsi mengambil inputan melalui file */
	public void inputTitikFile(String filename) {
		/*
		 * I.S. : Titik untuk interpolasi belum terdefinisi F.S. : Titik untuk
		 * interpolasi telah terdefinisi dari masukan file Catatan : Prekondisi : titik
		 * harus berjumlah minimal 2 titik.
		 */
		int jumlahData = BacaFile.jumlahData(filename)[0];

		this.N = jumlahData;

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

	/* Fungsi untuk mengambil nilai polinom */
	public String getOutputPolinom() {
		/*
		 * Deskripsi : Fungsi getOutputPolinom menghasilkan suatu String yang
		 * menggambarkan fungsi dari interpolasi yang dilakukan terhadap sejumlah titik
		 * yang diberikan. Catatan : Jumlah angka di belakang koma yang ditampilkan
		 * adalah 3 angka dibelakang koma.
		 */
		double pol[] = reverse(polinom, polinom.length);
		String stringPolinom = "y = ";
		int i = pol.length - 1;
		int j;
		while (i > 0 && pol[i] == 0) { // cari koef pertama yang tidak nol
			i--;
		}
		stringPolinom += String.format("%.10f", pol[i]);
		stringPolinom += "x";
		if (i != 1) {
			stringPolinom += "^" + i;
		}
		for (j = i - 1; j >= 0; j--) {
			if (pol[j] != 0) {
				stringPolinom += " + ";
				stringPolinom += String.format("%.10f", pol[j]);
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

	private void ubahPersLinear() {
		/*
		 * I.S. : Titik - titik telah terdefinisi / telah dimasukkan 
		 * F.S. : Terbentuk
		 * suatu matrix yang berisi SPL yang dibentuk dari nilai titik yang diberikan
		 */

		/* Kamus Lokal */
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

	private static double[] getResultGaussian(Matrix matrix) {
		/*
		 * Deskripsi : Menghasilkan suatu array double yang berisi koefisien, dimana
		 * nilai indeks dari array tersebut berkoresponden dengan nilai pangkat x
		 * koefisien yang ditampung
		 */
		SPL.solveGauss(matrix);
		SPL.solveGaussJordan(matrix);
		SPL.solveGauss(matrix);
		SPL.solveGaussJordan(matrix);
		SPL.solveGauss(matrix);
		
		double arr[] = new double[matrix.getRow()];

		// create Gaussian solver
		/* Catatan, nanti ganti dengan solver punya kita sendiri */
		for (int i = 0; i < matrix.getRow(); i++) {
			arr[i] = matrix.getMatrix()[i][matrix.getColumn() - 1];
		}

		// hitung hasil
		return arr;
	}

	public static double[] reverse(double a[], int n) {
		/*
		 * Deskripsi : Membalikkan isi array
		 */
		double[] b = new double[n];
		int j = n;
		for (int i = 0; i < n; i++) {
			b[j - 1] = a[i];
			j = j - 1;
		}
		return b;
	}

	/* Output result dalam bentuk string */
	private void polinomInterpolasi() {
		/* I.S. : Fungsi Polinom hasil interpolasi belum terdefinisi */
		/* F.S : Fungsi Polinom hasil interpolasi telah terdefinisi */
		double result[] = getResultGaussian(matrix);
		double result_reversed[] = reverse(result, result.length);
		this.polinom = result_reversed;
	}

	/* Fungsi Interpolasi */

	public static double getInterpolasi(boolean allDefined, double x, int N, double[] polinom) {
		/* I.S. : Semua properti telah diisi 
		 * F.S. : Menghasilkan suatu nilai hasil interpolasi terhadap suatu titik
		 * melalui nilai titik" yang telah dimasukkan sebelumnya
		 */
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

	/* Fungsi baca titik */
	public static double bacaTitikBaru() {
		/*
		 * Deskripsi : Membaca suatu titik X baru untuk diinterpolasi
		 */

		double x;
		Scanner scanner = new Scanner(System.in);

		System.out.printf("%s: ", "Masukkan suatu titik");
		x = scanner.nextDouble();

		return x;
	}

	public void writeInterpolasi(String filename) {
		/*
		 * I.S. : Tahapan interpolasi telah selesai dilakukan F.S. : Hasil interpolasi
		 * telah tercatat di suatu file yang ditentukan pengguna
		 */
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

	/* Gabungan semua fungsi menjadi 1 prosedur */
	public void prosedurInterpolasi() {
		/*
		 * I.S. : Titik belum terdefinisi 
		 * F.S. : Proses interpolasi telah selesai
		 * dilakukan Deskripsi : Menggabungkan seluruh fungsi interpolasi dengan opsi
		 * membaca dalam keyboard menjadi 1 prosedur
		 */
		inputTitik();
		ubahPersLinear();
		polinomInterpolasi();
		this.allDefined = true;
		System.out.print("Polinom yang diperoleh: ");
		System.out.println(getOutputPolinom());
		// baca input
		double x = bacaTitikBaru();
		double y = getInterpolasi(allDefined, x, N, polinom);
		this.x = x;
		this.y = y;
		// output keluaran
		
		System.out.println("Hasil keluaran interpolasi: " + String.format("%.3f", y));

	}

	public void showResultFileInterpolasi(String filename) {
		/*
		 * I.S. : Titik belum terdefinisi F.S. : Proses interpolasi telah selesai
		 * dilakukan Deskripsi : Menggabungkan seluruh fungsi interpolasi dengan opsi
		 * membaca melalui file menjadi 1 prosedur
		 */
		inputTitikFile(filename);
		ubahPersLinear();
		polinomInterpolasi();
		this.allDefined = true;
		System.out.print("Polinom yang diperoleh: ");
		System.out.println(getOutputPolinom());
		double x = bacaTitikBaru();
		double y = getInterpolasi(allDefined, x, N, polinom);
		this.x = x;
		this.y = y;
		
		System.out.println("Hasil keluaran interpolasi: " + String.format("%.3f", y));
	}

}
