
package lucashumberto.product_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lucashumberto.product_api.models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}