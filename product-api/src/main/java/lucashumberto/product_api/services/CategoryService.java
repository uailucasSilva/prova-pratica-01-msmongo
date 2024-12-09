package lucashumberto.product_api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lucashumberto.product_api.models.Category;
import lucashumberto.product_api.models.dto.CategoryDTO;
import lucashumberto.product_api.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::convert)
                .collect(Collectors.toList());
    }

    public Page<CategoryDTO> getAllCategoriesPage(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryDTO::convert);
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = Category.fromDTO(categoryDTO);
        return CategoryDTO.convert(categoryRepository.save(category));
    }

    public CategoryDTO updateCategory(String id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setNome(categoryDTO.getNome());
            return CategoryDTO.convert(categoryRepository.save(category));
        } else {
            throw new RuntimeException("Categoria não encontrada.");
        }
    }

    public void deleteCategory(String id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoria não encontrada.");
        }
    }
}