package co.chimeralabs.advertiser.server.apiDTO;

import java.util.List;

public class ImageTextureAdsDTO {
	private List<ImageTextureAdDTO> ads;
	private String errorMsg="";
	public List<ImageTextureAdDTO> getAds() {
		return ads;
	}

	public void setAds(List<ImageTextureAdDTO> ads) {
		this.ads = ads;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
