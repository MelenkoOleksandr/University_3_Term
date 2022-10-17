import java.util.Arrays;

public class Gauss {
    public static void method(double[][] A_original, double[] b_original) {
        double[][] A = new double[b_original.length][b_original.length];
        for (int i = 0; i < b_original.length ; i++) {
            System.arraycopy(A_original[i], 0, A[i], 0, b_original.length);
        }
        double[] b = new double[b_original.length];
        System.arraycopy(b_original, 0, b, 0, b_original.length);


        for (int i = 0; i < b.length; i++) {
            double cur = A[i][i];
            for (int j = 0; j < b.length; j++) {
                A[i][j] /= cur;
            }

            b[i] /= cur;

            for (int j = i + 1; j < b.length; j++) {
                double current_line_element = A[j][i];
                for (int k = 0; k < b.length; k++) {
                    A[j][k] = A[j][k] - A[i][k] * current_line_element;
                }
                b[j] = b[j] - b[i] * current_line_element;
            }
        }

        for (int i = b.length - 1; i >= 0; i--) {
            for (int j = 0; j < b.length; j++) {
                if (i!=j) {
                    b[j] -= b[i] * A[j][i];
                    A[j][i] = 0;
                }
            }
        }

        System.out.println("Gaus result: " + Arrays.toString(b));
    }
}
