import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public void addTrackToAlbum(Track track) {
        System.out.println("Track " + track.getName() + " was added to album");
        albumTracks.add(track);
    }

    public double getAlbumDuration() {
        return albumTracks.stream().map(Track::getDuration).reduce(0.0, Double::sum);
    }

    public void deleteTrackFromAlbum(String trackName) {
        for (int i = 0; i < albumTracks.size(); i++) {
            if (albumTracks.get(i).getName() == trackName) {
                albumTracks.remove(i);
                System.out.println("Track " + trackName + " was deleted from album");
            }
        }
    }

    public void changeAlbumOrderByGenre(String trackGenre) {
        Collections.sort(albumTracks, new Comparator<Track>() {
            @Override
            public int compare(Track o1, Track o2) {
                if ((o1.getGenre() == trackGenre && o2.getGenre() == trackGenre) || (o1.getGenre() != trackGenre && o2.getGenre() != trackGenre) ) {
                    return 0;
                } else if (o1.getGenre() == trackGenre && o2.getGenre() != trackGenre) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public void getAlbumInfo() {
        System.out.println("Album: " + albumName + "\nDuration: " + getAlbumDuration());
    }

    @Override
    public String toString() {
        String tracksString = "";
        for (Track track: albumTracks) {
            tracksString += track.toString() + "\n";
        }
        return "Album: " + albumName + "\nDuration: " + getAlbumDuration() + "\nTracks:\n" + tracksString;
    }
}
