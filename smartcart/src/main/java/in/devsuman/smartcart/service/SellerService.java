package in.devsuman.smartcart.service;

import in.devsuman.smartcart.dto.SellerRequestDTO;
import in.devsuman.smartcart.dto.SellerResponseDTO;
import in.devsuman.smartcart.entity.Seller;

public interface SellerService {
    SellerResponseDTO createSeller(SellerRequestDTO sellerRequestDTO);
    SellerResponseDTO getSellerById(Long id);
    SellerResponseDTO updateSeller(Long id, SellerRequestDTO sellerRequestDTO);
}
