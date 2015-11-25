package co.chimeralabs.advertiser.server.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.chimeralabs.advertiser.server.model.AdGroup;

@Repository
@Transactional
public interface AdGroupRepository extends JpaRepository<AdGroup, Long> {

}
