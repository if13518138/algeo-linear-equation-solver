package bin;

public interface MatrixInterface {

    // /*** Konstruktor ***/
    // public Matrix ();
    // /* Untuk membentuk Matrix kosong dengan jumlah baris dan kolom 1 */
    // public Matrix (int m, int n);
    // /* Untuk membentuk Matrix kosong dengan jumlah baris m dan jumlah kolom n */
    // public Matrix (double[][] data);
    // /* Untuk membentuk Matrix yang komponen matrixnya adalah data */

    /*** Getter ***/
    public int getRow();
    /* Mengembalikan jumlah baris Matrix */
    public int getColumn();
    /* Mengembalikan jumlah kolom Matrix */
    public double[][] getMatrix();
    /* Mengembalikan komponen matrix */

    /*** Setter ***/
    public void setRow(int m);
    /* Untuk mengubah elemen M dari Matrix menjadi m */
    public void setColumn(int n);
    /* Untuk mengubah elemen N dari Matrix menjadi n */
    public void setMatrix(double[][] arr);
    /* Untuk mengubah elemen matrix dari Matrix menjadi arr */
    public void setElmtMatrix(double x, int i, int j);
    /* Untuk mengubah elemen matrix baris i kolom j dengan nilai x */

    /*** Operasi Pada Matrix ***/
    public void swap_row(int i, int j);
    /* I. S. Matrix terdefinisi */
    /* F. S. Matriks baris i telah bertukar tempat dengan matriks baris j */
    public static Matrix delBrsMatrix (Matrix M, int idx_brs);
    /* Matrix M terdefinisi, idx_brs <= baris M*/
    /* Mengembalikan Matrix yang menghilangkan baris ke idx_brs */
    public static Matrix delKolMatrix (Matrix M, int idx_kol);
    /* Matrix M terdefinisi, idx_kol <= kolom M*/
    /* Mengembalikan Matrix yang menghilangkan kolom ke idx_kol */
    public static Matrix multiplication (Matrix M, Matrix N);
    /* Matrix M dan N terdefinisi dan jumlah kolom M = jumlah baris N */
    /* Mengembalikan matriks hasil perkalian MxN */
    public static Matrix minor (Matrix M, int idx_brs, int idx_kol);
    /* Matrix M terdefinisi dan harus nxn, idx_brs <= baris M, idx_kol <= kolom M */
    /* Mengembalikan sub matriks/minor Matrix yang menghilangkan baris ke idx_brs dan kolom ke idx_kol */
    public static Matrix transpose (Matrix M);
    /* Matrix M terdefinisi, tidak harus nxn */
    /* Mengembalikan Matrix transpose baris dan kolomnya */
    /* Apabila M adalah matrix 2x3, maka keluarannya adalah matrix 3x2 */

    /*** Fungsi Lain ***/
    public Boolean isSquare();
    /* Mengembalikan true apabila ukuran Matrix nxn */
    public void show();
    /* I. S. Matrix terdefinisi */
    /* F. S. Matrix ditampilkan pada layar */
    public static void input(Matrix M);
    /* I.S. Matrix M sembarang */
    /* F.S. terbentuk matriks M terdefinisi dengan jumlah baris dan kolom sesuai input */
    public static void inputSPL(Matrix M);
    /* I.S. Matrix M sembarang */
    /* F.S. terbentuk Matrix M terdefinisi */
    /* Matrix M berupa Matrix augmented */
    public static void inputNxN(Matrix M);
	/* I.S. Matrix M sembarang */
    /* F.S. terbentuk Matrix M terdefinisi */
    /* Matrix M berupa Matrix berukuran nxn */

}
