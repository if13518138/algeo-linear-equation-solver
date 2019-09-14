import java.util.Scanner;

public class Determinan {
    public static double[][] subMatrix (double M[][], int ukuran, int idx) {
    // untuk membuat sub matriks yang menghilangkan baris ke 1 dan kolom ke idx 
        int i, j;
        int j_new;
        double[][] matriks = new double[ukuran-1][ukuran-1];

        for (i = 1; i < ukuran; i++) {
            j_new = 0;
            for (j = 0; j < ukuran; j++) {
                if (j != idx) {
                    matriks[i-1][j_new] = M[i][j];
                    j_new++;
                }
            }  
        }
        return matriks;
    }

    /*** Fungsi untuk menghitung determinan ***/
	public static double cofactorDet (double M[][], int n) {
    /* I.S. : Matrix terdefinisi, n = ukuran matriks */
    /* F.S. : determinan matriks (angka belakang koma belum ditentukan) 
              perhitungan menggunakan metode kofaktor */
		/* KAMUS LOKAL */
        int i;
        double result;
        double[][] sub;
		/* ALGORITMA */
        if (n == 1) { // basis jika matriks 1x1
            result = M[0][0];
        } else if (n == 2) { // basis jika matriks 2x2
            result = M[0][0] * M[1][1] - M[0][1] * M[1][0];
        } else { // matriks nxn
            result = 0;
            for (i = 0; i < n; i++) {
                sub = subMatrix(M, n, i);
                result += Math.pow((-1.0), i) * M[0][i] * cofactorDet(sub, (n-1));
            }
        }
        return result;
    }
//public static double sarrusDet ()
    
}