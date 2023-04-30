package API.TestBot.Core;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.LongPollingBot;


public interface BotCore extends LongPollingBot {
    void onUpdateReceived(Update update);

    String getBotUsername();

    String getBotToken();
}
