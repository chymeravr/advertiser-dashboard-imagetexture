package co.chimeralabs.advertiser.server.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Advertiser implements Serializable{
	private static final long serialVersionUID = -5498033954327208834L;
	
	@Id
	@GeneratedValue
	private Long advertiserId;
	
	@OneToOne(mappedBy="advertiser")
	UserLogin userLogin;
	
	@OneToMany(mappedBy="advertiser", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@Fetch(value=FetchMode.SUBSELECT)
	private List<Campaign> campaigns;
	
	public Long getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(Long advertiserId) {
		this.advertiserId = advertiserId;
	}

	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}
	
	
}
