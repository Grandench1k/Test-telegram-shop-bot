package API.TestBot.Handlers;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;


public interface CallBackHandler {
    EditMessageText callbackChecker(Update update);
}
