package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.onoregl.bankapi.model.User;
import ru.onoregl.bankapi.config.Config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = Config.class)
@SpringBootTest(classes = {ru.onoregl.bankapi.Main.class})
public final class MainTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void createUser() throws Exception {
		var result = mvc.perform(post("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
						"\t\"firstName\": \"Tom Riddle\",\n" +
						"\t\"username\": \"admin\",\n" +
						"\t\"password\": \"admin\"\n" +
						"}".getBytes())  // Set the request body as byte array
		).andExpect(status().isCreated()).andReturn();

		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(result.getResponse().getContentAsString(), User.class);
		System.out.println("Password = " + user.getPassword());
	}
}
