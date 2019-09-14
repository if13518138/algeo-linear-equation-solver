
public class FungsiLain {

    static Matrix matrix;
    /*** Fungsi untuk menghitung determinan ***/
	public static double kofaktorDet (Matrix M, int n) {
    /* Matrix M terdefinisi, n = ukuran matriks */
    /* menghitung determinan matriks (angka belakang koma belum ditentukan) menggunakan metode kofaktor */
		/* KAMUS LOKAL */
        int i;
        double result;
        Matrix sub;
        double[][] m = M.getMatrix();
		/* ALGORITMA */
        if (n == 1) { // basis jika matriks 1x1
            result = m[0][0];
        } else if (n == 2) { // basis jika matriks 2x2
            result = m[0][0] * m[1][1] - m[0][1] * m[1][0];
        } else { // matriks nxn
            result = 0;
            for (i = 0; i < n; i++) {
                sub = matrix.subMatriks(M, n, i, 0);
                result += Math.pow((-1.0), i) * m[0][i] * kofaktorDet(sub, (n-1));
            }
        }
        return result;
    }

    public static double sarrusDet (Matrix M){
    /* M harus berukuran 3x3 */

        /* KAMUS */
        double[][] m = M.getMatrix();
        double res,a1, a2, a3, a4, a5, a6; 

        /* ALGORITMA */
        a1 = m[0][0] * m[1][1] * m[2][2];
        a2 = m[0][1] * m[1][2] * m[2][0];
        a3 = m[0][2] * m[1][0] * m[2][1];
        a4 = m[0][2] * m[1][1] * m[2][0];
        a5 = m[0][0] * m[1][2] * m[2][1];
        a6 = m[0][1] * m[1][0] * m[2][2];
        res = a1 + a2 + a3 - a4 - a5 - a6;
        return res;
    }

    public static Matrix hitungKofaktor(Matrix M, int panjang){
        int i, j;
        double[][] m = new double[panjang][panjang];
		Matrix result = new Matrix(m);
        for (i = 0 ; i < panjang ; i++){
            for (j = 0 ; j < panjang ; j++){
                m[i][j] = Math.pow((-1.0),(i+j))*kofaktorDet(matrix.subMatriks(M, panjang, j, i), panjang-1);
            }
        }
		result.setRow(panjang);
		result.setColumn(panjang);
		result.setMatrix(m);
        return result;
    }

    public static Matrix hitungAdjoin (Matrix M, int panjang){
        double[][] temp = new double[panjang][panjang];
        double[][] result = new double[panjang][panjang];
        Matrix res = M;
        Matrix kof = new Matrix(temp);
        int i,j;
        kof = hitungKofaktor(M, panjang);
        temp = kof.getMatrix();
        for (i = 0 ; i < panjang ; i++){
            for (j = 0 ; j < panjang ; j++){
                temp[j][i] = result[i][j];
            }
        }
        res.setMatrix(result);
        return res;
    }
    
}