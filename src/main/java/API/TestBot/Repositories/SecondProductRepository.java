package API.TestBot.Repositories;

import API.TestBot.Models.SecondProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondProductRepository extends JpaRepository<SecondProduct, Long> {
    SecondProduct findById(long id);
}
