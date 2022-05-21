package bot.info.mal.commands;

import bot.info.mal.beans.Anime;
import bot.info.mal.utils.MALUtils;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static bot.info.mal.constants.Constants.*;

public class AnimeCommand implements SlashCommand {
    @Override
    public String getName() {
        return "anime";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String title = event.getOption("title")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        Anime anime = MALUtils.getAnimeByTitle(title);

        if (anime != null) {
            EmbedCreateSpec animeEmbed = getAnimeEmbed(anime);
            return event.reply()
                    .withEmbeds(animeEmbed);
        } else {
            return event.reply()
                    .withContent("Anime not found.");
        }
    }

    public EmbedCreateSpec getAnimeEmbed(Anime anime) {
        return EmbedCreateSpec.builder()
                .color(Color.GREEN)
                .title(anime.getTitle() + "(" + anime.getMediaType().toUpperCase() + ")")
                .url(MAL_ANIME_URL + anime.getId())
                .author("MyAnimeList", MAL_ANIME_URL + anime.getId(), "https://upload.wikimedia.org/wikipedia/commons/7/7a/MyAnimeList_Logo.png")
                .thumbnail("https://upload.wikimedia.org/wikipedia/commons/7/7a/MyAnimeList_Logo.png")
                .addField("Score", anime.getMean().toString(), false)
                .addField("Rank", String.valueOf(anime.getRank()), true)
                .addField("Popularity", String.valueOf(anime.getPopularity()), true)
                .addField("Episodes", String.valueOf(anime.getNumEpisodes()), false)
                .description(anime.getSynopsis())
                .image(anime.getLargeImage())
                .timestamp(Instant.now())
                .build();
    }
}
