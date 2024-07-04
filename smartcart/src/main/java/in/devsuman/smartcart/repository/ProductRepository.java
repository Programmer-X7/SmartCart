package in.devsuman.smartcart.repository;

import in.devsuman.smartcart.entity.Category;
import in.devsuman.smartcart.entity.Product;
import in.devsuman.smartcart.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Fetch products for a Category with Pagination
    Page<Product> findByCategoryId(Category categoryId, Pageable pageable);

    // Fetch All products for a Category
    List<Product> findByCategoryId(Category category);

    // Fetch products sold by a specific Seller
    List<Product> findBySoldById(Seller seller);
}
