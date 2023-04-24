package API.TestBot.Core;

import API.TestBot.Config.BotConfig;
import API.TestBot.Models.User;
import API.TestBot.Models.UserDetails;
import API.TestBot.Repositories.*;
import API.TestBot.Services.MessageService;
import API.TestBot.Services.OrderService;
import API.TestBot.Services.UserService;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static API.TestBot.Constants.TelegramBotConstants.constants.RULES_TEXT;

@RequiredArgsConstructor
@Component
public class UpdateHandler {

    private final BotConfig botConfig;

    private final UserRepository userRepository;

    private final UserDetailsRepository userDetailsRepository;

    private final UserService userService;

    private final MessageService messageService;

    private final OrderService orderService;

    private final FirstProductRepository firstProductRepository;

    private final SecondProductRepository secondProductRepository;

    private final ThirdProductRepository thirdProductRepository;

    private final FourthProductRepository fourthProductRepository;

    public SendMessage updateWithoutCallBackCheck(Update update) {
        String ownerUserName = botConfig.getOwnerUserName();
        String messageText = update.getMessage().getText();
        User user = userRepository.findUserByChatId(update.getMessage().getChatId());
        UserDetails userDetails = userDetailsRepository.findUserDetailsByChatId(update.getMessage().getChatId());
        long chatId = update.getMessage().getChatId();
        String userName = update.getMessage().getChat().getUserName();
        if (update.hasMessage() && userDetails.isDeposit()) {
            return messageService.messageWithInteractiveKeyboard(userService.setUserBalance(update, user, userDetails), update.getMessage().getChatId());
        } else if (update.hasMessage() && userDetails.isBuyingProducts()) {
            return messageService.messageWithInteractiveKeyboard(orderService.buyProducts(update), update.getMessage().getChatId());
        } else {
            if (userName != null && userName.equals(ownerUserName) && messageText.contains("/send") || (messageText.contains("/add"))) {
                if (messageText.contains("/send")) {
                    for (User users : userRepository.findAll()) {
                        return messageService.messageWithInteractiveKeyboard(EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" "))), users.getChatId());
                    }
                } else if (messageText.contains("/addFirstProduct")) {
                    return messageService.messageWithInteractiveKeyboard(messageService.messageContainsFirstProduct(messageText), chatId);
                } else if (messageText.contains("/addSecondProduct")) {
                    return messageService.messageWithInteractiveKeyboard(messageService.messageContainsSecondProduct(messageText), chatId);
                } else if (messageText.contains("/addThirdProduct")) {
                    return messageService.messageWithInteractiveKeyboard(messageService.messageContainsThirdProduct(messageText), chatId);
                } else if (messageText.contains("/addFourthProduct")) {
                    return messageService.messageWithInteractiveKeyboard(messageService.messageContainsFourthProduct(messageText), chatId);
                }
            } else {
                switch (messageText) {
                    case "/start" -> {
                        return messageService.messageWithInteractiveKeyboard(EmojiParser.parseToUnicode("Привет, " + user.getUserName() + ":blush:" + ", добро пожаловать в мой магазин! \n" + "напиши /help для того, чтобы узнать, как работают команды"), chatId);
                    }
                    case "\uD83D\uDCD6Список товаров\uD83D\uDCD6" -> {
                        return messageService.messageWithListOfLists(chatId);
                    }
                    case "✅Наличие товаров❎" -> {
                        return messageService.justMessage("✅Наличие товаров❎\n\n" + "Первый раздел:\n\n" + "Первый продукт | 15 руб. | В наличии: " + firstProductRepository.findAll().size() + " шт.\n" + "Второй продукт  | 20 руб. | В наличии: " + secondProductRepository.findAll().size() + " шт.\n\n" + "Второй раздел:\n\n" + "Третий продукт         | 25 руб. | В наличии: " + thirdProductRepository.findAll().size() + " шт.\n" + "Четвёртый продукт | 30 руб. | В наличии: " + fourthProductRepository.findAll().size() + " шт.", chatId);
                    }
                    case "\uD83E\uDD29Профиль\uD83E\uDD29", "/profile" -> {
                        return messageService.messageWithProfile(chatId);
                    }
                    case "\uD83D\uDCC4 Правила\uD83D\uDCC4", "/rules" -> {
                        return messageService.messageWithInteractiveKeyboard(RULES_TEXT, chatId);
                    }
                    case "\uD83D\uDE91Помощь\uD83D\uDE91", "/help" -> {
                        if (!(userName.equals(ownerUserName))) {
                            return messageService.messageWithInteractiveKeyboard(EmojiParser.parseToUnicode("Команда /start нужна для того, чтобы начать работу с ботом \n"
                                    + "команда /profile нужна для того, чтобы увидеть свой id и баланс кошелька \n"
                                    + "команда /rules нужна для того, чтобы ознакомиться с правилами \n"
                                    + "ну и команда /help нужна для того, чтобы вновь показать данное сообщение \n\n"
                                    + "По остальным вопросам следует писать мне, ник @" + botConfig.getOwnerUserName() + ":smirk:\n"
                                    + "Желаю продуктивного использования данного бота!" + ":grin:"), chatId);
                        } else {
                            return messageService.messageWithInteractiveKeyboard(EmojiParser.parseToUnicode("Команда /start нужна для того, чтобы начать работу с ботом \n"
                                    + "команда /profile нужна для того, чтобы увидеть свой id и баланс кошелька \n"
                                    + "команда /rules нужна для того, чтобы ознакомиться с правилами \n"
                                    + "команда /send нужна для того, чтобы отправить нужное вам сообщение всем пользователям бота \n"
                                    + "команда /addFirstProduct {сам продукт} нужна для того, чтобы добавить первый продукт в базу данных \n"
                                    + "команда /addSecondProduct {сам продукт} нужна для того, чтобы добавить второй продукт в базу данных \n"
                                    + "команда /addThirdProduct {сам продукт} нужна для того, чтобы добавить третий продукт в базу данных \n"
                                    + "команда /addFourthProduct {сам продукт} нужна для того, чтобы добавить четвёртый продукт в базу данных \n"
                                    + "ну и команда /help нужна для того, чтобы вновь показать данное сообщение \n\n"
                                    + "По остальным вопросам следует писать мне, ник @" + botConfig.getOwnerUserName() + ":smirk:\n"
                                    + "Желаю продуктивного использования данного бота!" + ":grin:"), chatId);
                        }
                    }
                    default -> {
                        return messageService.justMessage(EmojiParser.parseToUnicode("Извините, команда не найдена" + ":sweat_smile:"), chatId);
                    }
                }
            }
        }
        return messageService.justMessage(EmojiParser.parseToUnicode("Извините, команда не найдена" + ":sweat_smile:"), chatId);
    }
}
