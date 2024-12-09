package lucashumberto.shopping_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lucashumberto.shopping_api.models.Shopping;
import lucashumberto.shopping_api.models.dto.ShoppingDTO;
import lucashumberto.shopping_api.repositories.ShoppingRepository;

@Service
@RequiredArgsConstructor
public class ShoppingService {

    private final ShoppingRepository shoppingRepository;

    public List<ShoppingDTO> getAllShops() {
        return shoppingRepository.findAll().stream()
                .map(ShoppingDTO::fromModel)
                .collect(Collectors.toList());
    }

    public ShoppingDTO getShopById(String id) {
        Shopping shopping = shoppingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada."));
        return ShoppingDTO.fromModel(shopping);
    }

    public ShoppingDTO saveShop(ShoppingDTO shoppingDTO) {
        Shopping shopping = ShoppingDTO.toModel(shoppingDTO);
        shopping.setDate(LocalDateTime.now());
        Shopping savedShopping = shoppingRepository.save(shopping);
        return ShoppingDTO.fromModel(savedShopping);
    }

    public List<ShoppingDTO> getShopsByUser(String userIdentifier) {
        return shoppingRepository.findByUserIdentifier(userIdentifier).stream()
                .map(ShoppingDTO::fromModel)
                .collect(Collectors.toList());
    }

    public List<ShoppingDTO> getShopsByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return shoppingRepository.findByDateBetween(startDate, endDate).stream()
                .map(ShoppingDTO::fromModel)
                .collect(Collectors.toList());
    }

    public List<ShoppingDTO> getShopsByProductIdentifier(String productIdentifier) {
        return shoppingRepository.findByItensProductIdentifier(productIdentifier).stream()
                .map(ShoppingDTO::fromModel)
                .collect(Collectors.toList());
    }

    public List<ShoppingDTO> getShopsByFilter(LocalDateTime startDate, LocalDateTime endDate, Double minValue) {
        return shoppingRepository.getShopsByFilter(startDate, endDate, minValue).stream()
                .map(ShoppingDTO::fromModel)
                .collect(Collectors.toList());
    }

    public List<ShoppingDTO> getReportByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return shoppingRepository.getReportByDate(startDate, endDate).stream()
                .map(ShoppingDTO::fromModel)
                .collect(Collectors.toList());
    }
}