public class Main {
    public static void main(String[] args) {
        System.out.println("Regression Method: ");
        RegressionMethod regressionMethod = new RegressionMethod(-1, 0, 5);
        regressionMethod.regressionMethod();
        System.out.println("\n\nHalfDivision Method: ");
        HalfDivision halfDivision = new HalfDivision(-0.6, -0.4, 0.0001);
        halfDivision.halfDivisionMethod();
        System.out.println("\n\nNewton Method: ");
        NewtonMethod newtonMethod = new NewtonMethod(-0.6, -0.4, 0.0001);
        newtonMethod.NewtonMethod();
    }
}
