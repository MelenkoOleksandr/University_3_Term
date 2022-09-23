import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    ArrayList<Track> library = new ArrayList<Track>();
    ArrayList<Album> albums = new ArrayList<Album>();
    Scanner scanner = new Scanner(System.in);

    public void showLibrary() {
        System.out.println("Your tracks:");
        for (Track track : library) {
            System.out.println(track.toString());
        }
    }

    public void showAlbums(){
        for (Album album : albums) {
            album.getAlbumInfo();
        }
    }


    public void addNewTrack() {
        System.out.println("Select song type: | jazz | rock | pop |");
        String songType = scanner.nextLine();
        library.add(Studio.createSong(songType));
    }

    public ArrayList<Track> getSongsByDuration(double durationMin, double durationMax) {
        ArrayList<Track> songs = new ArrayList<Track>();
        System.out.println("Enter the min and max duration of the song in seconds");
        double minDuration = scanner.nextDouble();
        double maxDuration = scanner.nextDouble();
        for (Track song : library) {
            if (song.getDuration() >= minDuration && song.getDuration() <= maxDuration) {
                songs.add(song);
            }
        }

        return songs;
    }

    public void addAlbum() {
        System.out.println("Enter album name");
        String albumName = scanner.nextLine();
        Album album = new Album(albumName);
        int songIndex = 0;
        System.out.println("Your Library Songs:");
        for (Track song : library) {
            System.out.println("Track " + songIndex + 1 + "\n" + song.toString());
            songIndex++;
        }

        System.out.println("Select song number to add in album |  q - exit");
        String userInput = scanner.nextLine();
        while (userInput != "q") {
            album.addTrackToAlbum(library.get(Integer.parseInt(userInput) - 1));

            System.out.println("Select song number to add in album |  q - exit");
            userInput = scanner.nextLine();
        }

        System.out.println("Album was created");
        albums.add(album);
    }
}
