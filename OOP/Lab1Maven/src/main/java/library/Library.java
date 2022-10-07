package library;

import tracks.Track;

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

    public Track addNewTrack(String trackGenre, String songName, int songDuration, Singer singer) {
        Track track = Studio.createSong(trackGenre, songName, songDuration, singer);
        library.add(track);
        return track;
    }

    public ArrayList<Track> getSongsByDuration(int durationMin, int durationMax) {
        if (durationMin > durationMax) {
            return new ArrayList<Track>();
        } else {
            ArrayList<Track> songs = new ArrayList<Track>();
            for (Track song : library) {
                if (song.getDuration() >= durationMin && song.getDuration() <= durationMax) {
                    songs.add(song);
                }
            }

            return songs;
        }
    }

    public void addAlbum() {
        System.out.println("Enter album name");
        String albumName = scanner.nextLine();
        Album album = new Album(albumName);
        int songIndex = 0;
        System.out.println("Your library.Library Songs:");
        for (Track song : library) {
            System.out.println("tracks.Track " + songIndex + 1 + "\n" + song.toString());
            songIndex++;
        }

        System.out.println("Select song number to add in album |  q - exit");
        String userInput = scanner.nextLine();
        while (userInput != "q") {
            album.addTrackToAlbum(library.get(Integer.parseInt(userInput) - 1));

            System.out.println("Select song number to add in album |  q - exit");
            userInput = scanner.nextLine();
        }

        System.out.println("library.Album was created");
        albums.add(album);
    }

    public ArrayList<Track> getLibrary() {
        return library;
    }
}
