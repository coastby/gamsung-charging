package com.hoonimom.realreal.controller;

import com.hoonimom.realreal.api.ApiService;
import com.hoonimom.realreal.entity.Show;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {
	final private ApiService apiService;

	@GetMapping(value = "/api/shows")
	public List<Show> getPerform() {
		List<Show> result;
		try {
			result = apiService.getPerform();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	@GetMapping(value = "/api/exhibitions")
	public String getExhibition() {
		String result;
		try {
			result = apiService.getPerformAndExhibition();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@GetMapping(value = "/api/realestates")
	public String getReal() {
		String result;
		try {
			result = apiService.getRealEstate();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
