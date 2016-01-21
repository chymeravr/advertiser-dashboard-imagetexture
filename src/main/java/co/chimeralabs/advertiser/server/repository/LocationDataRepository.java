package co.chimeralabs.advertiser.server.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.chimeralabs.advertiser.server.model.LocationData;

@Transactional
@Repository
public interface LocationDataRepository extends JpaRepository<LocationData, Long>{

	@Query("select c "
			+ "from LocationData c where c.locationType=?1 ")
	public List<LocationData> getLocationDatasByLocationType(LocationData.LocationType locationType);
}
