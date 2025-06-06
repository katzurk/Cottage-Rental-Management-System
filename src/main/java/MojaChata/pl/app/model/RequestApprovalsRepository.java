package MojaChata.pl.app.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestApprovalsRepository extends CrudRepository<RequestApproval, Long> {
    boolean existsByRequestId(Long requestId);
    RequestApproval findByRequestId(Long requestId);
}
