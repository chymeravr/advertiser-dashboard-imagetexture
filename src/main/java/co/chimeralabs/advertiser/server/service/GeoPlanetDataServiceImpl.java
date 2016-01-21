package co.chimeralabs.advertiser.server.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.chimeralabs.advertiser.server.apiDTO.GeoPlanetLocationDTO;
import co.chimeralabs.advertiser.server.apiDTO.LocationSolrDTO;
import co.chimeralabs.advertiser.server.model.LocationData;
import co.chimeralabs.advertiser.server.model.LocationData.LocationType;
import co.chimeralabs.advertiser.server.repository.LocationDataRepository;

@Service
public class GeoPlanetDataServiceImpl implements LocationDataService{
	@Autowired
	LocationDataRepository locationDataRepository;
	
	@Override
	public void downloadContinents() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://where.yahooapis.com/v1/continents?format=json&appid=dj0yJmk9eGM5aVM4RGdjNTlJJmQ9WVdrOWNVcElZbTh5TkdrbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wOA--";
		List<GeoPlanetLocationDTO> continents = null;
		String response="";
		try{
			response = restTemplate.getForObject(url, String.class);
		}
		catch(RestClientException e){
		}
		response = response.substring(response.indexOf("[") + 1);
		response = response.substring(0, response.indexOf("]"));
		
		//response = "{\"places\":[" + response + "]}";
		response = "[" + response + "]";
		response = response.replace("\"placeTypeName attrs\":{\"code\":29},", "");
		response = response.replace(",\"lang\":\"en-US\"", "");
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			continents = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, GeoPlanetLocationDTO.class));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (GeoPlanetLocationDTO continent : continents) {
			LocationData locationData = new LocationData(continent.getWoeid().longValue(), continent.getName(), LocationData.LocationType.CONTINENT, null);
			locationDataRepository.save(locationData);
		}
	}

	@Override
	public void downloadCountries() {
		RestTemplate restTemplate = new RestTemplate();
		List<LocationData> continents = locationDataRepository.getLocationDatasByLocationType(LocationData.LocationType.CONTINENT);
		for (LocationData continent : continents) {
			String url = "http://where.yahooapis.com/v1/countries/"+continent.getPlaceId()+"?format=json&appid=dj0yJmk9eGM5aVM4RGdjNTlJJmQ9WVdrOWNVcElZbTh5TkdrbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wOA--";
			List<GeoPlanetLocationDTO> countryDTOs = null;
			String response="";
			try{
				response = restTemplate.getForObject(url, String.class);
			}
			catch(RestClientException e){
			}
			response = response.substring(response.indexOf("[") + 1);
			response = response.substring(0, response.indexOf("]"));
			
			//response = "{\"places\":[" + response + "]}";
			response = "[" + response + "]";
			response = response.replace("\"placeTypeName attrs\":{\"code\":12},", "");
			response = response.replace(",\"lang\":\"en-US\"", "");
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				countryDTOs = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, GeoPlanetLocationDTO.class));
				for (GeoPlanetLocationDTO countryDTO : countryDTOs) {
					LocationData country = new LocationData(countryDTO.getWoeid().longValue(), countryDTO.getName(), LocationData.LocationType.COUNTRY, continent);
					locationDataRepository.save(country);
				}
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void downloadAdmin1() {
		RestTemplate restTemplate = new RestTemplate();
		List<LocationData> countries = locationDataRepository.getLocationDatasByLocationType(LocationData.LocationType.COUNTRY);
		for (LocationData country : countries) {
			String url = "http://where.yahooapis.com/v1/admin1s/"+country.getPlaceId()+"?format=json&appid=dj0yJmk9eGM5aVM4RGdjNTlJJmQ9WVdrOWNVcElZbTh5TkdrbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wOA--";
			List<GeoPlanetLocationDTO> admin1DTOs = null;
			String response="";
			try{
				response = restTemplate.getForObject(url, String.class);
			}
			catch(RestClientException e){
			}
			try{
				response = response.substring(response.indexOf("[") + 1);
				response = response.substring(0, response.indexOf("]"));
				response = "[" + response + "]";
				response = response.replace("\"placeTypeName attrs\":{\"code\":8},", "");
				response = response.replace(",\"lang\":\"en-US\"", "");
			}
			catch(IndexOutOfBoundsException e){
				//country.setName(null);
				//locationDataRepository.save(country);
				continue;
			}
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				admin1DTOs = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, GeoPlanetLocationDTO.class));
				for (GeoPlanetLocationDTO admin1DTO : admin1DTOs) {
					LocationData admin1 = new LocationData(admin1DTO.getWoeid().longValue(), admin1DTO.getName(), LocationData.LocationType.ADMIN1, country);
					locationDataRepository.save(admin1);
				}
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void downloadAdmin2() {
		RestTemplate restTemplate = new RestTemplate();
		List<LocationData> admin1s = locationDataRepository.getLocationDatasByLocationType(LocationData.LocationType.ADMIN1);
		for (LocationData admin1 : admin1s) {
			String url = "http://where.yahooapis.com/v1/admin2s/"+admin1.getPlaceId()+"?format=json&appid=dj0yJmk9eGM5aVM4RGdjNTlJJmQ9WVdrOWNVcElZbTh5TkdrbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wOA--";
			List<GeoPlanetLocationDTO> admin2DTOs = null;
			String response="";
			try{
				response = restTemplate.getForObject(url, String.class);
			}
			catch(RestClientException e){
			}
			
			try{
				response = response.substring(response.indexOf("[") + 1);
				response = response.substring(0, response.indexOf("]"));
				
				//response = "{\"places\":[" + response + "]}";
				response = "[" + response + "]";
				response = response.replace("\"placeTypeName attrs\":{\"code\":9},", "");
				response = response.replace(",\"lang\":\"en-US\"", "");
			}
			catch(IndexOutOfBoundsException e){
				//admin1.setName(null);
				//locationDataRepository.save(admin1);
				continue;
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				admin2DTOs = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, GeoPlanetLocationDTO.class));
				for (GeoPlanetLocationDTO admin2DTO : admin2DTOs) {
					LocationData admin2 = new LocationData(admin2DTO.getWoeid().longValue(), admin2DTO.getName(), LocationData.LocationType.ADMIN2, admin1);
					locationDataRepository.save(admin2);
				}
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	@Transactional
	public List<LocationData> getAllLocationWithAncestors() {
		List<LocationData> continents = locationDataRepository.getLocationDatasByLocationType(LocationData.LocationType.CONTINENT);
		for (LocationData continent : continents) {
			continent.getChildren().size();
			List<LocationData> countries = continent.getChildren();
			for (LocationData country : countries) {
				country.getChildren().size();
				List<LocationData> admin1s = country.getChildren();
				for (LocationData admin1 : admin1s) {
					admin1.getChildren().size();
					//List<LocationData> admin2s = admin1.getChildren();
				}
			}
		}
		return continents;
	}
	
}
