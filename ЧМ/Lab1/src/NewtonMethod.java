public class NewtonMethod {
    private double a, b, e;

    public NewtonMethod(double a, double b, double e) {
        this.a = a;
        this.b = b;
        this.e = e;
    }

    public void NewtonMethod() {
        double x;
        int k = 0;
        System.out.printf("a = %.2f b = %.2f e = %f\n", a, b, e);
        if (Function.f(a) * Function.f2(a)>0) {
            System.out.println("Convergence condition is satisfied for x = a = " + a);
            x = a;
        } else {
            if (Function.f(b) * Function.f2(b) > 0) {
                System.out.println("Convergence condition is satisfied for x = b = " + b);
                x = b;
            } else {
                System.out.println("Convergence condition is NOT satisfied");
                return;
            }
        }

        while (true) {
            x = x - Function.f(x) / Function.f1(x);
            k++;
            if (Math.abs(Function.f(x)) < e) break;
        }

        System.out.println("X = " + x);
        System.out.println("Amount of iterations k= " + k);
    }
}
