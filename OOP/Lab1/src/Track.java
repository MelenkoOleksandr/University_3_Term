public class Track {
    private String trackCover;
    private String name;
    private Singer singer;
    private double duration;
    private String genre;
    private String clipUrl;

    public Track(String name, Singer singer, double duration, String genre) {
        this.name = name;
        this.singer = singer;
        this.duration = duration;
        this.genre = genre;
    }

    public String parseDuration() {
        int minutes = (int) (duration / 60);
        int seconds = (int) (this.duration - minutes * 60);
        return minutes + ":" + seconds;
    }

    public void playTrack() {
        System.out.println("Track " + this.name + " is playing...");
    }

    public void setClipUrl(String clipUrl) {
        this.clipUrl = clipUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTrackCover(String trackCover) {
        this.trackCover = trackCover;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public double getDuration() {
        return duration;
    }

    public Singer getSinger() {
        return singer;
    }

    public String getClipUrl() {
        return clipUrl;
    }

    public String getName() {
        return name;
    }

    public String getTrackCover() {
        return trackCover;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.singer.toString() + "\n" + parseDuration() ;
    }
}
