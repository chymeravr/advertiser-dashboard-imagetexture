package co.chimeralabs.advertiser.server.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class LocationData implements Serializable{
	
	public enum LocationType{CONTINENT, COUNTRY, ADMIN1, ADMIN2};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7823767483001650959L;

	@Id
	@Column(name="place_id")
	private Long placeId;
	private String name;
	private LocationType locationType;
	
	@ManyToOne
	@JoinColumn(name="parent_place_id")
	private LocationData parentPlace;
	
	@OneToMany(mappedBy="parentPlace", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@Fetch(value=FetchMode.SUBSELECT)
	private List<LocationData> children;

	public LocationData(){
		
	}
	
	public LocationData(Long placeId, String name, LocationType locationType, LocationData parentPlace){
		this.placeId = placeId;
		this.name = name;
		this.locationType = locationType;
		this.parentPlace = parentPlace;
	}
	
	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public LocationData getParentPlace() {
		return parentPlace;
	}

	public void setParentPlace(LocationData parentPlace) {
		this.parentPlace = parentPlace;
	}

	public List<LocationData> getChildren() {
		return children;
	}

	public void setChildren(List<LocationData> children) {
		this.children = children;
	}
	
	
}
