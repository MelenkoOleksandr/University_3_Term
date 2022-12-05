import java.util.Arrays;

public class PageRank {

    //Web graph
    // 1 -> 2
    // 2 -> 3, 5
    // 3 -> 4
    // 4 -> 5
    // 5 -> 1, 2, 3, 4
    static double[][] matrix = {
            {0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 0, 1, 0, 1},
            {0, 1, 0, 1, 0}
    };

    // j by Google
    static final double j = 0.15;
    static final double epsilon = 0.0000000000001;

    public static double[][] calculateMarkovMatrix() {
            double[][] markovMatrix = new double[matrix.length][matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                    int ways = 0;
                    for (double[] column : matrix) {
                        if (column[i] == 1) {
                            ways++;
                        }
                    }

                    for (int j = 0; j < matrix.length; j++) {
                            if (matrix[j][i] == 1) {
                                    markovMatrix[j][i] = 1.0 / ways;
                            }
                    }
            }
            return markovMatrix;
    }

    public  static double[][]  createB() {
        double[][] B = new double[matrix.length][matrix.length];
       for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    B[i][j] = 1.0 / matrix.length;
                }
       }
       return B;
    }

    // j*B + (1-j)*A
    public static double[][] calculateM(double[][] markovMatrix) {
        return MatrixHelper.getSumOfMatrices(MatrixHelper.multiplyMatrixByNumber(markovMatrix, 1 - j), MatrixHelper.multiplyMatrixByNumber(createB(), j));
    }

    public static double[] properNumbersByStepsMethod(double[][] markovMatrix) {
        double[] x0 = new double[matrix.length];
        Arrays.fill(x0, 1.0 / matrix.length);
        double[] x1 = new double[matrix.length];
        int i = 1;
        while (true) {
            x1 = MatrixHelper.multiplyMatrixByVector(MatrixHelper.getMatrixDegree(markovMatrix, i), x0);
            if (MatrixHelper.getNorm(MatrixHelper.subtractVectors(x1, x0)) < epsilon) {
                break;
            }
            i++;
            x0 = x1;
        }
        System.out.println(i);
        return x1;
    }

    public static void main(String[] args) {
        double[][] markovMatrix = calculateMarkovMatrix();
        MatrixHelper.printMatrix(markovMatrix, "Markov Matrix");
        double[][] M = calculateM(markovMatrix);
        MatrixHelper.printMatrix(M, "M");
        double[] x = properNumbersByStepsMethod(M);
        MatrixHelper.printVector(x, "x");
    }
}
