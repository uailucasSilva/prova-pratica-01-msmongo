package lucashumberto.shopping_api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucashumberto.shopping_api.models.dto.ShoppingDTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "shopping")
public class Shopping {

    @Id
    private String id;

    @NotBlank(message = "O identificador do usuário é obrigatório.")
    private String userIdentifier;

    @NotNull(message = "A data da compra deve ser informada.")
    private LocalDateTime date;

    @NotNull(message = "A lista de itens não pode ser vazia.")
    private List<Item> itens;

    @NotNull(message = "O total da compra deve ser informado.")
    @Positive(message = "O total da compra deve ser um valor positivo.")
    private Double total;

    public static Shopping fromDTO(ShoppingDTO shoppingDTO) {
        List<Item> itens = shoppingDTO.getItens().stream()
                .map(Item::fromDTO)
                .collect(Collectors.toList());
        return new Shopping(
                shoppingDTO.getId(),
                shoppingDTO.getUserIdentifier(),
                shoppingDTO.getDate(),
                itens,
                shoppingDTO.getTotal());
    }
}