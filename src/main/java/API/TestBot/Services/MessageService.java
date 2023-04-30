package API.TestBot.Services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface MessageService {
    SendMessage justMessage(String text, long chatId);

    SendMessage messageWithInteractiveKeyboard(String answer, long chatId);
    SendMessage messageWithListOfLists(long chatId);
    SendMessage messageWithProfile(long chatId);
}
