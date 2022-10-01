package tracks;

import library.Singer;

import java.util.Objects;

public class Track {
    private String trackCover;
    private String name;
    private Singer singer;
    private int duration;
    private String genre = "";
    private String clipUrl;

    public Track(String name, Singer singer, int duration) {
        this.name = name;
        this.singer = singer;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object obj) {
        Track track = (Track) obj;
        return Objects.equals(track.getName(), this.name) && track.getDuration() == this.duration && Objects.equals(track.getGenre(), this.genre) && Objects.equals(track.getSinger().getName(), this.singer.getName());
    }

    public String parseDuration() {
        int minutes = (int) (duration / 60);
        int seconds = (int) (this.duration - minutes * 60);
        return (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
    }

    public String playTrack() {
        return  "Track " + this.name + " is playing...";
    }

    public String getClipUrl() {
        return clipUrl;
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

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        if (duration >= 0) {
            this.duration = duration;
        }
    }

    public Singer getSinger() {
        return singer;
    }
    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (!Objects.equals(name, "")) {
            this.name = name;
        }
    }

    public String getTrackCover() {
        return trackCover;
    }
    public void setTrackCover(String trackCover) {
        this.trackCover = trackCover;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.singer.toString() + "\n" + parseDuration() ;
    }
}
