package in.devsuman.smartcart.mapper;

import in.devsuman.smartcart.dto.ProductDTO;
import in.devsuman.smartcart.entity.Category;
import in.devsuman.smartcart.entity.Product;
import in.devsuman.smartcart.entity.Seller;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO, Seller seller, Category category) {
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                seller,
                productDTO.getQuantity(),
                category
        );
    }

    public ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSoldById().getId(),
                product.getSoldById().getName(),
                product.getQuantity(),
                product.getCategoryId().getId(),
                product.getCategoryId().getName()
        );
    }
}
