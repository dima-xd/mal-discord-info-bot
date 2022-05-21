package bot.info.mal.listeners;

import bot.info.mal.commands.SlashCommand;
import bot.info.mal.commands.TitleCommand;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandListener {
    private final static List<SlashCommand> commands = new ArrayList<>();

    static {
        commands.add(new TitleCommand());
    }

    public static Mono<Void> handle(ChatInputInteractionEvent event) {
        return Flux.fromIterable(commands)
            .filter(command -> command.getName().equals(event.getCommandName()))
            .next()
            .flatMap(command -> command.handle(event));
    }
}
