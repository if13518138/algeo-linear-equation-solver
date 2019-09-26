import java.io.*;

public class SPL {

    /*** Metode Invers ***/
    public static double[] solveSPLInvers(Matrix M) {
        /* Mengeluarkan array solusi SPL dari Matriks augmented M berukuran nxn+1 */
        /* Matrix koefisien M (tanpa kolom terakhir) harus memiliki determinan != 0 */
        /* KAMUS LOKAL */
        double[] arrRes = new double[M.getColumn()];
        Matrix inv = new Matrix(M.getRow(), M.getColumn());
        Matrix konstanta = new Matrix(M.getRow(), 1);
        double[][] arrKons = new double[M.getRow()][1];
        int i;
        /* ALGORITMA */
        // pindahin konstanta ke matrix baru
        for (i = 0; i < M.getRow(); i++) {
            arrKons[i][0] = M.getMatrix()[i][M.getColumn() - 1];
        }
        konstanta.setMatrix(arrKons);
        // hapus kolom terakhir matrix M
        M = Matrix.delKolMatrix(M, M.getColumn() - 1);
        // cari invers M
        inv = Dependencies.inversAdj(M);
        konstanta = Matrix.multiplication(inv, konstanta);
        // pengisian dimulai dari 1 sesuai index x;
        for (i = 1; i <= M.getColumn(); i++) {
            arrRes[i] = konstanta.getMatrix()[i - 1][0];
        }
        return arrRes;
    };

    public static void showResultInv(Matrix M) {
        /* I. S. Matrix M adalah Matrix SPL augmented */
        /* Menampilkan solusi SPL ke layar */
        /* Mengeluarkan pesan error apabila SPL tidak dapat diselesaikan dengan metode invers */
        /* KAMUS LOKAL */
        int i, j;
        Matrix koef = new Matrix(M.getRow(), M.getColumn());
        koef = M;
        double[] res = new double[M.getColumn()];
        koef = Matrix.delKolMatrix(koef, koef.getColumn() - 1);
        /* ALGORITMA */
        if (M.getRow() > M.getColumn()-1) {
            System.out.println("Tidak dapat diselesaikan dengan metode Invers!");
        } else if (Dependencies.detOBE(koef) == 0) {
            System.out.println("Tidak dapat diselesaikan dengan metode Invers!");
        } else {
            res = solveSPLInvers(M);
            System.out.println("Solusi SPL tersebut adalah:");
            for (i = 1; i < M.getColumn(); i++) {
                System.out.println("X" + i + " = " + String.format("%.3f", res[i]));
            }
        }
    }


    // nulis file buat result spl inverses
    public static void writeResultInv(Matrix M, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int i, j;
            Matrix koef = new Matrix(M.getRow(), M.getColumn());
            koef = M;
            double[] res = new double[M.getColumn()];
            koef = Matrix.delKolMatrix(koef, koef.getColumn() - 1);
            /* ALGORITMA */
            if (Dependencies.detOBE(koef) == 0) {
                bufferedWriter.write("Tidak dapat diselesaikan dengan metode Invers!");
                bufferedWriter.close();
                System.out.println("Success ..");
            } else {
                res = solveSPLInvers(M);
                bufferedWriter.write("Solusi SPL tersebut adalah:");
                bufferedWriter.newLine();
                for (i = 1; i < M.getColumn(); i++) {
                    bufferedWriter.write("X" + i + " = " + res[i]);
                    bufferedWriter.newLine();
                }
                System.out.println("Success ..");
                bufferedWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Error dalam menulis file");
        }

    }


    /*** Metode Crammer ***/

