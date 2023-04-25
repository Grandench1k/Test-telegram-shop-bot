package API.TestBot.Services;

import API.TestBot.Models.*;
import API.TestBot.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;


@RequiredArgsConstructor
@Service
public class OrderService {
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final FirstProductRepository firstProductRepository;
    private final SecondProductRepository secondProductRepository;
    private final ThirdProductRepository thirdProductRepository;
    private final FourthProductRepository fourthProductRepository;

    public String buyProducts(Update update) {
        try {
            int valueOfProducts = Integer.parseInt(update.getMessage().getText());
            long chatId = update.getMessage().getChatId();
            User user = userRepository.findUserByChatId(chatId);
            UserDetails userDetails = userDetailsRepository.findUserDetailsByChatId(chatId);
            int userBalance = user.getBalance();
            if (userDetails.getWhatProduct() == 1) {
                return buyFirstProducts(user, userDetails, valueOfProducts, userBalance);
            }
            if (userDetails.getWhatProduct() == 2) {
                return buySecondProducts(user, userDetails, valueOfProducts, userBalance);
            }
            if (userDetails.getWhatProduct() == 3) {
                return buyThirdProducts(user, userDetails, valueOfProducts, userBalance);
            }
            if (userDetails.getWhatProduct() == 4) {
                return buyFourthProducts(user, userDetails, valueOfProducts, userBalance);
            }
            return null;
        } catch (NumberFormatException e) {
            return "Пожалуйста введите сумму без посторонних символов";
        }
    }

    private String buyFirstProducts(User user, UserDetails userDetails, int valueOfProducts, int userBalance) {
        List<FirstProduct> firstProductList = firstProductRepository.findAll();
        if (valueOfProducts <= 0) {
            return "Вы не можете меньше 1 товара";
        }
        if (valueOfProducts > firstProductList.size()) {
            userDetails.setBuyingProducts(false);
            userRepository.save(user);
            return "Вы ввели количество товара, превыщающее товаров в наличии";
        }
        if (userBalance == 0 || userBalance < valueOfProducts * 15) {
            userDetails.setBuyingProducts(false);
            userRepository.save(user);
            return "У вас недостаточно средств на балансе, его можно пополнить в в разделе \"Профиль\"";
        }
        if (userBalance >= valueOfProducts * 15) {
            StringBuilder newText = new StringBuilder("Ваши товары: \n\n");
            long firstIdOfFirstProductList = firstProductRepository.findAll().stream().findFirst().get().getId();
            long valueOfFirstProducts = firstIdOfFirstProductList + valueOfProducts;
            for (long i = firstIdOfFirstProductList; i < valueOfFirstProducts; i++) {
                newText.append(firstProductRepository.findById(i).getFirstProductData()).append("\n");
                firstProductRepository.deleteById(i);
            }
            newText.append("\nСпасибо за покупку!");
            userDetails.setBuyingProducts(false);
            user.setBalance(userBalance - valueOfProducts * 15);
            userDetails.setTotalPurchases(userDetails.getTotalPurchases() + valueOfProducts * 15);
            userRepository.save(user);
            userDetailsRepository.save(userDetails);
            return newText.toString();
        }
        return null;
    }

    private String buySecondProducts(User user, UserDetails userDetails, int valueOfProducts, int userBalance) {
        List<SecondProduct> secondProductList = secondProductRepository.findAll();
        if (valueOfProducts <= 0) {
            return "Вы не можете меньше 1 товара";
        }
        if (valueOfProducts > secondProductList.size()) {
            userDetails.setBuyingProducts(false);
            userRepository.save(user);
            return "Вы ввели количество товара, превыщающее товаров в наличии";
        }
        if (userBalance == 0 || userBalance < valueOfProducts * 20) {
            userDetails.setBuyingProducts(false);
            userRepository.save(user);
            return "У вас недостаточно средств на балансе, его можно пополнить в в разделе \"Профиль\"";
        }
        if (userBalance >= valueOfProducts * 20) {
            StringBuilder newText = new StringBuilder("Ваши товары: \n\n");
            long firstIdOfSecondProductList = secondProductRepository.findAll().stream().findFirst().get().getId();
            long valueOfSecondProducts = firstIdOfSecondProductList + valueOfProducts;
            for (long i = firstIdOfSecondProductList; i < valueOfSecondProducts; i++) {
                newText.append(secondProductRepository.findById(i).getSecondProductData()).append("\n");
                secondProductRepository.deleteById(i);
            }
            newText.append("\nСпасибо за покупку!");
            userDetails.setBuyingProducts(false);
            user.setBalance(userBalance - valueOfProducts * 20);
            userDetails.setTotalPurchases(userDetails.getTotalPurchases() + valueOfProducts * 20);
            userRepository.save(user);
            userDetailsRepository.save(userDetails);
            return newText.toString();
        }
        return null;
    }

