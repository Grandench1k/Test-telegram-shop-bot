package API.TestBot.Services.Impl;

import API.TestBot.Models.UserDetails;
import API.TestBot.Repositories.UserDetailsRepository;
import API.TestBot.Services.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    public void userSetWhatProductAndPreviousCallBackQuery(UserDetails userDetails, int whatProduct, String callBack) {
        userDetails.setPreviousCallBackQuery(callBack);
        userDetails.setWhatProduct(whatProduct);
        userDetailsRepository.save(userDetails);
    }
    public void userSetPreviousCallBackQuery(UserDetails userDetails, String callBack) {
        userDetails.setPreviousCallBackQuery(callBack);
        userDetailsRepository.save(userDetails);
    }

    public void userSetBuyingProducts(UserDetails userDetails, boolean value) {
        userDetails.setBuyingProducts(value);
        userDetailsRepository.save(userDetails);
    }
    public void setUserTotalPurchases(UserDetails userDetails, int purchase) {
        userDetails.setTotalPurchases(userDetails.getTotalPurchases() + purchase);
        userDetailsRepository.save(userDetails);
    }

    public void setUserDetailsDeposit(UserDetails userDetails) {
        userDetails.setDeposit(true);
        userDetailsRepository.save(userDetails);
    }
}
