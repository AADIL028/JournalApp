package com.demo.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.main.cache.AppCache;
import com.demo.main.constants.PlaceHolders;
import com.demo.main.response.WeatherResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherService {
	@Value("${Weather.app.api}")
	private String apiKey;
//	private static final String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=APIKEY";
	
	@Autowired
	private AppCache appCache;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RedisService redisService;
	
	public WeatherResponse getWeather(String city) {
		WeatherResponse weatherResponse = redisService.get("weather_of_"+city, WeatherResponse.class);
		
		if(weatherResponse!=null) {
			log.info("Called from redis....");
			return weatherResponse;
		}
		else {
		String finalUrl = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY,city).replace(PlaceHolders.API_KEY, apiKey);
		ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalUrl, HttpMethod.GET,null,WeatherResponse.class);
		WeatherResponse body = responseEntity.getBody();
		if(body!=null) {
			redisService.set("weather_of_"+city, body, 300l);
		}
		log.info("Called from api....");
		return body;
		}
	}
}
