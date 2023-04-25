package API.TestBot.Services;

import API.TestBot.Config.BotConfig;
import API.TestBot.Models.User;
import API.TestBot.Repositories.*;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static API.TestBot.Constants.TelegramBotConstants.constants.*;

@RequiredArgsConstructor
@Service
public class UpdateWithoutCallBackService {
    private final UserRepository userRepository;
    private final MessageService messageService;
    private final BotConfig botConfig;
    private final FirstProductRepository firstProductRepository;
    private final SecondProductRepository secondProductRepository;
    private final ThirdProductRepository thirdProductRepository;
    private final FourthProductRepository fourthProductRepository;

    public SendMessage updateWithSlash(String messageText, long chatId) {
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
        return messageService.justMessage("Команда не найдена", chatId);
    }

    public SendMessage mainUpdateChecker(String messageText, String userName, String ownerUserName, long chatId, User user) {
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
                assert userName != null;
                if (!(userName.equals(ownerUserName))) {
                    return messageService.messageWithInteractiveKeyboard(EmojiParser.parseToUnicode(HELP_FOR_USERS + EXTRA_HELP
                            + "По остальным вопросам следует писать мне, ник @" + botConfig.getOwnerUserName() + ":smirk:\n"
                            + "Желаю продуктивного использования данного бота!" + ":grin:"), chatId);
                } else {
                    return messageService.messageWithInteractiveKeyboard(EmojiParser.parseToUnicode(HELP_FOR_USERS + EXTRA_HELP_FOR_OWNER + EXTRA_HELP
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
