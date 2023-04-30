package API.TestBot.Services.Impl;

import API.TestBot.Services.BotCommandsService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotCommandsServiceImpl implements BotCommandsService {
    public List<BotCommand> createListOfBotCommands() {
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", EmojiParser.parseToUnicode("старт бота" + ":rocket:")));
        listOfCommands.add(new BotCommand("/profile", EmojiParser.parseToUnicode("ваши данные" + ":sunglasses:")));
        listOfCommands.add(new BotCommand("/rules", EmojiParser.parseToUnicode("правила" + ":page_facing_up:")));
        listOfCommands.add(new BotCommand("/help", EmojiParser.parseToUnicode("помощь" + ":ambulance:")));
        return listOfCommands;
    }
}
