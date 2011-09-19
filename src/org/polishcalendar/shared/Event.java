package org.polishcalendar.shared;

import java.io.Serializable;
import java.util.Date;

import net.sf.gilead.pojo.gwt.LightEntity;

public class Event extends LightEntity implements Serializable{

	private static final long serialVersionUID = 7529922952278235792L;
	
	// Calendar widget data
	private String name;
	private Date startDate;
	private Date endDate;
	private Long index;
	
	// Description data
	//TODO one-to-many with organizations
	private String organizedBy;
	private String location;
	
	//TODO preference data
	
	//TODO many-to-many with attending users
	
	
	public Event() {}
	
	public Event(String name, String organized_by, Date date, Long index) {
		setName(name);
		setOrganizedBy(organized_by);
		setStartDate(date);
		setIndex(index);
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganizedBy() {
		return organizedBy;
	}
	public void setOrganizedBy(String organizedBy) {
		this.organizedBy = organizedBy;
	}
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	
	// for debugging
	public String toString() {
		return "Name: " + name + "\n" +
			   "Organized by: " + organizedBy + "\n" +
			   "Date: " + startDate + "\n" +
			   "Index: " + index + "\n"; 
	}
}
