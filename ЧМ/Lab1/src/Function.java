
// Math.pow(x, 2) - 4;
// 2 * x;
// 2;


// 2*Math.pow(x, 3) - 11*Math.pow(x, 2) + 12*x +9;
// 6*Math.pow(x, 2) - 22*x + 12;
//12*x - 22;

//Math.pow(x, 3) - 8;
//3 * Math.pow(x, 2);
//6*x;

public class Function {
    public static double f(double x) {
        return Math.pow(x, 2) - 4;
    }

    //First derivative
    public static double f1(double x) {
        return 2 * x;
    }

    //Second derivative
    public static double f2(double x) {
        return 2;
    }
}
