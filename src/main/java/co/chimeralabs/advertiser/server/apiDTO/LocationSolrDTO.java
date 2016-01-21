package co.chimeralabs.advertiser.server.apiDTO;

import co.chimeralabs.advertiser.server.model.LocationData.LocationType;;

public class LocationSolrDTO {
	private Long locationid;
	private LocationType locationtype;
	private Long continent;
	private Long country;
	private Long admin1;
	private String name;
	private String fullname;
	
	public LocationSolrDTO(){
		
	}
	public LocationSolrDTO(Long locationId, LocationType locationType, Long continent, Long country, Long admin1, String name, String fullname){
		this.locationid = locationId;
		this.locationtype = locationType;
		this.continent = continent;
		this.country = country;
		this.admin1 = admin1;
		this.name = name;
		this.fullname = fullname;
	}
	public Long getLocationid() {
		return locationid;
	}
	public void setLocationid(Long locationid) {
		this.locationid = locationid;
	}
	public LocationType getLocationtype() {
		return locationtype;
	}
	public void setLocationtype(LocationType locationtype) {
		this.locationtype = locationtype;
	}
	public Long getContinent() {
		return continent;
	}
	public void setContinent(Long continent) {
		this.continent = continent;
	}
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	public Long getAdmin1() {
		return admin1;
	}
	public void setAdmin1(Long admin1) {
		this.admin1 = admin1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
}
