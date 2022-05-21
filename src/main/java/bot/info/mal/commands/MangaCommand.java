package bot.info.mal.commands;

import bot.info.mal.beans.Manga;
import bot.info.mal.utils.MALUtils;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static bot.info.mal.constants.Constants.MAL_URL;

public class MangaCommand implements SlashCommand {
    @Override
    public String getName() {
        return "manga";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String title = event.getOption("title")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        Manga manga = MALUtils.getMangaByTitle(title);

        if (manga != null) {
            EmbedCreateSpec animeEmbed = getMangaEmbed(manga);
            return event.reply()
                    .withEmbeds(animeEmbed);
        } else {
            return event.reply()
                    .withContent("Manga not found.");
        }
    }

    public EmbedCreateSpec getMangaEmbed(Manga manga) {
        return EmbedCreateSpec.builder()
                .color(Color.GREEN)
                .title(manga.getTitle() + "(" + manga.getMediaType().toUpperCase() + ")")
                .url(MAL_URL + manga.getId())
                .author("MyAnimeList", MAL_URL + manga.getId(), "https://upload.wikimedia.org/wikipedia/commons/7/7a/MyAnimeList_Logo.png")
                .thumbnail("https://upload.wikimedia.org/wikipedia/commons/7/7a/MyAnimeList_Logo.png")
                .addField("Score", manga.getMean().toString(), false)
                .addField("Rank", String.valueOf(manga.getRank()), true)
                .addField("Popularity", String.valueOf(manga.getPopularity()), true)
                .addField("Chapters", String.valueOf(manga.getNumChapters()), false)
                .description(manga.getSynopsis())
                .image(manga.getLargeImage())
                .timestamp(Instant.now())
                .build();
    }
}
