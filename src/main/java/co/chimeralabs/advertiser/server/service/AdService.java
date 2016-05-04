package co.chimeralabs.advertiser.server.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.chimeralabs.advertiser.server.model.Ad;

public interface AdService {
	public Ad saveAd(Ad ad, Long adGroupId);
	public Ad getAd(Long adId);
	public List<Ad> getAds(Long adGroupId);
	public Ad saveImageTextureAd(Ad ad, MultipartFile imageFile, Long adGroupId);
	public String getAdUrl(Ad ad);
	public List<Ad> getAds();
	public Integer deleteAds(List<Long> ids);
}
