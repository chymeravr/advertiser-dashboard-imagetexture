package co.chimeralabs.advertiser.server.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.chimeralabs.advertiser.server.model.UserLogin;

@Repository
@Transactional
public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{
  public UserLogin findByUserId(String id);
}