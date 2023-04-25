package API.TestBot.Constants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramBotConstants {
    public interface constants {

        String FIRST_LIST = "FIRST_LIST";

        String SECOND_LIST = "SECOND_LIST";

        String BACK = "BACK";

        String ONE_PURCHASE = "ONE_PURCHASE";

        String MULTIPLE_PURCHASES = "MULTIPLE_PURCHASES";

        String FIRST_PRODUCT = "FIRST_PRODUCT";

        String SECOND_PRODUCT = "SECOND_PRODUCT";

        String THIRD_PRODUCT = "THIRD PRODUCT";

        String FOURTH_PRODUCT = "FOURTH PRODUCT";

        String PRODUCT_MISSING = "Извините, в данный момент товар отсутствует";

        String DEPOSIT = "DEPOSIT";

        String YOOMONEY = "YOOMONEY";

        String RULES_TEXT = """
                \uD83D\uDCCB 1. Общие правила
                1.1 Администрация оставляет за собой право вносить любые изменения и дополнения в правила без предупреждения.
                1.2 Возврат денежных средств осуществляется только на баланс бота.
                1.3 Незнание правил не освобождает от ответственности.
                ⚠ 2. Покупка товара
                2.1 Перед покупкой обязательно ознакомьтесь с описанием товара.
                2.2 В случае возникновения проблем покупатель должен незамедлительно написать в поддержку.
                2.3 Ответ оператора может занимать до 24 часов (в среднем 7 минут)
                ✅ 2. Гарантия
                2.1 Гарантийное время начинается с момента покупки.
                2.2 По истечении гарантийного времени товар возврату не подлежит.
                \uD83D\uDCDD 3. Пользовательское соглашение
                3.1 Совершая покупки в боте, вы автоматически соглашаетесь с вышеперечисленными правилами.
                3.2 Пользуясь ботом, вы автоматически даете согласие на обработку персональных данных согласно Федеральному закону 'О персональных данных' от 27.07.2006 N 152-ФЗ.""";
        String HELP_FOR_USERS = "Команда /start нужна для того, чтобы начать работу с ботом \n"
                + "команда /profile нужна для того, чтобы увидеть свой id и баланс кошелька \n"
                + "команда /rules нужна для того, чтобы ознакомиться с правилами \n";

        String EXTRA_HELP = "ну и команда /help нужна для того, чтобы вновь показать данное сообщение \n\n";

        String EXTRA_HELP_FOR_OWNER = "команда /send нужна для того, чтобы отправить нужное вам сообщение всем пользователям бота \n"
                + "команда /addFirstProduct {сам продукт} нужна для того, чтобы добавить первый продукт в базу данных \n"
                + "команда /addSecondProduct {сам продукт} нужна для того, чтобы добавить второй продукт в базу данных \n"
                + "команда /addThirdProduct {сам продукт} нужна для того, чтобы добавить третий продукт в базу данных \n"
                + "команда /addFourthProduct {сам продукт} нужна для того, чтобы добавить четвёртый продукт в базу данных \n";
    }
}
