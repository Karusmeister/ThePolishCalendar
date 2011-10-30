package org.polishcalendar.shared;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -5524445671708778042L;
	
	private int id;
	private String password;
	private String email;
	private Date joinedDate;
	private String type;
	
	/*
	private List <EventDTO> attendingEvents;
	private List <OrganizationDTO> followedOrganizations;
	private UserMetadataDTO metadata;
	*/

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	public List<OrganizationDTO> getFollowedOrganizations() {
		return followedOrganizations;
	}

	public void setFollowedOrganizations(List<OrganizationDTO> followedOrganizations) {
		this.followedOrganizations = followedOrganizations;
	}

	public UserMetadataDTO getMetadata() {
		return metadata;
	}

	public void setMetadata(UserMetadataDTO metadata) {
		this.metadata = metadata;
	}

	public void setAttendingEvents(List <EventDTO> attendingEvents) {
		this.attendingEvents = attendingEvents;
	}

	public List <EventDTO> getAttendingEvents() {
		return attendingEvents;
	}
	*/
	public String toString() {
		return (" UserDTO: " + 
			    " id: " + id +
			    " email: " + email + 
			    " joinedDate: " + joinedDate + 
			    " type: " + type);
	}
}
