package lucashumberto.product_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucashumberto.product_api.models.dto.ProductDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @NotBlank(message = "O identificador do produto não pode ser vazio.")
    private String productIdentifier;

    @NotBlank(message = "O nome do produto não pode ser vazio.")
    private String nome;

    private String descricao;

    @NotNull(message = "O preço do produto deve ser informado.")
    @Positive(message = "O preço do produto deve ser um valor positivo.")
    private Double preco;

    @NotNull(message = "A categoria do produto deve ser informada.")
    @DBRef
    private Category categoria;

    public static Product fromDTO(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setProductIdentifier(productDTO.getProductIdentifier());
        product.setNome(productDTO.getNome());
        product.setDescricao(productDTO.getDescricao());
        product.setPreco(productDTO.getPreco());
        product.setCategoria(category);
        return product;
    }
}