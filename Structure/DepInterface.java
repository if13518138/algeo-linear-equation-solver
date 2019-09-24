interface DepInterface {

    /*** Fungsi untuk menghitung determinan ***/
	public static double kofaktorDet (Matrix M);
    /* Matrix M terdefinisi berukuran nxn */
    /* Mengembalikan determinan matriks menggunakan metode kofaktor */
    public static double sarrusDet (Matrix M);
    /* M harus berukuran 3x3 */
    /* Mengembalikan determinan matriks M menggunakan metode sarrus */
    public static double detOBE(Matrix M);
    /* Matrix M terdefinisi berukuran nxn */
    /* Mengembalikan determinan matriks M menggunakan metode operasi baris elementer */

    /*** Fungsi lain ***/
    public static Matrix hitungKofaktor(Matrix M);
    /* Matrix M terdefinisi dan harus nxn */
    /* Mengembalikan matriks kofaktor dari M */
    public static Matrix hitungAdjoin (Matrix M);
    /* Matriks M terdefinisi dan harus nxn */
    /* Mengembalikan adjoin dari matriks M */
    
    /*** Fungsi untuk menghitung dan menampilkan invers ***/
    public static Matrix inversAdj (Matrix M);
    /* Matrix M terdefinisi dan merupakan matrix nxn */
    /* Mengembalikan matriks balikan dari matrix M dengan metode adjoin */
    private static Matrix createInversMatrix (Matrix M);
    /* Fungsi untuk membentuk augmented matrix yang digunakan dalam proses invers Matriks*/
    /* Membentuk matriks dengan ukuran dari nxn menjadi nx2n*/
    /* Matriks yang baru dibentuk berupa matriks ones*/
    public static Matrix inversGauss (Matrix M);
    /* Matrix M terdefinisi dan merupakan matrix nxn */
    /* Mengembalikan matriks balikan dari matrix M dengan metode gauss - jordan */
}