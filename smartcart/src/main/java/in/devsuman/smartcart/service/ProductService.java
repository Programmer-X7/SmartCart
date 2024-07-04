package in.devsuman.smartcart.service;

import in.devsuman.smartcart.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    // Todo: Get All products (All) with some limitation of-course for frontend
    List<ProductDTO> getAllProductsBySeller(Long sellerId);
    Page<ProductDTO> getAllProductsByCategory(Long categoryId, int page, int size);
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
