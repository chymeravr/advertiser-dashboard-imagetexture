package co.chimeralabs.advertiser.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.chimeralabs.advertiser.server.model.AdGroup;
import co.chimeralabs.advertiser.server.model.Campaign;
import co.chimeralabs.advertiser.server.repository.AdGroupRepository;

@Service
public class AdGroupServiceImpl implements AdGroupService{
	@Autowired
	AdGroupRepository adGroupRepository;
	
	@Autowired
	CampaignService campaignService;
	
	@Override
	public AdGroup saveAdGroup(AdGroup adGroup) {
		return adGroupRepository.save(adGroup);
	}

	@Override
	@Transactional
	public AdGroup saveAdGroup(AdGroup adGroup, Long campaignId) {
		Campaign campaign = campaignService.getCampaign(campaignId);
		adGroup.setCampaign(campaign);
		return adGroupRepository.save(adGroup);
	}

	@Override
	@Transactional
	public AdGroup getAdGroup(Long adGroupId) {
		return adGroupRepository.getOne(adGroupId);
	}

	@Override
	@Transactional
	public List<AdGroup> getAdGroups(Long campaignId) {
		return adGroupRepository.getAdGroupsByCampaignId(campaignId);
	}

	@Override
	@Transactional
	public List<AdGroup> getAdGroupsTree(Long campaignId) {
		List<AdGroup> adGroups = adGroupRepository.getAdGroupsByCampaignId(campaignId);
		for (AdGroup adGroup : adGroups) {
			adGroup.getAds().size();
		}
		if(adGroups != null && adGroups.size()>0)
			adGroups.get(0).getCampaign();
		return adGroups;
	}

}
