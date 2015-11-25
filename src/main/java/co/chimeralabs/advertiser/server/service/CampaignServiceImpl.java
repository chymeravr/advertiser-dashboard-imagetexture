package co.chimeralabs.advertiser.server.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.chimeralabs.advertiser.server.model.Campaign;
import co.chimeralabs.advertiser.server.repository.CampaignRepository;

@Service
public class CampaignServiceImpl implements CampaignService{
	private static final Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);
	
	@Autowired
	CampaignRepository campaignRepository;
	
	@Override
	@Transactional
	public Campaign saveNewCampaign(Campaign campaign) {
		// TODO Auto-generated method stub
		return campaignRepository.save(campaign);
	}
	
}
