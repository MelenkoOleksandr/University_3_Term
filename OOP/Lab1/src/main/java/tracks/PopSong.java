package tracks;

import library.Singer;

public class PopSong extends Track {

    public PopSong(String name, Singer singer, int duration) {
        super(name, singer, duration);
        this.setGenre("pop");
    }

    @Override
    public String playTrack() {
        return  "Pop Song " + this.getName() + " is playing...";
    }
}
