package API.TestBot.Handlers;

import API.TestBot.Config.BotConfig;
import API.TestBot.Models.User;
import API.TestBot.Models.UserDetails;
import API.TestBot.Repositories.UserDetailsRepository;
import API.TestBot.Repositories.UserRepository;
import API.TestBot.Services.MessageService;
import API.TestBot.Services.OrderService;
import API.TestBot.Services.UpdateWithoutCallBackService;
import API.TestBot.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class UpdateHandler {
    private final BotConfig botConfig;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final UserService userService;
    private final MessageService messageService;
    private final UpdateWithoutCallBackService updateWithoutCallBackService;
    private final OrderService orderService;

    public SendMessage updateWithoutCallBackCheck(Update update) {
        String ownerUserName = botConfig.getOwnerUserName();
        String messageText = update.getMessage().getText();
        User user = userRepository.findUserByChatId(update.getMessage().getChatId());
        UserDetails userDetails = userDetailsRepository.findUserDetailsByChatId(update.getMessage().getChatId());
        long chatId = update.getMessage().getChatId();
        String userName = update.getMessage().getChat().getUserName();
        if (update.hasMessage() && userDetails.isDeposit()) {
            return messageService.messageWithInteractiveKeyboard(userService.setUserBalance(update, user, userDetails), update.getMessage().getChatId());
        } else if (update.hasMessage() && userDetails.isBuyingProducts()) {
            return messageService.messageWithInteractiveKeyboard(orderService.buyProducts(update), update.getMessage().getChatId());
        } else {
            if (userName != null && userName.equals(ownerUserName) && messageText.contains("/send") || (messageText.contains("/add"))) {
                return updateWithoutCallBackService.updateWithSlash(messageText, chatId);
            } else {
                return updateWithoutCallBackService.mainUpdateChecker(messageText, userName, ownerUserName, chatId, user);
            }
        }
    }
}
