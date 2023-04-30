package API.TestBot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userDetailsTable")
public class UserDetails {
    @Id
    private Long chatId;
    private boolean isBuyingProducts;
    private boolean isDeposit;
    private int whatProduct;
    private int totalDeposit;
    private int TotalPurchases;
    private String previousCallBackQuery;

    public UserDetails(long chatId) {
        this.chatId = chatId;
        this.isBuyingProducts = false;
        this.isDeposit = false;
        this.totalDeposit = 0;
        this.TotalPurchases = 0;
    }
}
