import java.util.Scanner;

public class Studio {
    public static Track createSong(String trackGenre) {
        Scanner scanner = new Scanner(System.in);
        String songName = scanner.nextLine();
        double songDuration = Double.parseDouble(scanner.nextLine());
        Singer singer = new Singer(scanner.nextLine());

        return switch (trackGenre) {
            case "pop" -> new PopSong(songName, singer, songDuration, trackGenre);
            case "jazz" -> new JazzSong(songName, singer, songDuration, trackGenre);
            case "rock" -> new RockSong(songName, singer, songDuration, trackGenre);
            default -> new Track(songName, singer, songDuration, trackGenre);
        };
    }

}
