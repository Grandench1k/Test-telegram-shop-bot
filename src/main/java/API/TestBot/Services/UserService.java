package API.TestBot.Services;

import API.TestBot.Models.User;
import API.TestBot.Models.UserDetails;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserService {
    void registrationUser(Update update);

    String userBuyProduct(UserDetails userDetails, User user, int price, String product);


    String setUserBalance(Update update, User user, UserDetails userDetails);

}
