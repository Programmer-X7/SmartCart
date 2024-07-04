package in.devsuman.smartcart.service.impl;

import in.devsuman.smartcart.dto.ProductDTO;
import in.devsuman.smartcart.entity.Category;
import in.devsuman.smartcart.entity.Product;
import in.devsuman.smartcart.entity.Seller;
import in.devsuman.smartcart.mapper.ProductMapper;
import in.devsuman.smartcart.repository.CategoryRepository;
import in.devsuman.smartcart.repository.ProductRepository;
import in.devsuman.smartcart.repository.SellerRepository;
import in.devsuman.smartcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              SellerRepository sellerRepository,
                              CategoryRepository categoryRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Fetching seller
        Seller seller = sellerRepository.findById(productDTO.getSoldById())
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + productDTO.getSoldById()));

        // Fetching category
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + productDTO.getCategoryId()));

        // Convert: ProductDTO -> Product
        Product product = productMapper.toEntity(productDTO, seller, category);

        // Saving Product
        Product savedProduct = productRepository.save(product);

        // Convert & Return: Product -> ProductDTO
        return productMapper.toDTO(savedProduct);
    }

    // Todo: Get All products (All) with some limitation of-course

    @Override
    public List<ProductDTO> getAllProductsBySeller(Long sellerId) {
        // Fetch Seller
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + sellerId));

        return productRepository.findBySoldById(seller).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> getAllProductsByCategory(Long categoryId, int page, int size) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findByCategoryId(category, pageable)
                .map(productMapper::toDTO);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        if (productDTO.getName() != null) {
            existingProduct.setName(productDTO.getName());
        }
        if (productDTO.getDescription() != null) {
            existingProduct.setDescription(productDTO.getDescription());
        }
        if (productDTO.getPrice() != 0) {
            existingProduct.setPrice(productDTO.getPrice());
        }
        if (productDTO.getQuantity() != 0) {
            existingProduct.setQuantity(productDTO.getQuantity());
        }

        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + productDTO.getCategoryId()));

            existingProduct.setCategoryId(category);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
