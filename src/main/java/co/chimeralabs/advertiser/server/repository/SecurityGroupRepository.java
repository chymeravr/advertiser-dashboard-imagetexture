package co.chimeralabs.advertiser.server.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.chimeralabs.advertiser.server.model.SecurityGroup;

@Repository
@Transactional
public interface SecurityGroupRepository extends JpaRepository<SecurityGroup, Long> {
	public SecurityGroup findByName(String name);
}
