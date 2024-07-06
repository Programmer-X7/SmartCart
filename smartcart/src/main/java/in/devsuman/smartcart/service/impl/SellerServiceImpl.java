package in.devsuman.smartcart.service.impl;

import in.devsuman.smartcart.dto.SellerRequestDTO;
import in.devsuman.smartcart.dto.SellerResponseDTO;
import in.devsuman.smartcart.entity.Product;
import in.devsuman.smartcart.entity.Seller;
import in.devsuman.smartcart.entity.User;
import in.devsuman.smartcart.mapper.SellerMapper;
import in.devsuman.smartcart.repository.ProductRepository;
import in.devsuman.smartcart.repository.SellerRepository;
import in.devsuman.smartcart.repository.UserRepository;
import in.devsuman.smartcart.service.SellerService;
import in.devsuman.smartcart.utils.PasswordHashingUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final PasswordHashingUtil passwordHashingUtil;
    private final SellerMapper sellerMapper;
    private final UserRepository userRepository;

    @Autowired
    public SellerServiceImpl(
            SellerRepository sellerRepository,
            PasswordHashingUtil passwordHashingUtil,
            SellerMapper sellerMapper,
            ProductRepository productRepository, UserRepository userRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.passwordHashingUtil = passwordHashingUtil;
        this.sellerMapper = sellerMapper;
        this.userRepository = userRepository;
    }

    // Seller create
    @Override
    public SellerResponseDTO createSeller(SellerRequestDTO sellerRequestDTO) {

        // Convert: SellerRequestDTO -> Seller
        Seller seller = sellerMapper.toEntity(sellerRequestDTO);

//        log.info("Seller Entity: {}", seller);

        // Hashing password before saving
        String hashedPassword = passwordHashingUtil.hashPassword(seller.getPassword());
        seller.setPassword(hashedPassword);

        // Saving Seller to Global users table
        User user = new User();
        user.setUsername(seller.getPhoneNumber());
        user.setPassword(seller.getPassword());
        user.setActive(true);
        user.setRole("ROLE_SELLER");

//        log.info("User: {}", user);

        userRepository.save(user);

        // Saving to Seller table
        Seller savedSeller = sellerRepository.save(seller);

        // Convert & Return: Seller -> SellerResponseDTO
        return sellerMapper.toDTO(savedSeller);
    }

    // Get All Sellers - (Admin)
    @Override
    public List<SellerResponseDTO> getAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();
        return sellers.stream()
                .map(sellerMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get details of a particular seller
    @Override
    public SellerResponseDTO getSellerById(Long id) {

        // Check Seller existence
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + id));

        return sellerMapper.toDTO(seller);
    }

    // Seller Details Update
    @Override
    public SellerResponseDTO updateSeller(Long id, SellerRequestDTO sellerRequestDTO) {

        // Fetch existing seller by id
        Seller existingSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found with id: " + id));

        // Update Seller Details
        if (sellerRequestDTO.getName() != null) {
            existingSeller.setName(sellerRequestDTO.getName());
        }
        if (sellerRequestDTO.getCompanyName() != null) {
            existingSeller.setCompanyName(sellerRequestDTO.getCompanyName());
        }
        if (sellerRequestDTO.getEmail() != null) {
            existingSeller.setEmail(sellerRequestDTO.getEmail());
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

        // Update PhoneNumber & Password if Needed
        // Note: First Change Password then PhoneNumber
        if (sellerRequestDTO.getPassword() != null) {
            // perform hashing
            String hashedPassword = passwordHashingUtil.hashPassword(sellerRequestDTO.getPassword());
            existingSeller.setPassword(hashedPassword);

            // Update corresponding user's password in users table
            Optional<User> optionalUser = userRepository.findByUsername(existingSeller.getPhoneNumber());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPassword(hashedPassword); // Update password
                userRepository.save(user); // Save updated user
            } else {
                throw new EntityNotFoundException("User with username " + existingSeller.getPhoneNumber() + " not found");
            }
        }
        if (sellerRequestDTO.getPhoneNumber() != null) {
            // Update corresponding user's username in users table
            // Note: First Update phoneNumber on users table then existing seller
            Optional<User> optionalUser = userRepository.findByUsername(existingSeller.getPhoneNumber());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setUsername(sellerRequestDTO.getPhoneNumber());  // Update Username
                userRepository.save(user); // Save updated user
            } else {
                throw new EntityNotFoundException("User with username " + existingSeller.getPhoneNumber() + " not found");
            }

            existingSeller.setPhoneNumber(sellerRequestDTO.getPhoneNumber());
        }

        Seller updatedSeller = sellerRepository.save(existingSeller);

        return sellerMapper.toDTO(updatedSeller);
    }

    // Delete a seller - (Admin)
    @Override
    public void deleteSeller(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + id));

        // Delete all products associated with the seller
        List<Product> products = productRepository.findBySoldById(seller);
        if(!products.isEmpty()) {
            productRepository.deleteAll(products);
        }

        // Delete Seller from Global Users table
        Optional<User> optionalUser = userRepository.findByUsername(seller.getPhoneNumber());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            userRepository.delete(user);
        } else  {
            throw new EntityNotFoundException("Seller with username " + seller.getPhoneNumber() + " not found");
        }

        sellerRepository.delete(seller);
    }

}
