package co.chimeralabs.advertiser.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class UserLogin implements Serializable{
	private static final long serialVersionUID = -5498033954327208834L;

	@Id
	private String userId;
	private String passwordHash;
	private Boolean isEnabled;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="user_login_security_group",
			joinColumns=@JoinColumn(name="user_login_id"),
			inverseJoinColumns=@JoinColumn(name="security_group_id")
			)
	private Set<SecurityGroup> securityGroups = new java.util.HashSet<SecurityGroup>();

	@OneToOne
	@JoinColumn(name="user_login")
	private Advertiser advertiser;

	@CreatedDate
	private Date createdAt;
	@LastModifiedDate
	private Date updatedAt;

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<SecurityGroup> getSecurityGroups() {
		return securityGroups;
	}
	public void setSecurityGroups(Set<SecurityGroup> securityGroups) {
		this.securityGroups = securityGroups;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void addSecurityGroup(SecurityGroup securityGroup) {
		this.securityGroups.add(securityGroup);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}
	
}
