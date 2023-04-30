package API.TestBot.Services;

public interface MessageChecker {
    String messageContainsFirstProduct(String messageText);

    String messageContainsSecondProduct(String messageText);

    String messageContainsThirdProduct(String messageText);

    String messageContainsFourthProduct(String messageText);
}
