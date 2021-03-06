package co.chimeralabs.advertiser.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.chimeralabs.advertiser.server.model.Ad;
import co.chimeralabs.advertiser.server.model.AdGroup;
import co.chimeralabs.advertiser.server.model.TextureImageFormat;
import co.chimeralabs.advertiser.server.repository.AdRepository;

@Service
public class AdServiceImpl implements AdService{
	@Autowired
	AdRepository adRepository;
	
	@Autowired
	AdGroupService adGroupService;
	
	@Autowired
	TextureImageService textureImageService;
	
	@Override
	@Transactional
	public Ad saveAd(Ad ad, Long adGroupId) {
		AdGroup adGroup = adGroupService.getAdGroup(adGroupId);
		ad.setAdGroup(adGroup);
		return adRepository.save(ad);
	}

	@Override
	public Ad getAd(Long adId) {
		return adRepository.findOne(adId);
	}

	@Override
	@Transactional
	public Ad saveImageTextureAd(Ad ad, MultipartFile imageFile, Long adGroupId) {
		AdGroup adGroup = adGroupService.getAdGroup(adGroupId);
		ad.setAdGroup(adGroup);
		ad = adRepository.save(ad);
		String resourceIdentifier = textureImageService.saveImage(imageFile, ad.getAdId().toString());
		if(resourceIdentifier != null){
			ad.setAdResourceIdentifier(resourceIdentifier);
		}
		return adRepository.save(ad);
	}

	@Override
	public String getAdUrl(Ad ad) {
		return textureImageService.getImageUrl(ad.getAdResourceIdentifier());
	}

	@Override
	public List<Ad> getAds() {
		return adRepository.findAll();
	}

	@Override
	public List<Ad> getAds(Long adGroupId) {
		return adRepository.getAds(adGroupId);
	}

	@Override
	@Transactional
	public Integer deleteAds(List<Long> ids) {
		for (Long id : ids) {
			adRepository.delete(id);
		}
		return null;
	}

}