    private String buyThirdProducts(User user, UserDetails userDetails, int valueOfProducts, int userBalance) {
        List<ThirdProduct> thirdProductList = thirdProductRepository.findAll();
        if (valueOfProducts <= 0) {
            return "Вы не можете меньше 1 товара";
        }
        if (valueOfProducts > thirdProductList.size()) {
            userDetails.setBuyingProducts(false);
            userRepository.save(user);
            return "Вы ввели количество товара, превыщающее товаров в наличии";
        }
        if (userBalance == 0 || userBalance < valueOfProducts * 25) {
            userDetails.setBuyingProducts(false);
            userRepository.save(user);
            return "У вас недостаточно средств на балансе, его можно пополнить в в разделе \"Профиль\"";
        }
        if (userBalance >= valueOfProducts * 25) {
            StringBuilder newText = new StringBuilder("Ваши товары: \n\n");
            long firstIdOfThirdProductList = thirdProductRepository.findAll().stream().findFirst().get().getId();
            long valueOfThirdProducts = firstIdOfThirdProductList + valueOfProducts;
            for (long i = firstIdOfThirdProductList; i < valueOfThirdProducts; i++) {
                newText.append(thirdProductRepository.findById(i).getThirdProductData()).append("\n");
                thirdProductRepository.deleteById(i);
            }
            newText.append("\nСпасибо за покупку!");
            userDetails.setBuyingProducts(false);
            user.setBalance(userBalance - valueOfProducts * 25);
            userDetails.setTotalPurchases(userDetails.getTotalPurchases() + valueOfProducts * 25);
            userRepository.save(user);
            userDetailsRepository.save(userDetails);
            return newText.toString();
        }
        return null;
    }


    public String buyFourthProducts(User user, UserDetails userDetails, int valueOfProducts, int userBalance) {
        List<FourthProduct> fourthProductList = fourthProductRepository.findAll();
        if (valueOfProducts <= 0) {
            return "Вы не можете меньше 1 товара";
        } else if (valueOfProducts > fourthProductList.size()) {
            userDetails.setBuyingProducts(false);
            userRepository.save(user);
            return "Вы ввели количество товара, превыщающее товаров в наличии";
        } else if (userBalance == 0 || userBalance < valueOfProducts * 30) {
            userDetails.setBuyingProducts(false);
            userRepository.save(user);
            return "У вас недостаточно средств на балансе, его можно пополнить в в разделе \"Профиль\"";
        } else if (userBalance >= valueOfProducts * 30) {
            StringBuilder newText = new StringBuilder("Ваши товары: \n\n");
            long firstIdOfFourthProductList = fourthProductRepository.findAll().stream().findFirst().get().getId();
            long valueOfFourthProducts = firstIdOfFourthProductList + valueOfProducts;
            for (long i = firstIdOfFourthProductList; i < valueOfFourthProducts; i++) {
                newText.append(fourthProductRepository.findById(i).getFourthProductData()).append("\n");
                fourthProductRepository.deleteById(i);
            }
            newText.append("\nСпасибо за покупку!");
            userDetails.setBuyingProducts(false);
            user.setBalance(userBalance - valueOfProducts * 30);
            userDetails.setTotalPurchases(userDetails.getTotalPurchases() + valueOfProducts * 30);
            userRepository.save(user);
            userDetailsRepository.save(userDetails);
            return newText.toString();
        }
        return null;
    }

}
