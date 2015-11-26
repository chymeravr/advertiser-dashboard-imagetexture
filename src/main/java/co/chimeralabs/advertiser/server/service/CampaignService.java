package co.chimeralabs.advertiser.server.service;

import java.util.List;

import co.chimeralabs.advertiser.server.model.Campaign;

public interface CampaignService {
	public Campaign saveCampaign(Campaign campaign);
	public Campaign saveCampaign(Campaign campaign, Long advertiserId);
	public Campaign getCampaign(Long campaignId);
	public List<Campaign> getCampaigns(Long advertiserId);
	public List<Campaign> getCampaignTree(Long advertiserId);
}
