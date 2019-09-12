import java.util.Scanner;

public class determinan {
    public static double[][] subMatriks (double M[][], int ukuran, int idx) {
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

    public static double hitungDeterminan (double M[][], int panjang) {
    // menghitung determinan secara rekursif
        int i;
        double result;
        double[][] sub;
        if (panjang == 1) { // basis jika matriks 1x1
            result = M[0][0];
        } else if (panjang == 2) { // basis jika matriks 2x2
            result = M[0][0] * M[1][1] - M[0][1] * M[1][0];
        } else { // matriks nxn
            result = 0;
            for (i = 0; i < panjang; i++) {
                sub = subMatriks(M, panjang, i);
                result += Math.pow((-1.0), i) * M[0][i] * hitungDeterminan(sub, (panjang-1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Panjang matriks:");
        Scanner myObj = new Scanner(System.in);
        int panjang = myObj.nextInt();
        int i, j;
        System.out.println("Masukkan matriks!");
        double[][] M = new double[panjang][panjang];
        for (i = 0; i < panjang; i++) {
            for (j = 0; j < panjang; j++) {
                M[i][j] = myObj.nextInt();
            }
        }
        double det = hitungDeterminan(M, panjang);
        System.out.println(det);
    }
}