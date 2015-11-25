package co.chimeralabs.advertiser.server.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserPrincipal extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8336389399274821217L;
	
	private Long advertiserId;
	public UserPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities, Long advertiserId){
		super(username, password, true, true, true, true, authorities);
		this.setAdvertiserId(advertiserId);
	}

	public Long getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(Long advertiserId) {
		this.advertiserId = advertiserId;
	}

}
