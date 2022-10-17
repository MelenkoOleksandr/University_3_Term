import java.util.Arrays;

public class Jacobi {

    public static boolean conditionIsOk(double[] arr1, double[] arr2, double eps) {
        for (int i = 0; i < arr1.length; i++) {
            if (Math.abs(arr1[i] - arr2[i]) < eps) return false;
        }
        return true;
    }

    public static void method(double[][] A, double[] b, double eps ) throws InterruptedException {
        int iteration = 0;
         int n = b.length;
         double[] x_prev = new double[n];
         for (int i = 0; i < n; i++) {
            x_prev[i] = 0;
         }

         while (true) {
             iteration++;
             double[] x_cur = new double[n];
             for (int i = 0; i < n; i++) {
                 x_cur[i] = b[i];
                 for (int j = 0; j < n; j++) {
                     if (i != j) {
                         x_cur[i] -= (double) (A[i][j] * x_prev[j]) ;
                     }
                 }
                 x_cur[i] /= A[i][i];
             }

             if (!conditionIsOk(x_prev, x_cur, eps)) {
                 System.arraycopy(x_prev, 0, x_cur, 0, n);
                 break;
             }

             System.arraycopy(x_cur, 0, x_prev, 0, n);
         }

        System.out.println("Jacobi Result: " + Arrays.toString(x_prev));
    }

    public static void main(String[] args) throws InterruptedException {
        double[][] A = {{5, -1, 2}, {3, 8, -2}, {1, 1, 4}};
        double[] b = {12, -25, 6};
        method(A, b, 0.001);
    }
}
