package tubessantuy;
import java.util.Scanner;
import java.io.*;


public class App {
    public static void clrScr() {
        System.out.flush();
    }

    public static void menu() {
        System.out.println("=====================");
        System.out.println("||     Welcome     ||");
        System.out.println("||       To        ||");
        System.out.println("||   AlgeoSantuy   ||");
        System.out.println("=====================");
        System.out.println();
        System.out.println("       ++++++");
        System.out.println("       +MENU+");
        System.out.println("       ++++++");
        System.out.println();
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Matriks kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar");
        System.out.println();
        System.out.print("> Pilih menu: ");
    }

    public static void subSPL() {
        System.out.println();
        System.out.println();
        System.out.println("       ==============");
        System.out.println("       Pilihan Metode");
        System.out.println("       ==============");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Crammer");
        System.out.print("> Pilihan metode : ");
    }

    public static void subDet() {
        System.out.println();
        System.out.println();
        System.out.println("       ==============");
        System.out.println("       Pilihan Metode");
        System.out.println("       ==============");
        System.out.println("1. Metode kofaktor");
        System.out.println("2. Metode sarrus (hanya bisa untuk matriks 3x3)");
        System.out.println("3. Metode operasi baris elementer");
        System.out.print("> Pilihan metode : ");

    }

