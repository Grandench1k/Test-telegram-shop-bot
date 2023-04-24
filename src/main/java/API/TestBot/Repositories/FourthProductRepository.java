package API.TestBot.Repositories;

import API.TestBot.Models.FourthProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FourthProductRepository extends JpaRepository<FourthProduct, Long> {
    FourthProduct findById(long id);
}