    public static double[] solveSPLCrammer(Matrix M) {
        /* Mengeluarkan array solusi SPL dari Matriks augmented M berukuran nxn+1 */
        /* Determinan koefisien M (tanpa kolom terakhir) tidak boleh 0 */
        /* KAMUS LOKAL */
        double[] arrRes = new double[M.getRow()];
        Matrix D = Matrix.delKolMatrix(M, M.getColumn() - 1);
        double det = Dependencies.kofaktorDet(D);
        Matrix min = new Matrix(M.getRow(), M.getRow());
        int i, j, p, q;
        /* ALGORITMA */
        for (i = 0; i < M.getRow(); i++) { // kolom
            // mengubah elemen min menjadi sama dengan elemen D
            for (p = 0; p < M.getRow(); p++) {
                for (q = 0; q < M.getRow(); q++) {
                    min.getMatrix()[p][q] = D.getMatrix()[p][q];
                }
            }
            for (j = 0; j < M.getRow(); j++) { //baris
                min.getMatrix()[j][i] = M.getMatrix()[j][M.getColumn() - 1];
            }
            // pengisian solusi yaitu dengan membagi det(min)/det(D)
            arrRes[i] = Dependencies.kofaktorDet(min) / det;
        }
        return arrRes;
    }

    public static void showResultCrammer(Matrix M) {
        /* I. S. Matrix M adalah Matrix spl augmented */
        /* Mengeluarkan pesan error apabila SPL tidak dapat diselesaikan dengan metode crammer */
        /* KAMUS LOKAL */
        int i, j;
        Matrix koef = new Matrix(M.getRow(), M.getColumn());
        koef = M;
        double[] res = new double[M.getRow()];
        koef = Matrix.delKolMatrix(koef, koef.getColumn() - 1);
        /* ALGORITMA */
        if (M.getRow() > M.getColumn()-1) {
            System.out.println("Tidak dapat diselesaikan dengan metode Crammer!");
        } else if (Dependencies.kofaktorDet(koef) == 0){
            System.out.println("Tidak dapat diselesaikan dengan metode Crammer!");
        } else {
            res = solveSPLCrammer(M);
            System.out.println("Solusi SPL tersebut adalah:");
            for (i = 0; i < M.getRow(); i++) {
                System.out.println("X" + (i + 1) + " = " + String.format("%.3f", res[i]));
            }
        }
    }

