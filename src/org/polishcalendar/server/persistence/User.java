package org.polishcalendar.server.persistence;

import java.util.Date;
import java.util.Set;

import com.gwtent.reflection.client.Reflectable;

@Reflectable
public class User {

	private int id;
	private String email;
	private String password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
