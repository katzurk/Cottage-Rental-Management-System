package MojaChata.pl.app.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CottageRepository extends CrudRepository<Cottage, Long> {
    @Query("SELECT a FROM Cottage a WHERE (:address IS NULL OR a.address LIKE %:address%)")
    List<Cottage> searchCottage(@Param("address") String address);
}
