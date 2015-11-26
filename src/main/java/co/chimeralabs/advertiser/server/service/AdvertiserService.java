package co.chimeralabs.advertiser.server.service;

import co.chimeralabs.advertiser.server.model.Advertiser;

public interface AdvertiserService {
	public Advertiser getAdvertiser(Long advertiserId);
	public Advertiser saveAdvertiser(Advertiser advertiser);
}
