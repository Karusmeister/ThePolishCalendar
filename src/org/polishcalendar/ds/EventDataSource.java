package org.polishcalendar.ds;

import org.polishcalendar.client.services.EventService;
import org.polishcalendar.client.services.EventServiceAsync;
import org.polishcalendar.shared.EventDTO;

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
		
		DataSourceIntegerField index_f = new DataSourceIntegerField("id");
		index_f.setPrimaryKey(true);
		index_f.setHidden(true);
		
		DataSourceTextField name_f =  new DataSourceTextField("name");
		name_f.setRequired(true);
		
		DataSourceTextField location_f = new DataSourceTextField("location");
		location_f.setRequired(true);
		
		DataSourceIntegerField attendeesNumber_f = new DataSourceIntegerField("attendeesNumber");
		attendeesNumber_f.setRequired(true);
		
		DataSourceDateField startDate_f = new DataSourceDateField("startDate");
		startDate_f.setRequired(true);
		
		DataSourceTextField organizationName_f = new DataSourceTextField("organizationName");
		organizationName_f.setRequired(true);
		
		// TODO
		// Should we also add support for associations in DTOs?
		
		setFields(index_f , name_f , location_f , attendeesNumber_f , 
				startDate_f , organizationName_f);
	}

	@Override
	protected void executeAdd(final String requestId, final DSRequest request,
			final DSResponse response) {
		
		// retriving record
		JavaScriptObject js_data = request.getData();
		ListGridRecord event_record = new ListGridRecord(js_data);
		EventDTO event = new EventDTO();
		copyValues(event_record, event);
		
		// creating service
		EventServiceAsync service = GWT.create (EventService.class);
		service.addEvent(event, new AsyncCallback<EventDTO>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(EventDTO result) {
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
	protected void executeFetch(final String requestId, final DSRequest request,
			final DSResponse response) {
		
		// retriving record
		JavaScriptObject js_data = request.getData();
		ListGridRecord event_record = new ListGridRecord(js_data);
		EventDTO event = new EventDTO();
		copyValues(event_record, event);
		
		// creating service
		EventServiceAsync service = GWT.create (EventService.class);
		service.fetchEvent(event, new AsyncCallback<EventDTO>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(EventDTO result) {
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
	protected void executeRemove(final String requestId, final DSRequest request,
			final DSResponse response) {
		// retriving record
		JavaScriptObject js_data = request.getData();
		ListGridRecord event_record = new ListGridRecord(js_data);
		EventDTO event = new EventDTO();
		copyValues(event_record, event);
		
		// creating service
		EventServiceAsync service = GWT.create (EventService.class);
		service.deleteEvent(event, new AsyncCallback<EventDTO>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(EventDTO result) {
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
	protected void executeUpdate(final String requestId, final DSRequest request,
			final DSResponse response) {
		// retriving record
		JavaScriptObject js_data = request.getData();
		ListGridRecord event_record = new ListGridRecord(js_data);
		EventDTO event = new EventDTO();
		copyValues(event_record, event);
		
		// creating service
		EventServiceAsync service = GWT.create (EventService.class);
		service.updateEvent(event, new AsyncCallback<EventDTO>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(EventDTO result) {
                ListGridRecord[] list = new ListGridRecord[1];
                ListGridRecord newRec = new ListGridRecord ();
                copyValues (result, newRec);
                list[0] = newRec;
                response.setData (list);
                processResponse (requestId, response);
			}		
		});
	}
	
	/* Copy values between ListGridRecord for view and EventDTO. */
	private static void copyValues(ListGridRecord from, EventDTO to) {
		to.setName(from.getAttributeAsString("name"));
		to.setId(from.getAttributeAsInt("id"));
		to.setLocation(from.getAttributeAsString("location"));
		to.setAttendeesNumber(from.getAttributeAsInt("attendeesNumber"));
		to.setStartDate(from.getAttributeAsDate("startDate"));
		to.setOrganizationName(from.getAttributeAsString("organizationName"));
	}
	
	private static void copyValues(EventDTO from, ListGridRecord to) {
		to.setAttribute("id", from.getId());
		to.setAttribute("name", from.getName());
		to.setAttribute("location", from.getLocation());
		to.setAttribute("attendeesNumber", from.getAttendeesNumber());
		to.setAttribute("startDate", from.getStartDate());
		to.setAttribute("organizationName", from.getOrganizationName());
	}
}
