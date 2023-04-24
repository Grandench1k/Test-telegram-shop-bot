package API.TestBot.Services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static API.TestBot.Constants.TelegramBotConstants.constants.*;

@Service
public class InLineKeyboardService {
    public InlineKeyboardMarkup firstList(String firstText, String firstCallBackData, String secondText, String secondCallBackData) {
        InlineKeyboardMarkup markupLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        InlineKeyboardButton firstProduct = new InlineKeyboardButton();
        firstProduct.setText(firstText);
        firstProduct.setCallbackData(firstCallBackData);
        rowInLine.add(firstProduct);
        rowsInLine.add(rowInLine);
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        InlineKeyboardButton secondProduct = new InlineKeyboardButton();
        secondProduct.setText(secondText);
        secondRowInLine.add(secondProduct);
        secondProduct.setCallbackData(secondCallBackData);
        rowsInLine.add(secondRowInLine);
        markupLine.setKeyboard(rowsInLine);
        return markupLine;
    }

    public InlineKeyboardMarkup list(String firstText, String firstCallBackData, String secondText, String secondCallBackData) {
        InlineKeyboardMarkup markupLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        InlineKeyboardButton firstProduct = new InlineKeyboardButton();
        firstProduct.setText(firstText);
        firstProduct.setCallbackData(firstCallBackData);
        rowInLine.add(firstProduct);
        rowsInLine.add(rowInLine);
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        InlineKeyboardButton secondProduct = new InlineKeyboardButton();
        secondProduct.setText(secondText);
        secondRowInLine.add(secondProduct);
        secondProduct.setCallbackData(secondCallBackData);
        rowsInLine.add(secondRowInLine);
        InlineKeyboardButton back = new InlineKeyboardButton();
        List<InlineKeyboardButton> thirdRowInLine = new ArrayList<>();
        back.setText("Назад");
        back.setCallbackData(BACK);
        thirdRowInLine.add(back);
        rowsInLine.add(thirdRowInLine);
        markupLine.setKeyboard(rowsInLine);
        return markupLine;
    }

    public InlineKeyboardMarkup buyOneOrMultiplyProducts() {
        InlineKeyboardMarkup markupLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var firstProduct = new InlineKeyboardButton();
        firstProduct.setText("Купить");
        firstProduct.setCallbackData(ONE_PURCHASE);
        rowInLine.add(firstProduct);
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        var secondProduct = new InlineKeyboardButton();
        secondProduct.setText("Купить несколько");
        secondProduct.setCallbackData(MULTIPLE_PURCHASES);
        rowInLine.add(secondProduct);
        rowsInLine.add(rowInLine);
        markupLine.setKeyboard(rowsInLine);
        var back = new InlineKeyboardButton();
        back.setText("Назад");
        back.setCallbackData(BACK);
        secondRowInLine.add(back);
        rowsInLine.add(secondRowInLine);
        markupLine.setKeyboard(rowsInLine);
        return markupLine;
    }

    public InlineKeyboardMarkup buyOnlyOneProduct() {
        InlineKeyboardMarkup markupLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var firstProduct = new InlineKeyboardButton();
        firstProduct.setText("Купить");
        firstProduct.setCallbackData(ONE_PURCHASE);
        rowInLine.add(firstProduct);
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        rowsInLine.add(rowInLine);
        markupLine.setKeyboard(rowsInLine);
        var back = new InlineKeyboardButton();
        back.setText("Назад");
        back.setCallbackData(BACK);
        secondRowInLine.add(back);
        rowsInLine.add(secondRowInLine);
        markupLine.setKeyboard(rowsInLine);
        return markupLine;
    }

    public InlineKeyboardMarkup variationOfPayments() {
        InlineKeyboardMarkup markupLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var firstProduct = new InlineKeyboardButton();
        firstProduct.setText("ЮMoney");
        firstProduct.setCallbackData(YOOMONEY);
        rowInLine.add(firstProduct);
        rowsInLine.add(rowInLine);
        markupLine.setKeyboard(rowsInLine);
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        var back = new InlineKeyboardButton();
        back.setText("Назад");
        back.setCallbackData(BACK);
        secondRowInLine.add(back);
        rowsInLine.add(secondRowInLine);
        markupLine.setKeyboard(rowsInLine);
        return markupLine;
    }

    public InlineKeyboardMarkup depositKeyboard() {
        InlineKeyboardMarkup markupLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var button = new InlineKeyboardButton();
        button.setText("Пополнить баланс");
        button.setCallbackData(DEPOSIT);
        rowInLine.add(button);

        rowsInLine.add(rowInLine);

        markupLine.setKeyboard(rowsInLine);
        return markupLine;
    }

    public InlineKeyboardMarkup listOfLists() {
        return firstList("Первый раздел", FIRST_LIST, "Второй раздел", SECOND_LIST);
    }

    public InlineKeyboardMarkup firstlistOfProducts() {
        return list("Первый продукт", FIRST_PRODUCT, "Второй продукт", SECOND_PRODUCT);
    }

    public InlineKeyboardMarkup secondlistOfProducts() {
        return list("Третий продукт", THIRD_PRODUCT, "Четвёртый продукт", FOURTH_PRODUCT);
    }
}
