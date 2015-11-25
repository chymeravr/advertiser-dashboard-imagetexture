package co.chimeralabs.advertiser.server.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.chimeralabs.advertiser.server.model.Advertisement;

@Repository
@Transactional
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{

}
