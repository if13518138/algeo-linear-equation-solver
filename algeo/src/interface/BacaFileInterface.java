package bin;

public interface BacaFileInterface {
	/* Opsi Baca */
	/*
	1. baca SPL (Matriks nxn+1)
	2. baca determinan (matriks nxn) (sama juga buat matriks balikan dan kofaktor, adjoin juga)
	3. baca interpolasi (kumpulan titik" x y)
	*/
    /*** Fungsi Lain ***/
	public static int[] jumlahData(String filename);
    /* Mengembalikan panjang data dengan dimensi(bentuk data) */
	/* Nilai pertama mengembalikan nilai panjang file */
	/* Nilai kedua mengembalikan nilai lebar file */

    /*** Fungsi Input ***/
    public Matrix inputMatrix();
    /* Dilakukan  sesuai opsi */
    public Matrix inputPersamaanMatrix();
	/* Dilakukan pembacaan terhadap sistem persamaan linear */
	public double[][] inputTitik();
	/* Dilakukan pembacaan titik disimpan dalam matriks / array multidimensional */
}
