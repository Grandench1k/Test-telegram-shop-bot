package API.TestBot.Services.Impl;

import API.TestBot.Models.*;
import API.TestBot.Repositories.FirstProductRepository;
import API.TestBot.Repositories.FourthProductRepository;
import API.TestBot.Repositories.SecondProductRepository;
import API.TestBot.Repositories.ThirdProductRepository;
import API.TestBot.Services.EditMessageService;
import API.TestBot.Services.UpdateWithCallBackService;
import API.TestBot.Services.UserDetailsService;
import API.TestBot.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import static API.TestBot.Constants.TelegramBotConstants.constants.*;

@Service
@RequiredArgsConstructor
public class UpdateWithCallBackServiceImpl implements UpdateWithCallBackService {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final EditMessageService editMessageService;
    private final FirstProductRepository firstProductRepository;
    private final SecondProductRepository secondProductRepository;
    private final ThirdProductRepository thirdProductRepository;
    private final FourthProductRepository fourthProductRepository;

    public EditMessageText callBackEqualFirstList(long chatId, int messageId, String LIST, UserDetails userDetails) {
        userDetailsService.userSetPreviousCallBackQuery(userDetails, LIST);
        return editMessageService.editMessageWithFirstListOfProducts(chatId, messageId);
    }

    public EditMessageText callBackEqualSecondList(long chatId, int messageId, String LIST, UserDetails userDetails) {
        userDetailsService.userSetPreviousCallBackQuery(userDetails, LIST);
        return editMessageService.editMessageWithSecondListOfProducts(chatId, messageId);
    }

    public EditMessageText callBackEqualProduct(int productSize, long chatId, int messageId, int whatProduct, String numberOfProduct, UserDetails userDetails, String PRODUCT) {
        userDetailsService.userSetWhatProductAndPreviousCallBackQuery(userDetails, whatProduct, PRODUCT);
        if (productSize == 0) {
            return editMessageService.editMessage(PRODUCT_MISSING, chatId, messageId);
        } else if (productSize == 1) {
            return editMessageService.editMessageTextWithKeyboardToPayOnlyOneProduct(numberOfProduct + " товар: \n Количество товара: " + productSize + " шт.", chatId, messageId);
        } else {
            return editMessageService.editMessageTextWithKeyboardToPayOneOrMultiplyProducts(numberOfProduct + " товар: \n Количество товара: " + productSize + " шт.", chatId, messageId);
        }
    }

    public EditMessageText callBackEqualOnePurchase(UserDetails userDetails, User user, int userProduct, long chatId, int messageId) {
        if (userProduct == 1) {
            FirstProduct firstProduct = firstProductRepository.findAll().stream().findFirst().get();
            firstProductRepository.deleteById(firstProduct.getId());
            return editMessageService.editMessage(userService.userBuyProduct(userDetails, user, 15, firstProduct.getFirstProductData()), chatId, messageId);
        } else if (userProduct == 2) {
            SecondProduct secondProduct = secondProductRepository.findAll().stream().findFirst().get();
            secondProductRepository.deleteById(secondProduct.getId());
            return editMessageService.editMessage(userService.userBuyProduct(userDetails, user, 20, secondProduct.getSecondProductData()), chatId, messageId);
        } else if (userProduct == 3) {
            ThirdProduct thirdProduct = thirdProductRepository.findAll().stream().findFirst().get();
            thirdProductRepository.deleteById(thirdProduct.getId());
            return editMessageService.editMessage(userService.userBuyProduct(userDetails, user, 25, thirdProduct.getThirdProductData()), chatId, messageId);
        } else if (userProduct == 4) {
            FourthProduct fourthProduct = fourthProductRepository.findAll().stream().findFirst().get();
            fourthProductRepository.deleteById(fourthProduct.getId());
            return editMessageService.editMessage(userService.userBuyProduct(userDetails, user, 30, fourthProduct.getFourthProductData()), chatId, messageId);
        } else {
            return editMessageService.editMessage("У вас недостаточно средств на балансе, его можно пополнить в разделе \"Профиль\"", chatId, messageId);
        }
    }

    public EditMessageText callBackEqualsBack(long chatId, int messageId, UserDetails userDetails) {
        switch (userDetails.getPreviousCallBackQuery()) {
            case FIRST_LIST, SECOND_LIST -> {
                return editMessageService.editedMessageWithListOfLists(chatId, messageId);
            }
            case FIRST_PRODUCT, SECOND_PRODUCT -> {
                userDetailsService.userSetPreviousCallBackQuery(userDetails, FIRST_LIST);
                return editMessageService.editMessageWithFirstListOfProducts(chatId, messageId);
            }
            case THIRD_PRODUCT, FOURTH_PRODUCT -> {
                userDetailsService.userSetPreviousCallBackQuery(userDetails, SECOND_LIST);
                return editMessageService.editMessageWithSecondListOfProducts(chatId, messageId);
            }
            case DEPOSIT -> {
                return editMessageService.editMessageWithProfile(chatId, messageId);
            }
        }
        return editMessageService.editMessage("Команда не найдена", chatId, messageId);
    }
}
