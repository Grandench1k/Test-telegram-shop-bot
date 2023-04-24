package API.TestBot.Repositories;

import API.TestBot.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByChatId(long chatId);

}
