package co.chimeralabs.advertiser.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.chimeralabs.advertiser.server.model.Advertiser;
import co.chimeralabs.advertiser.server.repository.AdvertiserRepository;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {
	@Autowired
	AdvertiserRepository advertiserRepository;
	
	@Override
	@Transactional
	public Advertiser getAdvertiser(Long advertiserId) {
		return advertiserRepository.findOne(advertiserId);
	}

	@Override
	public Advertiser saveAdvertiser(Advertiser advertiser) {
		return advertiserRepository.save(advertiser);
	}

}
