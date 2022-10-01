import library.Singer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tracks.Track;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {

    @Test
    @DisplayName("tracks.Track duration")
    void parseDuration() {
        Track track1 = new Track("Test tracks.Track 1", new Singer("Test library.Singer 1"), 120);
        assertEquals(track1.parseDuration(), "02:00");
        track1.setDuration(100);
        assertEquals(track1.parseDuration(), "01:40");
        track1.setDuration(121);
        assertEquals(track1.parseDuration(), "02:01");
        track1.setDuration(0);
        assertEquals(track1.parseDuration(), "00:00");
    }

    @Test
    @DisplayName("Play the track")
    void playTrack() {
        Track track1 = new Track("Test Track 1", new Singer("Test library.Singer 1"), 120);
        assertEquals(track1.playTrack(), "Track Test Track 1 is playing...");
        track1.setName("NewTrackName");
        assertEquals(track1.playTrack(), "Track NewTrackName is playing...");
        track1.setName("");
        assertEquals(track1.playTrack(), "Track NewTrackName is playing...");
    }
}