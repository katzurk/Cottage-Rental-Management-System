package MojaChata.pl.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest{
	BCryptPasswordEncoder bc = new BCryptPasswordEncoder(); 

	@Autowired
	private UserRepository userRepository;

	@Test
	public void canFindById (){
		assertEquals("Jan", userRepository.findById(1).get().getUsername());
	}

	@Test
	public void canFindByUsername (){
		assertEquals(1, userRepository.findByUsername("Jan").get().getId());
	}

	@Test 
	public void canInsertUser (){
		User user = new User("Andrzej", bc.encode("Andrzejewski"));
		userRepository.save(user);
		Optional<User> us = userRepository.findByUsername("Andrzej");
		System.out.println(us.get().getUsername());

		assertEquals("Andrzej", userRepository.findById(101).get().getUsername());
		assertEquals(101, userRepository.findByUsername("Andrzej").get().getId());
		assertTrue(bc.matches("Andrzejewski", userRepository.findByUsername("Andrzej").get().getPasswordHash()));
		userRepository.delete(user);
	}

	@Test
	public void isNo1UserPassCorrect (){
		assertTrue(bc.matches("Kowalski", userRepository.findByUsername("Jan").get().getPasswordHash()));
	}

	@Test
	public void isNo2UserPassCorrect (){
		assertEquals("1", userRepository.findByUsername("w").get().getPasswordHash());
	}
}