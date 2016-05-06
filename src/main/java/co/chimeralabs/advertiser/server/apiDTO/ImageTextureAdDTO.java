package co.chimeralabs.advertiser.server.apiDTO;

import co.chimeralabs.advertiser.server.model.Ad;
import co.chimeralabs.advertiser.server.service.CloudStorageService;

public class ImageTextureAdDTO {
	private String advertiserId;
	private String adId;
	private String adResourceUrl;
	private String errorMsg = "";
	
	public ImageTextureAdDTO(){

	}

	public String getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(String advertiserId) {
		this.advertiserId = advertiserId;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public ImageTextureAdDTO(Ad ad, CloudStorageService cloudStorageService){
		this.adResourceUrl = cloudStorageService.getImageTextureAdImageUrl(ad.getAdResourceIdentifier());
		this.adId = ad.getAdId().toString();
		this.advertiserId = ad.getAdGroup().getCampaign().getAdvertiser().getAdvertiserId().toString();
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getAdResourceUrl() {
		return adResourceUrl;
	}

	public void setAdResourceUrl(String adResourceUrl) {
		this.adResourceUrl = adResourceUrl;
	}
	
}
