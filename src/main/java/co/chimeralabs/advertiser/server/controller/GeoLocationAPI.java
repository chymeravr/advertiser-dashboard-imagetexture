package co.chimeralabs.advertiser.server.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.chimeralabs.advertiser.server.apiDTO.LocationSolrDTO;
import co.chimeralabs.advertiser.server.model.LocationData;
import co.chimeralabs.advertiser.server.service.LocationDataService;

@RestController
public class GeoLocationAPI {
	@Autowired
	LocationDataService locationDataService;
	
	@RequestMapping(value={"/api/downloadlocationdatafromyahoo/continent"}, method=RequestMethod.GET, headers="Accept=application/json")
	public String DownloadLocationDataFromYahooContinent(){
		locationDataService.downloadContinents();
		return "success";
	}
	@RequestMapping(value={"/api/downloadlocationdatafromyahoo/country"}, method=RequestMethod.GET, headers="Accept=application/json")
	public String DownloadLocationDataFromYahooCountry(){
		locationDataService.downloadCountries();
		return "success";
	}
	@RequestMapping(value={"/api/downloadlocationdatafromyahoo/admin1"}, method=RequestMethod.GET, headers="Accept=application/json")
	public String DownloadLocationDataFromYahooAdmin1(){
		locationDataService.downloadAdmin1();
		return "success";
	}
	@RequestMapping(value={"/api/downloadlocationdatafromyahoo/admin2"}, method=RequestMethod.GET, headers="Accept=application/json")
	public String DownloadLocationDataFromYahooAdmin2(){
		locationDataService.downloadAdmin2();
		return "success";
	}
	@RequestMapping(value={"/api/downloadlocationdatafromyahoo/admin1and2"}, method=RequestMethod.GET, headers="Accept=application/json")
	public String DownloadLocationDataFromYahooAdmin1And2(){
		locationDataService.downloadAdmin1();
		locationDataService.downloadAdmin2();
		return "success";
	}
	@RequestMapping(value={"/api/convertdatafromdatabasetosolrjson"}, method=RequestMethod.GET, headers="Accept=application/json")
	public String ConvertDataFromDatabaseToSolrJson(){
		List<LocationData> continents = locationDataService.getAllLocationWithAncestors();
		List<LocationSolrDTO> dtos = new ArrayList<LocationSolrDTO>();
		for (LocationData continent : continents) {
			List<LocationData> countries = continent.getChildren();
			for (LocationData country : countries) {
				dtos.add(new LocationSolrDTO(country.getPlaceId(), country.getLocationType(), continent.getPlaceId(), null, null, country.getName(), country.getName()));
				List<LocationData> admin1s = country.getChildren();
				for (LocationData admin1 : admin1s) {
					dtos.add(new LocationSolrDTO(admin1.getPlaceId(), admin1.getLocationType(), continent.getPlaceId(), country.getPlaceId(), null, admin1.getName(), country.getName()+"/"+admin1.getName()));
					List<LocationData> admin2s = admin1.getChildren();
					for (LocationData admin2 : admin2s) {
						dtos.add(new LocationSolrDTO(admin2.getPlaceId(), admin2.getLocationType(), continent.getPlaceId(), country.getPlaceId(), admin1.getPlaceId(), admin2.getName(), country.getName()+"/"+admin1.getName()+"/"+admin2.getName()));
						
					}
				}
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		String rootDir = System.getProperty("catalina.home");
	    File file = new File(rootDir+File.separator+"webapps/advertiser/data"+File.separator+"locationsolrdata.json");
		try {
			objectMapper.writeValue(file, dtos);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
}
