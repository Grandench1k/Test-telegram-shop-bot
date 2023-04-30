package API.TestBot.Services;

import API.TestBot.Models.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface UpdateWithoutCallBackService {
    public SendMessage compareUserNameWithOwnerName(String userName, String ownerUserName, String messageText, long chatId, User user);
    SendMessage updateWithSlash(String messageText, long chatId);

    SendMessage mainUpdateChecker(String messageText, String userName, String ownerUserName, long chatId, User user);
}
