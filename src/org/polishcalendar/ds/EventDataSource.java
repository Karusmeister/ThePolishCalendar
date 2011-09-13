package org.polishcalendar.ds;

import org.polishcalendar.client.services.EventService;
import org.polishcalendar.client.services.EventServiceAsync;
import org.polishcalendar.shared.Event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EventDataSource extends GwtRpcDataSource {

	private static EventDataSource event_ds = null;
	
	public static DataSource getEventDS() {
		if (event_ds == null) {
			event_ds = new EventDataSource("eventDS");
		}
		return event_ds;
	}
	
	private EventDataSource (String id) {
		setID(id);
		
		DataSourceIntegerField index_field = new DataSourceIntegerField("index");
		index_field.setPrimaryKey(true);
		index_field.setHidden(true);
		
		DataSourceTextField name_field =  new DataSourceTextField("name");
		name_field.setRequired(true);
		
		DataSourceTextField organized_by_field = new DataSourceTextField("organized_by");
		organized_by_field.setRequired(true);
		
		DataSourceDateField date_field = new DataSourceDateField("date");
		date_field.setRequired(true);
		
		setFields(name_field,organized_by_field,date_field,index_field);
	}

	@Override
	protected void executeAdd(final String requestId, final DSRequest request,
			final DSResponse response) {
		
		// retriving record
		JavaScriptObject js_data = request.getData();
		ListGridRecord event_record = new ListGridRecord(js_data);
		Event event = new Event();
		copyValues(event_record, event);
		
		// creating service
		EventServiceAsync service = GWT.create (EventService.class);
		service.addEvent(event, new AsyncCallback<Event>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(Event result) {
                ListGridRecord[] list = new ListGridRecord[1];
                ListGridRecord newRec = new ListGridRecord ();
                copyValues (result, newRec);
                list[0] = newRec;
                response.setData (list);
                processResponse (requestId, response);
			}		
		});
	}

	@Override
	protected void executeFetch(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void executeRemove(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void executeUpdate(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	private static void copyValues(ListGridRecord from, Event to) {
		to.setName(from.getAttributeAsString("name"));
		to.setOrganizedBy(from.getAttributeAsString("organized_by"));
		to.setDate(from.getAttributeAsDate("date"));
		to.setIndex(from.getAttributeAsInt("index"));
	}
	
	private static void copyValues(Event from, ListGridRecord to) {
		to.setAttribute("name" , from.getName());
		to.setAttribute("organized_by" , from.getOrganizedBy());
		to.setAttribute("date" , from.getDate());
		to.setAttribute("index", from.getIndex());
	}
}
