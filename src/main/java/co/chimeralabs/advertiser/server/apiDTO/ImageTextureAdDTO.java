package co.chimeralabs.advertiser.server.apiDTO;

import co.chimeralabs.advertiser.server.model.Ad;

public class ImageTextureAdDTO {
	private String adResourceIdentifier;
	private String adResourceFormat;
	private String errorMsg = "";
	
	public ImageTextureAdDTO(){

	}

	public ImageTextureAdDTO(Ad ad){
		this.adResourceIdentifier = ad.getAdResourceIdentifier();
		this.adResourceFormat = ad.getAdResourceFormat();
	}

	public String getAdResourceIdentifier() {
		return adResourceIdentifier;
	}

	public void setAdResourceIdentifier(String adResourceIdentifier) {
		this.adResourceIdentifier = adResourceIdentifier;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getAdResourceFormat() {
		return adResourceFormat;
	}

	public void setAdResourceFormat(String adResourceFormat) {
		this.adResourceFormat = adResourceFormat;
	}
	
}
