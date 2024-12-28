package MojaChata.pl.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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

        RequestApproval requestApproval = new RequestApproval();
        requestApproval.setRequest(request);
        requestApproval.setDateCreated(LocalDate.now());
        requestApproval.setApproved(false);

        requestApprovalsRepository.save(requestApproval);
    }

}