package co.chimeralabs.advertiser.server.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.chimeralabs.advertiser.server.model.Campaign;

@Repository
@Transactional
public interface CampaignRepository extends JpaRepository<Campaign, Long>{
	@Query("select c "
			+ "from Campaign c join c.advertiser a "
			+ "where a.advertiserId=?1")
	public List<Campaign> getCampaignsByAdvertiserId(Long advertiserId);
	
	@Query("select c "
			+ "from Campaign c join c.advertiser a join fetch c.adGroups "
			+ "where a.advertiserId=?1")
	public List<Campaign> getCampaignsByAdvertiserIdWithAdGroupEagerLoad(Long advertiserId);
}
