package API.TestBot.Services.Impl;

import API.TestBot.Models.User;
import API.TestBot.Models.UserDetails;
import API.TestBot.Repositories.UserDetailsRepository;
import API.TestBot.Repositories.UserRepository;
import API.TestBot.Services.EditMessageService;
import API.TestBot.Services.InLineKeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
@RequiredArgsConstructor
public class EditMessageServiceImpl implements EditMessageService {
    private final InLineKeyboardService inLineKeyboardService;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    public EditMessageText editedMessageWithListOfLists(long chatId, int messageId) {
        EditMessageText sendMessage = editMessage("Список разделов: ", chatId, messageId);
        sendMessage.setReplyMarkup(inLineKeyboardService.listOfLists());
        return sendMessage;
    }

    public EditMessageText editMessage(String text, long chatId, int messageId) {
        EditMessageText sendMessage = new EditMessageText(text);
        sendMessage.setChatId(chatId);
        sendMessage.setMessageId(messageId);
        return sendMessage;
    }

    public EditMessageText editMessageWithProfile(long chatId, int messageId) {
        User user = userRepository.findUserByChatId(chatId);
        UserDetails userDetails = userDetailsRepository.findUserDetailsByChatId(chatId);
        String time = user.getRegisteredAt().toString();
        EditMessageText message = editMessage("Ваш профиль: \n\n" + "Ваш Id: " + chatId + "\n\n" + "Вы были зарегистрированы: " + time.substring(8, 10) + "." + time.substring(5, 7) + "." + time.substring(0, 4) + " в " + time.substring(11, 16) + "\n\n" + "Ваш баланс: " + user.getBalance() + " руб." + "\n\nОбщая сумма покупок: " + userDetails.getTotalPurchases() + " руб." + "\n\nОбщая сумма пополнений: " + userDetails.getTotalDeposit() + " руб.", chatId, messageId);
        message.setReplyMarkup(inLineKeyboardService.depositKeyboard());
        return message;
    }

    public EditMessageText editMessageWithFirstListOfProducts(long chatId, int messageId) {
        EditMessageText editMessageText = editMessage("Cписок товаров: ", chatId, messageId);
        editMessageText.setReplyMarkup(inLineKeyboardService.firstListOfProducts());
        return editMessageText;
    }


    public EditMessageText editMessageWithSecondListOfProducts(long chatId, int messageId) {
        EditMessageText editMessageText = editMessage("Cписок товаров: ", chatId, messageId);
        editMessageText.setReplyMarkup(inLineKeyboardService.secondListOfProducts());
        return editMessageText;
    }

    public EditMessageText editMessageTextWithKeyboardToPayOneOrMultiplyProducts(String text, long chatId, int messageId) {
        InlineKeyboardMarkup markupLine = inLineKeyboardService.buyOneOrMultiplyProducts();
        EditMessageText editMessageText = editMessage(text, chatId, messageId);
        editMessageText.setReplyMarkup(markupLine);
        return editMessageText;
    }

    public EditMessageText editMessageTextWithKeyboardToPayOnlyOneProduct(String text, long chatId, int messageId) {
        InlineKeyboardMarkup markupLine = inLineKeyboardService.buyOnlyOneProduct();
        EditMessageText editMessageText = editMessage(text, chatId, messageId);
        editMessageText.setReplyMarkup(markupLine);
        return editMessageText;
    }

    public EditMessageText editMessageWithVariationOfPayments(String text, long chatId, int messageId) {
        EditMessageText editMessageText = editMessage(text, chatId, messageId);
        editMessageText.setReplyMarkup(inLineKeyboardService.variationOfPayments());
        return editMessageText;
    }
}
