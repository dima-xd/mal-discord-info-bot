package bot.info.mal.beans;

import java.util.List;

public class Anime {
    private int id;
    private String title;
    private String largeImage;
    private String synopsis;
    private Double mean;
    private int rank;
    private int popularity;
    private String mediaType;
    private List<String> genres;
    private int numEpisodes;

    public Anime(int id, String title, String largeImage, String synopsis, Double mean, int rank, int popularity, String mediaType, List<String> genres, int numEpisodes) {
        this.id = id;
        this.title = title;
        this.largeImage = largeImage;
        this.synopsis = synopsis;
        this.mean = mean;
        this.rank = rank;
        this.popularity = popularity;
        this.mediaType = mediaType;
        this.genres = genres;
        this.numEpisodes = numEpisodes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Double getMean() {
        return mean;
    }

    public int getRank() {
        return rank;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getMediaType() {
        return mediaType;
    }

    public List<String> getGenres() {
        return genres;
    }

    public int getNumEpisodes() {
        return numEpisodes;
    }
}
