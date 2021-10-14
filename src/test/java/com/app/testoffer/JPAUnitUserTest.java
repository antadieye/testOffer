package com.app.testoffer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.app.testoffer.exception.ResourceException;
import com.app.testoffer.model.UserEntity;
import com.app.testoffer.service.UserServiceInterface;


@WebAppConfiguration
@SpringBootTest
public class JPAUnitUserTest {
	 
	@Test
	void contextLoads() {
	}

	private MockMvc mockMVC;
	
	@Autowired
	UserServiceInterface userServiceI;
	 
	/**
	 * Inject the instance of WebApplicationContext.
	 */
	@Autowired
	private WebApplicationContext webApplicationContext;

	/**
	 * Initialize and build our mockMVC.
	 */
	
	@Before
	public void setup(){
		mockMVC= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	 
	/**
	 *  Start a POST-type Rest request for the url '/ data'
	 * @throws ResourceException
	 */
	@Test
	public void createUserMockMVCTest() throws ResourceException {
		
		String payload = "{\r\n"
				+ "\"name\":\"Anta DIEYE\",\r\n"
				+ "\"birthDate\":\"24-08-1994\",\r\n"
				+ "\"countryResidence\":\"France\",\r\n"
				+ "\"phoneNumber\":\"+3 7 80 91 61 40\",\r\n"
				+ "\"gender\":\"Feminine\",\r\n"
				+ "\"email\":\"anta.dieye@atos.net\"\r\n"
				+ "}";
		try {
			
			mockMVC.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(payload))
			.andExpect(status().isOk())
			.andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Start a GET-type Rest request for the url '/ data'
	 * @throws ResourceException
	 */
@Test
public void fetcUserMockMVCTest() throws ResourceException {
	try {
		mockMVC.perform(get("/api/users/{id}", 1L)).andExpect(status().isOk()).andReturn();
	} catch (Exception e) {
		e.printStackTrace();
	}

}

/**
 * unit test for the service layer
 */
LocalDate localDate = LocalDate.of(2000, 10, 05);
@Test
public void createUserByServiceTest() throws ResourceException {
	UserEntity user = new UserEntity(0, "Jean LOUIS", localDate, "France", "+3 6 26 02 45 81", "Male", "jeanlouis@gmail.com");
	userServiceI.createUser(user);
	assertThat(user).hasFieldOrPropertyWithValue("name", "Jean LOUIS");
	assertThat(user).hasFieldOrPropertyWithValue("countryResidence", "France");
	assertThat(user).hasFieldOrPropertyWithValue("gender", "Male");
	assertThat(user).hasFieldOrPropertyWithValue("email", "jeanlouis@gmail.com");
}
  

}