package MojaChata.pl.app.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CottageRepository extends CrudRepository<Cottage, Long> {
    @Query("SELECT c FROM cottages c WHERE" +
            "(:minPrice IS NULL OR c.minPricePerDay >= :minPrice)" +
            "AND (:maxPrice IS NULL OR c.minPricePerDay <= :maxPrice)" +
            "AND (:minSize IS NULL OR c.size_m2 >= :minSize)" +
            "AND (:maxSize IS NULL OR c.size_m2 <= :maxSize)" +
            "AND (:ownerId IS NULL OR c.ownerId != :ownerId)"
            )
    List<Cottage> searchCottage(
        //     @Param("address") String address,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("minSize") Integer minSize,
            @Param("maxSize") Integer maxSize,
            @Param("ownerId") Long ownerId);
    List<Cottage> findByOwnerId(long ownerId);
}
