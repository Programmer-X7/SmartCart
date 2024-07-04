package in.devsuman.smartcart.controller;

import in.devsuman.smartcart.dto.SellerRequestDTO;
import in.devsuman.smartcart.dto.SellerResponseDTO;
import in.devsuman.smartcart.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    // Endpoints
    // Create Seller
    @PostMapping
    public ResponseEntity<SellerResponseDTO> createSeller(@RequestBody SellerRequestDTO sellerRequestDTO) {
        SellerResponseDTO createdSeller = sellerService.createSeller(sellerRequestDTO);
        return new ResponseEntity<>(createdSeller, HttpStatus.CREATED);
    }

    // Endpoint to get All Sellers - (Admin)
    @GetMapping
    public ResponseEntity<List<SellerResponseDTO>> getAllSellers() {
        List<SellerResponseDTO> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers);
    }

    // Endpoint to get details of a Seller by ID
    @GetMapping("/{id}")
    public ResponseEntity<SellerResponseDTO> getSellerById(@PathVariable Long id) {
        SellerResponseDTO seller = sellerService.getSellerById(id);
        return ResponseEntity.ok(seller);
    }

    // Endpoint to update details of a Seller by ID
    @PutMapping("/{id}")
    public ResponseEntity<SellerResponseDTO> updateSeller(@PathVariable Long id, @RequestBody SellerRequestDTO sellerRequestDTO) {
        SellerResponseDTO updatedSeller = sellerService.updateSeller(id, sellerRequestDTO);
        return ResponseEntity.ok(updatedSeller);
    }

    // Todo: Implement something so that Seller can request for Account Deletion to Admin, Not directly delete
    // Endpoint to Delete a Seller - (Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
