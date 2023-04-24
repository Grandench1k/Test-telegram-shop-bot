package API.TestBot.Core;

import API.TestBot.Config.BotConfig;
import API.TestBot.Repositories.*;
import API.TestBot.Services.BotCommandsService;
import API.TestBot.Services.MessageService;
import API.TestBot.Services.OrderService;
import API.TestBot.Services.UserService;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

@Component
public class TestBot extends TelegramLongPollingBot {

    private final MessageService messageService;

    private final UserRepository userRepository;

    private final UserDetailsRepository userDetailsRepository;

    private final UserService userService;

    private final OrderService orderService;

    private final BotConfig botConfig;

    private final FirstProductRepository firstProductRepository;

    private final SecondProductRepository secondProductRepository;

    private final ThirdProductRepository thirdProductRepository;

    private final FourthProductRepository fourthProductRepository;

    private final UpdateHandler updateHandler;

    private final UpdateWithCallBackHandler updateWithCallBackHandler;


    @SneakyThrows
    public TestBot(MessageService messageService, UserRepository userRepository, UserDetailsRepository userDetailsRepository, UserService userService, OrderService orderService, BotConfig botConfig, FirstProductRepository firstProductRepository, SecondProductRepository secondProductRepository, BotCommandsService listOfCommands, ThirdProductRepository thirdProductRepository, FourthProductRepository fourthProductRepository, UpdateHandler updateHandler, UpdateWithCallBackHandler updateWithCallBackHandler) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.userService = userService;
        this.orderService = orderService;
        this.botConfig = botConfig;
        this.messageService = messageService;
        this.firstProductRepository = firstProductRepository;
        this.secondProductRepository = secondProductRepository;
        this.thirdProductRepository = thirdProductRepository;
        this.fourthProductRepository = fourthProductRepository;
        this.updateHandler = updateHandler;
        this.updateWithCallBackHandler = updateWithCallBackHandler;
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
                execute(messageService.messageWithInteractiveKeyboard(EmojiParser.parseToUnicode("Привет, " +  update.getMessage().getChat().getFirstName() + ":blush:" + ", добро пожаловать в мой магазин! \n" + "напиши /help для того, чтобы узнать, как работают команды"), update.getMessage().getChatId()));
            }
        } else if (update.hasCallbackQuery()) {
            execute(updateWithCallBackHandler.callbackChecker(update));
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
