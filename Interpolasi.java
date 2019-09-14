import java.util.Scanner;
import java.lang.Math;
import java.io.FileReader;

public class Interpolasi {

	Matrix matrix;		// augmented matrix dari titik"
	int N; 				// jumlah derajat polinom
	double[] polinom;	// polinomial hasil interpolasi
						// Cat : tingkat derajat polinom sesuai dengan n
	double[] arr_X;		// array untuk menyimpan nilai titik X
	double[] arr_Y;		// array untuk menyimpan nilai titik Y
	boolean allDefined = false;

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
		}while (n < 2);
		
		// Inisialisasi nilai derajat polinom objek interpolarisasi
		this.N = n;
		
		double x,y;	// placeholder untuk nilai x dan y yang baru
		double[] arrX = new double[n];
		double[] arrY = new double[n];

		System.out.println("Masukkan nilai titik x dan y dipisahkan dengan spasi");
		for (int i = 0; i < n;i++) {
			System.out.printf("Titik ke-%d :",i+1);
			x = scanner.nextDouble();
			y = scanner.nextDouble();

			// masukkan nilai ke dalam array
			arrX[i] = x;
			arrY[i] = y;
		}

		// kembalikan nilai kedalam properti dari Interpolasi
		this.arr_X = arrX;
		this.arr_Y = arrY;
	}

	/*Fungsi mengambil inputan melalui file*/

	/*Fungsi mengubah titik" menjadi sistem persamaan linear(dalam bentuk augmented matrix)*/
	private void ubahPersLinear () {
		/*I.S. : Titik telah diinput kedalam objek*/
		/*F.S. : Terdapat augmented matrix dari persamaan garis nilai titik yang telah diinput */

		/*Kamus Lokal*/
		double arrTitik[][] = new double[N][N+1];
		
		// inisialisasi ke dalam array
		for (int i = 0; i < N;i++){
			for (int j = 0; j < N; j++){
				arrTitik[i][j] = Math.pow(arr_X[i],j);
			}
			arrTitik[i][(N+1) - 1] = arr_Y[i];
		}

		Matrix augmentedMatrix = new Matrix(arrTitik);
		this.matrix = augmentedMatrix;
	}


	private static double [] getResultGaussian(Matrix matrix) {
		double[][] arrX = new double[matrix.getRow()][matrix.getColumn() - 1];
		double[] arrY = new double[matrix.getRow()];

		// pengkopian array
		for (int i = 0; i < arrX.length; i++){
			for (int j = 0; j < arrX[0].length; j++){
				arrX[i][j] = matrix.getMatrix()[i][j];
			}
			arrY[i] = matrix.getMatrix()[i][matrix.getMatrix()[0].length - 1];
		}

		// create Gaussian solver
		GaussianElimination solver = new GaussianElimination();

		// hitung hasil
		double[] x = solver.lsolve(arrX, arrY);
		return x;
	}

	/*Method untuk membalikkan isi array*/
	private static double[] reverse(double a[], int n){
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
	public static double getInterpolasi (boolean allDefined, double x, int N,double[] polinom){
		// Public agar dapat diakses di tempat lain
		if (allDefined) {
			double result = polinom[polinom.length - 1];
			for (int i = 1; i < N; i++){
				result += polinom[polinom.length - 1 - i] * Math.pow(x,i);
			}
			return result;
		} else {
			return -999; // means not all properties defined
		}

	}

	/*Gabungan semua fungsi menjadi 1 prosedur*/
	public void prosedurInterpolasi() {
		inputTitik();
		ubahPersLinear();
		polinomInterpolasi();
		this.allDefined = true;

		// define scanner
		// baca input
		System.out.printf("%s: ", "Masukkan suatu titik");
		double x = scanner.nextDouble();
		double y = getInterpolasi(allDefined,x, N,polinom);

		System.out.println("Hasil keluaran interpolasi: "+String.format("%.2f",y));
		
		scanner.close();
	}
}


