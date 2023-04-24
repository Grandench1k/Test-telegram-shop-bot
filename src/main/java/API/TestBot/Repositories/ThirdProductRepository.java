package API.TestBot.Repositories;

import API.TestBot.Models.ThirdProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdProductRepository extends JpaRepository<ThirdProduct, Long> {
    ThirdProduct findById(long id);
}
