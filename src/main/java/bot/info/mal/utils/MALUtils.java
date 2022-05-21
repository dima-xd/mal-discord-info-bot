package bot.info.mal.utils;

import bot.info.mal.beans.Anime;
import bot.info.mal.beans.Manga;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static bot.info.mal.constants.Constants.*;

public class MALUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MALUtils.class);

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final String CLIENT_ID = CredentialsUtils.getMALClientID();

    public static Anime getAnimeByTitle(String title) {
        final String ANIME_URI = "https://api.myanimelist.net/v2/anime";
        try {
            HttpUriRequest request = RequestBuilder.get()
                    .setUri(new URI(ANIME_URI))
                    .addParameter(Q_PARAMETER, title)
                    .addParameter(LIMIT_PARAMETER, "1")
                    .addHeader(MAL_CLIENT_ID, CLIENT_ID)
                    .build();
            CloseableHttpResponse response = httpClient.execute(request);

            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONArray jsonArray = obj.getJSONArray(DATA_JSON_OBJECT);

            JSONObject dataObj = jsonArray.getJSONObject(0);
            JSONObject nodeObj = dataObj.getJSONObject(NODE_JSON_OBJECT);

            HttpUriRequest requestAnime = RequestBuilder.get()
                    .setUri(new URI(ANIME_URI + "/" + nodeObj.getInt(ID)))
                    .addParameter(FIELDS_PARAMETER, ANIME_FIELDS)
                    .addHeader(MAL_CLIENT_ID, CLIENT_ID)
                    .build();
            CloseableHttpResponse responseAnime = httpClient.execute(requestAnime);

            JSONObject animeObj = new JSONObject(EntityUtils.toString(responseAnime.getEntity()));
            JSONObject mainPictureObj = animeObj.getJSONObject(MAIN_PICTURE_OBJECT);

            httpClient.close();
            response.close();
            responseAnime.close();
            return new Anime(nodeObj.getInt(ID), animeObj.getString(TITLE), mainPictureObj.getString(LARGE), animeObj.getString(SYNOPSIS),
                    animeObj.getDouble(MEAN), animeObj.getInt(RANK), animeObj.getInt(POPULARITY),
                    animeObj.getString(MEDIA_TYPE), animeObj.getInt(NUM_EPISODES));
        } catch (URISyntaxException | IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static Manga getMangaByTitle(String title) {
        final String MANGA_URI = "https://api.myanimelist.net/v2/manga";
        try {
            HttpUriRequest request = RequestBuilder.get()
                    .setUri(new URI(MANGA_URI))
                    .addParameter(Q_PARAMETER, title)
                    .addParameter(LIMIT_PARAMETER, "1")
                    .addHeader(MAL_CLIENT_ID, CLIENT_ID)
                    .build();
            CloseableHttpResponse response = httpClient.execute(request);

            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONArray jsonArray = obj.getJSONArray(DATA_JSON_OBJECT);

            JSONObject dataObj = jsonArray.getJSONObject(0);
            JSONObject nodeObj = dataObj.getJSONObject(NODE_JSON_OBJECT);

            HttpUriRequest requestAnime = RequestBuilder.get()
                    .setUri(new URI(MANGA_URI + "/" + nodeObj.getInt(ID)))
                    .addParameter(FIELDS_PARAMETER, MANGA_FIELDS)
                    .addHeader(MAL_CLIENT_ID, CLIENT_ID)
                    .build();
            CloseableHttpResponse responseManga = httpClient.execute(requestAnime);

            JSONObject mangaObj = new JSONObject(EntityUtils.toString(responseManga.getEntity()));
            JSONObject mainPictureObj = mangaObj.getJSONObject(MAIN_PICTURE_OBJECT);

            httpClient.close();
            response.close();
            responseManga.close();
            return new Manga(nodeObj.getInt(ID), mangaObj.getString(TITLE), mainPictureObj.getString(LARGE), mangaObj.getString(SYNOPSIS),
                    mangaObj.getDouble(MEAN), mangaObj.getInt(RANK), mangaObj.getInt(POPULARITY),
                    mangaObj.getString(MEDIA_TYPE), mangaObj.getInt(NUM_CHAPTERS));
        } catch (URISyntaxException | IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

}
