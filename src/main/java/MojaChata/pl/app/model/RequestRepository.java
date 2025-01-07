package MojaChata.pl.app.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findByCustomerId(Long customerId);
    List<Request> findByCustomerIdAndCottageId(Long customerId, Long cottageId);
    List<Request> findByCottageId(Long cottageId);
    boolean existsByCustomerIdAndCottageId(Long customerId, Long cottageId);
}
