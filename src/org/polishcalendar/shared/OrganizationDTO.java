package org.polishcalendar.shared;

import java.io.Serializable;

public class OrganizationDTO implements Serializable {

	private static final long serialVersionUID = -4285725148216861746L;
	
	private int id;
	private String name;
	private int followersNumber;
	private String description;
	
	/*
	private List<EventDTO> events;
	private List<UserDTO> followers;
	private OrganizationMetadataDTO metadata;
	*/
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFollowersNumber() {
		return followersNumber;
	}
	public void setFollowersNumber(int followersNumber) {
		this.followersNumber = followersNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*
	public OrganizationMetadataDTO getMetadata() {
		return metadata;
	}
	public void setMetadata(OrganizationMetadataDTO metadata) {
		this.metadata = metadata;
	}
	public List<EventDTO> getEvents() {
		return events;
	}
	public void setEvents(List<EventDTO> events) {
		this.events = events;
	}
	public List<UserDTO> getFollowers() {
		return followers;
	}
	public void setFollowers(List<UserDTO> followers) {
		this.followers = followers;
	}
	*/
	public String toString() {
		return ("OrganizationDTO " + 
				" id: " + id +
				" name: " + name + 
				" followersNumber: " + followersNumber +
				" description: " + description);
	}
}
