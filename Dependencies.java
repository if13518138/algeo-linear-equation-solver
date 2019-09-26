import java.io.*;

public class Dependencies {

    /*** Fungsi untuk menghitung determinan ***/
    public static double kofaktorDet (Matrix M) {
        /* Matrix M terdefinisi berukuran nxn */
        /* menghitung determinan matriks (angka belakang koma belum ditentukan) menggunakan metode kofaktor */
        /* KAMUS LOKAL */
        int i;
        double result;
        Matrix sub;
        double[][] m = M.getMatrix();
        int n = M.getColumn();

        /* ALGORITMA */
        if (n == 1) { // basis jika matriks 1x1
            result = m[0][0];
        } else if (n == 2) { // basis jika matriks 2x2
            result = m[0][0] * m[1][1] - m[0][1] * m[1][0];
        } else { // rekursif untuk matriks nxn
            result = 0;
            for (i = 0; i < n; i++) {
                sub = Matrix.minor(M, 0, i);
                result += Math.pow((-1.0), i) * m[0][i] * kofaktorDet(sub);
            }
        }
        return result;
    }

    public static double sarrusDet (Matrix M) {
        /* M harus berukuran 3x3 */
        /* untuk menghitung determinan matriks M menggunakan metode sarrus */

        /* KAMUS */
        double[][] m = M.getMatrix();
        double res, a1, a2, a3, a4, a5, a6;

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

    public static double detOBE(Matrix M) {
        /* untuk menghitung determinan matriks M menggunakan metode operasi baris elementer */

        /* KAMUS */
        double[][] m = M.getMatrix();
        double res = 1;
        double count = 1;

        /* ALGORITMA */
        //SPL.makeUrutMatriksDet(M.getMatrix(), M.getColumn(), count);
        count *= SPL.makeUrutMatriksDet(M);
        SPL.solveGaussDet(M);
        count *= SPL.makeUrutMatriksDet(M);
        SPL.solveGaussDet(M);
        count *= SPL.makeUrutMatriksDet(M);
        //SPL.makeUrutDet(M, count);
        //SPL.makeUrutMatriksDet(M.getMatrix(), M.getColumn(), count);
        for (int i = 0; i < M.getColumn(); i++) {
            res *= M.getMatrix()[i][i];
        }
        res *= count;
        return res;
    }

    public static void writeDet(double det, String filename) {

        try {
            FileWriter fileWriter = new FileWriter(filename + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.format("%.3f", det));
            bufferedWriter.close();
            System.out.println("Success ..");
        } catch (IOException e) {
            System.out.println("Error dalam menulis file");
        }
    }

    public static void writeMatrix(Matrix matrix, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // do all in here
            int nRow = matrix.getRow();
            int nCol = matrix.getColumn();

            double[][] arr = matrix.getMatrix();

            for (int i = 0; i < nRow; i++) {
                for (int j = 0; j < nCol; j++) {
                    bufferedWriter.write(String.format("%.3f ", arr[i][j]));
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Success ..");
        } catch (FileNotFoundException ex) {
            System.out.println("File tidak ditemukan");
        } catch ( IOException e) {
            System.out.println("Error dalam penulisan file");
        }
    }

    public static Matrix hitungKofaktor(Matrix M) {
        /* Matrix M terdefinisi dan harus nxn */
        /* mengembalikan matriks kofaktor dari M */
        /* KAMUS LOKAL */
        int i, j;
        int panjang = M.getRow();
        double[][] m = new double[panjang][panjang];
        Matrix result = new Matrix(m);

        /* ALGORITMA */
        for (i = 0 ; i < panjang ; i++) {
            for (j = 0 ; j < panjang ; j++) {
                m[i][j] = Math.pow((-1.0), (i + j)) * kofaktorDet(Matrix.minor(M, i, j));
            }
        }
        result.setRow(panjang);
        result.setColumn(panjang);
        result.setMatrix(m);
        return result;
    }

    public static Matrix hitungAdjoin (Matrix M) {
        /* Matriks M terdefinisi dan harus nxn */
        /* menghitung adjoin dari matriks M */
        /* KAMUS LOKAL */
        Matrix kof, adj = new Matrix(M.getRow(), M.getColumn());
        /* ALGORITMA */
        kof = hitungKofaktor(M);
        adj = Matrix.transpose(kof);
        return adj;
    }

    public static Matrix inversAdj (Matrix M) {
        /* Matrix M terdefinisi dan merupakan matrix nxn */
        /* Mengeluarkan matriks balikan dari matrix M dengan metode adjoin */
        /* KAMUS LOKAL */
        Matrix result = new Matrix(M.getRow(), M.getColumn());
        double[][] m = M.getMatrix();
        int i, j ;
        double determinan = kofaktorDet(M);
        m = hitungAdjoin(M).getMatrix();
        /* ALGORITMA */
        //hitung invers dengan cara 1/determinan kali adjoint
        for (i = 0 ; i < M.getRow(); i++) {
            for (j = 0 ; j < M.getColumn(); j++) {
                m[i][j] = (1.0 / determinan) * m[i][j];
            }
        }
        result.setMatrix(m);
        return result;
    }

    private static Matrix createInversMatrix (Matrix M) {
        /* Fungsi untuk membentuk augmented matrix yang digunakan dalam proses invers Matriks*/
        /* Membentuk matriks dengan ukuran dari nxn menjadi nx2n*/
        /* Matriks yang baru dibentuk berupa matriks ones*/

        Matrix newM = new Matrix(M.getRow(), M.getColumn() * 2);
        double [][] arr = new double[M.getRow()][M.getColumn() * 2];
        // membuat augmented matrix
        // dengan tambahan matrix ones

        for (int i = 0; i < M.getRow(); i++) {
            for (int j = 0; j < M.getColumn() * 2; j++) {
                // ketika ada di diagonal matrix ones
                if (j < M.getColumn()) {
                    arr[i][j] = M.getMatrix()[i][j];
                } else {
                    // create ones
                    // check if diagonal
                    if (j == (i + M.getColumn())) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = 0;
                    }
                }
            }
        }
        // set Matriks kembali ke matriks augmented baru
        newM.setMatrix(arr);
        return newM;
    }
    public static Matrix inversGauss (Matrix M) {
        /* Matrix M terdefinisi dan merupakan matrix nxn */
        /* Mengeluarkan matriks balikan dari matrix M dengan metode gauss - jordan */
        Matrix newAugmented = createInversMatrix(M);

        // lakukan interchange sampai menjadi 1 semua
        // interchange dilakukan dari belakang

        for (int i = M.getRow() - 1; i > 0; i-- ) {
            // swapping each and every element of the rows
            if (newAugmented.getMatrix()[i - 1][0] < newAugmented.getMatrix()[i][0]) {
                // swapping each elements of the row
                for (int j = 0; j < 2 * M.getRow(); j++) {
                    // swap element of row
                    double temp = newAugmented.getMatrix()[i][j];
                    newAugmented.getMatrix()[i][j] = newAugmented.getMatrix()[i - 1][j];
                    newAugmented.getMatrix()[i - 1][j] = temp;
                }
            }
        }

        // mengganti suatu row dengan jumlah dari dirinya sendiri atau dari row lain
        for (int i = 0; i < M.getRow(); i++) {
            for (int j = 0; j < M.getRow(); j++) {
                if (j != i) {
                    double temp = newAugmented.getMatrix()[j][i] / newAugmented.getMatrix()[i][i];
                    for ( int k = 0; k < 2 * M.getRow(); k++) {
                        newAugmented.getMatrix()[j][k] -= newAugmented.getMatrix()[i][k] * temp;
                    }
                }
            }
        }

        // kalikan setiap row dengan integer bukan 0.
        // kemudian kalikan setiap row elementnya dengan diagonal elemen itu sendir
        for (int i = 0; i < M.getRow(); i++) {
            double temp = newAugmented.getMatrix()[i][i];
            for (int j = 0; j < 2 * M.getRow(); j++) {
                newAugmented.getMatrix()[i][j] = newAugmented.getMatrix()[i][j] / temp;
            }
        }

        Matrix newM = new Matrix(M.getRow(), M.getColumn());
        double arrN[][] = new double[M.getRow()][M.getColumn()];
        // ambil hanya setengah bagian matriks
        for (int i = 0; i < M.getRow(); i++) {
            for (int j = M.getColumn(); j < M.getColumn() * 2; j++) {
                arrN[i][j - M.getColumn()] = newAugmented.getMatrix()[i][j];
            }
        }

        newM.setMatrix(arrN);
        return newM;
    }
}