package co.chimeralabs.advertiser.server.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Campaign {
	private static final long serialVersionUID = -5498033954327208834L;
	
	@Id
	@GeneratedValue
	private Long campaignId;
	
	private String name;
	private Double dailyBudget;
	private AdType adType;
	private Timestamp startDate;
	private Timestamp endDate;
	
	
	@ManyToOne
	@JoinColumn(name="advertiser_id")
	private Advertiser advertiser;
	
	@OneToMany(mappedBy="campaign", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@Fetch(value=FetchMode.SUBSELECT)
	private List<AdGroup> adGroups;
	
	public Long getCampaignId() {
		return campaignId;
	}

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AdGroup> getAdGroups() {
		return adGroups;
	}

	public void setAdGroups(List<AdGroup> adGroups) {
		this.adGroups = adGroups;
	}

	public Double getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(Double dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public AdType getAdType() {
		return adType;
	}

	public void setAdType(AdType adType) {
		this.adType = adType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = new Timestamp(startDate.getTime());
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = new Timestamp(endDate.getTime());
	}
}
