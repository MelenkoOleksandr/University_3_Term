public class JazzSong extends Track {
    public JazzSong(String name, Singer singer, double duration, String genre) {
        super(name, singer, duration, genre);
    }

    @Override
    public void playTrack() {
        System.out.println("Jazz Song " + this.getName() + " is playing...");
    }
}
