package API.TestBot.Repositories;

import API.TestBot.Models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findUserDetailsByChatId(long chatId);
}
