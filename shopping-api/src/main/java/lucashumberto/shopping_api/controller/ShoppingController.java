package lucashumberto.shopping_api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lucashumberto.shopping_api.models.dto.ShoppingDTO;
import lucashumberto.shopping_api.services.ShoppingService;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShoppingController {

    private final ShoppingService shoppingService;

    @GetMapping
    public List<ShoppingDTO> getAllShops() {
        return shoppingService.getAllShops();
    }

    @GetMapping("/{id}")
    public ShoppingDTO getShopById(@PathVariable String id) {
        return shoppingService.getShopById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingDTO saveShop(@RequestBody ShoppingDTO shoppingDTO) {
        return shoppingService.saveShop(shoppingDTO);
    }

    @GetMapping("/shopByUser")
    public List<ShoppingDTO> getShopsByUser(@RequestParam String userIdentifier) {
        return shoppingService.getShopsByUser(userIdentifier);
    }

    @GetMapping("/shopByDate")
    public List<ShoppingDTO> getShopsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return shoppingService.getShopsByDate(startDate, endDate);
    }

    @GetMapping("/{productIdentifier}/product")
    public List<ShoppingDTO> getShopsByProductIdentifier(@PathVariable String productIdentifier) {
        return shoppingService.getShopsByProductIdentifier(productIdentifier);
    }

    @GetMapping("/search")
    public List<ShoppingDTO> getShopsByFilter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam Double minValue) {
        return shoppingService.getShopsByFilter(startDate, endDate, minValue);
    }

    @GetMapping("/report")
    public List<ShoppingDTO> getReportByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return shoppingService.getReportByDate(startDate, endDate);
    }
}