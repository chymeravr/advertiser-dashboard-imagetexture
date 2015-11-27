package co.chimeralabs.advertiser.server.formDTO;

import org.springframework.web.multipart.MultipartFile;

import co.chimeralabs.advertiser.server.model.Ad;

public class ImageAdUploadFormDTO {
	Ad ad;
	MultipartFile file;
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}
	
}
