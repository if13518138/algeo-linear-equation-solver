public class SPLGaussJordan {

public static void makeSegitigaBawah(Matrix M){
    boolean found = true;
    while(found) {
    for (int j = 0 ; j<M.getRow() ; j++){
        int i =0;
        while(i <M.getColumn()){
        
            boolean foundswap = true;
            int counter = i+1 ;
            found = false;
            while (foundswap && counter<M.getColumn()){
                if (M.getMatrix()[i][j] == 0 && M.getMatrix()[counter][j]!=0){
                    M.swap_row(i, counter);
                    found = true;
                    foundswap = false;
                } else {
                    counter ++;
                }
            }
            if (M.getMatrix()[i][j] != 0 ){
                i++;
            }
        }
    }
    }
}

public static Matrix solveGaussJordan(Matrix M){
    int i, j, k;
    double x;
    double[][] M_in = M.getMatrix();
    int n_brs = M.getRow();
    int n_kol = M.getColumn();
    double[][] z = new double[n_brs][n_kol];
    Matrix result = new Matrix(z);

    //make reduction echelon tapi belom 1 1
    for(j = 0 ; j < n_kol ; j++){
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
    
    //bikin jadi 1 1
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
    double arr [][] = {{2,1,8,3,10},{1,5,6,9, 7},{1,5,6,9, 7},{5,6,8,9, 15}}; 
    Matrix matrix = new Matrix (arr);
    matrix.show();
    System.out.print("==========================\n");
    makeSegitigaBawah(matrix);
    matrix.show();
}

}