    public static void subInv() {
        System.out.println();
        System.out.println();
        System.out.println("       ==============");
        System.out.println("       Pilihan Metode");
        System.out.println("       ==============");
        System.out.println("1. Metode Gauss-Jordan");
        System.out.println("2. Metode adjoin");
        System.out.print("> Pilihan metode : ");

    }
    public static boolean isInputFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("> Apakah input dari file ? (y/n)");
        String s;
        do {
            s = scanner.next();
            if (!(s.equals("Y") || s.equals("y") || s.equals("N") || s.equals("n"))) {
                System.out.println("Masukkan tidak valid. Coba lagi!");
                System.out.print("> Apakah input dari file ?(y/n): ");
            }
        } while (!(s.equals("Y") || s.equals("y") || s.equals("N") || s.equals("n")));
        return (s.equals("Y") || s.equals("y"));
    }

    public static String inputNamaFIle() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("> Masukkan nama file : ");
        String s;
        s = scanner.next();
        return s;
    }

    public static boolean isSalinFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("> Salin ke file? (y/n): ");

        String s;
        do {
            s = scanner.next();
            if (!(s.equals("Y") || s.equals("y") || s.equals("N") || s.equals("n"))) {
                System.out.println("Masukkan tidak valid. Coba lagi!");
                System.out.print("> Salin ke file? (y/n): ");
            }
        } while (!(s.equals("Y") || s.equals("y") || s.equals("N") || s.equals("n")));

        return (s.equals("Y") || s.equals("y"));
    }

    public static String namaFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("> Masukkan nama file: ");

        String s;
        s = scanner.next();

        return s;
    }



    public static void showSPL(Matrix M, int x) {
        /* Matrix M terdefinisi */
        /* untuk menghitung SPL sesuai menu yang dipilih */
        /* x = 1, 2, 3, 4 */
        if (x == 1) {
            //eliminasi gauss
            SPL.showResultGauss(M);
        } else if (x == 2) {
            //eliminasi gauss jordan
            SPL.showResultGaussJordan(M);
        } else if (x == 3) {
            //metode matriks balikan
            SPL.showResultInv(M);
        } else if (x == 4) {
            //metode creammer
            SPL.showResultCrammer(M);
        }


    }

    public static void writeSPL(Matrix M, int x, String filename) {
        /* Matrix M terdefinisi */
        /* untuk menghitung SPL sesuai menu yang dipilih */
        /* x = 1, 2, 3, 4 */
        if (x == 1) {
            SPL.showResultGaussFile(M, filename);
        } else if (x == 2) {
            SPL.showResultGaussJordanFile(M, filename);
        } else if (x == 3) {
            SPL.writeResultInv(M, filename);
        } else if (x == 4) {
            SPL.writeResultCrammer(M, filename);
        }
    }

    public static void writeDet(double det, String filename){
        Dependencies.writeDet(det,filename);
        System.out.println("Success..");
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
        /* mengembalikan matriks invers sesConvolutional Neural Network (CNN) adalah salah satu jenis neural network yang biasa digunakan pada data image. CNN bisa digunakan untuk mendeteksi dan mengenali object pada sebuah image.uai menu yang dipilih */
        /* x = 1, 2 */
        if (x == 1) {
            return Dependencies.inversGauss(M);
        } else if (x == 2) {
            return Dependencies.inversAdj(M);
        } else return M;
    }

    // public static Matrix writeInvers (Matrix M, int x) {
    //     /* Matrix M terdefinisi */
    //     /* mengembalikan matriks invers sesuai menu yang dipilih */
    //     /* x = 1, 2 */

    // }

    public static Matrix inputMatrix(String filename) {
        int[] bentukMatriks = BacaFile.jumlahData(filename);

        double[][] arr = new double[bentukMatriks[0]][bentukMatriks[1]];

        try {
            File file = new File(filename + ".txt");

            Scanner scanner = new Scanner(file);

            String s;
            String[] sSplit;
            double[] arrTemp = new double[bentukMatriks[1]];
            int count = 0;

            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                sSplit = s.split(" ");
                for (int i = 0; i < bentukMatriks[1]; i++) {
                    arr[count][i] = Double.parseDouble(sSplit[i]);
                }
                count++;
            }
            Matrix matrix = new Matrix(arr);
            scanner.close();
            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan");

            Matrix matrix = new Matrix(arr);
            return matrix;
        }
    }

    public static void writeMatrix(Matrix M, String filename){
        Dependencies.writeMatrix(M,filename);
    }

    /*Dilakukan pembacaan terhadap sistem persamaan linear*/
    public static Matrix inputPersamaanMatrix(String filename) {
        Matrix matrix = inputMatrix(filename);

        /*Dilakukan penambahan apabila bentuk tidak */
        while (matrix.getRow() < matrix.getColumn() - 1) {
            matrix.setRow(matrix.getRow() + 1);
            double[][] arr = new double[matrix.getRow()][matrix.getColumn()];

            for (int i = 0; i < matrix.getRow(); i++) {
                if (i != matrix.getRow() - 1) {
                    for (int j = 0; j < matrix.getColumn(); j++) {
                        arr[i][j] = matrix.getMatrix()[i][j];
                    }
                } else {
                    for (int j = 0; j < matrix.getColumn(); j++) {
                        arr[i][j] = 0;
                    }
                }
            }
            matrix.setMatrix(arr);
        }
        return matrix;
    }


    public static void main(String[] args) {
        String nama;


        Matrix M = new Matrix();
        Matrix kof = new Matrix();
        Matrix inv = new Matrix();
        Matrix adj = new Matrix();
        Matrix spl = new Matrix();
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
                subSPL();
                sub = scan.nextInt();
                clrScr();
                if (isInputFile()){
                    nama = inputNamaFIle();
                    spl = inputPersamaanMatrix(nama);
                } else {
                    Matrix.inputSPL(spl);
                }
                showSPL(spl, sub);
                if (isSalinFile()) {
                    nama = namaFile();
                    writeSPL(spl, sub, nama);
                }
            } else if (mn == 2) { // determinan
                subDet();
                sub = scan.nextInt();
                clrScr();
                if (isInputFile()){
                    nama = inputNamaFIle();
                    M = inputPersamaanMatrix(nama);
                }else {
                    System.out.print("Masukkan matrix nxn:");
                    Matrix.inputNxN(M);
                }
                det = getDet(M, sub);
                System.out.println("Determinannya adalah :" + det);
                if (isSalinFile()){
                    nama = namaFile();
                    writeDet(det, nama);
                }
                M.show();
            } else if (mn == 3) { // invers
                // hanya ada jika determinannya tidak = 0
                subInv();
                sub = scan.nextInt();
                clrScr();
                if (isInputFile()){
                    nama = inputNamaFIle();
                    M = inputPersamaanMatrix(nama);
                } else {
                    System.out.print("Masukkan matriks nxn:");
                    Matrix.inputNxN(M);
                }
                if (Dependencies.kofaktorDet(M) == 0) {
                    System.out.println("Tidak ada invers");
                    if(isSalinFile()){
                        nama = namaFile();
                        try{
                            FileWriter fileWriter = new FileWriter(nama + ".txt");
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                            bufferedWriter.write("Tidak ada invers");
                            bufferedWriter.close();
                            System.out.println("Succes..");
                        } catch (IOException e){
                            System.out.println("Error dalam menulis file");
                        }
                    }
                } else {
                    inv = getInvers(M, sub);
                    System.out.println("Matriks balikannya adalah:");
                    inv.show();
                    if (isSalinFile()){
                        nama = namaFile();
                        writeMatrix(inv,nama);
                    }
                }

            } else if (mn == 4) { // kofaktor
                if (isInputFile()){
                    nama = inputNamaFIle();
                    M = inputPersamaanMatrix(nama);
                } else {
                    System.out.print("Masukkan matriks nxn:");
                    Matrix.inputNxN(M);
                }

                kof = Dependencies.hitungKofaktor(M);
                System.out.println("Matriks kofaktornya adalah:");
                kof.show();
                if (isSalinFile()){
                    nama = namaFile();
                    writeMatrix(kof, nama);
                }
            } else if (mn == 5) { // adjoin
                if (isInputFile()){
                    nama = inputNamaFIle();
                    M = inputPersamaanMatrix(nama);
                } else {
                    System.out.print("Masukkan matriks nxn:");
                    Matrix.inputNxN(M);
                }
                adj = Dependencies.hitungAdjoin(M);
                System.out.println("Matriks adjoinnya adalah:");
                adj.show();
                if (isSalinFile()){
                    nama = namaFile();
                    writeMatrix(adj,nama);
                }
            } else if (mn == 6) {
                if (isInputFile()){
                    nama = inputNamaFIle();
                    interpolasi.showResultFileInterpolasi(nama);
                    
                } else {
                    interpolasi.prosedurInterpolasi();
                }
                
                if (isSalinFile()){
                    nama = namaFile();
                    interpolasi.writeInterpolasi(nama);
                }
            } else if (mn == 7) {
                keluar = true;
            }
        }
        scan.close();
    }
}
