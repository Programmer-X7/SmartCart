package in.devsuman.smartcart.mapper;

import in.devsuman.smartcart.dto.SellerRequestDTO;
import in.devsuman.smartcart.dto.SellerResponseDTO;
import in.devsuman.smartcart.entity.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {

    public Seller toEntity(SellerRequestDTO dto) {

        return new Seller(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getCompanyName(),
                dto.getPhoneNumber(),
                dto.getStreetAddress(),
                dto.getCity(),
                dto.getState(),
                dto.getPostalCode(),
                dto.getCountry()
        );
    }

    public SellerResponseDTO toDTO(Seller seller) {
        return new SellerResponseDTO(
                seller.getId(),
                seller.getName(),
                seller.getEmail(),
                seller.getCompanyName(),
                seller.getPhoneNumber(),
                seller.getStreetAddress(),
                seller.getCity(),
                seller.getState(),
                seller.getPostalCode(),
                seller.getCountry()
        );
    }
}
