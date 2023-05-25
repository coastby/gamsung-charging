package com.hoonimom.realreal.api;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = "test")
class ApiServiceTest {
	@Autowired
	private ApiService apiService;

	@Test
	@DisplayName("XML -> Show instance")
	void parsing() {
		try {
			apiService.getPerform();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}