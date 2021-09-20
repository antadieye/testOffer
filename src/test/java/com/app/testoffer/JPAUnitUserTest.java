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

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAUnitUserTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UserRepository repository;
	
	LocalDate localDate = LocalDate.of(2000, 10, 05);


	@Test
	public void should_store_a_user() {
		UserEntity utilisateur = new UserEntity(0, "Anta DIEYE", localDate, "French", "+221777911383", "Feminine", "anta.dieye@atos.net");
		repository.save(utilisateur);

		assertThat(utilisateur).hasFieldOrPropertyWithValue("name", "Anta DIEYE");
		assertThat(utilisateur).hasFieldOrPropertyWithValue("countryResidence", "French");
		assertThat(utilisateur).hasFieldOrPropertyWithValue("gender", "Feminine");
	}

	@Test
	public void should_find_user_by_id() {
		UserEntity user1 = new UserEntity(0, "Anta DIEYE", localDate, "French", "+221777911383", "Feminine", "anta.dieye@atos.net");
		entityManager.persist(user1);

		UserEntity user2 = new UserEntity(0, "Khalifa NDIAYE", localDate, "French", "+3 6 26 02 45 81", "Male", "khalifa.ndiaye@atos.net");
		entityManager.persist(user2);

		UserEntity foundUser = repository.findById(user2.getId()).get();

		assertThat(foundUser).isEqualTo(user2);
	}

}