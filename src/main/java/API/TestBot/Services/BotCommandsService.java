package API.TestBot.Services;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommandsService {
    List<BotCommand> createListOfBotCommands();
}
