package com.app.testoffer;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.testoffer.model.UserEntity;
import com.app.testoffer.repository.UserRepository;
import com.app.testoffer.service.UserServiceInterface;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAUnitUserTest {

	@Autowired
	private TestEntityManager entityManager;
	

	@Autowired
	UserRepository repository;
	
	@Autowired
	UserServiceInterface userServiceI;
	 
	  
	LocalDate localDate = LocalDate.of(2000, 10, 05);


	@Test
	public void should_store_a_user_repository() {
		UserEntity user = new UserEntity(0, "Anta DIEYE", localDate, "France", "+221777911383", "Feminine", "anta.dieye@atos.net");
		repository.save(user);

		assertThat(user).hasFieldOrPropertyWithValue("name", "Anta DIEYE");
		assertThat(user).hasFieldOrPropertyWithValue("countryResidence", "France");
		assertThat(user).hasFieldOrPropertyWithValue("gender", "Feminine");
	}
	
	@Test
	public void should_store_a_user_by_service() {
		UserEntity user = new UserEntity(0, "Jean LOUIS", localDate, "France", "+3 7 25 48 69 32", "Male", "jeanlouis@af.org");
		userServiceI.createUser(user);

		assertThat(user).hasFieldOrPropertyWithValue("name", "Jean LOUIS");
		assertThat(user).hasFieldOrPropertyWithValue("countryResidence", "France");
		assertThat(user).hasFieldOrPropertyWithValue("gender", "Male");
		assertThat(user).hasFieldOrPropertyWithValue("email", "jeanlouis@af.org");
	}

	@Test
	public void should_find_user_by_id() {
		UserEntity user1 = new UserEntity(0, "Anta DIEYE", localDate, "France", "+221777911383", "Feminine", "anta.dieye@atos.net");
		entityManager.persist(user1);

		UserEntity user2 = new UserEntity(0, "Khalifa NDIAYE", localDate, "France", "+3 6 26 02 45 81", "Male", "khalifa.ndiaye@atos.net");
		entityManager.persist(user2);

		UserEntity foundUser = repository.findById(user2.getId()).get();

		assertThat(foundUser).isEqualTo(user2);
	}

}