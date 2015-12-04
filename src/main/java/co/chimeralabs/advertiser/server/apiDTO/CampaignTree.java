package co.chimeralabs.advertiser.server.apiDTO;

import java.util.List;

import co.chimeralabs.advertiser.server.model.Campaign;

public class CampaignTree {
	List<Campaign> campaigns;

	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	
}
