package MojaChata.pl.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class CottageService {
    @Autowired
    private CottageRepository cottageRepository;

    public List<Cottage> searchCottage(SearchDTO search) {
        return cottageRepository.searchCottage(
                search.getAddressCountry(),
                search.getAddressCity(),
                search.getAddressStreet(),
                search.getMinPrice(),
                search.getMaxPrice(),
                search.getMinSize(),
                search.getMaxSize(),
                search.getOwnerId()

        );
    }

    public BigDecimal calculateTotalCost(LocalDate checkInDate, LocalDate checkOutDate, Cottage cottage) {
        BigDecimal price = cottage.getMinPricePerDay();
        long days = Duration.between(checkInDate.atStartOfDay(), checkOutDate.atStartOfDay()).toDays();
        BigDecimal total = price.multiply(BigDecimal.valueOf(days));
        return total;
    }
}
