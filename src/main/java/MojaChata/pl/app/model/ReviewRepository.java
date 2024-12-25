package MojaChata.pl.app.model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Request> findByCottageId(Long cottageId);
    boolean existsByCottageId(Long cottageId);

}
