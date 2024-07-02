package in.devsuman.smartcart.service.impl;

import in.devsuman.smartcart.dto.SellerRequestDTO;
import in.devsuman.smartcart.dto.SellerResponseDTO;
import in.devsuman.smartcart.entity.Seller;
import in.devsuman.smartcart.mapper.SellerMapper;
import in.devsuman.smartcart.repository.SellerRepository;
import in.devsuman.smartcart.service.SellerService;
import in.devsuman.smartcart.utils.PasswordHashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final PasswordHashingUtil passwordHashingUtil;
    private final SellerMapper sellerMapper;

    @Autowired
    public SellerServiceImpl(
            SellerRepository sellerRepository,
            PasswordHashingUtil passwordHashingUtil,
            SellerMapper sellerMapper) {
        this.sellerRepository = sellerRepository;
        this.passwordHashingUtil = passwordHashingUtil;
        this.sellerMapper = sellerMapper;
    }

    // Seller create
    @Override
    public SellerResponseDTO createSeller(SellerRequestDTO sellerRequestDTO) {

        // Convert: SellerRequestDTO -> Seller
        Seller seller = sellerMapper.toEntity(sellerRequestDTO);

        // Hashing password before saving
        String hashedPassword = passwordHashingUtil.hashPassword(seller.getPassword());
        seller.setPassword(hashedPassword);

        // Saving to Database
        Seller savedSeller = sellerRepository.save(seller);

        // Convert & Return: Seller -> SellerResponseDTO
        return sellerMapper.toResponseDTO(savedSeller);
    }

    @Override
    public SellerResponseDTO getSellerById(Long id) {

        // Check Seller existence
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + id));

        return sellerMapper.toResponseDTO(seller);
    }

    // Seller Details Update
    @Override
    public SellerResponseDTO updateSeller(Long id, SellerRequestDTO sellerRequestDTO) {

        // Add logic to fetch existing seller by id, update fields, and save
        Seller existingSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + id));

        // perform updates
        if (sellerRequestDTO.getName() != null) {
            existingSeller.setName(sellerRequestDTO.getName());
        }
        if (sellerRequestDTO.getCompanyName() != null) {
            existingSeller.setCompanyName(sellerRequestDTO.getCompanyName());
        }
        if (sellerRequestDTO.getPhoneNumber() != null) {
            existingSeller.setPhoneNumber(sellerRequestDTO.getPhoneNumber());
        }
        if (sellerRequestDTO.getStreetAddress() != null) {
            existingSeller.setStreetAddress(sellerRequestDTO.getStreetAddress());
        }
        if (sellerRequestDTO.getCity() != null) {
            existingSeller.setCity(sellerRequestDTO.getCity());
        }
        if (sellerRequestDTO.getState() != null) {
            existingSeller.setState(sellerRequestDTO.getState());
        }
        if (sellerRequestDTO.getPostalCode() != null) {
            existingSeller.setPostalCode(sellerRequestDTO.getPostalCode());
        }
        if (sellerRequestDTO.getCountry() != null) {
            existingSeller.setCountry(sellerRequestDTO.getCountry());
        }

        // Update Email & Password if Needed
        if (sellerRequestDTO.getEmail() != null) {
            existingSeller.setEmail(sellerRequestDTO.getEmail());
        }
        if (sellerRequestDTO.getPassword() != null) {
            // perform hashing
            String hashedPassword = passwordHashingUtil.hashPassword(sellerRequestDTO.getPassword());
            existingSeller.setPassword(hashedPassword);
        }

        Seller updatedSeller = sellerRepository.save(existingSeller);

        return sellerMapper.toResponseDTO(updatedSeller);
    }
}
