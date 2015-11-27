package co.chimeralabs.advertiser.server.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.chimeralabs.advertiser.server.model.TextureImageFormat;
import co.chimeralabs.advertiser.server.util.RetrieveResources;
import co.chimeralabs.advertiser.server.util.TypeConversion;

@Service
public class TextureImageServiceImpl implements TextureImageService{
	private static final Logger logger = LoggerFactory.getLogger(TypeConversion.class);
	private BufferedImage image;
	private String saverId;
	private String format;
	
	/*
	 * Currently not implementing this. So, you have to verify this at client side that image is in right spirit and format
	 * (non-Javadoc)
	 * @see co.chimeralabs.advertiser.server.service.TextureImageService#checkImageFormat(org.springframework.web.multipart.MultipartFile, co.chimeralabs.advertiser.server.model.TextureImageFormat)
	 */
	@Override
	public Boolean checkImageFormat(MultipartFile imageFile, TextureImageFormat imageFormat) {
		if(imageFormat.getHeight() != this.image.getHeight()){
			return false;
		}
		if(imageFormat.getWidth() != this.image.getWidth())
			return false;
		return true;
	}

	@Override
	public String getImagePath(String resourceIdentifier) {
		return getTextureImagePathPrefix()+File.separator+resourceIdentifier;
	}

	@Override
	public Boolean loadFile(MultipartFile imageFile, String saverId) {
		this.saverId = saverId;
		// TODO Auto-generated method stub
		try {
			InputStream in = new ByteArrayInputStream(imageFile.getBytes());
			ImageInputStream iis = ImageIO.createImageInputStream(in);
			Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
			while (imageReaders.hasNext()) {
			    ImageReader reader = (ImageReader) imageReaders.next();
			    this.format = reader.getFormatName();
			}
			this.image = ImageIO.read(iis);
			return true;
		} catch (IOException e) {
			logger.debug("IO exception while loading Multipart file into Buffered Image" + e);
			return false;
		}
	}

	@Override
	public String saveImage() {
	    String resourceIdentifier = generateUniqueFileName();
	    File file = new File(getTextureImagePathPrefix()+File.separator+resourceIdentifier+"."+this.format);
	    try {
			if(ImageIO.write(this.image, this.format, file))
				return resourceIdentifier;
			else
				return null;
		} catch (IOException e) {
			logger.debug("IO exception while saving Buffered Image" + e);
			return null;
		}
	}
	
	private String getTextureImagePathPrefix(){
		InputStream inputStream = getClass().getResourceAsStream("/application/AppConstants.xml");
		String baseDir = RetrieveResources.retrieveResourcesAppConatants(inputStream, "textureimageurl").get(0);
	    String rootDir = System.getProperty("catalina.home");
	    return rootDir+File.separator+baseDir;
	}
	 private  String generateUniqueFileName(){
	        String filename="";
	        long millis=System.currentTimeMillis();
	        String rndchars=RandomStringUtils.randomAlphanumeric(16);
	        filename=saverId+"_"+rndchars+"_"+"_"+millis;
	        return filename;
	}
}
