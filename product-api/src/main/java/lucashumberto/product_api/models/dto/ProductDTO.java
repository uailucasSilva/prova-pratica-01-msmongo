package lucashumberto.product_api.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucashumberto.product_api.models.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String id;

    @NotBlank(message = "O identificador do produto é obrigatório")
    private String productIdentifier;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "O preço do produto deve ser informado")
    @Positive(message = "O preço do produto deve ser positivo")
    private Double preco;

    @NotBlank(message = "O ID da categoria é obrigatório")
    private String categoryId;

    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setNome(product.getNome());
        productDTO.setDescricao(product.getDescricao());
        productDTO.setPreco(product.getPreco());
        productDTO.setCategoryId(product.getCategoria().getId());
        return productDTO;
    }
}
