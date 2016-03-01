package co.chimeralabs.advertiser.server.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.chimeralabs.advertiser.server.model.Ad;

@Repository
@Transactional
public interface AdRepository extends JpaRepository<Ad, Long>{
	@Query("select a "
			+ "from Ad a where a.adGroup.adGroupId=?1")
	List<Ad> getAds(Long adGroupID);
	
}
