
// Math.pow(x, 2) - 4;
// 2 * x;
// 2;

public class Function {
    public static double f(double x) {
        return  2*Math.pow(x, 3) - 11*Math.pow(x, 2) + 12*x +9;
    }

    //First derivative
    public static double f1(double x) {
        return 6*Math.pow(x, 2) - 22*x + 12;
    }

    //Second derivative
    public static double f2(double x) {
        return 12*x - 22;
    }
}
