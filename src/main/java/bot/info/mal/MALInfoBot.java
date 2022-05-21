package bot.info.mal;

import bot.info.mal.listeners.SlashCommandListener;
import bot.info.mal.utils.CredentialsUtils;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MALInfoBot {
    public static final Logger LOGGER = LoggerFactory.getLogger(MALInfoBot.class);

    public static void main(String[] args) {
        final GatewayDiscordClient client = DiscordClientBuilder.create(CredentialsUtils.getDiscordToken()).build()
            .login()
            .block();

        List<String> commands = List.of("anime.json", "manga.json");
        try {
            new GlobalCommandRegistrar(client.getRestClient()).registerCommands(commands);
        } catch (Exception e) {
            LOGGER.error("Error trying to register global slash commands", e);
        }

        client.on(ChatInputInteractionEvent.class, SlashCommandListener::handle)
            .then(client.onDisconnect())
            .block();
    }
}
