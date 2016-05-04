package co.chimeralabs.advertiser.server.service;

import org.springframework.web.multipart.MultipartFile;

import co.chimeralabs.advertiser.server.model.TextureImageFormat;

public interface TextureImageService {
	String getImageUrl(String resourceIdentifier);
	public String saveImage(MultipartFile imageFile, String saverId);
}
