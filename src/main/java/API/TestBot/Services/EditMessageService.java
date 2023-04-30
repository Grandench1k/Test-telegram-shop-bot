package API.TestBot.Services;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface EditMessageService {
    EditMessageText editedMessageWithListOfLists(long chatId, int messageId);

    EditMessageText editMessage(String text, long chatId, int messageId);

    EditMessageText editMessageWithProfile(long chatId, int messageId);

    EditMessageText editMessageWithFirstListOfProducts(long chatId, int messageId);

    EditMessageText editMessageWithSecondListOfProducts(long chatId, int messageId);

    EditMessageText editMessageTextWithKeyboardToPayOneOrMultiplyProducts(String text, long chatId, int messageId);

    EditMessageText editMessageTextWithKeyboardToPayOnlyOneProduct(String text, long chatId, int messageId);

    EditMessageText editMessageWithVariationOfPayments(String text, long chatId, int messageId);
}
