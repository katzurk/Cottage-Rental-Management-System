package MojaChata.pl.app.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

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

	@Test
	public void canInsertNewCottage() {
		// given
		Cottage newCottage = new Cottage("test-cottage", new Address(), 1, 2, 4, 8, BigDecimal.valueOf(7), new User("userName", "pass"));

		// when
		Cottage savedCottage = cottageRepository.save(newCottage);

		// then
		Optional<Cottage> foundCottage = cottageRepository.findById(savedCottage.getId());
		assertTrue(foundCottage.isPresent());
		assertEquals("test-cottage", foundCottage.get().getName());
	}
}