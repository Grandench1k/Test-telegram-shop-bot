package API.TestBot.Services;

import API.TestBot.Models.*;
import API.TestBot.Repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;


@Service
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


    public SendMessage messageWithProfile(long chatId) {
        User user = userRepository.findUserByChatId(chatId);
        UserDetails userDetails = userDetailsRepository.findUserDetailsByChatId(chatId);
        String time = user.getRegisteredAt().toString();
        SendMessage message = justMessage("Ваш профиль: \n\n" + "Ваш Id: " + chatId + "\n\n" + "Вы были зарегистрированы: " + time.substring(8, 10) + "." + time.substring(5, 7) + "." + time.substring(0, 4) + " в " + time.substring(11, 16) + "\n\n" + "Ваш баланс: " + user.getBalance() + " руб." + "\n\nОбщая сумма покупок: " + userDetails.getTotalPurchases() + " руб." + "\n\nОбщая сумма пополнений: " + userDetails.getTotalDeposit() + " руб.", chatId);
        message.setReplyMarkup(inLineKeyboardService.depositKeyboard());
        return message;
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
}
