import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library myLibrary = new Library();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your Library");
        System.out.println("Select the actions from the list below:");
        System.out.println("showLibrary - lib");
        System.out.println("showAlbums - albs");
        System.out.println("add album - albCreate");
        System.out.println("add new track - track");
        System.out.println("get songs by duration - durationList");
    }
}
