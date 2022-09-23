public class Main {
    public static void main(String[] args) {
        System.out.println("\n\nHalfDivision Method: ");
        HalfDivision halfDivision = new HalfDivision(1.1, 3, 0.0001);
        halfDivision.halfDivisionMethod();
        System.out.println("\n\nRelax Method: ");
        Relax relax = new Relax(1, 3, 0.0001);
        relax.relax();
        System.out.println("\n\nNewton Method: ");
        NewtonMethod newtonMethod = new NewtonMethod(1, 3, 0.0001);
        newtonMethod.NewtonMethod();
    }
}
