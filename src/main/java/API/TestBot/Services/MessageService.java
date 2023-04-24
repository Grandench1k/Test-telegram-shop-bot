package API.TestBot.Services;

import API.TestBot.Models.*;
import API.TestBot.Repositories.*;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@EqualsAndHashCode
@Component
@RequiredArgsConstructor
public class MessageService {

    private final KeyboardService keyboardService;

    private final UserRepository userRepository;

    private final UserDetailsRepository userDetailsRepository;

    private final InLineKeyboardService inLineKeyboardService;

    private final FirstProductRepository firstProductRepository;

    private final SecondProductRepository secondProductRepository;

    private final ThirdProductRepository thirdProductRepository;

    private final FourthProductRepository fourthProductRepository;

    public SendMessage justMessage(String text, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }

    @SneakyThrows
    public SendMessage messageWithInteractiveKeyboard(String answer, long chatId) {
        SendMessage sendMessage = justMessage(answer, chatId);
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardService.mainKeyBoard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage messageWithListOfLists(long chatId) {
        SendMessage sendMessage = justMessage("Список разделов: ", chatId);
        sendMessage.setReplyMarkup(inLineKeyboardService.listOfLists());
        return sendMessage;
    }

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

    public SendMessage messageWithProfile(long chatId) {
        User user = userRepository.findUserByChatId(chatId);
        UserDetails userDetails = userDetailsRepository.findUserDetailsByChatId(chatId);
        String time = user.getRegisteredAt().toString();
        SendMessage message = justMessage("Ваш профиль: \n\n" + "Ваш Id: " + chatId + "\n\n" + "Вы были зарегистрированы: " + time.substring(8, 10) + "." + time.substring(5, 7) + "." + time.substring(0, 4) + " в " + time.substring(11, 16) + "\n\n" + "Ваш баланс: " + user.getBalance() + " руб." + "\n\nОбщая сумма покупок: " + userDetails.getTotalPurchases() + " руб." + "\n\nОбщая сумма пополнений: " + userDetails.getTotalDeposit() + " руб.", chatId);
        message.setReplyMarkup(inLineKeyboardService.depositKeyboard());
        return message;
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


    public String messageContainsFirstProduct(String messageText) {
        if (messageText.length() == 16) {
            return "Вы не ввели сам продукт";
        }
        if (!(messageText.substring(messageText.indexOf(" ")).contains("/"))) {
            FirstProduct firstProduct = new FirstProduct();
            String productToAdd = messageText.substring(messageText.indexOf(" "));
            firstProduct.setFirstProductData(productToAdd);
            firstProductRepository.save(firstProduct);
            return "Первый продукт успешно сохранен";
        } else {
            return "Пожалуйста, введите товар без посторонних знаков";
        }
    }

    public String messageContainsSecondProduct(String messageText) {
        if (messageText.length() == 17) {
            return "Вы не ввели сам продукт";
        }
        if (!(messageText.substring(messageText.indexOf(" ")).contains("/"))) {
            SecondProduct secondProduct = new SecondProduct();
            String productToAdd = messageText.substring(messageText.indexOf(" "));
            secondProduct.setSecondProductData(productToAdd);
            secondProductRepository.save(secondProduct);
            return "Второй продукт успешно сохранен";
        } else {
            return "Пожалуйста, введите товар без посторонних знаков";
        }
    }

    public String messageContainsThirdProduct(String messageText) {
        if (messageText.length() == 16) {
            return "Вы не ввели сам продукт";
        }
        if (!(messageText.substring(messageText.indexOf(" ")).contains("/"))) {
            ThirdProduct thirdProduct = new ThirdProduct();
            String productToAdd = messageText.substring(messageText.indexOf(" "));
            thirdProduct.setThirdProductData(productToAdd);
            thirdProductRepository.save(thirdProduct);
            return "Третий продукт успешно сохранен";
        } else {
            return "Пожалуйста, введите товар без посторонних знаков";
        }
    }

    public String messageContainsFourthProduct(String messageText) {
        if (messageText.length() == 17) {
            return "Вы не ввели сам продукт";
        }
        if (!(messageText.substring(messageText.indexOf(" ")).contains("/"))) {
            FourthProduct fourthProduct = new FourthProduct();
            String productToAdd = messageText.substring(messageText.indexOf(" "));
            fourthProduct.setFourthProductData(productToAdd);
            fourthProductRepository.save(fourthProduct);
            return "Четвёртый продукт успешно сохранен";
        } else {
            return "Пожалуйста, введите товар без посторонних знаков";
        }
    }

    public EditMessageText editMessageWithVariationOfPayments(String text, long chatId, int messageId) {
        EditMessageText editMessageText = editMessage(text, chatId, messageId);
        editMessageText.setReplyMarkup(inLineKeyboardService.variationOfPayments());
        return editMessageText;
    }
}
