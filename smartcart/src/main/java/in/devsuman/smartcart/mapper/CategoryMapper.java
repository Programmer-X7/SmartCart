package in.devsuman.smartcart.mapper;

import in.devsuman.smartcart.dto.CategoryDTO;
import in.devsuman.smartcart.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryDTO categoryDTO) {
        return new Category(
                categoryDTO.getId(),
                categoryDTO.getName(),
                categoryDTO.getDescription(),
                null
        );
    }

    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}