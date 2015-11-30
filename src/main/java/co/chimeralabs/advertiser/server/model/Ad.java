package co.chimeralabs.advertiser.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Ad {
	private static final long serialVersionUID = -5498033954327208834L;
	
	@Id
	@GeneratedValue
	private Long adId;

	private String name;
	private AdType adType;
	private Integer resolutionWidth;
	private Integer resolutionHeight;
	private String adResourceIdentifier;
	private String adResourceFormat;
	
	@ManyToOne
	@JoinColumn(name="ad_group_id")
	private AdGroup adGroup;
	
	
	public Long getAdId(){
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public AdGroup getAdGroup() {
		return adGroup;
	}

	public void setAdGroup(AdGroup adGroup) {
		this.adGroup = adGroup;
	}

	public AdType getAdType() {
		return adType;
	}

	public void setAdType(AdType adType) {
		this.adType = adType;
	}

	public Integer getResolutionWidth() {
		return resolutionWidth;
	}

	public void setResolutionWidth(Integer resolutionWidth) {
		this.resolutionWidth = resolutionWidth;
	}

	public Integer getResolutionHeight() {
		return resolutionHeight;
	}

	public void setResolutionHeight(Integer resolutionHeight) {
		this.resolutionHeight = resolutionHeight;
	}

	public String getAdResourceIdentifier() {
		return adResourceIdentifier;
	}

	public void setAdResourceIdentifier(String adResourceIdentifier) {
		this.adResourceIdentifier = adResourceIdentifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdResourceFormat() {
		return adResourceFormat;
	}

	public void setAdResourceFormat(String adResourceFormat) {
		this.adResourceFormat = adResourceFormat;
	}
	
}
