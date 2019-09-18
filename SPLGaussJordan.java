
public class SPLGaussJordan {

public static void makeSegitigaBawah(double[][] M_in, int n_brs, int n_kol){
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

public static Matrix solveGaussJordan(Matrix M){
    /*Mengembalikan matriks berupa bentuk eselon baris tereduksi */
    /*Kamus */
    int i, j, k;
    double x;

    /*Algoritma */
    double[][] M_in = M.getMatrix();
    int n_brs = M.getRow();
    int n_kol = M.getColumn();
    double[][] z = new double[n_brs][n_kol];
    Matrix result = new Matrix(z);

    //Membentuk matriks tereduksi
    for(j = 0 ; j < n_brs ; j++){
        makeSegitigaBawah(M_in, n_brs, n_kol);
        for (i = 0 ; i < n_brs ; i++){
            if (i != j){
                if (M_in[j][j] != 0){
                    x = M_in[i][j]/M_in[j][j];
                    for (k=0 ; k < n_brs+1 ; k++) {
                        M_in[i][k] = M_in[i][k] - x*M_in[j][k];
                    }
                }
            }
        }
    }

    //bikin jadi jadi satu pertama
    for (int p = 0 ; p < n_brs ; p++){
        int q = 0;
        while (M_in[p][q]== 0 && q<n_kol-1){
            q++;
        }
        if (q != n_kol-1){
            for (int r = q+1 ; r < n_kol ; r++){
                //System.out.print(y);
                //System.out.print("\n");
                M_in[p][r] = M_in[p][r] / M_in[p][q];
            }
            M_in[p][q] = 1;
        }
    }
    
    result.setRow(n_brs);
    result.setColumn(n_kol);
    result.setMatrix(M_in);
    return result;
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
    int[] koef = new int[M.getRow()];
    for (int i = 0 ; i < M.getRow() ; i++){
        koef[i] = 0;
    }
    
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
                if (M.getMatrix()[k][j] != 0){
                    System.out.print("(" + M.getMatrix()[k][j] + ")A" + koef[j] +"+");
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
    M = solveGaussJordan(M);
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
    //double arr [][] = {{0, 1, 0, 0, 0, 1, 1}, {0, 0, 0, 1, 1, 0, -1},{0, 0, 0, 0, 1, -1, 1},{0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0}}; 
    double arr [][] = {{1, -1, 0, 0, 1, 0,3}, {1, 1, 0, -3, 0,0, 6}, {2, -1, 0, 1, -1,0, 5}, {-1, 2, 0, -2, -1,0, -1},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0}};
    Matrix matrix = new Matrix (arr);
    matrix.show();
    System.out.print("==========================\n");
    matrix = solveGaussJordan(matrix);
    matrix.show();
    System.out.println("ini banyaknya row kosong " + countBarisKosong(matrix));
    showResult(matrix);
    
}

}