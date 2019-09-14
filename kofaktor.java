import java.util.Scanner;

public class kofaktor {
    public static double[][] subMatriks (double M[][], int ukuran, int idx_kol, int idx_brs) {
    // untuk membuat sub matriks yang menghilangkan baris ke 1 dan kolom ke idx 
        int i, j;
        int new_b, new_k;
        double[][] matriks = new double[ukuran-1][ukuran-1];

        new_b = 0;
        for (i=0 ; i< ukuran ; i++){
            new_k = 0;
            if (i != idx_brs){
                for (j = 0 ; j < ukuran ; j++){
                    if (j != idx_kol){
                        matriks[new_b][new_k] = M[i][j];
                        new_k++;
                    }
                }
                new_b++;
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
                sub = subMatriks(M, panjang, i, 0);
                result += Math.pow((-1.0), i) * M[0][i] * hitungDeterminan(sub, (panjang-1));
            }
        }
        return result;
    }

    public static double[][] hitungKofaktor (double M[][], int panjang){
        int i, j;
        double[][] result = new double[panjang][panjang];
        for (i = 0 ; i < panjang ; i++){
            for (j = 0 ; j < panjang ; j++){
                result[i][j] = Math.pow((-1.0),(i+j))*hitungDeterminan(subMatriks(M, panjang, j, i), panjang-1);
            }
        }
        return result;
    }


    public static double[][] hitungAdjoin (double M[][], int panjang){
        double[][] result = new double[panjang][panjang];
        double[][] result2 = new double[panjang][panjang];
        int i,j;
        result = hitungKofaktor(M, panjang);
        for (i = 0 ; i < panjang ; i++){
            for (j = 0 ; j < panjang ; j++){
                result2[j][i] = result[i][j];
            }
        }
        return result2;
    }

    public static double[][] hitungInvers (double M[][], int panjang){
        double[][] result = new double[panjang][panjang];
        int i, j ;
        double determinan = hitungDeterminan(M, panjang);
        result = hitungAdjoin(M, panjang);
        //hitung invers dengan cara 1/determinan kali adjoint
        for (i = 0 ; i < panjang ; i++){
            for (j = 0 ; j < panjang ; j++){
                result[i][j] = (1.0/determinan)*result[i][j];
            }
        }
        return result;
    }

    public static double hitungInterpolasi (double M[][], int panjang, double x){
        double[][] matriks = new double[panjang][panjang];
        double[] koefisien = new double[panjang];
        double result;
        double sum;
        int i, j;

        for (i = 0 ; i < panjang ; i++){
            for (j = 0 ; j < panjang ; j++){
                matriks[i][j] = Math.pow((M[0][i]), j);
            }
        }

        matriks = hitungInvers(matriks, panjang);
        for (i = 0 ; i < panjang ; i++){
            sum = 0;
            for (j = 0 ; j < panjang ; j++){
                sum += matriks[i][j]*M[1][j];
            }
            koefisien[i] = sum;
        }

        result = 0;
        for (i = 0; i < panjang ; i++){
            result += koefisien[i]*Math.pow(x, i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Panjang matriks:");
        Scanner myObj = new Scanner(System.in);
        int panjang = myObj.nextInt();
        int i, j;
        System.out.println("Masukkan matriks!");
        double[][] M = new double[2][panjang];
        double[][] S = new double[panjang][panjang];
        double[][] T = new double[panjang][panjang];
        for (i = 0; i < 2; i++) {
            for (j = 0; j < panjang; j++) {
                M[i][j] = myObj.nextDouble();
            }
        }
        System.out.println("Masukkan nilai x yang akan di interpolasi :");
        double x = myObj.nextDouble();

        System.out.println("ini hasilnya");

        System.out.println(hitungInterpolasi(M, panjang, x));
        /*
        double det = hitungDeterminan(M, panjang);
        System.out.println(det+"\n");

        S = hitungKofaktor(M, panjang);
        for (i = 0; i < panjang; i++) {
            for (j = 0; j < panjang; j++) {
                System.out.print(S[i][j] + " ");
            }
            System.out.println();
        }

        T = hitungAdjoin(M, panjang);
        for (i = 0; i < panjang; i++) {
            for (j = 0; j < panjang; j++) {
                System.out.print(T[i][j] + " ");
            }
            System.out.println();
        }
        */
    }
}