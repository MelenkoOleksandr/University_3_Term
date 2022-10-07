package tracks;

import library.Singer;

public class RockSong extends Track {
    public RockSong(String name, Singer singer, int duration) {
        super(name, singer, duration);
        this.setGenre("rock");
    }

    @Override
    public String playTrack() {
        return  "Rock Song " + this.getName() + " is playing...";
    }
}
