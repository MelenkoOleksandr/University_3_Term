import library.Album;
import library.Singer;
import library.Studio;
import org.junit.jupiter.api.Test;
import tracks.Track;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {

    @Test
    void addTrackToAlbum() {
        Album album = new Album("TestAlbum1");
        album.addTrackToAlbum(Studio.createSong("rock", "TestTrack", 70, new Singer("TestSinger")));
        album.addTrackToAlbum(Studio.createSong("rock", "TestTrack2", 87, new Singer("TestSinger")));
        album.addTrackToAlbum(Studio.createSong("rock", "TestTrack3", 135, new Singer("TestSinger")));
        assertEquals(album.getAlbumTracks().size(), 3);
    }

    @Test
    void getAlbumDuration() {
        Album album = new Album("TestAlbum1");
        Track track1 = Studio.createSong("rock", "TestTrack", 70, new Singer("TestSinger"));
        Track track2 = Studio.createSong("rock", "TestTrack2", 87, new Singer("TestSinger"));
        Track track3 = Studio.createSong("rock", "TestTrack3", 135, new Singer("TestSinger"));
        Track track4 = Studio.createSong("rock", "TestTrack4", 48, new Singer("TestSinger"));

        album.addTrackToAlbum(track1);
        album.addTrackToAlbum(track2);
        album.addTrackToAlbum(track3);
        assertEquals(album.getAlbumDuration(), 292);

        album.addTrackToAlbum(track4);
        assertEquals(album.getAlbumDuration(), 340);

        album.deleteTrackFromAlbum(track1);
        assertEquals(album.getAlbumDuration(), 270);
    }

    @Test
    void changeAlbumOrderByGenre() {
        Album album = new Album("TestAlbum1");
        Track track1 = Studio.createSong("", "TestTrack1", 70, new Singer("TestSinger"));
        Track track2 = Studio.createSong("rock", "TestTrack2", 87, new Singer("TestSinger"));
        Track track3 = Studio.createSong("pop", "TestTrack3", 135, new Singer("TestSinger"));
        Track track4 = Studio.createSong("pop", "TestTrack4", 48, new Singer("TestSinger"));
        Track track5 = Studio.createSong("jazz", "TestTrack5", 67, new Singer("TestSinger"));
        Track track6 = Studio.createSong("pop", "TestTrack6", 342, new Singer("TestSinger"));

        album.addTrackToAlbum(track1);
        album.addTrackToAlbum(track2);
        album.addTrackToAlbum(track3);
        album.addTrackToAlbum(track4);
        album.addTrackToAlbum(track5);
        album.addTrackToAlbum(track6);

        album.changeAlbumOrderByGenre("pop");


        System.out.println(album);
        assertArrayEquals(album.getAlbumTracks().toArray(), new Track[]{ track3, track4, track6, track1, track2, track5 });
    }
}