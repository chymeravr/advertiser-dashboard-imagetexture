package co.chimeralabs.advertiser.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.chimeralabs.advertiser.server.apiDTO.AdResourceData;

@RestController
public class PublisherAdAPIController {
	private static final Logger logger = LoggerFactory.getLogger(PublisherAdAPIController.class);
	
	@RequestMapping(value={"/publisher/api/loadad", "/"}, method=RequestMethod.GET, headers="Accept=application/json")
	public AdResourceData getAdMetadata(){
		logger.debug("Inside advertiserserver.publisheradapicontroller.getadmetadata");
		String resourceURL = "http://www.chimeralabs.co";
		String resourceMetadata = "jpeg";
		String resourceErrorCode = "0";
		AdResourceData adResourceData = new AdResourceData(resourceURL, resourceMetadata, resourceErrorCode);
		return adResourceData;
	}
	
}
