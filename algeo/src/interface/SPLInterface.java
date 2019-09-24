package bin;

public interface SPLInterface {
    
    /*** Metode Invers ***/
    public static double[] solveSPLInvers(Matrix M);
    /* Mengeluarkan array solusi SPL dari Matriks augmented M berukuran nxn+1 */
    /* Matrix koefisien M (tanpa kolom terakhir) harus memiliki determinan != 0 */
    public static void showResultInv(Matrix M);
    /* I. S. Matrix M adalah Matrix SPL augmented */
    /* Menampilkan solusi SPL ke layar */
    /* Mengeluarkan pesan error apabila SPL tidak dapat diselesaikan dengan metode invers */
    
    /*** Metode Crammer ***/
    public static double[] solveSPLCrammer(Matrix M);
    /* Mengeluarkan array solusi SPL dari Matriks augmented M berukuran nxn+1 */
    /* Determinan koefisien M (tanpa kolom terakhir) tidak boleh 0 */
    public static void showResultCrammer(Matrix M);
    /* I. S. Matrix M adalah Matrix spl augmented */
    /* Mengeluarkan pesan error apabila SPL tidak dapat diselesaikan dengan metode crammer */

    /*** Metode Gauss dan Gauss-Jordan ***/
    public static void makeUrutMatriks(double[][] M_in, int n_brs, int n_kol);
    /* I. S. M terdefinisi */
    /* F. S. Membuat M menjadi matriks segitiga bawah (kumpulan 0 di kiri bawah) */
    public static double makeUrutMatriksDet(Matrix M);
    /* M terdefinisi, n adalah baris dan kolom M, count = 1 */
    /* Mengembalikan hasil perkalian -1 tiap di swap */
    public static int getIdxFirstNonZero(Matrix M, int i);
    /* Prekondisi matriks ukuran n*(n+1) */
    /* Mengembalikan index bukan nol pertama dalam matrix pada baris i */
    public static void solveGaussDet(Matrix M);
    /* I. S. Matrix M terdefinisi */
    /* F. S. Matrix M menjadi bentuk eselon */
    public static void solveGauss(Matrix M);
    /* I. S. Matrix M terdefinisi */
    /* F. S. Matrix M menjadi bentuk eselon, diagonalnya semua 1 */
    public static void solveGaussJordan(Matrix M);
    /* I. S. Matrix M terdefinisi dan adalah matriks eselon */
    /* F. S. Matrix M menjadi bentuk eselon tereduksi */
    public static int countBarisKosong(Matrix M);
    /* Mengembalikan jumlah baris yang kosong pada suatu matriks eselon */
    public static boolean cekNoSolution (Matrix M);
    /* Mengembalikan true jika M tidak mempunyai solusi */
    public static void generateMultiSolutionGaussJordan(Matrix M);
    /* I. S. Matrix M terdefinisi */
    /* Membentuk array koefisien untuk menampung variable bebas */
    /* Menampilkan solusi bebas ke layar dengan metode gauss-jordan*/
    public static void generateMultiSolutionGaussJordanFile (Matrix M, String filename);
    /* I. S. Matrix M terdefinisi, dibaca dari filename */
    /* Membentuk array koefisien untuk menampung variable bebas */
    /* Menampilkan solusi bebas ke layar dengan metode gauss-jordan */
    public static void generateMultiSolutionGauss(Matrix M);
    /* I. S. Matrix M terdefinisi */
    /* Membentuk array koefisien untuk menampung variable bebas */
    /* Menampilkan solusi bebas ke layar dengan metode gauss */
    public static void showResultGauss(Matrix M);
    /* I. S. Matrix M terdefinisi */
    /* Menampilkan solusi tergantung kasus (solusi unik, tidak ada solusi, solusi banyak) ke layar */
    /* Menggunakan metode gauss */
    public static void showResultGaussFile(Matrix M, String filename);
    /* I. S. Matrix M terdefinisi dibaca dari filename */
    /* Menampilkan solusi tergantung kasus (solusi unik, tidak ada solusi, solusi banyak) ke layar */
    /* Menggunakan metode gauss */
    public static void showResultGaussJordan(Matrix M);
    /* I. S. Matrix M terdefinisi */
    /* Menampilkan solusi tergantung kasus (solusi unik, tidak ada solusi, solusi banyak) ke layar */
    /* Menggunakan metode gauss-jordan */
    public static void showResultGaussJordanFile(Matrix M, String filename);
    /* I. S. Matrix M terdefinisi dibaca dari filename */
    /* Menampilkan solusi sesuai kondsi (solusi unik, tidak ada solusi, solusi banyak) ke layar */
    /* Menggunakan metode gauss-jordan */
}
