package API.TestBot.Services.Impl;

import API.TestBot.Models.FirstProduct;
import API.TestBot.Models.FourthProduct;
import API.TestBot.Models.SecondProduct;
import API.TestBot.Models.ThirdProduct;
import API.TestBot.Repositories.FirstProductRepository;
import API.TestBot.Repositories.FourthProductRepository;
import API.TestBot.Repositories.SecondProductRepository;
import API.TestBot.Repositories.ThirdProductRepository;
import API.TestBot.Services.MessageChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageCheckerImpl implements MessageChecker {
    private final FirstProductRepository firstProductRepository;
    private final SecondProductRepository secondProductRepository;
    private final ThirdProductRepository thirdProductRepository;
    private final FourthProductRepository fourthProductRepository;

    public String messageContainsFirstProduct(String messageText) {
        if (messageText.length() == 16) {
            return "Вы не ввели сам продукт";
        }
        if (!(messageText.substring(messageText.indexOf(" ")).contains("/"))) {
            FirstProduct firstProduct = new FirstProduct();
            String productToAdd = messageText.substring(messageText.indexOf(" "));
            firstProduct.setFirstProductData(productToAdd);
            firstProductRepository.save(firstProduct);
            return "Первый продукт успешно сохранен";
        } else {
            return "Пожалуйста, введите товар без посторонних знаков";
        }
    }

    public String messageContainsSecondProduct(String messageText) {
        if (messageText.length() == 17) {
            return "Вы не ввели сам продукт";
        }
        if (!(messageText.substring(messageText.indexOf(" ")).contains("/"))) {
            SecondProduct secondProduct = new SecondProduct();
            String productToAdd = messageText.substring(messageText.indexOf(" "));
            secondProduct.setSecondProductData(productToAdd);
            secondProductRepository.save(secondProduct);
            return "Второй продукт успешно сохранен";
        } else {
            return "Пожалуйста, введите товар без посторонних знаков";
        }
    }

    public String messageContainsThirdProduct(String messageText) {
        if (messageText.length() == 16) {
            return "Вы не ввели сам продукт";
        }
        if (!(messageText.substring(messageText.indexOf(" ")).contains("/"))) {
            ThirdProduct thirdProduct = new ThirdProduct();
            String productToAdd = messageText.substring(messageText.indexOf(" "));
            thirdProduct.setThirdProductData(productToAdd);
            thirdProductRepository.save(thirdProduct);
            return "Третий продукт успешно сохранен";
        } else {
            return "Пожалуйста, введите товар без посторонних знаков";
        }
    }

    public String messageContainsFourthProduct(String messageText) {
        if (messageText.length() == 17) {
            return "Вы не ввели сам продукт";
        }
        if (!(messageText.substring(messageText.indexOf(" ")).contains("/"))) {
            FourthProduct fourthProduct = new FourthProduct();
            String productToAdd = messageText.substring(messageText.indexOf(" "));
            fourthProduct.setFourthProductData(productToAdd);
            fourthProductRepository.save(fourthProduct);
            return "Четвёртый продукт успешно сохранен";
        } else {
            return "Пожалуйста, введите товар без посторонних знаков";
        }
    }
}
