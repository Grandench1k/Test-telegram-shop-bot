package API.TestBot.Handlers.Impl;

import API.TestBot.Handlers.CallBackHandler;
import API.TestBot.Models.UserDetails;
import API.TestBot.Repositories.*;
import API.TestBot.Services.EditMessageService;
import API.TestBot.Services.UpdateWithCallBackService;
import API.TestBot.Services.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import static API.TestBot.Constants.TelegramBotConstants.constants.*;

@Component
@RequiredArgsConstructor
public class CallBackHandlerImpl implements CallBackHandler {
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsService userDetailsService;
    private final EditMessageService editMessageService;
    private final FirstProductRepository firstProductRepository;
    private final SecondProductRepository secondProductRepository;
    private final ThirdProductRepository thirdProductRepository;
    private final FourthProductRepository fourthProductRepository;
    private final UpdateWithCallBackService updateWithCallBackService;

    @SneakyThrows
    public EditMessageText callbackChecker(Update update) {
        UserDetails userDetails = userDetailsRepository.findUserDetailsByChatId(update.getCallbackQuery().getMessage().getChatId());
        int messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        switch (update.getCallbackQuery().getData()) {
            case FIRST_LIST -> {
                return updateWithCallBackService.callBackEqualFirstList(chatId, messageId, FIRST_LIST, userDetails);
            }
            case SECOND_LIST -> {
                return updateWithCallBackService.callBackEqualSecondList(chatId, messageId, SECOND_LIST, userDetails);
            }
            case FIRST_PRODUCT -> {
                return updateWithCallBackService.callBackEqualProduct(firstProductRepository.findAll().size(), chatId, messageId, 1, "Первый", userDetails, FIRST_PRODUCT);
            }
            case SECOND_PRODUCT -> {
                return updateWithCallBackService.callBackEqualProduct(secondProductRepository.findAll().size(), chatId, messageId, 2, "Второй", userDetails, SECOND_PRODUCT);
            }
            case THIRD_PRODUCT -> {
                return updateWithCallBackService.callBackEqualProduct(thirdProductRepository.findAll().size(), chatId, messageId, 3, "Третий", userDetails, THIRD_PRODUCT);
            }
            case FOURTH_PRODUCT -> {
                return updateWithCallBackService.callBackEqualProduct(fourthProductRepository.findAll().size(), chatId, messageId, 4, "Четвёртый", userDetails, FOURTH_PRODUCT);
            }
            case ONE_PURCHASE -> {
                return updateWithCallBackService.callBackEqualOnePurchase(userDetails, userRepository.findUserByChatId(update.getCallbackQuery().getMessage().getChatId()), userDetails.getWhatProduct(), chatId, messageId);
            }
            case MULTIPLE_PURCHASES -> {
                userDetailsService.userSetBuyingProducts(userDetails, true);
                return editMessageService.editMessage("Отправьте количество товаров для покупки", chatId, messageId);
            }
            case DEPOSIT -> {
                userDetailsService.userSetPreviousCallBackQuery(userDetails, DEPOSIT);
                return editMessageService.editMessageWithVariationOfPayments("Варианты оплаты", chatId, messageId);
            }
            case YOOMONEY -> {
                userDetailsService.setUserDetailsDeposit(userDetails);
                return editMessageService.editMessage("Введите сумму пополнения", chatId, messageId);
            }
            case BACK -> {
                return updateWithCallBackService.callBackEqualsBack(chatId, messageId, userDetails);
            }
            default -> {
                return editMessageService.editMessage("Команда не найдена.", chatId, messageId);
            }
        }
    }
}
