package lucashumberto.shopping_api.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import lucashumberto.shopping_api.models.Shopping;

@Repository
public interface ShoppingRepository extends MongoRepository<Shopping, String> {

    List<Shopping> findByUserIdentifier(String userIdentifier);

    List<Shopping> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Shopping> findByItensProductIdentifier(String productIdentifier);

    @Query("{ 'date': { $gte: ?0, $lte: ?1 }, 'total': { $gte: ?2 } }")
    List<Shopping> getShopsByFilter(LocalDateTime startDate, LocalDateTime endDate, Double minValue);

    @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
    List<Shopping> getReportByDate(LocalDateTime startDate, LocalDateTime endDate);
}