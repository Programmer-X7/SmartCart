package in.devsuman.smartcart.service;

import in.devsuman.smartcart.dto.SellerRequestDTO;
import in.devsuman.smartcart.dto.SellerResponseDTO;

import java.util.List;

public interface SellerService {
    SellerResponseDTO createSeller(SellerRequestDTO sellerRequestDTO);
    List<SellerResponseDTO> getAllSellers();
    SellerResponseDTO getSellerById(Long id);
    SellerResponseDTO updateSeller(Long id, SellerRequestDTO sellerRequestDTO);
    void deleteSeller(Long id);
}
