package API.TestBot.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thirdProductTable")
public class ThirdProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String thirdProductData;

}
