package co.chimeralabs.advertiser.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.chimeralabs.advertiser.server.model.Advertiser;
import co.chimeralabs.advertiser.server.model.SecurityGroup;
import co.chimeralabs.advertiser.server.model.UserLogin;
import co.chimeralabs.advertiser.server.repository.AdvertiserRepository;
import co.chimeralabs.advertiser.server.repository.SecurityGroupRepository;
import co.chimeralabs.advertiser.server.repository.UserLoginRepository;

@Service
public class UserLoginAndRegistrationServiceImpl implements UserLoginAndRegistrationService{
	
	@Autowired
	UserLoginRepository userLoginRepository;
	
	@Autowired
	AdvertiserRepository advertiserRepository;
	
	@Autowired
	SecurityGroupRepository securityGroupRepository;
	
	@Override
	@Transactional
	public UserLogin registerUser(UserLogin userLogin) {
		Advertiser advertiser = new Advertiser();
		advertiserRepository.save(advertiser);
		userLogin.setAdvertiser(advertiser);
		SecurityGroup securityGroup = securityGroupRepository.findByName("advertiser");
		userLogin.addSecurityGroup(securityGroup);
		userLogin = userLoginRepository.save(userLogin);
		return userLogin;
	}
}
