package API.TestBot.Core;

import API.TestBot.Models.*;
import API.TestBot.Repositories.*;
import API.TestBot.Services.MessageService;
import API.TestBot.Services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import static API.TestBot.Constants.TelegramBotConstants.constants.*;

@Component
@RequiredArgsConstructor
public class UpdateWithCallBackHandler {

    private final UserRepository userRepository;

    private final UserDetailsRepository userDetailsRepository;

    private final UserService userService;

    private final MessageService messageService;

    private final FirstProductRepository firstProductRepository;

    private final SecondProductRepository secondProductRepository;

    private final ThirdProductRepository thirdProductRepository;

    private final FourthProductRepository fourthProductRepository;

    @SneakyThrows
    public EditMessageText callbackChecker(Update update) {
        User user = userRepository.findUserByChatId(update.getCallbackQuery().getMessage().getChatId());
        UserDetails userDetails = userDetailsRepository.findUserDetailsByChatId(update.getCallbackQuery().getMessage().getChatId());
        int messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        int userProduct = userDetails.getWhatProduct();
        switch (update.getCallbackQuery().getData()) {
            case FIRST_LIST -> {
                userService.userSetPreviousCallBackQuery(userDetails, FIRST_LIST);
                return messageService.editMessageWithFirstListOfProducts(chatId, messageId);
            }
            case SECOND_LIST -> {
                userService.userSetPreviousCallBackQuery(userDetails, SECOND_LIST);
                return messageService.editMessageWithSecondListOfProducts(chatId, messageId);
            }
            case FIRST_PRODUCT -> {
                int firstProductSize = firstProductRepository.findAll().size();
                if (firstProductSize == 0) {
                    return messageService.editMessage(PRODUCT_MISSING, chatId, messageId);
                } else if (firstProductSize == 1) {
                    return messageService.editMessageTextWithKeyboardToPayOnlyOneProduct("Первый товар: \n Количество товара: " + firstProductSize + " шт.", chatId, messageId);
                } else {
                    userService.userSetWhatProductAndPreviousCallBackQuery(userDetails, 1, FIRST_PRODUCT);
                    return messageService.editMessageTextWithKeyboardToPayOneOrMultiplyProducts("Первый товар: \n Количество товара: " + firstProductSize + " шт.", chatId, messageId);
                }
            }
            case SECOND_PRODUCT -> {
                int secondProductSize = secondProductRepository.findAll().size();
                if (secondProductSize == 0) {
                    return messageService.editMessage(PRODUCT_MISSING, chatId, messageId);
                } else if (secondProductSize == 1) {
                    return messageService.editMessageTextWithKeyboardToPayOnlyOneProduct("Второй товар: \n Количество товара: " + secondProductSize + " шт.", chatId, messageId);
                } else {
                    userService.userSetWhatProductAndPreviousCallBackQuery(userDetails, 2, SECOND_PRODUCT);
                    return messageService.editMessageTextWithKeyboardToPayOneOrMultiplyProducts("Второй товар: \n Количество товара: " + secondProductSize + " шт.", chatId, messageId);
                }
            }
            case THIRD_PRODUCT -> {
                int thirdProductSize = thirdProductRepository.findAll().size();
                if (thirdProductSize == 0) {
                    return messageService.editMessage(PRODUCT_MISSING, chatId, messageId);
                } else if (thirdProductSize == 1) {
                    return messageService.editMessageTextWithKeyboardToPayOnlyOneProduct("Третий товар: \n Количество товара: " + thirdProductSize + " шт.", chatId, messageId);
                } else {
                    userService.userSetWhatProductAndPreviousCallBackQuery(userDetails, 3, THIRD_PRODUCT);
                    return messageService.editMessageTextWithKeyboardToPayOneOrMultiplyProducts("Третий товар: \n Количество товара: " + thirdProductSize + " шт.", chatId, messageId);
                }
            }
            case FOURTH_PRODUCT -> {
                int fourthProductSize = fourthProductRepository.findAll().size();
                if (fourthProductSize == 0) {
                    return messageService.editMessage(PRODUCT_MISSING, chatId, messageId);
                } else if (fourthProductSize == 1) {
                    return messageService.editMessageTextWithKeyboardToPayOnlyOneProduct("Четвёртый товар: \n Количество товара: " + fourthProductSize + " шт.", chatId, messageId);
                } else {
                    userService.userSetWhatProductAndPreviousCallBackQuery(userDetails, 4, FOURTH_PRODUCT);
                    return messageService.editMessageTextWithKeyboardToPayOneOrMultiplyProducts("Четвёртый товар: \n Количество товара: " + fourthProductSize + " шт.", chatId, messageId);
                }
            }
            case ONE_PURCHASE -> {
                if (userProduct == 1) {
                    FirstProduct firstProduct = firstProductRepository.findAll().stream().findFirst().get();
                    firstProductRepository.deleteById(firstProduct.getId());
                    userService.setUserTotalPurchases(userDetails, 15);
                    return messageService.editMessage(userService.userBuyProduct(user, 15, firstProduct.getFirstProductData()), chatId, messageId);
                } else if (userProduct == 2) {
                    SecondProduct secondProduct = secondProductRepository.findAll().stream().findFirst().get();
                    secondProductRepository.deleteById(secondProduct.getId());
                    userService.setUserTotalPurchases(userDetails, 20);
                    return messageService.editMessage(userService.userBuyProduct(user, 20, secondProduct.getSecondProductData()), chatId, messageId);
                } else if (userProduct == 3) {
                    ThirdProduct thirdProduct = thirdProductRepository.findAll().stream().findFirst().get();
                    thirdProductRepository.deleteById(thirdProduct.getId());
                    userService.setUserTotalPurchases(userDetails, 25);
                    return messageService.editMessage(userService.userBuyProduct(user, 25, thirdProduct.getThirdProductData()), chatId, messageId);
                } else if (userProduct == 4) {
                    FourthProduct fourthProduct = fourthProductRepository.findAll().stream().findFirst().get();
                    fourthProductRepository.deleteById(fourthProduct.getId());
                    userService.setUserTotalPurchases(userDetails, 30);
                    return messageService.editMessage(userService.userBuyProduct(user, 30, fourthProduct.getFourthProductData()), chatId, messageId);
                } else {
                    return messageService.editMessage("У вас недостаточно средств на балансе, его можно пополнить в разделе \"Профиль\"", chatId, messageId);
                }
            }
            case MULTIPLE_PURCHASES -> {
                userService.userSetBuyingProductsTrue(userDetails);
                return messageService.editMessage("Отправьте количество товаров для покупки", chatId, messageId);
            }
            case DEPOSIT -> {
                userService.userSetPreviousCallBackQuery(userDetails, DEPOSIT);
                return messageService.editMessageWithVariationOfPayments("Варианты оплаты", chatId, messageId);
            }
            case YOOMONEY -> {
                userDetails.setDeposit(true);
                userDetailsRepository.save(userDetails);
                return messageService.editMessage("Введите сумму пополнения", chatId, messageId);
            }
            case BACK -> {
                switch (userDetails.getPreviousCallBackQuery()) {
                    case FIRST_LIST, SECOND_LIST -> {
                        return messageService.editedMessageWithListOfLists(chatId, messageId);
                    }
                    case FIRST_PRODUCT, SECOND_PRODUCT -> {
                        userService.userSetPreviousCallBackQuery(userDetails, FIRST_LIST);
                        return messageService.editMessageWithFirstListOfProducts(chatId, messageId);
                    }
                    case THIRD_PRODUCT, FOURTH_PRODUCT -> {
                        userService.userSetPreviousCallBackQuery(userDetails, SECOND_LIST);
                        return messageService.editMessageWithSecondListOfProducts(chatId, messageId);
                    }
                    case DEPOSIT -> {
                        return messageService.editMessageWithProfile(chatId, messageId);
                    }
                }
            }
        }
        return null;
    }
}
