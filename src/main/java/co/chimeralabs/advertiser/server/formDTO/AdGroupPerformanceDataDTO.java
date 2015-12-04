package co.chimeralabs.advertiser.server.formDTO;

import java.util.ArrayList;
import java.util.List;

import co.chimeralabs.advertiser.server.model.AdGroup;

public class AdGroupPerformanceDataDTO {
	private String adGroupName;
	private Long agid;
	private String campaignName;
	private Long cid;
	private Double bid;
	private Double interaction;
	private Double interactionRate;
	private Double avgCost;
	private Double cost;
	
	public AdGroupPerformanceDataDTO(){
		
	}
	
	public AdGroupPerformanceDataDTO(AdGroup adGroup){
		this.adGroupName = adGroup.getName();
		this.agid = adGroup.getAdGroupId();
		this.campaignName = adGroup.getCampaign().getName();
		this.cid = adGroup.getCampaign().getCampaignId();
		this.bid = adGroup.getBid();
	}
	
	public static List<AdGroupPerformanceDataDTO> getListOfDTOs(List<AdGroup> adGroups){
		if(adGroups == null) return null;
		List<AdGroupPerformanceDataDTO> dtos = new ArrayList<AdGroupPerformanceDataDTO>();
		for (AdGroup adGroup : adGroups) {
			AdGroupPerformanceDataDTO dto = new AdGroupPerformanceDataDTO(adGroup);
			dto.setInteraction(0.0);
			dto.setInteractionRate(0.0);
			dto.setAvgCost(0.0);
			dto.setCost(0.0);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public String getAdGroupName() {
		return adGroupName;
	}
	public void setAdGroupName(String adGroupName) {
		this.adGroupName = adGroupName;
	}
	public Long getAgid() {
		return agid;
	}
	public void setAgid(Long agid) {
		this.agid = agid;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Double getBid() {
		return bid;
	}
	public void setBid(Double bid) {
		this.bid = bid;
	}
	public Double getInteraction() {
		return interaction;
	}
	public void setInteraction(Double interaction) {
		this.interaction = interaction;
	}
	public Double getInteractionRate() {
		return interactionRate;
	}
	public void setInteractionRate(Double interactionRate) {
		this.interactionRate = interactionRate;
	}
	public Double getAvgCost() {
		return avgCost;
	}
	public void setAvgCost(Double avgCost) {
		this.avgCost = avgCost;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
}
