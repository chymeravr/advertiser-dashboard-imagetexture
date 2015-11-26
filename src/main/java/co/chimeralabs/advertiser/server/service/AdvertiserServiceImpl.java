package co.chimeralabs.advertiser.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.chimeralabs.advertiser.server.model.Advertiser;
import co.chimeralabs.advertiser.server.repository.AdvertiserRepository;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {
	@Autowired
	AdvertiserRepository advertiserRepository;
	
	@Override
	public Advertiser getAdvertiser(Long advertiserId) {
		return advertiserRepository.getOne(advertiserId);
	}

	@Override
	public Advertiser saveAdvertiser(Advertiser advertiser) {
		return advertiserRepository.save(advertiser);
	}

}
