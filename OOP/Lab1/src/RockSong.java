public class RockSong extends Track {
    public RockSong(String name, Singer singer, double duration, String genre) {
        super(name, singer, duration, genre);
    }

    @Override
    public void playTrack() {
        System.out.println("Rock Song " + this.getName() + " is playing...");
    }
}
