package co.chimeralabs.advertiser.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TextureImageFormat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609097611702062182L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Integer width;
	private Integer height;
	private Integer fileType; //jpeg, png, any
	public TextureImageFormat(){
		
	}
	public TextureImageFormat(Integer width, Integer height){
		this.width = width;
		this.height = height;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public Long getId() {
		return id;
	}
	
}
