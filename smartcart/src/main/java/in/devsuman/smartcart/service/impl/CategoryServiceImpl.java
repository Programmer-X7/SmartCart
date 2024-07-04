package in.devsuman.smartcart.service.impl;

import in.devsuman.smartcart.dto.CategoryDTO;
import in.devsuman.smartcart.entity.Category;
import in.devsuman.smartcart.entity.Product;
import in.devsuman.smartcart.mapper.CategoryMapper;
import in.devsuman.smartcart.repository.CategoryRepository;
import in.devsuman.smartcart.repository.ProductRepository;
import in.devsuman.smartcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryMapper = categoryMapper;
    }

    // Only for Admin
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);

        // Checks if Category Name is Unique or not
        Optional<Category> existingCategory = categoryRepository.findByName(category.getName());
        if(existingCategory.isPresent()) {
            throw new RuntimeException("Category with name '" + category.getName() + "' already exists.");
        }

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    // Used for showing Categorise at frontend
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Only for Admin
    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.toDTO(category);
    }

    // Only for Admin
    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        if (categoryDTO.getName() != null) {
            existingCategory.setName(categoryDTO.getName());
        }

        if (categoryDTO.getDescription() != null) {
            existingCategory.setDescription(categoryDTO.getDescription());
        }

        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toDTO(updatedCategory);
    }

    // Only for Admin
    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        List<Product> products = productRepository.findByCategoryId(category);
        if (!products.isEmpty()) {
            for (Product product : products) {
                productRepository.delete(product);
            }
        }

        categoryRepository.delete(category);
    }
}
