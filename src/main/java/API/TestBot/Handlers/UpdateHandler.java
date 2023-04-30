package API.TestBot.Handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {
    SendMessage updateWithoutCallBackCheck(Update update);
}
