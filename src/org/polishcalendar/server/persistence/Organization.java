package org.polishcalendar.server.persistence;

import java.util.Date;
import java.util.Set;

public class Organization {

	private int id;
	private Date creationDate;
	private String name;
	private Integer followersNumber;
	private String description;
	private Set<Event> events;
	private Set<User> followingUsers;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFollowersNumber() {
		return followersNumber;
	}
	public void setFollowersNumber(Integer followersNumber) {
		this.followersNumber = followersNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	public Set<Event> getEvents() {
		return events;
	}
	public void setFollowingUsers(Set<User> followingUsers) {
		this.followingUsers = followingUsers;
	}
	public Set<User> getFollowingUsers() {
		return followingUsers;
	}
	
	
}
