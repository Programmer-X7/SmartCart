//package in.devsuman.smartcart.service.impl;
//
//import in.devsuman.smartcart.dto.LoginDTO;
//import in.devsuman.smartcart.entity.Seller;
//import in.devsuman.smartcart.repository.SellerRepository;
//import in.devsuman.smartcart.service.AuthService;
//import in.devsuman.smartcart.utils.PasswordHashingUtil;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//    private final SellerRepository sellerRepository;
//    private final PasswordHashingUtil passwordHashingUtil;
//
//    public AuthServiceImpl(SellerRepository sellerRepository,
//                           PasswordHashingUtil passwordHashingUtil) {
//        this.sellerRepository = sellerRepository;
//        this.passwordHashingUtil = passwordHashingUtil;
//    }
//
//    @Override
//    public boolean sellerLogin(LoginDTO loginDTO) {
//        Seller seller = sellerRepository.findByEmail(loginDTO.getEmail());
//        return seller != null && passwordHashingUtil.verifyPassword(loginDTO.getPassword(), seller.getPassword());
//    }
//}
