
public class SPLGaussJordan {

public static void makeUrutMatriks(double[][] M_in, int n_brs, int n_kol){
    /*Membuat matriks menjadi matriks segitiga bawah (kumpulan 0 di kiri bawah) */
    /*KAMUS */
    int i, j, k;
    double x;

    /*Algoritma */
    int t = 0;
    for(j = 0 ; j < n_brs ; j++){
        for (int p = t ; p < n_brs ; p++){
            for(int counter = p+1 ; counter < n_brs ; counter++){
                if (M_in[p][j] == 0 && M_in[counter][j]!=0){
                    //swap
                    for (int r = 0; r<n_kol; r++){
			            double temp = M_in[p][r];
			            M_in[p][r] = M_in[counter][r];
			            M_in[counter][r] = temp;
		            }
                } 
            }
            if (M_in[p][j] != 0){
                t = t + 1;
            }
        }
    }
}

public static int getIdxFirstNonZero(Matrix M, int i){
    //Prekondisi matriks ukuran n*(n+1)
    for (int j = 0 ; j < M.getRow() ; j++){ //cek ke baris tersebut dari kolom 1 sampe maksimal kolom -1
        if (M.getMatrix()[i][j] != 0){
            return j;
        }
    }
    return -999;
}

public static void solveGauss(Matrix M){
    for (int i = 0 ; i<M.getRow() ; i++ ){ //untuk pengulangan ke bawah
        int idxNonZero = getIdxFirstNonZero(M, i); //index non zero
        if (idxNonZero != -999){ // validasi index non zero
            for (int p = i+1 ; p < M.getRow() ; p++){
            //pengulangan untuk index dibawah p, jika tidak sama dengan nol maka indeks yang di bawah i dikurangin
            //dengan kelipatan nya supaya hasilnya 0
                if (M.getMatrix()[p][idxNonZero] != 0){
                    double x = M.getMatrix()[p][idxNonZero]/M.getMatrix()[i][idxNonZero];
                    for (int k = 0 ; k < M.getColumn() ; k++){
                        M.getMatrix()[p][k] = M.getMatrix()[p][k] - x*M.getMatrix()[i][k];
                    }
                }
            }
        }
    }
}

public static void solveGaussJordan(Matrix M){
    for (int i = M.getRow()-1 ; i>=0 ; i-- ){ //untuk pengulangan ke bawah
        int idxNonZero = getIdxFirstNonZero(M, i); //index non zero
        if (idxNonZero != -999){ // validasi index non zero
            for (int p = i-1 ; p >= 0 ; p--){
            //pengulangan untuk index diatas p, jika tidak sama dengan nol maka indeks yang di bawah i dikurangin
            //dengan kelipatan nya supaya hasilnya 0
                if (M.getMatrix()[p][idxNonZero] != 0){
                    double x = M.getMatrix()[p][idxNonZero]/M.getMatrix()[i][idxNonZero];
                    for (int k = 0 ; k < M.getColumn() ; k++){
                        M.getMatrix()[p][k] = M.getMatrix()[p][k] - x*M.getMatrix()[i][k];
                    }
                }
            }
        }
    }
}


public static int countBarisKosong(Matrix M){
    /*Menghitung baris yang kosong pada suatu matriks eselon tereduksi */
    int count = 0;
    for (int i = 0 ; i < M.getRow() ; i++){
        int count_b = 0;
        for (int j = 0 ; j < M.getRow() ; j++){
            if (M.getMatrix()[i][j] == 0) count_b++;
        }
        if (count_b == M.getRow()){
            count ++;
        }
    }
    return count;
}

public static boolean cekNoSolution (Matrix M){
    /*Mengembalikan true jika M tidak mempunyai solusi */
    boolean found = false;
    for (int i = M.getRow()-1 ; i >= M.getRow()-countBarisKosong(M) ; i-- ){
        if (M.getMatrix()[i][M.getColumn()-1] != 0){
            found = true;
        }
    }
    return found;
}

public static void generateMultiSolution(Matrix M){
    //membentuk array koefisien untuk menampung variable bebas
    //inisiasi 0 semua elemen array
    int[] koef = new int[M.getRow()];
    for (int i = 0 ; i < M.getRow() ; i++){
        koef[i] = 0;
    }
    
    //masih salah validasinya
    int count_koef = 1;
    for (int j = 0 ; j < M.getRow() ; j++){
        int count_zero = 0;
        for (int i = 0 ; i < M.getRow() ; i++){
            if (M.getMatrix()[i][j] == 1) count_zero++;
        }
        if (count_zero != 1){
            koef[j] = count_koef;
            count_koef++;
        }
    } 

    System.out.println("Misalkan : ");
    for (int i = 0 ; i < M.getRow() ; i++){
        if (koef[i] != 0){
            System.out.println("X" + (i+1) + " = A" + koef[i]);
        }
    }
    System.out.println("Maka didapatkan : ");
    int k = 0;
    for (int i = 0 ; i < M.getRow() ; i++){
        if (koef[i] == 0){
            System.out.print("X" + (i+1) + " = ");
            for (int j = i ; j < M.getRow() ; j++){
                if (M.getMatrix()[k][j] != 0 && koef[j] != 0){
                    System.out.print("(" + (-1)*M.getMatrix()[k][j] + ")A" + koef[j] +"+");
                }
            }
            System.out.print("(" + M.getMatrix()[k][M.getColumn()-1] +")\n");
            k++;
        }
    }


    /* untuk debugg
    for (int i = 0 ; i < M.getRow() ; i++){
        System.out.println(i + "  >>>   " + koef[i]);
    }*/
}
public static void showResult(Matrix M){
    //untuk nampilin hasil spl sesuai kondisi
    solveGaussJordan(M);
    boolean found = true;
    for (int i = 0 ; i < M.getRow() ; i++){
        if (M.getMatrix()[i][i] == 0) found = false;
    }
    if (found){
        for (int i = 0 ; i<M.getRow() ; i++){
            System.out.println("X" + (i+1) + " = " + M.getMatrix()[i][M.getColumn()-1]);
        }
    } else if (cekNoSolution(M)){
        System.out.println("Tidak ada solusii");
    } else {
        System.out.println("solusi banyak lurr");
        generateMultiSolution(M);
    }

}

//buat test
public static void main(String[] args){
    //double arr [][] = {{1,2,3,6},{4,5,9,1},{0,9,6,3}}; 
    //double arr [][] = {{0, 0, 0, 0, 0, 0, 0},{0, 1, 0, 0, 0, 1, 1}, {0, 0, 0, 1, 1, 0, -1},{0, 0, 0, 0, 1, -1, 1},{0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0}}; 
    //double arr [][] = {{0,0,0,1},{1, 0, 0, 0},{0,1,2,0}}; //solve //tidak ada solusi
    //double arr [][] = {{1,0,3,-1},{0,1,-4,2},{0,0,0,0}}; //belomsolve
    double arr [][] = {{1,-5,1,4},{0,0,0,0},{0,0,0,0}}; //belom solve
    //double arr [][] = {{1,-1,0,0,1,3},{1,1,0,-3,0,6},{2,-1,0,1,-1,5},{-1,2,0,-2,-1,-1},{0,0,0,0,0,0}};
    //double arr [][] = {{0,0,1,3,1},{0,0,0,1,0},{1,0,1,2,3},{2,0,2,4,1}};
    Matrix matrix = new Matrix (arr);
    matrix.show();
    System.out.print("==========================\n");
    makeUrutMatriks(matrix.getMatrix(), matrix.getRow(), matrix.getColumn());
    matrix.show();
    System.out.print("==========================\n");
    solveGauss(matrix);
    matrix.show();
    System.out.print("==========================\n");
    makeUrutMatriks(matrix.getMatrix(), matrix.getRow(), matrix.getColumn());
    matrix.show();
    System.out.print("==========================\n");
    solveGaussJordan(matrix);
    matrix.show();
    //System.out.println("ini banyaknya row kosong " + countBarisKosong(matrix));
    System.out.print("==========================\n");
    showResult(matrix);
    
}

}