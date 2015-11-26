package co.chimeralabs.advertiser.server.service;

import java.util.List;

import co.chimeralabs.advertiser.server.model.AdGroup;

public interface AdGroupService {
	AdGroup saveAdGroup(AdGroup adGroup);
	AdGroup saveAdGroup(AdGroup adGroup, Long campaignId);
	AdGroup getAdGroup(Long adGroupId);
	List<AdGroup> getAdGroups(Long campaignId);
}
