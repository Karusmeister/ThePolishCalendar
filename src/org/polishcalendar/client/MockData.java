package org.polishcalendar.client;

import java.util.Date;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class MockData {

	private static OrganizationRecord[] org_records = null;
	private static EventRecord[] event_records = null;
	
	// preferences
	private static PreferenceRecord[] social_records = null;
	private static PreferenceRecord[] cultural_records = null;
	private static PreferenceRecord[] political_records = null;
	private static PreferenceRecord[] other_records = null;
	private static PreferenceRecord[] location_records = null;
	private static PreferenceRecord[] time_records = null;
	
	private static UserRecord valid_user = null;
	
	public static OrganizationRecord[] getOrganizations() {
		if (org_records == null) {
			OrganizationRecord org1 = new OrganizationRecord("Arka Gdynia" , true , 1);
			OrganizationRecord org2 = new OrganizationRecord("Browar Lomza" , true , 2);
			OrganizationRecord org3 = new OrganizationRecord("Imperial Polsoc" , false , 3);
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
				= new EventRecord("Meeting with president" , "London Polish Club" , common_date , 1);
			EventRecord e2
				= new EventRecord("Cooking with Napieralski" , "Polish City Club" , common_date , 2);
			EventRecord e3
				= new EventRecord("Polish Vodka Party" , "ICL Polsoc" , common_date , 3);
			EventRecord e4
				= new EventRecord("Polish Race Street" , "Race with Polish" , common_date , 4);
			
			event_records = new EventRecord[4];
			event_records[0] = e1;
			event_records[1] = e2;
			event_records[2] = e3;
			event_records[3] = e4;
		}
		return event_records;
	}
	
	public static PreferenceRecord[] getSocialPreferences() {
		if (social_records == null) {
			PreferenceRecord r1 = new PreferenceRecord("Pubbing" , false , 1);
			PreferenceRecord r2 = new PreferenceRecord("Clubbing" , true , 2);
			PreferenceRecord r3 = new PreferenceRecord("Outdoor Events", false , 3);
			PreferenceRecord r4 = new PreferenceRecord("Drinks" , true , 4);
			PreferenceRecord r5 = new PreferenceRecord("Other Organized Evenets" , true , 5);

			
			social_records = new PreferenceRecord[5];
			social_records[0] = r1;
			social_records[1] = r2;
			social_records[2] = r3;
			social_records[3] = r4;
			social_records[4] = r5;
		}
		
		return social_records;
	}
	
	public static PreferenceRecord[] getCulturalPreferences() {
		if (cultural_records == null) {
			PreferenceRecord r1 = new PreferenceRecord("Concerts" , false , 1);
			PreferenceRecord r2 = new PreferenceRecord("Theathre" , true , 2);
			PreferenceRecord r3 = new PreferenceRecord("Exhibitions", false , 3);
			PreferenceRecord r4 = new PreferenceRecord("Formal dinners" , true , 4);
			PreferenceRecord r5 = new PreferenceRecord("Cinema" , true , 5);
			
			cultural_records = new PreferenceRecord[5];
			cultural_records[0] = r1;
			cultural_records[1] = r2;
			cultural_records[2] = r3;
			cultural_records[3] = r4;
			cultural_records[4] = r5;
		}
		
		return cultural_records;
	}
	
	public static PreferenceRecord[] getPoliticalPreferences() {
		if (political_records == null) {
			PreferenceRecord r1 = new PreferenceRecord("Conferences" , false , 1);
			PreferenceRecord r2 = new PreferenceRecord("Talks" , true , 2);
			PreferenceRecord r3 = new PreferenceRecord("Meetings", false , 3);
			
			political_records = new PreferenceRecord[3];
			political_records[0] = r1;
			political_records[1] = r2;
			political_records[2] = r3;
		}
		
		return political_records;
	}
	
	public static PreferenceRecord[] getOtherPreferences() {
		if (other_records == null) {
			PreferenceRecord r1 = new PreferenceRecord("IT" , false , 1);
			PreferenceRecord r2 = new PreferenceRecord("Business" , true , 2);
			PreferenceRecord r3 = new PreferenceRecord("Corporate", false , 3);
			PreferenceRecord r4 = new PreferenceRecord("Other Organized Evenets" , true , 4);
			
			other_records = new PreferenceRecord[4];
			other_records[0] = r1;
			other_records[1] = r2;
			other_records[2] = r3;
			other_records[3] = r4;
		}
		
		return other_records;
	}
	
	public static PreferenceRecord[] getTimePreferences() {
		if (time_records == null) {
			PreferenceRecord r1 = new PreferenceRecord("Morning" , false , 1);
			PreferenceRecord r2 = new PreferenceRecord("Afternoon" , true , 2);
			PreferenceRecord r3 = new PreferenceRecord("Evening", false , 3);
		
			time_records = new PreferenceRecord[4];
			time_records[0] = r1;
			time_records[1] = r2;
			time_records[2] = r3;
		}
		
		return time_records;
	}
	
	public static PreferenceRecord[] getLocationPreferences() {
		if (location_records == null) {
			PreferenceRecord r1 = new PreferenceRecord("Poland" , false , 1);
			PreferenceRecord r2 = new PreferenceRecord("UK" , true , 2);
			PreferenceRecord r3 = new PreferenceRecord("USA", false , 3);
			PreferenceRecord r4 = new PreferenceRecord("Other", false , 4);
		
			location_records = new PreferenceRecord[4];
			location_records[0] = r1;
			location_records[1] = r2;
			location_records[2] = r3;
			location_records[3] = r4;
		}
		
		return location_records;
	}
	
	public static UserRecord getValidUser() {
		if (valid_user == null) {
			valid_user = new UserRecord("Krzysztof" , "Huszcza" , "krzys" , "krzys@krzys.com" , "krzys");
		}
		return valid_user;
	}
	
	
	/* Record classes does not have any state themselfs; they user setters and getters
	 *  of their ancestors to manage the state. */
	static class OrganizationRecord extends ListGridRecord {
		
		public OrganizationRecord (String name, boolean follow, int index) {
			setName(name);
			setFollow(follow);
			setIndex(index);
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
		public int getIndex() {
			return getAttributeAsInt("index");
		}
		public void setIndex(int index) {
			setAttribute("index" , index);
		}
	}
	
	static class EventRecord extends ListGridRecord {
		
		public EventRecord(String name, String organized_by, Date date, int index) {
			setName(name);
			setOrganizedBy(organized_by);
			setDate(date);
			setIndex(index);
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
		public int getIndex() {
			return getAttributeAsInt("index");
		}
		public void setIndex(int index) {
			setAttribute("index" , index);
		}
	}
	
	static class PreferenceRecord extends ListGridRecord {
		
		public PreferenceRecord(String name , boolean selected , int index) {
			setName(name);
			setSelected(selected);
			setIndex(index);
		}
		
		public String getName() {
			return getAttributeAsString("name"); 
		}
		public void setName(String name) {
			setAttribute("name" , name);
		}
		public boolean getSelected() {
			return getAttributeAsBoolean("selected");
		}
		public void setSelected(boolean selected) {
			setAttribute("selected" , selected);
		}
		public int getIndex() {
			return getAttributeAsInt("index");
		}
		public void setIndex(int index) {
			setAttribute("index" , index);
		}
	}
	
	static class UserRecord {
		
		private String first_name;
		private String last_name;
		private String login;
		private String email_address;
		private String password;
		
		public UserRecord(String first_name, String last_name, String login,
				String email_address, String password) {
			this.first_name = first_name;
			this.last_name = last_name;
			this.login = login;
			this.email_address = email_address;
			this.password = password;
		}
		
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String firstName) {
			first_name = firstName;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String lastName) {
			last_name = lastName;
		}
		public String getEmail_address() {
			return email_address;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public void setEmail_address(String emailAddress) {
			email_address = emailAddress;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
}
