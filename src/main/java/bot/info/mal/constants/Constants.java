package bot.info.mal.constants;

public class Constants {

    public static final String Q_PARAMETER = "q";
    public static final String LIMIT_PARAMETER = "limit";
    public static final String FIELDS_PARAMETER = "fields";

    public static final String MAL_CLIENT_ID = "X-MAL-CLIENT-ID";
    public static final String MAL_URL = "https://myanimelist.net/anime/";

    public static final String DATA_JSON_OBJECT = "data";
    public static final String NODE_JSON_OBJECT = "node";
    public static final String MAIN_PICTURE_OBJECT = "main_picture";

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String LARGE = "large";
    public static final String SYNOPSIS = "synopsis";
    public static final String MEAN = "mean";
    public static final String RANK = "rank";
    public static final String POPULARITY = "popularity";
    public static final String MEDIA_TYPE = "media_type";
    public static final String NUM_EPISODES = "num_episodes";
    public static final String COMMA = ",";

    public static final String ANIME_FIELDS = ID + COMMA + TITLE + COMMA + LARGE + COMMA
            + SYNOPSIS + COMMA + MEAN + COMMA + RANK + COMMA
            + POPULARITY + COMMA + MEDIA_TYPE + COMMA + NUM_EPISODES;

}
