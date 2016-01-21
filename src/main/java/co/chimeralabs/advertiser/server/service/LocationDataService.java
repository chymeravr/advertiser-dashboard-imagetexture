package co.chimeralabs.advertiser.server.service;

import java.util.List;

import co.chimeralabs.advertiser.server.model.LocationData;

public interface LocationDataService {
	public void downloadContinents();
	public void downloadCountries();
	public void downloadAdmin1();
	public void downloadAdmin2();
	public List<LocationData> getAllLocationWithAncestors();
}
