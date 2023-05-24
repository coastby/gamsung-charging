package com.hoonimom.realreal.controller;

import com.hoonimom.realreal.api.KpiApi;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {
	final private KpiApi kpiApi;

	@GetMapping(value = "/api/performs")
	public String getPerform() {
		String result;
		try {
			result = kpiApi.getPerform();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
