import java.util.Arrays;

public class Main {
    public static void  printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] a = {{2, 4, 6, 8}, {1, 2, 3, 4}, {6, 9, 2, 5}, {1, 3, 7, 8}};
        int[][] b = {{3, 7, 3, 0}, {4, 2, 12, 2}, {0, 8, 8, 0}, {2, 12, 19, 1}};

        printMatrix(a);
        printMatrix(b);

        printMatrix(MatrixMultiply.successive(a, b));
        printMatrix(MatrixMultiply.multiply(a, b, 1, "cannon"));
        printMatrix(MatrixMultiply.multiply(a, b, 1, "fox"));
        printMatrix(MatrixMultiply.multiply(a, b, 1, "tape"));
    }
}
