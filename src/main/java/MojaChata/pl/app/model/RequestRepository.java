package MojaChata.pl.app.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findBySubmitterId(Long submitterId);
    Request findBySubmitterIdAndCottageId(Long submitterId, Long cottageId);
    List<Request> findByCottageId(Long cottageId);
    
    boolean existsBySubmitterIdAndCottageId(Long submitterId, Long cottageId);
}
