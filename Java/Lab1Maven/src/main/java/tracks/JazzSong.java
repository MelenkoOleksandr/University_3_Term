package tracks;

import library.Singer;

public class JazzSong extends Track {
    public JazzSong(String name, Singer singer, int duration) {
        super(name, singer, duration);
        this.setGenre("jazz");
    }

    @Override
    public String playTrack() {
        return  "Jazz Song " + this.getName() + " is playing...";
    }
}
