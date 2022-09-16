public class RegressionMethod {

    private double a, b, n;

    public RegressionMethod(double a, double b, double n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    public void regressionMethod() {
        double h = (b - a) / n;
        System.out.printf("a = %.2f b = %.2f  n = %f h = %f\n", a, b, n, h);
        int k = 0;
        System.out.println("x   f(x)");
        for (double x = a; x <= b; x = x + h) {
            System.out.print(x + "   " + Function.f(x) + " ");
            if (x < b && Function.f(x) * Function.f(x + h) < 0) {
                System.out.println("Isolation gap " + x +"; " + (x + h));
                k++;
            } else {
                System.out.println();
            }
        }
        System.out.println("Amount of iterations: " + k);
    }
}
