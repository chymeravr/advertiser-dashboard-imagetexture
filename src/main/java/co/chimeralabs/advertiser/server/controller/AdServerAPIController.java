package co.chimeralabs.advertiser.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.chimeralabs.advertiser.server.apiDTO.AdResourceData;
import co.chimeralabs.advertiser.server.apiDTO.ImageTextureAdDTO;
import co.chimeralabs.advertiser.server.apiDTO.ImageTextureAdsDTO;
import co.chimeralabs.advertiser.server.model.Ad;
import co.chimeralabs.advertiser.server.service.AdService;

@RestController
public class AdServerAPIController {
	private static final Logger logger = LoggerFactory.getLogger(AdServerAPIController.class);
	
	@Autowired
	AdService adService;
	
	@RequestMapping(value={"/publisher/api/loadad"}, method=RequestMethod.GET, headers="Accept=application/json")
	public AdResourceData getAdMetadata(){
		logger.debug("Inside advertiserserver.publisheradapicontroller.getadmetadata");
		String resourceURL = "http://www.chimeralabs.co";
		String resourceMetadata = "jpeg";
		String resourceErrorCode = "0";
		AdResourceData adResourceData = new AdResourceData(resourceURL, resourceMetadata, resourceErrorCode);
		return adResourceData;
	}
	
	@RequestMapping(value={"/api/ads"}, method=RequestMethod.GET, headers="Accept=application/json")
	public ImageTextureAdsDTO getAllAds(){
		List<Ad> ads = adService.getAds();
		List<ImageTextureAdDTO> adDTOs = new ArrayList<ImageTextureAdDTO>(ads.size());
		for (Ad ad : ads) {
			adDTOs.add(new ImageTextureAdDTO(ad));
		}
		ImageTextureAdsDTO adsDTO = new ImageTextureAdsDTO();
		adsDTO.setAds(adDTOs);
		return adsDTO;
	}
}
