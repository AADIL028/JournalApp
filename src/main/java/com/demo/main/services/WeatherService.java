package com.demo.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.main.response.WeatherResponse;

@Service
public class WeatherService {
	@Value("${Weather.app.api}")
	private String apiKey;
	private static final String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=APIKEY";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public WeatherResponse getWeather(String city) {
		String finalUrl = apiUrl.replace("CITY",city).replace("APIKEY", apiKey);
		ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalUrl, HttpMethod.GET,null,WeatherResponse.class);
		WeatherResponse body = responseEntity.getBody();
		return body;
	}
}
