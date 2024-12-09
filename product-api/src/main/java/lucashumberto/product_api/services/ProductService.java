package lucashumberto.product_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lucashumberto.product_api.models.Category;
import lucashumberto.product_api.models.Product;
import lucashumberto.product_api.models.dto.ProductDTO;
import lucashumberto.product_api.repositories.CategoryRepository;
import lucashumberto.product_api.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Retorna todos os produtos
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public Page<ProductDTO> getAllProductsPage(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDTO::convert);
    }

    public List<ProductDTO> getProductsByCategory(String categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            return productRepository.findAll().stream()
                    .filter(product -> product.getCategoria().getId().equals(categoryId))
                    .map(ProductDTO::convert)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Categoria não encontrada.");
        }
    }

    public ProductDTO getProductByIdentifier(String productIdentifier) {
        return productRepository.findAll().stream()
                .filter(product -> product.getProductIdentifier().equals(productIdentifier))
                .findFirst()
                .map(ProductDTO::convert)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());

        if (optionalCategory.isPresent()) {
            productDTO.setProductIdentifier(UUID.randomUUID().toString());

            Product product = Product.fromDTO(productDTO, optionalCategory.get());
            return ProductDTO.convert(productRepository.save(product));
        } else {
            throw new RuntimeException("Categoria não encontrada.");
        }
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());

        if (optionalProduct.isPresent() && optionalCategory.isPresent()) {
            Product product = optionalProduct.get();
            product.setNome(productDTO.getNome());
            product.setDescricao(productDTO.getDescricao());
            product.setPreco(productDTO.getPreco());
            product.setCategoria(optionalCategory.get());
            return ProductDTO.convert(productRepository.save(product));
        } else {
            throw new RuntimeException("Produto ou Categoria não encontrados.");
        }
    }

    public void deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Produto não encontrado.");
        }
    }
}