package co.chimeralabs.advertiser.server.apiDTO;

public class AdResourceData {
	private String resourceURL;
	private String resourceMetadata;
	private String resourceErrorCode;
	
	public AdResourceData(){
	}
	
	public AdResourceData(String resourceURL, String resourceMetadata, String resourceErrorCode){
		this.resourceURL = resourceURL;
		this.resourceMetadata = resourceMetadata;;
		this.resourceErrorCode = resourceErrorCode;
	}
	
	public String getResourceURL() {
		return resourceURL;
	}
	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}
	public String getResourceMetadata() {
		return resourceMetadata;
	}
	public void setResourceMetadata(String resourceMetadata) {
		this.resourceMetadata = resourceMetadata;
	}
	public String getResourceErrorCode() {
		return resourceErrorCode;
	}
	public void setResourceErrorCode(String resourceErrorCode) {
		this.resourceErrorCode = resourceErrorCode;
	}
}
