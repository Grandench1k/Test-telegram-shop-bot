package API.TestBot.Services;

import API.TestBot.Models.User;
import API.TestBot.Models.UserDetails;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface UpdateWithCallBackService {
    EditMessageText callBackEqualFirstList(long chatId, int messageId, String LIST, UserDetails userDetails);

    EditMessageText callBackEqualSecondList(long chatId, int messageId, String LIST, UserDetails userDetails);

    EditMessageText callBackEqualProduct(int productSize, long chatId, int messageId, int whatProduct, String numberOfProduct, UserDetails userDetails, String PRODUCT);

    EditMessageText callBackEqualOnePurchase(UserDetails userDetails, User user, int userProduct, long chatId, int messageId);

    EditMessageText callBackEqualsBack(long chatId, int messageId, UserDetails userDetails);
}
