public class PopSong extends Track {
    public PopSong(String name, Singer singer, double duration, String genre) {
        super(name, singer, duration, genre);
    }

    @Override
    public void playTrack() {
        System.out.println("Pop Song " + this.getName() + " is playing...");
    }
}
