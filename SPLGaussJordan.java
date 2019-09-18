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

public static void showResult(Matrix matrix){
    //untuk nampilin hasil spl sesuai kondisi

}

//buat test
public static void main(String[] args){
    double arr [][] = {{1, -1, 0, 0, 1, 3}, {1, 1, 0, -3, 0, 6},{2, -1, 0, 1, -1, 5},{-1, 2, 0, -2, -1, -1}}; 
    Matrix matrix = new Matrix (arr);
    matrix.show();
    System.out.print("==========================\n");
    matrix = solveGaussJordan(matrix);
    solveGaussJordan(matrix);
    matrix.show();
}

}