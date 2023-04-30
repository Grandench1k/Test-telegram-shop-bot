package API.TestBot.Services.Impl;

import API.TestBot.Models.*;
import API.TestBot.Repositories.*;
import API.TestBot.Services.InLineKeyboardService;
import API.TestBot.Services.KeyboardService;
import API.TestBot.Services.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;


@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final KeyboardService keyboardService;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final InLineKeyboardService inLineKeyboardService;

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
}
