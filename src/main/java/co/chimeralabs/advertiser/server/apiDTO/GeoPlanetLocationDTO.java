package co.chimeralabs.advertiser.server.apiDTO;

public class GeoPlanetLocationDTO{
	private Integer woeid;
	private String placeTypeName;
	private String name;
	private String uri;
	public Integer getWoeid() {
		return woeid;
	}
	public void setWoeid(Integer woeid) {
		this.woeid = woeid;
	}
	public String getPlaceTypeName() {
		return placeTypeName;
	}
	public void setPlaceTypeName(String placeTypeName) {
		this.placeTypeName = placeTypeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
}