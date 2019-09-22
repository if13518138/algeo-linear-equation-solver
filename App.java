import java.util.Scanner;

public class App {
    public static void clrScr() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void menu() {
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Matriks kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar");
        System.out.println("Pilih menu: ");
    }

    public static void subSPL() {
        System.out.println("Pilih metode");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Crammer");
    }

    public static void subDet() {
        System.out.println("Pilih Metode");
        System.out.println("1. Metode kofaktor");
        System.out.println("2. Metode sarrus (hanya bisa untuk matriks 3x3)");
        System.out.println("3. Metode operasi baris elementer");
    }

    public static void subInv() {
        System.out.println("Pilih Metode");
        System.out.println("1. Metode Gauss-Jordan");
        System.out.println("2. Metode adjoin");
    }

    public static double getDet (Matrix M, int x) {
    /* Matrix M terdefinisi */
    /* untuk menghitung determinan sesuai menu yang dipilih */
    /* x = 1, 2, 3 */
        if (x == 1) {
            return Dependencies.kofaktorDet(M);
        } else if (x == 2) {
            return Dependencies.sarrusDet(M);
        } else if ( x == 3) {
            return Dependencies.detOBE(M);
        } else return 0;
    }

    public static Matrix getInvers (Matrix M, int x) {
    /* Matrix M terdefinisi */
    /* mengembalikan matriks invers sesuai menu yang dipilih */
    /* x = 1, 2 */
        if (x == 1) {
            return Dependencies.inversGauss(M);
        } else if (x == 2) {
            return Dependencies.inversAdj(M);
        } else return M;
    }

    public static void main(String[] args) {
        Matrix M = new Matrix();
        Matrix kof = new Matrix();
        Matrix inv = new Matrix();
        Matrix adj = new Matrix();
        double det;
        int mn, sub;
        boolean keluar = false;
        Scanner scan = new Scanner(System.in);
        String end;
        Interpolasi interpolasi = new Interpolasi(); // instance buat interpolasi
        while (!keluar) {
            menu();
            mn = scan.nextInt();
            clrScr();
            if (mn == 1) { // SPL
                
            } else if (mn == 2) { // determinan
                subDet();
                sub = scan.nextInt();
                clrScr();
                System.out.println("Masukkan matrix nxn:");
                Matrix.inputNxN(M);
                det = getDet(M, sub);
                System.out.println("Determinannya adalah :" + det);
            } else if (mn == 3) { // invers
                // hanya ada jika determinannya tidak = 0
                subInv();
                sub = scan.nextInt();
                clrScr();
                System.out.println("Masukkan matriks nxn:");
                Matrix.inputNxN(M);
                if (Dependencies.kofaktorDet(M) == 0) {
                    System.out.println("Tidak ada invers");
                } else {
                    inv = getInvers(M, sub);
                    System.out.println("Matriks balikannya adalah:");
                    inv.show();
                }
                
            } else if (mn == 4) { // kofaktor
                System.out.println("Masukkan matriks nxn:");
                Matrix.inputNxN(M);
                kof = Dependencies.hitungKofaktor(M);
                System.out.println("Matriks kofaktornya adalah:");
                kof.show();
            } else if (mn == 5) { // adjoin
                System.out.println("Masukkan matriks nxn:");
                Matrix.inputNxN(M);
                adj = Dependencies.hitungAdjoin(M);
                System.out.println("Matriks adjoinnya adalah:");
                adj.show();
            } else if (mn == 6) {
                interpolasi.prosedurInterpolasi();
            } else if (mn == 7) {
                keluar = true;
            }
        }
        
    }
}