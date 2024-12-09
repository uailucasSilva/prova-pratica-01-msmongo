package lucashumberto.product_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lucashumberto.product_api.models.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
