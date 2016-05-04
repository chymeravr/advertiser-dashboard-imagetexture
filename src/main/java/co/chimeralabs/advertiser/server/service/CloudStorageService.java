package co.chimeralabs.advertiser.server.service;

public interface CloudStorageService {
	public String getImageTextureAdImageUrl(String resourceIdentifier);
	public void storeImageTextureAdImage(byte[] fileBuffer, String filename);
}
