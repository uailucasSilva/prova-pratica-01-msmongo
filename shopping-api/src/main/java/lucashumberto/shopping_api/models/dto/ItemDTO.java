package lucashumberto.shopping_api.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucashumberto.shopping_api.models.Item;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    @NotBlank(message = "O identificador do produto é obrigatório.")
    private String productIdentifier;

    @NotNull(message = "O preço do item deve ser informado.")
    @Positive(message = "O preço do item deve ser um valor positivo.")
    private Double price;

    public static ItemDTO fromModel(Item item) {
        return new ItemDTO(item.getProductIdentifier(), item.getPrice());
    }

    public static Item toModel(ItemDTO itemDTO) {
        return new Item(itemDTO.getProductIdentifier(), itemDTO.getPrice());
    }

}
