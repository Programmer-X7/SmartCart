package in.devsuman.smartcart.repository;

import in.devsuman.smartcart.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
