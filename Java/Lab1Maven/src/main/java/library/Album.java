package library;

import tracks.Track;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Album {
    ArrayList<Track> albumTracks = new ArrayList<>();
    private String albumName;

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public ArrayList<Track> getAlbumTracks() {
        return albumTracks;
    }

    public void addTrackToAlbum(Track track) {
        System.out.println("tracks.Track " + track.getName() + " was added to album");
        albumTracks.add(track);
    }

    public int getAlbumDuration() {
        return albumTracks.stream().map(Track::getDuration).reduce(0, Integer::sum);
    }

    public void deleteTrackFromAlbum(Track track) {
        albumTracks.remove(track);
    }

    public void changeAlbumOrderByGenre(String trackGenre) {
        albumTracks.sort(new Comparator<Track>() {
            @Override
            public int compare(Track o1, Track o2) {
                if ((Objects.equals(o1.getGenre(), trackGenre) && Objects.equals(o2.getGenre(), trackGenre)) || (!Objects.equals(o1.getGenre(), trackGenre) && !Objects.equals(o2.getGenre(), trackGenre))) {
                    return 0;
                } else if (Objects.equals(o1.getGenre(), trackGenre) && !Objects.equals(o2.getGenre(), trackGenre)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
    }

    public void getAlbumInfo() {
        System.out.println("library.Album: " + albumName + "\nDuration: " + getAlbumDuration());
    }

    @Override
    public String toString() {
        String tracksString = "";
        for (Track track: albumTracks) {
            tracksString += track.getGenre() + "\n";
        }
        return "library.Album: " + albumName + "\nDuration: " + getAlbumDuration() + "\nTracks:\n" + tracksString;
    }
}
