package bot.info.mal.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialsUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialsUtils.class);

    private static Properties property = null;

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/credentials.properties");
            property = new Properties();
            property.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static String getDiscordToken() {
        return property.getProperty("discord.token");
    }

    public static String getMALClientID() {
        return property.getProperty("mal.clientID");
    }

}
