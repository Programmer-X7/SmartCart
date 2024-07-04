package in.devsuman.smartcart.controller;

import in.devsuman.smartcart.dto.ProductDTO;
import in.devsuman.smartcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Todo: Restrict Normal users for product changes

    // Create Product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Get All Products for a particular Selle
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ProductDTO>> getAllProductsBySeller(@PathVariable Long sellerId) {
        List<ProductDTO> products = productService.getAllProductsBySeller(sellerId);
        return ResponseEntity.ok(products);
    }

    // Get All Products for a particular Category with Pagination
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ProductDTO>> getAllProductsByCategory(@PathVariable Long categoryId,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Page<ProductDTO> products = productService.getAllProductsByCategory(categoryId, page, size);
        return ResponseEntity.ok(products);
    }

    // Get Single Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Update Product Information - (Seller)
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete Product - (Admin / Seller)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