    public static void writeResultCrammer(Matrix M, String filename) {

        try {
            FileWriter fileWriter = new FileWriter(filename + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            /* KAMUS LOKAL */
            int i, j;
            Matrix koef = new Matrix(M.getRow(), M.getColumn());
            koef = M;
            double[] res = new double[M.getRow()];
            koef = Matrix.delKolMatrix(koef, koef.getColumn() - 1);
            /* ALGORITMA */
            if (Dependencies.kofaktorDet(koef) == 0) {
                bufferedWriter.write("Tidak dapat diselesaikan dengan metode Crammer!");
                bufferedWriter.newLine();
                bufferedWriter.close();
                System.out.println("Success ..");
            } else {
                res = solveSPLCrammer(M);
                bufferedWriter.write("Solusi SPL tersebut adalah:");
                bufferedWriter.newLine();
                for (i = 0; i < M.getRow(); i++) {
                    bufferedWriter.write("X" + (i + 1) + " = " + res[i]);
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
                System.out.println("Success ..");
            }
        } catch (IOException e) {
            System.out.println("Error dalam menulis file");
        }
    }
    /*
    public static void makeUrutMatriks1(Matrix M){
        double temp;
        for (int i = 0 ; i < M.getRow()-1 ; i++){
            for (int j = i+1 ; j<M.getRow() ; j++ ){
                if (getIdxFirstNonZero(M, i)>getIdxFirstNonZero(M, j)){
                    for (int k = 0 ; k<M.getColumn() ; k++){
                        temp = M.getMatrix()[j][k];
                        M.getMatrix()[j][k] = M.getMatrix()[i][k];
                        M.getMatrix()[i][k] = temp;
                    }
                }
            }
        }
    }*/

    /*** Metode Gauss dan Gauss-Jordan ***/
    public static void makeUrutMatriks(double[][] M_in, int n_brs, int n_kol) {
        /* I. S. M terdefinisi */
        /* F. S. Membuat M menjadi matriks segitiga bawah (kumpulan 0 di kiri bawah) */
        /*KAMUS LOKAL */
        int i, j, k;
        double x;
        /* ALGORITMA */
        int t = 0; // buat ngebikin counter baris
        for (j = 0 ; j < n_kol - 1 ; j++) { //counter kolom
            for (int p = t ; p < n_brs ; p++) {
                for (int counter = p + 1 ; counter < n_brs ; counter++) {
                    //counter dari p+1 sampai mam_brs, kalau salah M(p,j) == nol dan M(counter, j) nggak nol, maka dituker
                    if (M_in[p][j] == 0 && M_in[counter][j] != 0) {
                        //untuk ngeswap
                        for (int r = 0; r < n_kol; r++) {
                            double temp = M_in[p][r];
                            M_in[p][r] = M_in[counter][r];
                            M_in[counter][r] = temp;
                        }
                    }
                }
                if (M_in[p][j] != 0) {
                    t = t + 1;
                }
            }
        }
    }

    public static double makeUrutMatriksDet(Matrix M) {
        /* M terdefinisi, n adalah baris dan kolom M, count = 1 */
        /* Mengembalikan hasil perkalian -1 tiap di swap */
        /* KAMUS LOKAL*/
        int i, j, k;
        double count = 1;
        /* ALGORITMA */
        int t = 0; // buat ngebikin counter baris
        for (j = 0 ; j < M.getRow() - 1 ; j++) { //counter kolom
            for (int p = t ; p < M.getRow() ; p++) {
                for (int counter = p + 1 ; counter < M.getRow() ; counter++) {
                    //counter dari p+1 sampai mam_brs, kalau salah M(p,j) == nol dan M(counter, j) nggak nol, maka dituker
                    if (M.getMatrix()[p][j] == 0 && M.getMatrix()[counter][j] != 0) {
                        //untuk ngeswap
                        for (int r = 0; r < M.getRow(); r++) {
                            double temp = M.getMatrix()[p][r];
                            M.getMatrix()[p][r] = M.getMatrix()[counter][r];
                            M.getMatrix()[counter][r] = temp;
                        }
                        count *= -1;
                    }
                }
                if (M.getMatrix()[p][j] != 0) {
                    t = t + 1;
                }
            }
        }
        return count;
    }

    public static int getIdxFirstNonZero(Matrix M, int i) {
        /* Prekondisi matriks ukuran n*(n+1) */
        /* Mengembalikan index bukan nol pertama dalam matrix pada baris i */
        /* ALGORITMA */
        for (int j = 0 ; j < M.getColumn() - 1 ; j++) { //cek ke baris tersebut dari kolom 1 sampe maksimal kolom -1
            if (M.getMatrix()[i][j] != 0) {
                return j;
            }
        }
        return 9999;
    }

    public static void solveGaussDet(Matrix M) {
        /* I. S. Matrix M terdefinisi */
        /* F. S. Matrix M menjadi bentuk eselon */
        /* ALGORITMA */
        makeUrutMatriks(M.getMatrix(), M.getRow(), M.getColumn());
        for (int i = 0 ; i < M.getRow() ; i++ ) { //untuk pengulangan ke bawah
            int idxNonZero = getIdxFirstNonZero(M, i); //index non zero
            if (idxNonZero != 9999) { // validasi index non zero
                for (int p = i + 1 ; p < M.getRow() ; p++) {
                    //pengulangan untuk index dibawah p, jika tidak sama dengan nol maka indeks yang di bawah i dikurangin
                    //dengan kelipatan nya supaya hasilnya 0
                    if (M.getMatrix()[p][idxNonZero] != 0) {
                        double x = M.getMatrix()[p][idxNonZero] / M.getMatrix()[i][idxNonZero];
                        for (int k = 0 ; k < M.getColumn() ; k++) {
                            M.getMatrix()[p][k] = M.getMatrix()[p][k] - x * M.getMatrix()[i][k];
                        }
                    }
                }
            }
        }
    }

    public static void solveGauss(Matrix M) {
        /* I. S. Matrix M terdefinisi */
        /* F. S. Matrix M menjadi bentuk eselon, diagonalnya semua 1 */
        /* ALGORITMA */
        makeUrutMatriks(M.getMatrix(), M.getRow(), M.getColumn());
        //makeUrutMatriks1(M);
        for (int i = 0 ; i < M.getRow() ; i++ ) { //untuk pengulangan ke bawah
            int idxNonZero = getIdxFirstNonZero(M, i); //index non zero
            if (idxNonZero != 9999) { // validasi index non zero
                for (int p = i + 1 ; p < M.getRow() ; p++) {
                    //pengulangan untuk index dibawah p, jika tidak sama dengan nol maka indeks yang di bawah i dikurangin
                    //dengan kelipatan nya supaya hasilnya 0
                    if (M.getMatrix()[p][idxNonZero] != 0) {
                        double x = M.getMatrix()[p][idxNonZero] / M.getMatrix()[i][idxNonZero];
                        for (int k = 0 ; k < M.getColumn() ; k++) {
                            M.getMatrix()[p][k] = M.getMatrix()[p][k] - x * M.getMatrix()[i][k];
                        }
                    }
                }
            }
        }
        //untuk membentuk non nol pertama jadi 1
        for (int p = 0 ; p < M.getRow() ; p++) {
            int q = 0;
            while (M.getMatrix()[p][q] == 0 && q < M.getColumn() - 1) {
                q++;
            }
            if (q != M.getColumn() - 1) {
                for (int r = q + 1 ; r < M.getColumn() ; r++) {
                    //System.out.print(y);
                    //System.out.print("\n");
                    M.getMatrix()[p][r] = M.getMatrix()[p][r] / M.getMatrix()[p][q];
                }
                M.getMatrix()[p][q] = 1;
            }
        }
        makeUrutMatriks(M.getMatrix(), M.getRow(), M.getColumn());
        //makeUrutMatriks1(M);
    }

    public static void solveGaussJordan(Matrix M) {
        /* I. S. Matrix M terdefinisi dan adalah matriks eselon */
        /* F. S. Matrix M menjadi bentuk eselon tereduksi */
        /* ALGORITMA */
        for (int i = M.getRow() - 1 ; i >= 0 ; i-- ) { //untuk pengulangan ke bawah
            int idxNonZero = getIdxFirstNonZero(M, i); //index non zero
            if (idxNonZero != 9999) { // validasi index non zero
                for (int p = i - 1 ; p >= 0 ; p--) {
                    //pengulangan untuk index diatas p, jika tidak sama dengan nol maka indeks yang di bawah i dikurangin
                    //dengan kelipatan nya supaya hasilnya 0
                    if (M.getMatrix()[p][idxNonZero] != 0) {
                        double x = M.getMatrix()[p][idxNonZero] / M.getMatrix()[i][idxNonZero];
                        for (int k = 0 ; k < M.getColumn() ; k++) {
                            M.getMatrix()[p][k] = M.getMatrix()[p][k] - x * M.getMatrix()[i][k];
                        }
                    }
                }
            }
        }
        makeUrutMatriks(M.getMatrix(), M.getRow(), M.getColumn());
    }

    public static int countBarisKosong(Matrix M) {
        /* Mengembalikan jumlah baris yang kosong pada suatu matriks eselon */
        /* KAMUS LOKAL */
        int count = 0;
        /* ALGORITMA */
        for (int i = 0 ; i < M.getRow() ; i++) {
            if (getIdxFirstNonZero(M, i) == 9999) {
                count ++;
            }
        }
        return count;
    }

    public static boolean cekNoSolution (Matrix M) {
        /* Mengembalikan true jika M tidak mempunyai solusi */
        /* ALGORITMA */
        boolean found = false;
        for (int i = M.getRow() - 1 ; i >= M.getRow() - countBarisKosong(M) ; i-- ) {
            if (M.getMatrix()[i][M.getColumn() - 1] != 0) {
                found = true;
            }
        }
        return found;
    }

    public static void generateMultiSolutionGaussJordan(Matrix M) {
        /* I. S. Matrix M terdefinisi */
        /* Membentuk array koefisien untuk menampung variable bebas */
        /* Menampilkan solusi bebas ke layar dengan metode gauss-jordan*/
        /* KAMUS LOKAL */
        int[] koef = new int[M.getRow()];
        int[] idxNonZero = new int[M.getRow()]; //untuk menyimpan idx non nol pertama
        /* ALGORITMA */
        for (int i = 0 ; i < M.getRow() ; i++) {
            koef[i] = 0;
            idxNonZero[i] = 9999;
        }

        //masih salah validasinya
        for (int i = 0 ; i < M.getRow() ; i++) {
            idxNonZero[i] = getIdxFirstNonZero(M, i);
        }

        int count_koef = 1;
        for (int i = 0 ; i < M.getRow() ; i++) {
            boolean found = false;
            for (int j = 0 ; j < M.getRow() ; j++) {
                if (i == idxNonZero[j]) {
                    found = true;
                }
            }
            if (!found) {
                koef[i] = count_koef;
                count_koef++;
            }
        }

        System.out.println("Misalkan : ");
        for (int i = 0 ; i < M.getRow() ; i++) {
            if (koef[i] != 0) {
                System.out.println("X" + (i + 1) + " = A" + String.format("%d", koef[i]));
            }
        }
        System.out.println("Maka didapatkan : ");
        int k = 0;
        for (int i = 0 ; i < M.getRow() ; i++) {
            if (koef[i] == 0) {
                System.out.print("X" + (i + 1) + " = ");
                for (int j = i ; j < M.getColumn() - 1 ; j++) {
                    if (M.getMatrix()[k][j] != 0 && koef[j] != 0) {
                        System.out.print("(" + String.format("%.3f",(-1) * M.getMatrix()[k][j]) + ")A" + String.format("%d", koef[j]) + "+");
                    }
                }
                System.out.print("(" + String.format("%.3f", M.getMatrix()[k][M.getColumn() - 1]) + ")\n");
                k++;
            }
        }
    }

    public static void generateMultiSolutionGaussJordanFile (Matrix M, String filename) {
        /* I. S. Matrix M terdefinisi, dibaca dari filename */
        /* Membentuk array koefisien untuk menampung variable bebas */
        /* Menampilkan solusi bebas ke layar dengan metode gauss-jordan */
        /* ALGORITMA */
        try {
            int[] koef = new int[M.getRow()];
            int[] idxNonZero = new int[M.getRow()]; //untuk menyimpan idx non nol pertama
            for (int i = 0 ; i < M.getRow() ; i++) {
                koef[i] = 0;
                idxNonZero[i] = 9999;
            }

            //masih salah validasinya
            for (int i = 0 ; i < M.getRow() ; i++) {
                idxNonZero[i] = getIdxFirstNonZero(M, i);
            }

            int count_koef = 1;
            for (int i = 0 ; i < M.getRow() ; i++) {
                boolean found = false;
                for (int j = 0 ; j < M.getRow() ; j++) {
                    if (i == idxNonZero[j]) {
                        found = true;
                    }
                }
                if (!found) {
                    koef[i] = count_koef;
                    count_koef++;
                }
            }
            FileWriter fileWriter = new FileWriter(filename + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            bufferedWriter.write("Misalkan :");
            bufferedWriter.newLine();
            for (int i = 0 ; i < M.getRow() ; i++) {
                if (koef[i] != 0) {
                    bufferedWriter.write("X");
                    bufferedWriter.write(String.format("%d", (i + 1)));
                    bufferedWriter.write(" = A");
                    bufferedWriter.write(String.format("%d", koef[i]));
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.write("Maka didapatkan : ");
            bufferedWriter.newLine();

            int k = 0;
            for (int i = 0 ; i < M.getRow() ; i++) {
                if (koef[i] == 0) {
                    bufferedWriter.write("X" + (i + 1) + " = ");
                    for (int j = i ; j < M.getColumn() - 1 ; j++) {
                        if (M.getMatrix()[k][j] != 0 && koef[j] != 0) {
                            bufferedWriter.write("(" + (-1) * M.getMatrix()[k][j] + ")A" + koef[j] + "+");
                        }
                    }
                    bufferedWriter.write("(" + M.getMatrix()[k][M.getColumn() - 1] + ")");
                    bufferedWriter.newLine();
                    k++;
                }
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Terjadi error dalam penulisan file");
        }
    }

    public static void generateMultiSolutionGauss(Matrix M) {
        /* I. S. Matrix M terdefinisi */
        /* Membentuk array koefisien untuk menampung variable bebas */
        /* Menampilkan solusi bebas ke layar dengan metode gauss */
        /* KAMUS LOKAL */
        int[] koef = new int[M.getRow()]; //untuk menympan koefisien bebas
        int[] idxNonZero = new int[M.getRow()]; //untuk menyimpan idx non nol pertama
        double[] konstanta = new double[M.getRow()];//untuk menimpan nilai konstanta
        /* ALGORITMA */
        for (int i = 0 ; i < M.getRow() ; i++) {
            koef[i] = 0;
            idxNonZero[i] = 9999;
            konstanta[i] = 0;
        }
        for (int i = 0 ; i < M.getRow() ; i++) {
            idxNonZero[i] = getIdxFirstNonZero(M, i);
        }
        int count_koef = 1;
        for (int i = 0 ; i < M.getRow() ; i++) {
            boolean found = false;
            for (int j = 0 ; j < M.getRow() ; j++) {
                if (i == idxNonZero[j]) {
                    found = true;
                }
            }
            if (!found) {
                koef[i] = count_koef;
                count_koef++;
            }
        }
        System.out.println("Misalkan : ");
        for (int i = 0 ; i < M.getRow() ; i++) {
            if (koef[i] != 0) {
                System.out.println("X" + (i + 1) + " = A" + String.format("%d", koef[i]));
            }
        }
        System.out.println("Maka didapatkan : ");
        int k = M.getRow() - countBarisKosong(M) - 1;
        for (int i = M.getRow() - 1 ; i >= 0 ; i--) {
            if (koef[i] == 0) {
                System.out.print("X" + (i + 1) + " = ");
                for (int j = i ; j < M.getRow() ; j++) {
                    if (M.getMatrix()[k][j] != 0 && koef[j] != 0) {
                        System.out.print("(" + String.format("%.3f",(-1) * M.getMatrix()[k][j]) + ")A" + String.format("%d", koef[i]) + "+");
                    }
                }
                double kons = M.getMatrix()[k][M.getColumn() - 1]; //variable untuk menyimpan jumlah konstanta
                for (int p = 0 ; p < M.getRow() ; p++) {
                    kons -= konstanta[p] * M.getMatrix()[k][p];
                }
                System.out.print("(" + String.format("%d", kons) + ")\n");
                konstanta[i] = kons;
                //System.out.println(kons);
                k--;
            }
        }


    }

    public static void showResultGauss(Matrix M) {
        /* I. S. Matrix M terdefinisi */
        /* Menampilkan solusi tergantung kasus (solusi unik, tidak ada solusi, solusi banyak) ke layar */
        /* Menggunakan metode gauss */
        /* ALGORITMA */
        solveGauss(M);
        M.show();
        boolean found = true;
        double arr[][];
        for (int i = 0 ; i < M.getColumn() - 1 ; i++) {
            if (M.getMatrix()[i][i] == 0) found = false;
        }

        System.out.println("Solusi Sistem persamaan linear :");
        if (cekNoSolution(M)) {
            System.out.println("Tidak ada solusi");
        } else if (found) {
            arr = M.getMatrix();
            double[] solution = new double[M.getColumn() - 1];
            double sum;
            for (int c = M.getColumn() - 1 - 1; c >= 0; c--) {
                sum = 0.0;
                for (int d = c + 1; d < M.getColumn() - 1; d++) {
                    sum += arr[c][d] * solution[d];
                }
                solution[c] = (arr[c][arr[0].length - 1] - sum);

            }
            for (int e = 0; e < M.getColumn() - 1; e++) {
                System.out.println("X" + (e + 1) + " = " + String.format("%.3f", solution[e]));
            }
        } else {
            System.out.println("SPL tersebut memiliki banyak solusi.");
            solveGaussJordan(M);
            generateMultiSolutionGaussJordan(M);
        }
    }

    public static void showResultGaussFile(Matrix M, String filename) {
        /* I. S. Matrix M terdefinisi dibaca dari filename */
        /* Menampilkan solusi tergantung kasus (solusi unik, tidak ada solusi, solusi banyak) ke layar */
        /* Menggunakan metode gauss */
        /* ALGORITMA */
        try {
            solveGauss(M);
            boolean found = true;
            double arr[][];
            for (int i = 0 ; i < M.getColumn() - 1 ; i++) {
                if (M.getMatrix()[i][i] == 0) found = false;
            }
            FileWriter fileWriter = new FileWriter(filename + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Solusi Sistem persamaan linear :");
            bufferedWriter.newLine();
            if (cekNoSolution(M)) {
                bufferedWriter.write("Tidak ada solusi");
                bufferedWriter.close();
            } else if (found) {
                arr = M.getMatrix();
                double[] solution = new double[M.getColumn() - 1];
                double sum;
                for (int c = M.getColumn() - 1 - 1; c >= 0; c--) {
                    sum = 0.0;
                    for (int d = c + 1; d < M.getColumn() - 1; d++) {
                        sum += arr[c][d] * solution[d];
                    }
                    solution[c] = (arr[c][arr[0].length - 1] - sum);

                }
                for (int e = 0; e < M.getColumn() - 1; e++) {
                    bufferedWriter.write("X" + (e + 1) + " = " + solution[e]);
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } else {
                bufferedWriter.write("SPL tersebut memiliki banyak solusi.");
                bufferedWriter.close();
                solveGaussJordan(M);
                generateMultiSolutionGaussJordanFile(M, filename);
            }

        } catch (IOException er) {
            System.out.println("Terjadi error dalam penulisan file");
        }

    }


    public static void showResultGaussJordan(Matrix M) {
        /* I. S. Matrix M terdefinisi */
        /* Menampilkan solusi tergantung kasus (solusi unik, tidak ada solusi, solusi banyak) ke layar */
        /* Menggunakan metode gauss-jordan */
        /* ALGORITMA */
        solveGauss(M);
        solveGaussJordan(M);
        boolean found = true;
        for (int i = 0 ; i < M.getColumn() - 1 ; i++) {
            if (M.getMatrix()[i][i] == 0) found = false;
        }
        System.out.println("Solusi Sistem persamaan linear :");
        if (cekNoSolution(M)) {
            System.out.println("Tidak ada solusi");
        } else if (found) {
            for (int i = 0 ; i < M.getColumn() - 1 ; i++) {
                System.out.println("X" + (i + 1) + " = " + String.format("%.3f", M.getMatrix()[i][M.getColumn() - 1]));
            }
        } else {
            System.out.println("SPL tersebut memiliki banyak solusi.");
            generateMultiSolutionGaussJordan(M);
        }

    }

    public static void showResultGaussJordanFile(Matrix M, String filename) {
        /* I. S. Matrix M terdefinisi dibaca dari filename */
        /* Menampilkan solusi sesuai kondsi (solusi unik, tidak ada solusi, solusi banyak) ke layar */
        /* Menggunakan metode gauss-jordan */
        /* ALGORITMA */
        try {
            FileWriter fileWriter = new FileWriter(filename + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            solveGauss(M);
            solveGaussJordan(M);
            boolean found = true;
            for (int i = 0 ; i < M.getColumn() - 1 ; i++) {
                if (M.getMatrix()[i][i] == 0) found = false;
            }
            bufferedWriter.write("Solusi Sistem persamaan linear :\n");
            if (cekNoSolution(M)) {
                bufferedWriter.write("Tidak ada solusi\n");
                bufferedWriter.close();
            } else if (found) {
                for (int i = 0 ; i < M.getColumn() - 1 ; i++) {
                    bufferedWriter.write("X" + (i + 1) + " = " + M.getMatrix()[i][M.getColumn() - 1] + "\n");
                }
                bufferedWriter.close();
            } else {
                bufferedWriter.write("SPL tersebut memiliki banyak solusi.\n");
                bufferedWriter.close();
                generateMultiSolutionGaussJordanFile(M, filename);
            }
        } catch (IOException e) {
            System.out.println("Terjadi error selama penulisan file");
        }
    }
    
}
