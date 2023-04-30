package API.TestBot.Services.Impl;

import API.TestBot.Services.KeyboardService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardServiceImpl implements KeyboardService {
    public ReplyKeyboardMarkup mainKeyBoard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add(EmojiParser.parseToUnicode(":book:" + "Список товаров" + ":book:"));
        row.add(EmojiParser.parseToUnicode(":white_check_mark:" + "Наличие товаров" + ":negative_squared_cross_mark:"));

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add(EmojiParser.parseToUnicode(":star_struck:" + "Профиль" + ":star_struck:"));

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add(EmojiParser.parseToUnicode(":page_facing_up: " + "Правила" + ":page_facing_up: "));
        row.add(EmojiParser.parseToUnicode(":ambulance:" + "Помощь" + ":ambulance:"));

        keyboardRows.add(row);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        return replyKeyboardMarkup;
    }
}
