package API.TestBot.Services;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface InLineKeyboardService {
    InlineKeyboardMarkup firstList(String firstText, String firstCallBackData, String secondText, String secondCallBackData);

    InlineKeyboardMarkup list(String firstText, String firstCallBackData, String secondText, String secondCallBackData);

    InlineKeyboardMarkup buyOneOrMultiplyProducts();

    InlineKeyboardMarkup buyOnlyOneProduct();

    InlineKeyboardMarkup variationOfPayments();

    InlineKeyboardMarkup depositKeyboard();

    InlineKeyboardMarkup listOfLists();

    InlineKeyboardMarkup firstListOfProducts();

    InlineKeyboardMarkup secondListOfProducts();
}
