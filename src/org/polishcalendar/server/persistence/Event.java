package org.polishcalendar.server.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


public class Event implements Serializable{

	private static final long serialVersionUID = 7529922952278235792L;
	
	// Calendar widget data
	private Integer id;
	private String name;
	private String location;
	private Integer attendeesNumber;
	private Date startDate;
	private Date creationDate;
	private Set<User> attendees;
	private Organization organization;
	
	//TODO many-to-many with attending users
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getAttendeesNumber() {
		return attendeesNumber;
	}

	public void setAttendeesNumber(Integer attendeesNumber) {
		this.attendeesNumber = attendeesNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	public Set<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(Set<User> attendees) {
		this.attendees = attendees;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Event() {}
	
	
	// for debugging
	public String toString() {
		return "Name: " + name + "\n" +
			   "Organized by: " + organization + "\n" +
			   "Date: " + startDate + "\n" +
			   "Index: " + id + "\n"; 
	}
}
