//package in.devsuman.smartcart.controller;
//
//import in.devsuman.smartcart.dto.LoginDTO;
//import in.devsuman.smartcart.service.AuthService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    // Endpoints For Sellers
//    @PostMapping("/seller/login")
//    public ResponseEntity<String> sellerLogin(@RequestBody LoginDTO loginDTO) {
//        if (authService.sellerLogin(loginDTO)) {
//            return ResponseEntity.ok("Login Successful");
//        } else {
//            return new ResponseEntity<>("Incorrect credentials", HttpStatus.UNAUTHORIZED);
//        }
//    }
//}
