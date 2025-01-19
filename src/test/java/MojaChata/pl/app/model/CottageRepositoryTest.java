package MojaChata.pl.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class CottageRepositoryTest{

	@Autowired
	private CottageRepository cottageRepository;

	@Test
	public void canFindCottagesForUserNo1 (){
		assertEquals(4, cottageRepository.findByOwnerId(1).size());
	}

    @Test
	public void canFindCottagesForUserNo2 (){
		assertEquals(3, cottageRepository.findByOwnerId(2).size());
	}

    @Test
	public void canSearchForCottageByCity (){
		assertEquals(2, cottageRepository.searchCottage(null, "Warsaw", null, null, null, null, null, null).size());
	}

    @Test
	public void canSearchForCottageByCountryAndMinPrice (){
		assertEquals(3, cottageRepository.searchCottage("Poland", null, null, 20, null, null, null, null).size());
	}

    @Test
	public void canSearchForCottageByMinSizeAndMaxSize (){
		assertEquals(3, cottageRepository.searchCottage(null, null, null, null, null, 35, 70, null).size());
	}

    @Test
	public void canSearchForCottageByAdressAndCityAndMaxPrice (){
		assertEquals(1, cottageRepository.searchCottage(null, "Warsaw", "Mini 5", null, 129, null, null, null).size());
	}
}