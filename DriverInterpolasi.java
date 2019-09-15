import java.io.FileReader;
import java.util.*;

public class DriverInterpolasi {

    // // bentuk matrix
    //   public static void main(String[] args) {
    //   		double[][] A = {
    //         { 0, 1,  1 ,5},
    //         { 2, 4, -2 ,5},
    //         { 0, 3, 15 ,5}
    //     };
    //   	    Matrix matrix = new Matrix(A);

    // matrix.show();
    // System.out.println("Test Interpolasi:");
     // Interpolasi tester = new Interpolasi();
     public static void main(String[] args) {
       double arr [][] = {{5,7,9},{4,3,8},{7,5,6}};
      Matrix matrix = new Matrix (arr);

      matrix = Dependencies.inversGauss(matrix);
      matrix.show();
    }}
      
  
