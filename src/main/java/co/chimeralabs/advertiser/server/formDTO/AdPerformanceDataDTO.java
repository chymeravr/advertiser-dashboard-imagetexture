package co.chimeralabs.advertiser.server.formDTO;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import co.chimeralabs.advertiser.server.model.Ad;
import co.chimeralabs.advertiser.server.util.RetrieveResources;

public class AdPerformanceDataDTO {
	private Long adId;
	private String adName;
	private String adImageUrl;
	private Long agid;
	private String adGroupName;
	private Long cid;
	private String campaignName;
	private Double interaction;
	private Double interactionRate;
	private Double avgCost;
	private Double cost;
	
	public AdPerformanceDataDTO(){
		
	}
	
	public AdPerformanceDataDTO(Ad ad){
		this.adId = ad.getAdId();
		this.adName = ad.getName();
		InputStream inputStream = getClass().getResourceAsStream("/machine/MachineConstants.xml");
		String baseDir = RetrieveResources.retrieveResourcesAppConatants(inputStream, "imageresourceurl").get(0);
		String imageUrl = baseDir + File.separator + ad.getAdResourceIdentifier() + "." + ad.getAdResourceFormat();
		this.adImageUrl = imageUrl;
		this.agid = ad.getAdGroup().getAdGroupId();
		this.adGroupName = ad.getAdGroup().getName();
		this.cid = ad.getAdGroup().getCampaign().getCampaignId();
		this.campaignName = ad.getAdGroup().getCampaign().getName();
	}
	
	public static List<AdPerformanceDataDTO> getListOfDTOs(List<Ad> ads){
		List<AdPerformanceDataDTO> dtos = new ArrayList<AdPerformanceDataDTO>(ads.size());
		for (Ad ad : ads) {
			AdPerformanceDataDTO dto = new AdPerformanceDataDTO(ad);
			dto.setInteraction(0.0);
			dto.setInteractionRate(0.0);
			dto.setAvgCost(0.0);
			dto.setCost(0.0);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdImageUrl() {
		return adImageUrl;
	}
	public void setAdImageUrl(String adImageUrl) {
		this.adImageUrl = adImageUrl;
	}
	public Long getAgid() {
		return agid;
	}
	public void setAgid(Long adGroupId) {
		this.agid = adGroupId;
	}
	public String getAdGroupName() {
		return adGroupName;
	}
	public void setAdGroupName(String adGroupName) {
		this.adGroupName = adGroupName;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long campaignId) {
		this.cid = campaignId;
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
