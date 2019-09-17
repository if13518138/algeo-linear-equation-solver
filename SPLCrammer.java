
public class SPLCrammer {

    public static double[] solveSPLCrammer(Matrix M) {
    /* Mengeluarkan array solusi SPL dari Matriks M berukuran nxn+1 */
    /* determinan M tidak boleh 0 */
        /* KAMUS LOKAL */
        double[] arrRes = new double[M.getColumn()]; 
        // pengisian array dimulai dari index 1 sesuai index x
        Matrix D = Matrix.delKolMatrix(M, M.getColumn());
        double det = Dependencies.kofaktorDet(D);
        Matrix min = new Matrix(M.getRow(), M.getColumn()-1);
        int i, j;
        /* ALGORITMA */
        for (i = 1; i < M.getColumn(); i++) {
            for (j = 0; j <= M.getRow(); j++) {
                min.getMatrix()[i-1][j] = M.getMatrix()[i-1][M.getColumn()];
                // i-1 karena index matrix mulai dari 0 sedangkan i mulai dai 1 karena menyesuaikan index solusi x
            }
            // pengisian solusi yaitu dengan membagi det(min)/det(D)
            arrRes[i] = Dependencies.kofaktorDet(min)/det;
        }
        return arrRes;
    }
}