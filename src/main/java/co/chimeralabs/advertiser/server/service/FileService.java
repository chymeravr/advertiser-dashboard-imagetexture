package co.chimeralabs.advertiser.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	Boolean saveFile(MultipartFile file);
}
