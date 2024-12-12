package MojaChata.pl.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CottageService {
    @Autowired
    private CottageRepository cottageRepository;

    public List<Cottage> searchCottage(SearchDTO search) {
        return cottageRepository.searchCottage(
                // search.getAddress(),
                search.getMinPrice(),
                search.getMaxPrice(),
                search.getMinSize(),
                search.getMaxSize(),
                search.getOwnerId()

        );
    }
}
