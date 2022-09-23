public class Relax {
    private double a, b, e;
    public Relax(double a, double b, double e) {
        this.a = a;
        this.b = b;
        this.e = e;
    }

    public void relax() {
        double m1 = 2, M1 = 6;
        double t = -2 /(m1 + M1);
        double x = Integer.MAX_VALUE, x1 = -0.5;

        double k = 0;
        while (Math.abs(x1 - x) > e) {
            System.out.println("Iteration " + k + " x: " + x);
            x = x1;
            x1 = x + t * Function.f(x);
            k++;
        }

        System.out.println("X = " + x);
        System.out.println("Amount of iterations k= " + k);
    }

}
