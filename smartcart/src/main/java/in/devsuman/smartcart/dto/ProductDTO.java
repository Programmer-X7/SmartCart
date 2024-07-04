package in.devsuman.smartcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long soldById;
    private String soldByName;
    private int quantity;
    private Long categoryId;
    private String categoryName;
}
