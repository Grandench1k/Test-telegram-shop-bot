package API.TestBot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usersTable")
public class User {
    @Id
    private Long chatId;
    private Integer balance = 0;
    private String userName;
    private Timestamp registeredAt;

    public User(Long chatId, String userName, Timestamp timestamp) {
        this.chatId = chatId;
        this.userName = userName;
        this.registeredAt = timestamp;
    }
}
