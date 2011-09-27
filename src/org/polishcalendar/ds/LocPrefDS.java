package org.polishcalendar.ds;

import java.util.ArrayList;

import org.polishcalendar.client.MockData;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class LocPrefDS extends DataSource {

	private static LocPrefDS instance = null;
	
	private LocPrefDS(String id) {
		setID(id);
		DataSourceIntegerField index_field = new DataSourceIntegerField("id");
		index_field.setPrimaryKey(true);
		index_field.setHidden(true);
		DataSourceTextField search_field = 
			new DataSourceTextField("search_loc");
		setFields(index_field,search_field);
		setClientOnly(true);
	}

	public static LocPrefDS getInstance() {
		if (instance == null) {
			instance = new LocPrefDS("locPrefDS");
			initialize_data();
		}
		return instance;
	}
	
	private static void initialize_data() {
		ArrayList<Record> records = new ArrayList<Record>();
		int id = 1;
		for (String s : MockData.getLocationsValuesMap()) {
			Record record = new ListGridRecord();
			record.setAttribute("id", id);
			record.setAttribute("search_loc", s);
			records.add(record);
			id ++;
		}
		for (Record record : records) {
			instance.addData(record);
		}
	}
}
