package org.polishcalendar.shared;

import java.io.Serializable;
import java.util.Date;

import net.sf.gilead.pojo.gwt.LightEntity;

public class Event extends LightEntity implements Serializable{

	private static final long serialVersionUID = 7529922952278235792L;
	
	private String name;
	private String organizedBy;
	private Date date;
	private int index;
	
	public Event() {}
	
	public Event(String name, String organized_by, Date date, int index) {
		setName(name);
		setOrganizedBy(organized_by);
		setDate(date);
		setIndex(index);
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	// for debugging
	public String toString() {
		return "Name: " + name + "\n" +
			   "Organized by: " + organizedBy + "\n" +
			   "Date: " + date + "\n" +
			   "Index: " + index + "\n"; 
	}
}
