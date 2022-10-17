import java.util.Arrays;

public class Seidel {
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
        double[] prev_state = new double[n];

        for (int i = 0; i < n; i++) {
            x_prev[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            prev_state[i] = 0;
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
                x_prev[i] =  x_cur[i] / A[i][i];
            }

            if (!conditionIsOk(x_prev, prev_state, eps)) {
                break;
            }
//            System.out.println("Iteration " + iteration + ":");
//            System.out.println(Arrays.toString(x_prev));
//
//            System.out.println("\n");
            System.arraycopy(x_prev, 0, prev_state, 0, n);
        }

        System.out.println("Seidel Result: " + Arrays.toString(x_prev));
    }

    public static void main(String[] args) throws InterruptedException {
        double[][] A = {{5, -1, 2}, {3, 8, -2}, {1, 1, 4}};
        double[] b = {12, -25, 6};
        method(A, b, 0.001);
    }
}
