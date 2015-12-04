package co.chimeralabs.advertiser.server.formDTO;

import java.util.ArrayList;
import java.util.List;

import co.chimeralabs.advertiser.server.model.Campaign;

public class CampaignPerformanceDataDTO {
	private Double budget;
	private Long cid;
	private String campaignName;
	private Double interaction;
	private Double interactionRate;
	private Double avgCost;
	private Double cost;
	
	public CampaignPerformanceDataDTO(){
		
	}
	
	public CampaignPerformanceDataDTO(Campaign campaign){
		this.budget = campaign.getDailyBudget();
		this.cid = campaign.getCampaignId();
		this.campaignName = campaign.getName();
	}
	
	public static List<CampaignPerformanceDataDTO> getListOfDTOs(List<Campaign> campaigns){
		if(campaigns == null) return null;
		List<CampaignPerformanceDataDTO> dtos = new ArrayList<CampaignPerformanceDataDTO>(campaigns.size());
		for (Campaign campaign : campaigns) {
			CampaignPerformanceDataDTO dto = new CampaignPerformanceDataDTO(campaign);
			dto.setInteraction(0.0);
			dto.setInteractionRate(0.0);
			dto.setAvgCost(0.0);
			dto.setCost(0.0);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
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
