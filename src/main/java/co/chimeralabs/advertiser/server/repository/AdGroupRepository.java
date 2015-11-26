package co.chimeralabs.advertiser.server.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.chimeralabs.advertiser.server.model.AdGroup;

@Repository
@Transactional
public interface AdGroupRepository extends JpaRepository<AdGroup, Long> {
	@Query("select ag "
			+ "from AdGroup ag where "
			+ "ag.campaign.campaignId=?1")
	public List<AdGroup> getAdGroupsByCampaignId(Long campaignId);
}
