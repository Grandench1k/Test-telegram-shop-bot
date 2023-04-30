package API.TestBot.Services;

import API.TestBot.Models.User;
import API.TestBot.Models.UserDetails;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface OrderService {
    String buyProducts(Update update);

    String buyFirstProducts(User user, UserDetails userDetails, int valueOfProducts, int userBalance);

    String buySecondProducts(User user, UserDetails userDetails, int valueOfProducts, int userBalance);

    String buyThirdProducts(User user, UserDetails userDetails, int valueOfProducts, int userBalance);


    String buyFourthProducts(User user, UserDetails userDetails, int valueOfProducts, int userBalance);

}
