package MojaChata.pl.app.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestApprovalsRepository extends CrudRepository<RequestApproval, Long> {
    boolean existsByRequestIdAndIsApproved(Long requestId, boolean isApproved);
    RequestApproval findByRequestId(Long requestId);
    List<RequestApproval> findByRequest_CustomerId(Long customerId);
    List<RequestApproval> findByIsApprovedTrueAndRequest_CustomerId(Long customerId);
    List<RequestApproval> findByIsApprovedFalseAndRequest_CustomerId(Long customerId);
}
