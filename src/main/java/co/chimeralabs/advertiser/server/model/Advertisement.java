package co.chimeralabs.advertiser.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="advertisement")
public class Advertisement {
	private static final long serialVersionUID = -5498033954327208834L;
	
	@Id
	@GeneratedValue
	private Long advertisementId;

	@ManyToOne
	@JoinColumn(name="ad_group_id")
	private AdGroup adGroup;
	
	
	public Long getAdvertisementId(){
		return advertisementId;
	}

	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}

	public AdGroup getAdGroup() {
		return adGroup;
	}

	public void setAdGroup(AdGroup adGroup) {
		this.adGroup = adGroup;
	}
}
