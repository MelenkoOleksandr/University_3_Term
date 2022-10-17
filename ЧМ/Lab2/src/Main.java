public class Main {
    public static void main(String[] args) throws Exception {
        int matrixSize = 20;
        double[][] A = MatrixGenerator.createRandomMatrix(matrixSize);
        double[] b = MatrixGenerator.createVector(A, matrixSize);
        double eps = 0.001;

        //while (!MatrixGenerator.checkConvergence(A)) {
         //   A = MatrixGenerator.createRandomMatrix(matrixSize);
        //}
        MatrixGenerator.printMatrix(A);

        Gauss.method(A, b);
        Jacobi.method(A, b, eps);
        Seidel.method(A, b, eps);
    }
}
