package API.TestBot.Repositories;

import API.TestBot.Models.FirstProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstProductRepository extends JpaRepository<FirstProduct, Long> {
    FirstProduct findById(long id);
}
