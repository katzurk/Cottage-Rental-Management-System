package MojaChata.pl.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import MojaChata.pl.app.model.CottageRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CottageRepositoryTest{

	@Autowired
	private CottageRepository cottageRepository;

	@Test
	public void canFindCottagesForUserNo1 (){
		assertEquals(4, cottageRepository.findByOwnerId(1).size());
	}
}