import library.Singer;
import library.Studio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tracks.JazzSong;
import tracks.PopSong;
import tracks.RockSong;

import static org.junit.jupiter.api.Assertions.*;

class StudioTest {

    @Test
    @DisplayName("Song has correct genre")
    void checkGenreCreation() {
        assertTrue(Studio.createSong("jazz", "TestJazzSong", 60, new Singer("JazzSinger")) instanceof JazzSong);
        assertTrue(Studio.createSong("rock", "TestRockSong", 72, new Singer("RockSinger")) instanceof RockSong);
        assertTrue(Studio.createSong("pop", "TestPopSong", 87, new Singer("PopSinger")) instanceof PopSong);
    }
}