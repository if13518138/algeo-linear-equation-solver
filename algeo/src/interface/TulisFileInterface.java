package bin;

public interface TulisFileInterface {
	/* Opsi Tulis */ 
	/*
	1. keluaran SPL ( Y x ^ 4 bla blabla)
	2. baca determinan (matriks nxn) (sama juga buat matriks balikan dan kofaktor, adjoin juga) ( keluaran bentuk matriks)
	3. baca interpolasi (kumpulan titik" x y) ( keluaran : polinom dan titik)
	*/


    /* Catatan: */
    /*
	Contoh cara nulis:
	    bufferedWriter.write(" here is some text.");
	Contoh cara kasi newline:
	    bufferedWriter.newLine();
    */
	
    public static void tulisHasilMatriks(String filename, Matrix matrix);
    /* I. S. filename sembarang, matrix terdefinisi yang akan disimpan */
    /* F. S. filename berisi matrix hasil */
    /* Mengeluarkan pesan error apabila filename tidak ada/tidak valid */
    /* Mengeluarkan pesan error apabila gagal menyimpan ke filename */
    public static void tulisHasilInterpolasi(String filename,String polinom,double X,double Y);
    /* I. S. filename sembarang, polinom hasil interpolasi terdefinisi yang akan disimpan */
    /* F. S. filename berisi polinom dan nilai hasil interpolasi */
    /* Mengeluarkan pesan error apabila filename tidak ada/tidak valid */
    /* Mengeluarkan pesan error apabila gagal menyimpan ke filename */
}
