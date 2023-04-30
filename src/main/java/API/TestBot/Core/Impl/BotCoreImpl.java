package API.TestBot.Core.Impl;

import API.TestBot.Config.BotConfig;
import API.TestBot.Core.BotCore;
import API.TestBot.Handlers.CallBackHandler;
import API.TestBot.Handlers.UpdateHandler;
import API.TestBot.Services.BotCommandsService;
import API.TestBot.Services.MessageService;
import API.TestBot.Services.UserService;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

@Component
public class BotCoreImpl extends TelegramLongPollingBot implements BotCore {
    private final MessageService messageService;
    private final UserService userService;
    private final BotConfig botConfig;
    private final UpdateHandler updateHandler;
    private final CallBackHandler callBackHandler;

    @SneakyThrows
    public BotCoreImpl(MessageService messageService, UserService userService, BotConfig botConfig, BotCommandsService listOfCommands, UpdateHandler updateHandler, CallBackHandler callBackHandler) {
        this.userService = userService;
        this.botConfig = botConfig;
        this.messageService = messageService;
        this.updateHandler = updateHandler;
        this.callBackHandler = callBackHandler;
        this.execute(new SetMyCommands(listOfCommands.createListOfBotCommands(), new BotCommandScopeDefault(), null));
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                execute(updateHandler.updateWithoutCallBackCheck(update));
            } catch (NullPointerException e) {
                userService.registrationUser(update);
                execute(messageService.messageWithInteractiveKeyboard(EmojiParser.parseToUnicode("Привет, " + update.getMessage().getChat().getFirstName() + ":blush:" + ", добро пожаловать в мой магазин! \n" + "напиши /help для того, чтобы узнать, как работают команды"), update.getMessage().getChatId()));
            }
        } else if (update.hasCallbackQuery()) {
            execute(callBackHandler.callbackChecker(update));
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
