package org.polishcalendar.server.persistence;

import java.util.Date;
import java.util.Set;

public class User {

	private int id;
	private String name;
	private Date joinedDate;
	private String type;
	private Set<Event> events;
	private Set<Organization> followedOrganizations;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	public Set<Event> getEvents() {
		return events;
	}
	public void setFollowedOrganizations(Set<Organization> followedOrganizations) {
		this.followedOrganizations = followedOrganizations;
	}
	public Set<Organization> getFollowedOrganizations() {
		return followedOrganizations;
	}	
	
}
