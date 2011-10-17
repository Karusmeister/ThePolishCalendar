package org.polishcalendar.shared;

import java.io.Serializable;
import java.util.Date;

public class EventDTO implements Serializable{

	private static final long serialVersionUID = -9134022262542142784L;
	
	private String name;
	private String location;
	private String organizationName;
	private int id;
	private int attendeesNumber;
	private Date startDate;
	private Date endDate;
	
	public EventDTO() { };
	
	/*
	private OrganizationDTO organization;
	private EventMetadataDTO metadata;
	private List<UserDTO> attendees;
	*/
	
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
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public int getAttendeesNumber() {
		return attendeesNumber;
	}
	public void setAttendeesNumber(int attendeesNumber) {
		this.attendeesNumber = attendeesNumber;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	/*
	public OrganizationDTO getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationDTO organization) {
		this.organization = organization;
	}
	public List<UserDTO> getAttendees() {
		return attendees;
	}
	public void setAttendees(List<UserDTO> attendees) {
		this.attendees = attendees;
	}
	public EventMetadataDTO getMetadata() {
		return metadata;
	}
	public void setMetadata(EventMetadataDTO metadata) {
		this.metadata = metadata;
	}
	*/
	public String toString() {
		return ("name: " + name + 
				" index: " + id + 
				" location: " + location +
				" attendeesNumber: " + attendeesNumber +
				" startDate: " + startDate +
				" organizationName: " + organizationName);
	} 
}
