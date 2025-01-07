package MojaChata.pl.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestApprovalsRepository requestApprovalsRepository;
    @Autowired
    private CottageService cottageService;

    @Transactional
    public void sendRequest(Request request) {
        BigDecimal totalPrice = cottageService.calculateTotalCost(request.getCheckInDate(), request.getCheckOutDate(), request.getCottage());

        request.setTotalPrice(totalPrice.floatValue());
        requestRepository.save(request);
    }

    public boolean existsByCottageIdAndNoApproval(long cottageId) {
        List<Request> requests = requestRepository.findByCottageId(cottageId);

        boolean exists = requests.stream()
                .anyMatch(request -> !requestApprovalsRepository.existsByRequestId(request.getId()));
        return exists;
    }

    public boolean existsByCustomerIdAndCottageIdAndNoApproval(long customerId, long cottageId) {
        List<Request> requests = requestRepository.findByCustomerIdAndCottageId(customerId, cottageId);

        boolean exists = requests.stream()
                .anyMatch(request -> !requestApprovalsRepository.existsByRequestId(request.getId()));
        return exists;
    }

    public boolean existsByRequestIdAndIsApproved(long requestId, boolean isApproved) {
        RequestApproval approval = requestApprovalsRepository.findByRequestId(requestId);
        if (approval == null) {
            return false;
        }
        return approval.isApproved() == isApproved;
    }

    public int getStatus(long requestId) {
        if (existsByRequestIdAndIsApproved(requestId, true)) {
            return 1;
        }
        else if (existsByRequestIdAndIsApproved(requestId, false)) {
            return 2;
        }
        else {
            return 0;
        }
    }
}