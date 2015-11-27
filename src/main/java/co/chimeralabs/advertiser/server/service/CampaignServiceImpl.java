package co.chimeralabs.advertiser.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.chimeralabs.advertiser.server.model.AdGroup;
import co.chimeralabs.advertiser.server.model.Advertiser;
import co.chimeralabs.advertiser.server.model.Campaign;
import co.chimeralabs.advertiser.server.repository.CampaignRepository;

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService{
	private static final Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);
	
	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	AdvertiserService advertiserService;
	
	@Override
	@Transactional
	public Campaign saveCampaign(Campaign campaign) {
		// TODO Auto-generated method stub
		return campaignRepository.save(campaign);
	}

	@Override
	@Transactional
	public List<Campaign> getCampaigns(Long advertiserId) {
		return campaignRepository.getCampaignsByAdvertiserId(advertiserId);
	}

	@Override
	@Transactional
	public List<Campaign> getCampaignTree(Long advertiserId) {
		//List<Campaign> campaigns = campaignRepository.getCampaignsByAdvertiserIdWithAdGroupEagerLoad(advertiserId);
		List<Campaign> campaigns = campaignRepository.getCampaignsByAdvertiserId(advertiserId);
		for (Campaign campaign : campaigns) {
			//Hibernate.initialize(campaign.getAdGroups());
			campaign.getAdGroups().size();
		}
		return campaigns;
	}

	@Override
	@Transactional
	public Campaign saveCampaign(Campaign campaign, Long advertiserId) {
		Advertiser advertiser = advertiserService.getAdvertiser(advertiserId);
		campaign.setAdvertiser(advertiser);
		return campaignRepository.save(campaign);
	}

	@Override
	public Campaign getCampaign(Long campaignId) {
		return campaignRepository.getOne(campaignId);
	}
	
}
