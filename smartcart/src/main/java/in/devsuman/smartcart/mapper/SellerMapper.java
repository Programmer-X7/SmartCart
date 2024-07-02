package in.devsuman.smartcart.mapper;

import in.devsuman.smartcart.dto.SellerRequestDTO;
import in.devsuman.smartcart.dto.SellerResponseDTO;
import in.devsuman.smartcart.entity.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {

    public Seller toEntity(SellerRequestDTO dto) {

        Seller seller = new Seller();

        seller.setName(dto.getName());
        seller.setEmail(dto.getEmail());
        seller.setPassword(dto.getPassword());
        seller.setCompanyName(dto.getCompanyName());
        seller.setPhoneNumber(dto.getPhoneNumber());
        seller.setStreetAddress(dto.getStreetAddress());
        seller.setCity(dto.getCity());
        seller.setState(dto.getState());
        seller.setPostalCode(dto.getPostalCode());
        seller.setCountry(dto.getCountry());

        return seller;
    }

    public SellerResponseDTO toResponseDTO(Seller seller) {
        SellerResponseDTO dto = new SellerResponseDTO();

        dto.setId(seller.getId());
        dto.setName(seller.getName());
        dto.setEmail(seller.getEmail());
        dto.setCompanyName(seller.getCompanyName());
        dto.setPhoneNumber(seller.getPhoneNumber());
        dto.setStreetAddress(seller.getStreetAddress());
        dto.setCity(seller.getCity());
        dto.setState(seller.getState());
        dto.setPostalCode(seller.getPostalCode());
        dto.setCountry(seller.getCountry());

        return dto;
    }
}
