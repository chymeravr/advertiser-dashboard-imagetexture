package co.chimeralabs.advertiser.server.service;

import org.springframework.web.multipart.MultipartFile;

import co.chimeralabs.advertiser.server.model.TextureImageFormat;

public interface TextureImageService {
	public Boolean loadFile(MultipartFile imageFile, String saverId);
	public String saveImage();
	public Boolean checkImageFormat(MultipartFile imageFile, TextureImageFormat imageFormat);
	public String getImagePath(String resourceIdentifier);
	public String getImageFormat();
}
