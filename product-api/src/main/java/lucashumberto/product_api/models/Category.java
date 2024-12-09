package lucashumberto.product_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucashumberto.product_api.models.dto.CategoryDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
public class Category {

    @Id
    private String id;

    @NotBlank(message = "O nome da categoria não pode ser vazio.")
    private String nome;

    public static Category fromDTO(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setNome(categoryDTO.getNome());
        return category;
    }
}
