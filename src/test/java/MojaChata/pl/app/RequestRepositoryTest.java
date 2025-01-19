package MojaChata.pl.app;

import static org.assertj.core.api.Assertions.assertThat;

import MojaChata.pl.app.model.Cottage;
import MojaChata.pl.app.model.CottageRepository;
import MojaChata.pl.app.model.Request;
import MojaChata.pl.app.model.RequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RequestRepositoryTest{

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CottageRepository cottageRepository;

    @Test
    @Transactional
    void testFindByCustomerId() {
        Cottage cottage = cottageRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Cottage not found"));

        Request request = new Request(cottage, 1L);
        requestRepository.save(request);

        List<Request> customerRequests = requestRepository.findByCustomerId(1L);

        assertThat(customerRequests).hasSize(1);
    }

    @Test
    @Transactional
    void testFindByCustomerIdAndCottageId() {
        Cottage cottage = cottageRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("Cottage not found"));

        Request request = new Request(cottage, 2L);
        requestRepository.save(request);

        List<Request> specificRequests = requestRepository.findByCustomerIdAndCottageId(2L, 2L);

        assertThat(specificRequests).hasSize(1);
    }

    @Test
    @Transactional
    void testFindByCottageId() {
        Cottage cottage = cottageRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Cottage not found"));

        Request request1 = new Request(cottage, 1L);
        Request request2 = new Request(cottage, 2L);
        requestRepository.save(request1);
        requestRepository.save(request2);

        List<Request> cottageRequests = requestRepository.findByCottageId(1L);

        assertThat(cottageRequests).hasSize(2);
    }

    @Test
    @Transactional
    void testExistsByCustomerIdAndCottageId() {
        Cottage cottage = cottageRepository.findById(4L)
                .orElseThrow(() -> new IllegalArgumentException("Cottage not found"));

        Request request = new Request(cottage, 2L);
        requestRepository.save(request);

        boolean exists = requestRepository.existsByCustomerIdAndCottageId(2L, 4L);
        boolean doesNotExist = requestRepository.existsByCustomerIdAndCottageId(3L, 101L);

        assertThat(exists).isTrue();
        assertThat(doesNotExist).isFalse();
    }

}