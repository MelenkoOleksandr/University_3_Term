package library;

import tracks.JazzSong;
import tracks.PopSong;
import tracks.RockSong;
import tracks.Track;

public class Studio {
    public static Track createSong(String trackGenre, String songName, int songDuration, Singer singer) {
        return switch (trackGenre) {
            case "pop" -> new PopSong(songName, singer, songDuration);
            case "jazz" -> new JazzSong(songName, singer, songDuration);
            case "rock" -> new RockSong(songName, singer, songDuration);
            default -> new Track(songName, singer, songDuration);
        };
    }

}
