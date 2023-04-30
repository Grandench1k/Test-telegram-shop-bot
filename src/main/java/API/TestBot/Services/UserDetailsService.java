package API.TestBot.Services;

import API.TestBot.Models.UserDetails;

public interface UserDetailsService {
    void userSetWhatProductAndPreviousCallBackQuery(UserDetails userDetails, int whatProduct, String callBack);

    void userSetPreviousCallBackQuery(UserDetails userDetails, String callBack);

    void userSetBuyingProducts(UserDetails userDetails, boolean value);

    void setUserTotalPurchases(UserDetails userDetails, int purchase);

    void setUserDetailsDeposit(UserDetails userDetails);
}
