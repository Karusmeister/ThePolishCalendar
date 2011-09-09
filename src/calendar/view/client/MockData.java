package calendar.view.client;

import java.util.Date;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class MockData {

	private static OrganizationRecord[] org_records = null;
	private static EventRecord[] event_records = null;
	
	public static OrganizationRecord[] getOrganizations() {
		if (org_records == null) {
			OrganizationRecord org1 = new OrganizationRecord("Arka Gdynia" , true);
			OrganizationRecord org2 = new OrganizationRecord("Browar Lomza" , true);
			OrganizationRecord org3 = new OrganizationRecord("Imperial Polsoc" , false);
			org_records = new OrganizationRecord[3];
			org_records[0] = org1;
			org_records[1] = org2;
			org_records[2] = org3;
		}
		return org_records;
	}
	
	public static ListGridRecord[] getEvents() {
		if (event_records == null) {
			Date common_date = new Date();
			
			EventRecord e1 
				= new EventRecord("Meeting with president" , "London Polish Club" , common_date);
			EventRecord e2
				= new EventRecord("Cooking with Napieralski" , "Polish City Club" , common_date);
			EventRecord e3
				= new EventRecord("Polish Vodka Party" , "ICL Polsoc" , common_date);
			EventRecord e4
				= new EventRecord("Polish Race Street" , "Race with Polish" , common_date);
			
			event_records = new EventRecord[4];
			event_records[0] = e1;
			event_records[1] = e2;
			event_records[2] = e3;
			event_records[3] = e4;
		}
		return event_records;
	}
	
	
	/* Record classes does not have any state themselfs; they user setters and getters
	 *  of their ancestors to manage the state. */
	static class OrganizationRecord extends ListGridRecord {
		
		public OrganizationRecord (String name, boolean follow) {
			setName(name);
			setFollow(follow);
		}
		
		public String getName() {
			return getAttributeAsString("name"); 
		}
		public void setName(String name) {
			setAttribute("name" , name);
		}
		public boolean getFollow() {
			return getAttributeAsBoolean("follow");
		}
		public void setFollow(boolean follow) {
			setAttribute("follow" , follow);
		}
	}
	
	static class EventRecord extends ListGridRecord {
		
		public EventRecord(String name, String organized_by, Date date) {
			setName(name);
			setOrganizedBy(organized_by);
			setDate(date);
		}
		
		public String getName() {
			return getAttributeAsString("name"); 
		}
		public void setName(String name) {
			setAttribute("name" , name);
		}
		public String getOrganizedBy() {
			return getAttributeAsString("oranized_by");
		}
		public void setOrganizedBy(String organizedBy) {
			setAttribute("organized_by" , organizedBy);
		}
		public Date getDate() {
			return this.getAttributeAsDate("date");
		}
		public void setDate(Date date) {
			setAttribute("date" , date);
		}
	}
}
