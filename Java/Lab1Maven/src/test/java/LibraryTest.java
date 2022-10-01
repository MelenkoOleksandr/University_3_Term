import library.Library;
import library.Singer;
import org.junit.jupiter.api.Test;
import tracks.Track;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {


    @Test
    void addNewTrack() {
        Library library = new Library();
        assertEquals(library.getLibrary().size(), 0);
        library.addNewTrack("jazz", "TestTrack1", 40, new Singer("TestSinger1"));
        library.addNewTrack("", "TestTrack2", 230, new Singer("TestSinger2"));
        library.addNewTrack("pop", "TestTrack3", 25, new Singer("TestSinger3"));
        assertEquals(library.getLibrary().size(), 3);
        library.addNewTrack("", "TestTrack4", 75, new Singer("TestSinger2"));
        assertEquals(library.getLibrary().size(), 4);
    }

    @Test
    void getSongsByDuration() {
        Library library = new Library();
        Track track1 = library.addNewTrack("jazz", "TestTrack1", 40, new Singer("TestSinger1"));
        Track track2 =library.addNewTrack("", "TestTrack2", 230, new Singer("TestSinger2"));
        Track track3 =library.addNewTrack("pop", "TestTrack3", 25, new Singer("TestSinger3"));
        Track track4 =library.addNewTrack("rock", "TestTrack4", 75, new Singer("TestSinger2"));
        Track track5 =library.addNewTrack("rock", "TestTrack5", 125, new Singer("TestSinger2"));
        Track track6 =library.addNewTrack("", "TestTrack6", 95, new Singer("TestSinger2"));

        ArrayList<Track> listByDuration = library.getSongsByDuration(30, 50);
        assertArrayEquals(listByDuration.toArray(), new Track[] {
                track1
        });

        ArrayList<Track> listByDuration2 = library.getSongsByDuration(40, 200);
        assertArrayEquals(listByDuration2.toArray(), new Track[] {
                track1, track4, track5, track6
        });

        ArrayList<Track> listByDuration3 = library.getSongsByDuration(0, 0);
        assertArrayEquals(listByDuration3.toArray(), new Track[] {});

        ArrayList<Track> listByDuration4 = library.getSongsByDuration(90, 20);
        assertArrayEquals(listByDuration4.toArray(), new Track[] {});
    }
}