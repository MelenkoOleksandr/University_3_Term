public class HalfDivision {
    private double a, b, e;

    public HalfDivision(double a, double b, double e) {
        this.a = a;
        this.b = b;
        this.e = e;
    }

    public void halfDivisionMethod() {
        int k = 0;
        double x;
        System.out.printf("a = %.2f b = %.2f e = %f\n", a, b, e);
        if (Function.f(a) * Function.f(b) < 0) {
            System.out.println("Convergence condition is satisfied");
            while (true) {
                x = (a + b) / 2;
                k++;
                if (Math.abs(Function.f(x)) < e)  break;

                if (Function.f(a) * Function.f(x) < 0) {
                    b = x;
                } else {
                    a = x;
                }
            }
            System.out.println("X = " + x);
            System.out.println("Amount of iterations k= " + k);
        } else {
            System.out.println("Convergence condition is NOT satisfied");
        }
    }
}
