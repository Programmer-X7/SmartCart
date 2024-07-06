package in.devsuman.smartcart.mapper;

import in.devsuman.smartcart.dto.SellerRequestDTO;
import in.devsuman.smartcart.dto.SellerResponseDTO;
import in.devsuman.smartcart.entity.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {

    public Seller toEntity(SellerRequestDTO dto) {

        Seller seller = new Seller();

        seller.setId(dto.getId());
        seller.setName(dto.getName());
        seller.setCompanyName(dto.getCompanyName());
        seller.setPhoneNumber(dto.getPhoneNumber());
        seller.setPassword(dto.getPassword());
        seller.setEmail(dto.getEmail());
        seller.setStreetAddress(dto.getStreetAddress());
        seller.setCity(dto.getCity());
        seller.setState(dto.getState());
        seller.setPostalCode(dto.getPostalCode());
        seller.setCountry(dto.getCountry());

        return seller;
    }

    public SellerResponseDTO toDTO(Seller seller) {
        SellerResponseDTO sellerResponseDTO = new SellerResponseDTO();

        sellerResponseDTO.setId(seller.getId());
        sellerResponseDTO.setName(seller.getName());
        sellerResponseDTO.setCompanyName(seller.getCompanyName());
        sellerResponseDTO.setPhoneNumber(seller.getPhoneNumber());
        sellerResponseDTO.setEmail(seller.getEmail());
        sellerResponseDTO.setStreetAddress(seller.getStreetAddress());
        sellerResponseDTO.setCity(seller.getCity());
        sellerResponseDTO.setState(seller.getState());
        sellerResponseDTO.setPostalCode(seller.getPostalCode());
        sellerResponseDTO.setCountry(seller.getCountry());

        return sellerResponseDTO;
    }
}
